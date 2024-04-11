package py.com.progweb.parcial1.ejb;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import py.com.progweb.parcial1.model.Cliente;
import py.com.progweb.parcial1.model.ReglasAsignacion;
import py.com.progweb.parcial1.model.VencimientosPuntos;
import py.com.progweb.parcial1.model.bolsa.BolsaPuntos;
import py.com.progweb.parcial1.utils.QueryBuilder;

@Stateless
public class BolsaPuntosDAO {

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarBolsaPuntos(BolsaPuntos bolsaPuntos) {
        this.em.persist(bolsaPuntos);
    }

    public List<BolsaPuntos> listarBolsasPuntos(Integer idCliente, Integer puntosasignados, Integer vencimiento) {

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM bolsa_puntos WHERE 1=1");
        if (idCliente != null) {
            queryBuilder.append(" AND cliente_id = :idCliente");
        }

        if (puntosasignados != null) {
            queryBuilder.append(" AND puntaje_asignado = :puntosasignados");
        }

        if (vencimiento != null) {
            queryBuilder.append(" AND (fecha_caducidad - fecha_asignacion) = :vencimiento");
        }

        Query q = em.createNativeQuery(queryBuilder.toString(), BolsaPuntos.class);
        if (idCliente != null) {
            q.setParameter("idCliente", idCliente);
        }

        if (puntosasignados != null) {
            q.setParameter("puntosasignados", puntosasignados);
        }

        if (vencimiento != null) {
            q.setParameter("vencimiento", vencimiento);
        }

        return q.getResultList();
    }

    public BolsaPuntos buscarPorId(Integer id) {
        return this.em.find(BolsaPuntos.class, id);
    }

    public void actualizarBolsaPuntos(BolsaPuntos bolsaPuntos) {
        this.em.merge(bolsaPuntos);
    }

    /**
     * anadir nuevos puntos a un cliente
     *
     * @param idCliente
     * @param montoOperacion
     */
    public void cargarPuntos(Integer idCliente, BigDecimal montoOperacion) {
        Cliente cliente = em.find(Cliente.class, idCliente);

        if (cliente == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("El usuario no existe")
                            .build());
        }

        // traer el monto de equivalencia en puntos
        List<ReglasAsignacion> request = (List<ReglasAsignacion>) em.createQuery(
                "SELECT r FROM ReglasAsignacion r").getResultList();

        if (request == null || request.isEmpty()) {
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("No existen reglas de asignacion de puntos activas")
                            .build());
        }

        ReglasAsignacion reglaAsig = request.get(0);
        BigDecimal equivalencia = new BigDecimal(reglaAsig.getMontoEquivalencia());
        int puntosAsignados = montoOperacion.divide(equivalencia).intValue();

        // asignar una fecha de caducidad
        QueryBuilder qb = new QueryBuilder("select v from VencimientosPuntos v")
                .addCondition("v.fechaFinValidez >= :fecha", "fecha", LocalDate.now())
                .addText("order by v.id asc");

        List<VencimientosPuntos> reglasVenc = (List<VencimientosPuntos>) qb.build(this.em).getResultList();

        if (reglasVenc == null || reglasVenc.isEmpty()) {
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("No existen reglas de asignacion fechas de vencimiento")
                            .build());
        }

        Integer caducidad = reglasVenc.get(0).getDiasDuracionPuntaje();

        // realizar la insercion
        em.getTransaction().begin();

        em.persist(new BolsaPuntos(
                cliente,
                LocalDate.now().plusDays(caducidad),
                puntosAsignados,
                montoOperacion));

        em.getTransaction().commit();
    }
}
