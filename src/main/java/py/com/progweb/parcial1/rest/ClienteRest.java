package py.com.progweb.parcial1.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import py.com.progweb.parcial1.ejb.ClienteDAO;
import py.com.progweb.parcial1.model.Cliente;

@Path("clientes")
@Consumes("application/json")
@Produces("application/json")
public class ClienteRest {
    @Inject
    private ClienteDAO cliente;

    @POST
    @Path("/")
    public Response agregar(Cliente p) {
        this.cliente.agregarCliente(p);
        return Response.ok().build();
    }

    @GET
    @Path("/")
    public Response listarClientes(
            @QueryParam("sort") List<String> order,
            @QueryParam("page") int page,
            @QueryParam("maxResults") int maxResults) {

        return Response.ok(this.cliente.listarClientes()).build();
    }
}
