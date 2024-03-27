package py.com.progweb.parcial1.model.bolsa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import py.com.progweb.parcial1.model.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bolsa_puntos")
public class BolsaPuntos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    @Basic(optional = false) // si es un campo opcional en la insercion (obvio no)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull
    private Cliente cliente;

    @Column(name = "fecha_asignacion")
    @Basic(optional = false)
    @NotNull
    private LocalDate fechaAsignacion;

    @Column(name = "fecha_caducidad")
    @Basic(optional = false)
    @NotNull
    private LocalDate fechaCaducidad;

    @Column(name = "puntaje_asignado")
    @Basic(optional = false)
    @NotNull
    private Integer puntajeAsignado;

    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado = 0;

    @Column(name = "saldo_puntos")
    private BigDecimal saldoPuntos;

    @Column(name = "monto_operacion")
    private BigDecimal montoOperacion;

    // TODO: Constructor, getters, and setters
}
