package py.com.progweb.parcial1.model.bolsa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalle_puntos_usados")
public class DetallePuntos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "puntos_usados_id")
    @NotNull
    private PuntosUsados puntosUsados;

    @ManyToOne
    @JoinColumn(name = "bolsa_id")
    @NotNull
    private BolsaPuntos bolsa;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    @NotNull
    private Integer puntajeUtilizado;

    public DetallePuntos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PuntosUsados getPuntosUsados() {
        return puntosUsados;
    }

    public void setPuntosUsados(PuntosUsados puntosUsados) {
        this.puntosUsados = puntosUsados;
    }

    public BolsaPuntos getBolsa() {
        return bolsa;
    }

    public void setBolsa(BolsaPuntos bolsa) {
        this.bolsa = bolsa;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }
}
