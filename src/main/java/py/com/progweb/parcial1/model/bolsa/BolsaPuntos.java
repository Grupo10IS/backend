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
    private Integer puntajeUtilizado;

    @Column(name = "saldo_puntos")
    private Integer saldoPuntos;

    @Column(name = "monto_operacion")
    private BigDecimal montoOperacion;

    public BolsaPuntos(Cliente cliente, LocalDate fechaCaducidad, Integer puntajeAsignado, BigDecimal montoOperacion) {
        this.cliente = cliente;
        this.fechaCaducidad = fechaCaducidad;
        this.puntajeAsignado = puntajeAsignado;
        this.montoOperacion = montoOperacion;

        this.fechaAsignacion = LocalDate.now();
        this.puntajeUtilizado = 0;
        this.saldoPuntos = puntajeAsignado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getPuntajeAsignado() {
        return puntajeAsignado;
    }

    public void setPuntajeAsignado(Integer puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Integer getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(Integer saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public BigDecimal getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(BigDecimal montoOperacion) {
        this.montoOperacion = montoOperacion;
    }
}
