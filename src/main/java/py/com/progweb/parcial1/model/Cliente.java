package py.com.progweb.parcial1.model;

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

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id // identificador de la tabla
    @Basic(optional = false) // si es un campo opcional en la insercion (obvio no)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer idCliente;

    @ManyToOne
    @JoinColumn(name = "tipo_documento")
    @NotNull
    private TipoDocumento forum;

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
    private Integer nroDocumento;

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
    private LocalDate nacimiento;

    // -- TODO: getters and setters --
}
