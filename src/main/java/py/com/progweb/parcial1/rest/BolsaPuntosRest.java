package py.com.progweb.parcial1.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;

import py.com.progweb.parcial1.ejb.BolsaPuntosDAO;
import py.com.progweb.parcial1.model.bolsa.BolsaPuntos;

@Stateless
@Path("/bolsaPuntos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BolsaPuntosRest {
    @Inject
    private BolsaPuntosDAO bolsaPuntosDAO;

    @GET
    public Response listarBolsasPuntos(
            @QueryParam("cliente") Integer idCliente,
            @QueryParam("puntosasignados") Integer puntosasignados,
            @QueryParam("vencimiento") Integer vencimiento) {
        List<BolsaPuntos> bolsasPuntos = this.bolsaPuntosDAO.listarBolsasPuntos(idCliente, puntosasignados, vencimiento);
        return Response.ok().entity(bolsasPuntos).build();
    }


    @POST
    @Path("/")
    public Response cargarPuntos(@QueryParam("idCliente") Integer idCliente,
                                 @QueryParam("montoOperacion") BigDecimal montoOperacion) {
        try {
            bolsaPuntosDAO.cargarPuntos(idCliente, montoOperacion);
            return Response.ok().build();
        } catch (Exception arei) {
            return Response.status(Response.Status.BAD_REQUEST).entity(arei.getMessage()).build();
        }
    }


    @POST
    @Path("/")
    public Response agregarBolsaPuntos(BolsaPuntos bolsaPuntos) {
        bolsaPuntosDAO.agregarBolsaPuntos(bolsaPuntos);
        return Response.status(Response.Status.CREATED).build();
    }
    

    @PUT
    @Path("/")
    public Response utilizarPuntos(@QueryParam("idCliente") Integer idCliente,
                                @QueryParam("idConcepto") Integer idConcepto) {
    
        BolsaPuntos bolsa = bolsaPuntosDAO.buscarPorId(idCliente);
        if (bolsa == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        bolsaPuntosDAO.actualizarBolsaPuntos(bolsa);
    
        return Response.ok().build();
    }
    
}
