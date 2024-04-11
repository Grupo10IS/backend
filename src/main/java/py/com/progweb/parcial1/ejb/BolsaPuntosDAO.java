package py.com.progweb.parcial1.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import py.com.progweb.parcial1.model.bolsa.BolsaPuntos;

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

    
    public void cargarPuntos(Integer idCliente, BigDecimal montoOperacion) {
        BolsaPuntos bolsaPuntos = em.find(BolsaPuntos.class, idCliente);
        if (bolsaPuntos == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("NULL")
                            .build());        
        }
        BigDecimal montoReglaAsignacon = getMontoEquivalencia();
        int puntosAsignados = montoOperacion.divide(montoReglaAsignacon).intValue();
        bolsaPuntos.setPuntajeAsignado(bolsaPuntos.getPuntajeAsignado() + puntosAsignados);
        bolsaPuntos.setMontoOperacion(montoOperacion);
        em.merge(bolsaPuntos);
    }
    public BigDecimal getMontoEquivalencia(){
        Query query = em.createQuery("SELECT monto_equivalencia FROM reglas_asignacion_puntos", BigDecimal.class);
        List<BigDecimal> result = query.getResultList();
        if (result.isEmpty()) {
            return BigDecimal.valueOf(1); //cargar 1 si que está vacio la consulta 
        }
        return result.get(0);
    }


}
