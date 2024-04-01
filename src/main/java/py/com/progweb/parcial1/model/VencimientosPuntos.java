package py.com.progweb.parcial1.model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vencimientos_puntos")
public class VencimientosPuntos {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_inicio_validez")
    @Basic(optional = false)
    @NotNull
    private LocalDate fechaInicioValidez;

    @Column(name = "fecha_fin_validez")
    @Basic(optional = false)
    @NotNull
    private LocalDate fechaFinValidez;

    @Column(name = "dias_duracion_puntaje")
    @Basic(optional = false)
    @NotNull
    private Integer diasDuracionPuntaje;

    public VencimientosPuntos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaInicioValidez() {
        return fechaInicioValidez;
    }

    public void setFechaInicioValidez(LocalDate fechaInicioValidez) {
        this.fechaInicioValidez = fechaInicioValidez;
    }

    public LocalDate getFechaFinValidez() {
        return fechaFinValidez;
    }

    public void setFechaFinValidez(LocalDate fechaFinValidez) {
        this.fechaFinValidez = fechaFinValidez;
    }

    public Integer getDiasDuracionPuntaje() {
        return diasDuracionPuntaje;
    }

    public void setDiasDuracionPuntaje(Integer diasDuracionPuntaje) {
        this.diasDuracionPuntaje = diasDuracionPuntaje;
    }
}
