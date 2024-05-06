package py.com.progweb.parcial1.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.com.progweb.parcial1.ejb.ConceptoDAO;
import py.com.progweb.parcial1.model.ConceptoUsos;

@Stateless
@Path("/conceptos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConceptosRest {
    @Inject
    private ConceptoDAO conceptosDAO;

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    @POST
    public void agregarConcepto(ConceptoUsos data) {
        this.conceptosDAO.agregarConcepto(data);
    }

    @GET
    public Response listarConceptos() {
        List<ConceptoUsos> conceptos = this.conceptosDAO.listarConceptos();
        return Response.ok().entity(conceptos).build();
    }
}
