package py.com.progweb.parcial1.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

import py.com.progweb.parcial1.model.bolsa.BolsaPuntos;

@Stateless
public class BolsaPuntosDAO {

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarBolsaPuntos(BolsaPuntos bolsaPuntos) {
        this.em.persist(bolsaPuntos);
    }

    public List<BolsaPuntos> listarBolsasPuntos(Integer idCliente, Integer puntosasignados, Integer vencimiento) {
        
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM bolsa_puntos WHERE 1=1");
        if (idCliente != null) {
            queryBuilder.append(" AND cliente_id = :idCliente");
        }
    
        if (puntosasignados != null) {
            queryBuilder.append(" AND puntaje_asignado = :puntosasignados");
        }
    
        if (vencimiento != null) {
            queryBuilder.append(" AND (fecha_caducidad - fecha_asignacion) = :vencimiento");
        }
    
        Query q = em.createNativeQuery(queryBuilder.toString(), BolsaPuntos.class);
        if (idCliente != null) {
            q.setParameter("idCliente", idCliente);
        }
    
        if (puntosasignados != null) {
            q.setParameter("puntosasignados", puntosasignados);
        }
    
        if (vencimiento != null) {
            q.setParameter("vencimiento", vencimiento);
        }
        return q.getResultList();
    }
    
    
    
    public BolsaPuntos buscarPorId(Integer id) {
        return this.em.find(BolsaPuntos.class, id);
    }

    public void actualizarBolsaPuntos(BolsaPuntos bolsaPuntos) {
        this.em.merge(bolsaPuntos);
    }


}
