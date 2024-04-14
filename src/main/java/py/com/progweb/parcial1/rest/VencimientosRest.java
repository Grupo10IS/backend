package py.com.progweb.parcial1.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.com.progweb.parcial1.ejb.VencimientosDAO;
import py.com.progweb.parcial1.model.VencimientosPuntos;

@Stateless
@Path("/vencimientosPuntos")
@Produces(MediaType.APPLICATION_JSON)
public class VencimientosRest {
    @Inject
    private VencimientosDAO vDAO;

    @GET
    public Response listarReglas() {
        List<VencimientosPuntos> res = this.vDAO.listarReglasVencimiento();
        return Response.ok().entity(res).build();
    }

    @POST
    public Response agregarRegla(VencimientosPuntos regla) {
        this.vDAO.agregarVencimiento(regla);
        return Response.ok().build();
    }
}
