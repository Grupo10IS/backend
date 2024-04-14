package py.com.progweb.parcial1.ejb;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import py.com.progweb.parcial1.model.VencimientosPuntos;
import py.com.progweb.parcial1.utils.QueryBuilder;

public class VencimientosDAO {
    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public void agregarVencimiento(VencimientosPuntos entidad) {
        this.em.persist(entidad);
    }

    public List<VencimientosPuntos> listarReglasVencimiento() {
        QueryBuilder qb = new QueryBuilder("select v from VencimientosPuntos v");
        qb.addCondition("v.fechaInicioValidez >= :fecha", "fecha", LocalDate.now());

        return (List<VencimientosPuntos>) qb.build(this.em).getResultList();
    }
}
