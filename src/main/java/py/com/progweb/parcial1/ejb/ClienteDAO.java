/* Los ejb son la representacion de nuestra logica de negocios
independiente a la arquitectura de la BD por debajo

Transforma las entidades que recibimos del ORM y hacemos algo usando el widfly
*/
package py.com.progweb.parcial1.ejb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.parcial1.model.Cliente;
import py.com.progweb.parcial1.utils.QueryBuilder;

@Stateless
public class ClienteDAO {

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarCliente(Cliente entidad) {
        this.em.persist(entidad);
    }

    public List<Cliente> listarClientes(
            String nacionalidad,
            String nacimiento) throws DateTimeParseException {

        QueryBuilder qb = new QueryBuilder("SELECT c FROM Cliente c")
                .addCondition("c.nacionalidad = :nacionalidad", "nacionalidad", nacionalidad);

        if (nacimiento != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate cumpleanos = LocalDate.parse(nacimiento, formatter);
            qb.addCondition("c.nacimiento = :nacimiento", "nacimiento", cumpleanos);
        }

        return (List<Cliente>) qb.build(this.em).getResultList();
    }

    // PERF: para que las consultas sin el filtro de "puntos a vencer" sea mas veloz
    public List<Cliente> listarClientes(
            String nacionalidad,
            String nacimiento,
            String diasVencimiento) throws DateTimeParseException {

        // formatear las fechas de la query
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaActual = LocalDate.now();
        LocalDate vencimiento = fechaActual.plusDays(new Integer(diasVencimiento));

        QueryBuilder qb = new QueryBuilder(
                "select c from Cliente c left join BolsaPuntos b on b.cliente.idCliente = c.idCliente")
                .addCondition("c.nacionalidad = :nacionalidad", "nacionalidad", nacionalidad)
                .addCondition("b.fechaCaducidad <= :vencimientoPuntos", "vencimientoPuntos", vencimiento)
                .addCondition("b.fechaAsignacion <= :fechaActual", "fechaActual", fechaActual)
                .addText("and b.fechaCaducidad >= :fechaActual");

        if (nacimiento != null) {
            qb.addCondition("c.nacimiento = :nacimiento",
                    "nacimiento", LocalDate.parse(nacimiento, formatter));
        }

        qb.addText("group by c.idCliente");

        return (List<Cliente>) qb.build(this.em).getResultList();
    }
}
