/* Los ejb son la representacion de nuestra logica de negocios
independiente a la arquitectura de la BD por debajo

Transforma las entidades que recibimos del ORM y hacemos algo usando el widfly
*/
package py.com.progweb.parcial1.ejb;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import py.com.progweb.parcial1.model.bolsa.DetallePuntos;
import py.com.progweb.parcial1.utils.QueryBuilder;

@Stateless
public class PuntosDAO {

    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public List<DetallePuntos> listarPuntosUsados(
            String cliente,
            String concepto,
            String fechaMinima,
            String fechaMaxima) throws DateTimeParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        QueryBuilder qb = new QueryBuilder("select d from DetallePuntos d")
                .addCondition("d.puntosUsados.idCliente = :cliente", "cliente", cliente)
                .addCondition("d.puntosUsados.concepto = :concepto", "concepto", concepto);

        if (fechaMaxima != null) {
            qb.addCondition("d.puntosUsados.fecha <= :fechaMaxima",
                    "fechaMaxima", LocalDate.parse(fechaMaxima, formatter));
        }

        if (fechaMinima != null) {
            qb.addCondition("d.puntosUsados.fecha <= :fechaMinima",
                    "fechaMinima", LocalDate.parse(fechaMinima, formatter));
        }

        return (List<DetallePuntos>) qb.build(em).getResultList();
    }
}
