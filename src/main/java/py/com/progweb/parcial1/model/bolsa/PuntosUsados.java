package py.com.progweb.parcial1.model.bolsa;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import py.com.progweb.parcial1.model.Cliente;
import py.com.progweb.parcial1.model.ConceptoUsos;
import py.com.progweb.parcial1.utils.LocalDateDeserializer;

@Entity
@Table(name = "puntos_usados")
public class PuntosUsados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull
    private Cliente cliente;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    @NotNull
    private Integer puntajeUtilizado;

    @Column(name = "fecha")
    @Basic(optional = false)
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "concepto")
    @NotNull
    private ConceptoUsos concepto;

    public PuntosUsados() {
    }

    public Integer getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public ConceptoUsos getConcepto() {
        return concepto;
    }

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setPuntajeUtilizado(Integer puntajeUtilizado) {
		this.puntajeUtilizado = puntajeUtilizado;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setConcepto(ConceptoUsos concepto) {
		this.concepto = concepto;
	}
}
