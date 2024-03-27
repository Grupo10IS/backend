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

    // TODO: Constructor, getters, and setters
}
