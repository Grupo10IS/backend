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
import py.com.progweb.parcial1.utils.FilterBuilder;

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

        StringBuilder queryString = new StringBuilder("select c from Cliente c");
        FilterBuilder fb = new FilterBuilder(queryString);

        fb.addEqualsFilter("c.nacionalidad", nacionalidad)
                .addEqualsFilter("c.nacimiento", nacimiento);

        Query q = this.em.createQuery(fb.build());

        List<Cliente> clientes = (List<Cliente>) q.getResultList();

        return Response.ok().entity(clientes).build();
    }
}
