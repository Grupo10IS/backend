package py.com.progweb.parcial1.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "concepto_uso_puntos")
public class ConceptoUsos {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer idConcepto;

    public Integer getIdConcepto() {
        return idConcepto;
    }

    @Column(name = "descripcion")
    @Basic(optional = false)
    @NotNull
    private String descripcion;

    @Column(name = "puntos_requeridos")
    @Basic(optional = false)
    @NotNull
    private Integer puntos;

    public ConceptoUsos() {

    }

	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
}
