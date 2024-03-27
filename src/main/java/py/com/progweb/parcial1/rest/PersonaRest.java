package py.com.progweb.parcial1.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import py.com.progweb.parcial1.ejb.PersonaDAO;
import py.com.progweb.parcial1.model.Persona;

@Path("persona")
@Consumes("application/json")
@Produces("application/json")
public class PersonaRest {
    @Inject
    private PersonaDAO personaDAO;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(this.personaDAO.listaPersonas()).build();
    }

    @GET
    @Path("/hola")
    public Response hola() {
        return Response.ok("Hola").build();
    }

    @POST
    @Path("/")
    public Response agregar(Persona p) {
        this.personaDAO.agregarPersona(p);
        return Response.ok().build();
    }
}
