package py.com.progweb.parcial1.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.com.progweb.parcial1.ejb.ReglasAsignacionDAO;
import py.com.progweb.parcial1.model.ReglasAsignacion;

@Stateless
@Path("/asignaciones")
@Produces(MediaType.APPLICATION_JSON)
public class ReglasAsignacionRest {
    @Inject
    private ReglasAsignacionDAO reglasAsignacionDAO;

    @GET
    @Path("/")
    public Response getReglasEquivalencia() {
        ReglasAsignacion reglaEquivalencia = reglasAsignacionDAO.getReglaEquivalencia();
        if (reglaEquivalencia == null) {
            throw new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST)
                        .entity("NULL")
                        .build());
        }
        return Response.ok(reglaEquivalencia).build();
    }
}
