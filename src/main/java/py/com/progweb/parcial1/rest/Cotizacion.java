package py.com.progweb.parcial1.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import py.com.progweb.parcial1.ejb.ReglasAsignacionDAO;
import py.com.progweb.parcial1.model.ReglasAsignacion;

@Path("cotizacion")
@Produces("application/json")
public class Cotizacion {
    @Inject
    private ReglasAsignacionDAO rgDAO;

    // utilizado para generar la respuesta json
    private class response {
        @SuppressWarnings("unused")
        public Integer cotizacion;

        public response(Integer monto) {
            this.cotizacion = monto;
        }
    }

    @GET
    @Path("/")
    public response cotizarMonto(@QueryParam("monto") Integer monto) {
        if (monto == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("Query parameter 'monto' is mandatory")
                            .build());
        }

        ReglasAsignacion regla = this.rgDAO.getReglaEquivalencia();
        if (regla == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("No existe reglas de asignacion de puntos registradas")
                            .build());
        }

        return new response(monto / regla.getMontoEquivalencia());
    }
}
