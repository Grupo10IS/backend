package py.com.progweb.parcial1.utils;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * <pre>
 * This class is a small util to add "filters" and conditions given by URL
 * querys withing the DAO classes.
 *
 * This process is anoying and can be a mess, so this class provides a simple
 * way to add conditions only and only if the value returned by the url query
 * exists.
 * </pre>
 */
public class QueryBuilder {
    private Integer condCounter;
    private StringBuilder query;

    private HashMap<String, Object> objects;

    public QueryBuilder(String query) {
        this.query = new StringBuilder(query);
        this.objects = new HashMap<String, Object>();
        this.condCounter = 0;
    }

    /**
     * Adds a new hql condition to the query.
     *
     * @param condition  the hql condition to be added.
     * @param identifier the identifier (without the colon) inside the hql
     *                   condition. Example: "fecha < :fecha"
     * @param value      The value to be inserted inside the condition.
     * @return
     */
    public QueryBuilder addCondition(String condition, String identifier, Object value) {
        if (value == null) {
            return this;
        }

        if (this.condCounter == 0) {
            this.query.append(" where");
        } else {
            this.query.append(" and");
        }

        this.query.append(" " + condition);
        this.objects.put(identifier, value);
        this.condCounter++;

        return this;
    }

    /**
     * This is intended for clauses which did not require values.
     * Like group by - order by
     * 
     * @param query
     * @return
     */
    public QueryBuilder addText(String query) {
        this.query.append(" " + query);
        return this;
    }

    /**
     * @param em
     * @return returns the Query object
     */
    public Query build(EntityManager em) {
        Query q = em.createQuery(this.query.toString());

        for (Entry<String, Object> var : this.objects.entrySet()) {
            q.setParameter(var.getKey(), var.getValue());
        }

        return q;
    }
}
