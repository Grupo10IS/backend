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

import py.com.progweb.parcial1.model.Cliente;

@Stateless
public class ClienteDAO {

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarCliente(Cliente entidad) {
        this.em.persist(entidad);
    }

    public List<Cliente> listarClientes(String nacionalidad, String nacimiento, String vencimientoPuntos) {
        if (vencimientoPuntos != null && vencimientoPuntos.length() != 0) {
            return listarClientesPorBolsa(nacionalidad, nacimiento, vencimientoPuntos);
        }

        String query = String.format(
                "select c from Cliente c " +
                        "where c.nacionalidad = %s " +
                        "and c.nacimiento = %s ",
                nacionalidad, nacimiento);

        Query q = this.em.createQuery(query);

        return (List<Cliente>) q.getResultList();
    }

    // creo una funcion especial para listar los clientes con puntos a vencer en x
    // dias para que las consultas
    // sin el filtro de puntos a vencer sea mas veloz
    private List<Cliente> listarClientesPorBolsa(String nacionalidad, String nacimiento, String vencimientoPuntos) {
        String query = String.format(
                "select b.cliente from BolsaPuntos b " +
                        "where b.cliente.nacionalidad = %s " +
                        "and b.cliente.nacimiento = %s " +
                        "and b.fechaCaducidad = %s ",
                nacionalidad, nacimiento, vencimientoPuntos);

        Query q = this.em.createQuery(query);

        return (List<Cliente>) q.getResultList();
    }
}
