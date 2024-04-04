/* Los ejb son la representacion de nuestra logica de negocios
independiente a la arquitectura de la BD por debajo

Transforma las entidades que recibimos del ORM y hacemos algo usando el widfly
*/
package py.com.progweb.parcial1.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import py.com.progweb.parcial1.model.Cliente;

@Stateless
public class ClienteDAO {

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarCliente(Cliente entidad) {
        this.em.persist(entidad);
    }

    public List<Cliente> listarClientes(String nacionalidad) {
        StringBuilder queryString = new StringBuilder("select c from Cliente c where 1=1");
        
        if (nacionalidad != null && !nacionalidad.isEmpty()) {
            queryString.append(" and c.nacionalidad = :nacionalidad");
        }
        Query q = this.em.createQuery(queryString.toString());

        if (nacionalidad != null && !nacionalidad.isEmpty()) {
            q.setParameter("nacionalidad", nacionalidad);
        }
        return (List<Cliente>) q.getResultList();
    }
}
