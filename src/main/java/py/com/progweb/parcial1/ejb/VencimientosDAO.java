package py.com.progweb.parcial1.ejb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.parcial1.model.VencimientosPuntos;

public class VencimientosDAO {
    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarVencimiento(VencimientosPuntos entidad) {
        this.em.persist(entidad);
    }

    public List<VencimientosPuntos> listarReglasVencimiento() {
        // determinar la fecha actual en formato yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = LocalDate.now().format(formatter);

        String query = String.format(
                "select v from VencimientosPuntos v " +
                        "where v.fechaInicioValidez >= %s",
                fechaActual);

        Query q = this.em.createQuery(query);
        return (List<VencimientosPuntos>) q.getResultList();
    }
}
