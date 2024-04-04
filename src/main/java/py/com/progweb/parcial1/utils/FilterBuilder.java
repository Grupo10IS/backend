package py.com.progweb.parcial1.utils;

public class FilterBuilder {
    StringBuilder query;
    private Integer filters;

    public FilterBuilder(StringBuilder query) {
        this.query = query;
        this.filters = 0;
    }

    private FilterBuilder addFilter(String filter, String operador, String valor) {
        if (filter == null || filter.isEmpty()) {
            return this;
        }

        if (valor == null || valor.isEmpty()) {
            return this;
        }

        if (this.filters == 0) {
            query.append(" where");
        } else {
            this.query.append(" and");
        }

        this.query.append(" " + filter + " = " + valor);
        this.filters++;

        return this;

    }

    public FilterBuilder addEqualsFilter(String filter, String valor) {
        System.out.println(filter + valor);
        return this.addFilter(filter, "=", valor);
    }

    public FilterBuilder addLtFilter(String filter, String valor) {
        return this.addFilter(filter, "<", valor);
    }

    public FilterBuilder addGtFilter(String filter, String valor) {
        return this.addFilter(filter, ">", valor);
    }

    public FilterBuilder addGtoEqFilter(String filter, String valor) {
        return this.addFilter(filter, ">=", valor);
    }

    public FilterBuilder addLtoEqFilter(String filter, String valor) {
        return this.addFilter(filter, "<=", valor);
    }

    public String build() {
        return query.toString();
    }
}
