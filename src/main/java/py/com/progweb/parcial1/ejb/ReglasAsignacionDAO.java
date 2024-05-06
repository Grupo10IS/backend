package py.com.progweb.parcial1.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.parcial1.model.ReglasAsignacion;

@Stateless
public class ReglasAsignacionDAO {
    @PersistenceContext(unitName = "parcial1PU")
    private EntityManager em;

    public ReglasAsignacionDAO() {

    }

    public ReglasAsignacion getReglaEquivalencia() {
        Query q = this.em.createQuery(
                "select r from ReglasAsignacion r order by r.idRegla asc")
                .setMaxResults(1);

        List<ReglasAsignacion> result;
        result = q.getResultList();

        if (result == null || result.size() == 0) {
            return null;
        }

        return result.get(0);
    }

    public void changeReglaEquivalencia(Integer monto) {
        Query q = this.em.createQuery(
                "update ReglasAsignacion set montoEquivalencia = :value");

        q.setParameter("value", monto);
        q.executeUpdate();
    }
}
