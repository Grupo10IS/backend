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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import py.com.progweb.parcial1.utils.LocalDateDeserializer;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id // identificador de la tabla
    @Basic(optional = false) // si es un campo opcional en la insercion (obvio no)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer idCliente;

    @Column(name = "tipo_documento")
    @Basic(optional = false)
    @NotNull
    private Integer tipoDocumento;

    @Column(name = "nombre", length = 50)
    @Basic(optional = false)
    @NotNull
    private String nombre;

    @Column(name = "apellido", length = 50)
    @Basic(optional = false)
    @NotNull
    private String apellido;

    @Column(name = "numero_documento")
    @Basic(optional = false)
    @NotNull
    private Integer numeroDocumento;

    @Column(name = "nacionalidad", length = 50)
    @Basic(optional = false)
    @NotNull
    private String nacionalidad;

    @Column(name = "email", length = 100)
    @Basic(optional = true)
    private String email;

    @Column(name = "telefono", length = 20)
    @Basic(optional = true)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    @Basic(optional = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)  
    private LocalDate nacimiento;

    public Cliente() {
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
