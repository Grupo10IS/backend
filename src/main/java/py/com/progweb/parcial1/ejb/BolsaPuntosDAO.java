package py.com.progweb.parcial1.ejb;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

import py.com.progweb.parcial1.model.Cliente;
import py.com.progweb.parcial1.model.ConceptoUsos;
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

    /**
     * Listar las bolsas activas y con puntos del cliente
     * Lorem ipsum dolor sit amet, officia excepteur ex fugiat reprehenderit enim
     * labore culpa sint ad nisi Lorem pariatur mollit ex esse exercitation amet.
     * Nisi anim cupidatat excepteur officia. Reprehenderit nostrud nostrud ipsum
     * Lorem est aliquip amet voluptate voluptate dolor minim nulla est proident.
     * Nostrud officia pariatur ut officia. Sit irure elit esse ea nulla sunt ex
     * occaecat reprehenderit commodo officia dolor Lorem duis laboris cupidatat
     * officia voluptate. Culpa proident adipisicing id nulla nisi laboris ex in
     * Lorem sunt duis officia eiusmod. Aliqua reprehenderit commodo ex non
     * excepteur duis sunt velit enim. Voluptate laboris sint cupidatat ullamco ut
     * ea consectetur et est culpa et culpa duis.
     * 
     * @param idCliente
     * @param puntosMin,
     * @param puntosMax,
     * @param vencimiento
     * @return
     */
    public List<BolsaPuntos> listarBolsasPuntos(
            Integer idCliente,
            Integer puntosMin,
            Integer puntosMax,
            Integer vencimiento) {

        QueryBuilder qb = new QueryBuilder("SELECT b FROM BolsaPuntos b")
                .addCondition("b.idCliente = :idCliente", "idCliente", idCliente)
                .addCondition("b.puntosAsignados <= :puntosMax", "puntosMax", puntosMax)
                .addCondition("b.puntosAsignados >= :puntosMin", "puntosMin", puntosMin)
                .addCondition("b.fechaCaducidad >= :fecha", "fecha", LocalDate.now());

        if (vencimiento != null) {
            qb.addCondition("b.fechaCaducidad - :vencimiento <= 0", "vencimiento",
                    LocalDate.now().plusDays(vencimiento));
        }

        qb.addText("and b.saldoPuntos > 0");

        return (List<BolsaPuntos>) qb.build(this.em).getResultList();
    }

    public BolsaPuntos buscarPorId(Integer id) {
        return this.em.find(BolsaPuntos.class, id);
    }

    public void actualizarBolsaPuntos(BolsaPuntos bolsaPuntos) {
        this.em.merge(bolsaPuntos);
    }

    /**
     * anadir nuevos puntos a un cliente
     * Lorem ipsum dolor sit amet, officia excepteur ex fugiat reprehenderit enim
     * labore culpa sint ad nisi Lorem pariatur mollit ex esse exercitation amet.
     * Nisi anim cupidatat excepteur officia. Reprehenderit nostrud nostrud ipsum
     * Lorem est aliquip amet voluptate voluptate dolor minim nulla est proident.
     * Nostrud officia pariatur ut officia. Sit irure elit esse ea nulla sunt ex
     * occaecat reprehenderit commodo officia dolor Lorem duis laboris cupidatat
     * officia voluptate. Culpa proident adipisicing id nulla nisi laboris ex in
     * Lorem sunt duis officia eiusmod. Aliqua reprehenderit commodo ex non
     * excepteur duis sunt velit enim. Voluptate laboris sint cupidatat ullamco ut
     * ea consectetur et est culpa et culpa duis.
     *
     * @param idCliente
     * @param montoOperacion
     */
    public Response cargarPuntos(Integer idCliente, BigDecimal montoOperacion) {
        Cliente cliente = em.find(Cliente.class, idCliente);

        if (cliente == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El usuario no existe")
                    .build();
        }

        /*
         * traer la regla de asignacion de puntos activa y asignar el monto de
         * equivalencia por cada punto
         */
        List<ReglasAsignacion> request = (List<ReglasAsignacion>) em.createQuery(
                "SELECT r FROM ReglasAsignacion r").getResultList();

        if (request == null || request.isEmpty()) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("No existen reglas de asignacion de puntos activas")
                    .build();
        }

        ReglasAsignacion reglaAsig = request.get(0);
        BigDecimal equivalencia = new BigDecimal(reglaAsig.getMontoEquivalencia());
        int puntosAsignados = montoOperacion.divide(equivalencia).intValue();

        /*
         * asignar una fecha de caducidad acorde a las reglas de caducidad activas
         * actualmente dentro de la aplicacion
         */
        QueryBuilder qb = new QueryBuilder("select v from VencimientosPuntos v")
                .addCondition("v.fechaFinValidez >= :fecha", "fecha", LocalDate.now())
                .addText("order by v.id asc");

        List<VencimientosPuntos> reglasVenc = (List<VencimientosPuntos>) qb.build(this.em).getResultList();

        if (reglasVenc == null || reglasVenc.isEmpty()) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("No existen reglas de asignacion fechas de vencimiento")
                    .build();
        }

        Integer caducidad = reglasVenc.get(0).getDiasDuracionPuntaje();

        /* realizar la insercion de los datos dentro de la base de datos */
        em.getTransaction().begin();

        em.persist(new BolsaPuntos(
                cliente,
                LocalDate.now().plusDays(caducidad),
                puntosAsignados,
                montoOperacion));

        em.getTransaction().commit();

        return null;
    }

    /**
     * utilizar una cantidad de puntos del cliente
     * Lorem ipsum dolor sit amet, officia excepteur ex fugiat reprehenderit enim
     * labore culpa sint ad nisi Lorem pariatur mollit ex esse exercitation amet.
     * Nisi anim cupidatat excepteur officia. Reprehenderit nostrud nostrud ipsum
     * Lorem est aliquip amet voluptate voluptate dolor minim nulla est proident.
     * Nostrud officia pariatur ut officia. Sit irure elit esse ea nulla sunt ex
     * occaecat reprehenderit commodo officia dolor Lorem duis laboris cupidatat
     * officia voluptate. Culpa proident adipisicing id nulla nisi laboris ex in
     * Lorem sunt duis officia eiusmod. Aliqua reprehenderit commodo ex non
     * excepteur duis sunt velit enim. Voluptate laboris sint cupidatat ullamco ut
     * ea consectetur et est culpa et culpa duis.
     *
     * @param idCliente
     * @param montoOperacion
     */
    public Response usarPuntos(Integer idCliente, Integer idConcepto) {
        // traer el cliente
        Cliente cliente = em.find(Cliente.class, idCliente);
        if (cliente == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El usuario no existe")
                    .build();
        }

        // traer el concepto de uso
        ConceptoUsos concepto = em.find(ConceptoUsos.class, idConcepto);
        if (concepto == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El concepto no existe no existe")
                    .build();
        }

        // Listar las bolsas activas del cliente
        QueryBuilder qb = new QueryBuilder("select b from BolsaPuntos b")
                .addCondition("b.fechaCaducidad >= :fecha", "fecha", LocalDate.now())
                .addText("b.saldoPuntos > 0")
                .addText("order by b.id asc");

        List<BolsaPuntos> bolsas = (List<BolsaPuntos>) qb.build(this.em).getResultList();

        if (bolsas == null || bolsas.isEmpty()) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("El cliente no cuenta con los puntos necesarios")
                    .build();
        }

        // hacer el recuento de puntos del cliente
        Integer saldo = 0;
        for (int i = 0; i < bolsas.size(); i++) {
            saldo += bolsas.get(i).getSaldoPuntos();
        }

        Integer puntosRequeridos = concepto.getPuntosRequeridos();
        if (saldo < puntosRequeridos) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("El cliente no cuenta con los puntos necesarios")
                    .build();
        }

        // realizar la operacion
        em.getTransaction().begin();

        Integer i = 0;
        while (puntosRequeridos > 0) {
            BolsaPuntos bolsa = bolsas.get(i);

            if (bolsa.getSaldoPuntos() < puntosRequeridos) {
                bolsa.setPuntajeUtilizado(bolsa.getPuntajeAsignado());
                puntosRequeridos -= bolsa.getSaldoPuntos();
                bolsa.setSaldoPuntos(0);

            } else {
                bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado() + puntosRequeridos);
                bolsa.setSaldoPuntos(bolsa.getPuntajeAsignado() - bolsa.getPuntajeUtilizado());
                puntosRequeridos = 0;
            }

            em.persist(bolsa);
            i++;
        }

        em.getTransaction().commit();

        return null;
    }
}
