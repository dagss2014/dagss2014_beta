/*
 Práctica Java EE 7, DAGSS 2013/14 (ESEI, U. de Vigo)
 */
package es.uvigo.esei.dagss.dominio.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
public class Tratamiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Paciente paciente;

    @ManyToOne
    Medico medico;

    @Size(min = 0, max = 255)
    String comentarios;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date fechaInicio;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date fechaFin;

    @OneToMany(mappedBy = "tratamiento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Prescripcion> prescipciones = new ArrayList<Prescripcion>();

    @Version
    Long version;

    public Tratamiento() {
    }

    public Tratamiento(Paciente paciente, Medico medico, String comentarios, Date fechaInicio, Date fechaFin) {
        this.paciente = paciente;
        this.medico = medico;
        this.comentarios = comentarios;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Prescripcion> getPrescipciones() {
        return prescipciones;
    }

    public void setPrescipciones(List<Prescripcion> prescipciones) {
        this.prescipciones = prescipciones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tratamiento other = (Tratamiento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
 
    
}
