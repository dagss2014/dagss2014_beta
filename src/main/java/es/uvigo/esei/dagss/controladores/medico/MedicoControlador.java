/*
 Proyecto Java EE, DAGSS-2013
 */
package es.uvigo.esei.dagss.controladores.medico;

import es.uvigo.esei.dagss.controladores.autenticacion.AutenticacionControlador;
import es.uvigo.esei.dagss.dominio.daos.MedicoDAO;
import es.uvigo.esei.dagss.dominio.daos.TratamientoDAO;
import es.uvigo.esei.dagss.dominio.daos.UsuarioDAO;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.TipoUsuario;
import es.uvigo.esei.dagss.dominio.entidades.Tratamiento;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author ribadas
 */
@Named(value = "medicoControlador")
@SessionScoped
public class MedicoControlador implements Serializable {

    private Medico medicoActual;
    private String dni;
    private String numeroColegiado;
    private String password;
    private Tratamiento tratamientoActual;
    private List<Tratamiento> tratamientos;

    @Inject
    private AutenticacionControlador autenticacionControlador;
    @Inject
    private TratamientoDAO tratamientoDAO;

    @EJB
    private MedicoDAO medicoDAO;
    @EJB
    private UsuarioDAO usuarioDAO;

    /**
     * Creates a new instance of AdministradorControlador
     */
    public MedicoControlador() {
    }
    
    public void inicializar() {
        tratamientos = tratamientoDAO.buscarPorMedico(medicoActual.getId());
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Medico getMedicoActual() {
        return medicoActual;
    }

    public Tratamiento getTratamientoActual() {
        return tratamientoActual;
    }

    public void setTratamientoActual(Tratamiento tratamientoActual) {
        this.tratamientoActual = tratamientoActual;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }
    
    public void setMedicoActual(Medico medicoActual) {
        this.medicoActual = medicoActual;
    }

    private boolean parametrosAccesoInvalidos() {
        return (((dni == null) && (numeroColegiado == null)) || (password == null));
    }

    private Medico recuperarDatosMedico() {
        Medico medico = null;
        if (dni != null) {
            medico = medicoDAO.buscarPorDNI(dni);
        }
        if ((medico == null) && (numeroColegiado != null)) {
            medico = medicoDAO.buscarPorNumeroColegiado(numeroColegiado);
        }
        return medico;
    }

    public String doLogin() {
        String destino = null;
        if (parametrosAccesoInvalidos()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha indicado un DNI ó un número de colegiado o una contraseña", ""));
        } else {
            Medico medico = recuperarDatosMedico();
            if (medico == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe ningún médico con los datos indicados", ""));
            } else {
                if (autenticacionControlador.autenticarUsuario(medico.getId(), password,
                        TipoUsuario.MEDICO.getEtiqueta().toLowerCase())) {
                    medicoActual = medico;
                    destino = "privado/index";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales de acceso incorrectas", ""));
                }
            }
        }
        return destino;
    }

    //Acciones
    public String doShowCita() {
        return "detallesCita";
    }
    
    public String doCancelar() {
      return "index";
    }
     
     public String doGuardar() {
         if (!medicoActual.getPassword().equals("")){
             medicoActual = medicoDAO.actualizar(medicoActual);
             usuarioDAO.actualizarPassword(medicoActual.getId(), 
                medicoActual.getPassword());
             medicoActual = recuperarDatosMedico();
             
         }else {
             String passAntigua = recuperarDatosMedico().getPassword();
             medicoActual.setPassword(passAntigua);
             medicoActual = medicoDAO.actualizar(medicoActual);
         }
         
         return "index";
     }
     
     public void doEliminar() {
        tratamientoDAO.eliminar(tratamientoActual);
        tratamientos = tratamientoDAO.buscarPorMedico(medicoActual.getId());
    }
    
}
