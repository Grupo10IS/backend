package py.com.progweb.parcial1.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

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
            @QueryParam("puntosMinimos") Integer minPuntos,
            @QueryParam("puntosMaximos") Integer maxPuntos,
            @QueryParam("vencimiento") Integer vencimiento) {

        List<BolsaPuntos> bolsas = this.bolsaPuntosDAO.listarBolsasPuntos(idCliente, minPuntos, maxPuntos,
                vencimiento);

        return Response.ok().entity(bolsas).build();
    }

    private static class ReqCarga {
        @JsonProperty("idCliente")
        Integer idCliente;
        @JsonProperty("montoOperacion")
        BigDecimal montoOperacion;
    }

    @POST
    @Path("/")
    public Response cargarPuntos(ReqCarga req) {
        if (req == null || req.idCliente == null || req.montoOperacion == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Faltan campos en la request").build();
        }

        this.bolsaPuntosDAO.cargarPuntos(req.idCliente, req.montoOperacion);

        return Response.ok().build();
    }

    private static class ReqUso {
        @JsonProperty("idCliente")
        Integer idCliente;
        @JsonProperty("idConcepto")
        Integer idConcepto;
    }

    @PUT
    @Path("/")
    public Response usarPuntos(ReqUso req) {
        try {
            if (req.idCliente == null || req.idConcepto == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Faltan campos en la request").build();
            }

            this.bolsaPuntosDAO.usarPuntos(req.idCliente, req.idConcepto);

            return Response.ok().build();
        } catch (Exception arei) {
            return Response.status(Response.Status.BAD_REQUEST).entity(arei.getMessage()).build();
        }
    }

}
