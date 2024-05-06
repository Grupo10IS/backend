package py.com.progweb.parcial1.rest;

import java.time.format.DateTimeParseException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import py.com.progweb.parcial1.ejb.PuntosDAO;
import py.com.progweb.parcial1.model.bolsa.DetallePuntos;

@Stateless
@Path("/puntosUsados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class puntosUsadosRest {
    @Inject
    private PuntosDAO puntosDAO;

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    @GET
    public Response listarConceptos(
            @QueryParam("cliente") String cliente,
            @QueryParam("concepto") String concepto,
            @QueryParam("fechaMinima") String fechaMinima,
            @QueryParam("fechaMaxima") String fechaMaxima) {

        try {
            List<DetallePuntos> conceptos = this.puntosDAO.listarPuntosUsados(
                    cliente, concepto, fechaMinima, fechaMaxima);

            return Response.ok().entity(conceptos).build();

        } catch (DateTimeParseException e) {
            return Response.status(Status.BAD_REQUEST).entity("Bad date format").build();
        }
    }
}
