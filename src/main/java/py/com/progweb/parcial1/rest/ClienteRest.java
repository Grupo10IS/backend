package py.com.progweb.parcial1.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public void agregarCliente(Cliente entidad) {
        this.em.persist(entidad);
    }

    @GET
    public Response listarClientes(
            @QueryParam("nacionalidad") String nacionalidad,
            @QueryParam("nacimiento") String nacimiento) {

        List<Cliente> clientes = this.clienteDAO.listarClientes(
                nacionalidad, nacimiento);

        return Response.ok().entity(clientes).build();
    }
}
