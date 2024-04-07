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

import py.com.progweb.parcial1.model.ConceptoUsos;

@Stateless
public class ConceptoDAO {

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarConcepto(ConceptoUsos entidad) {
        this.em.persist(entidad);
    }

    public List<ConceptoUsos> listarConceptos() {
        Query q = this.em.createQuery("select c from ConceptoUsos c");
        return (List<ConceptoUsos>) q.getResultList();
    }
}
