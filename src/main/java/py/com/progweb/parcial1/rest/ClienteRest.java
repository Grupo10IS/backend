package py.com.progweb.parcial1.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import py.com.progweb.parcial1.ejb.ClienteDAO;
import py.com.progweb.parcial1.model.Cliente;

@Stateless
@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteRest {
    @Inject
    private ClienteDAO clienteDAO;

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    @POST
    public Response agregarCliente(Cliente data) {
        this.clienteDAO.agregarCliente(data);
        return Response.ok().build();
    }

    @GET
    public Response listarClientes(
            @QueryParam("nacionalidad") String nacionalidad,
            @QueryParam("nacimiento") String nacimiento,
            @QueryParam("vencimientoPuntos") String vencimientos) {

        List<Cliente> clientes = this.clienteDAO.listarClientes(
                nacionalidad, nacimiento, vencimientos);

        return Response.ok().entity(clientes).build();
    }
}
