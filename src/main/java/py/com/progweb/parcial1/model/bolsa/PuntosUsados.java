package py.com.progweb.parcial1.model.bolsa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import py.com.progweb.parcial1.model.Cliente;
import py.com.progweb.parcial1.model.ConceptoUsos;

import java.time.LocalDate;

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

    public String getConcepto() {
        return concepto;
    }
}
