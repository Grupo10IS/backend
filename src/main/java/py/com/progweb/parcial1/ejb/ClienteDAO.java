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
    /*
     * El entity manager que se va a encargar de manejar los objetos por nosotros.
     * La unidad de persistencia le dice al entorno (wildfly) a cual bd apuntar.
     * Ver en el persistence.xml el nombre de la unidad de persistencia
     */
    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarCliente(Cliente entidad) {
        this.em.persist(entidad);
    }

    public List<Cliente> listarClientes() {
        Query q = this.em.createQuery("select c from Cliente c");
        return (List<Cliente>) q.getResultList();
    }
}
