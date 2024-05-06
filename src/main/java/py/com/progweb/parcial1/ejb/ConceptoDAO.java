/* Los ejb son la representacion de nuestra logica de negocios
independiente a la arquitectura de la BD por debajo

Transforma las entidades que recibimos del ORM y hacemos algo usando el widfly
*/
package py.com.progweb.parcial1.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import py.com.progweb.parcial1.model.ConceptoUsos;

@Stateless
public class ConceptoDAO {

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarConcepto(ConceptoUsos entidad) {
        if (!(entidad.getIdConcepto() > 0)) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("Monto de operacion no puede ser negativo o 0")
                            .build());
        }

        this.em.persist(entidad);
    }

    public List<ConceptoUsos> listarConceptos() {
        Query q = this.em.createQuery("select c from ConceptoUsos c");
        return (List<ConceptoUsos>) q.getResultList();
    }
}
