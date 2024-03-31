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
@Table(name = "reglas_asignacion_puntos")
public class ReglasAsignacion {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer idRegla;

    @Column(name = "limite_inferior")
    @Basic(optional = false)
    @NotNull
    private Integer limInferior;

    @Column(name = "limite_superior")
    @Basic(optional = false)
    @NotNull
    private Integer limSuperior;

    @Column(name = "monto_equivalencia")
    @Basic(optional = false)
    @NotNull
    private Integer montoEquivalencia;

    // TODO: Constructor, getters, and setters
}