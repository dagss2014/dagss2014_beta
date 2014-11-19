/*
 Proyecto Java EE, DAGSS-2014
 */
package es.uvigo.esei.dagss.controladores.administrador;

import es.uvigo.esei.dagss.dominio.daos.TipoAgendaDAO;
import es.uvigo.esei.dagss.dominio.entidades.TipoAgenda;
import es.uvigo.esei.dagss.dominio.entidades.TipoTurno;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author dagss
 */
@Named(value = "gestionTiposAgendaControlador")
@SessionScoped
public class GestionTiposAgendaControlador implements Serializable {

    @Inject
    TipoAgendaDAO tipoAgendaDAO;

    List<TipoAgenda> tiposAgenda;
    TipoAgenda tipoAgendaActual;

    
    
    /**
     * Creates a new instance of CentroSaludControlador
     */
    public GestionTiposAgendaControlador() {
    }

    @PostConstruct
    public void inicializar() {
        tiposAgenda = tipoAgendaDAO.buscarTodos();
    }

    public TipoTurno[] getTiposTurno() {
        return TipoTurno.values();
    }


    
    
    public List<TipoAgenda> getTiposAgenda() {
        return tiposAgenda;
    }

    public void setTiposAgenda(List<TipoAgenda> tiposAgenda) {
        this.tiposAgenda = tiposAgenda;
    }

    public TipoAgenda getTipoAgendaActual() {
        return tipoAgendaActual;
    }

    public void setTipoAgendaActual(TipoAgenda tipoAgendaActual) {
        this.tipoAgendaActual = tipoAgendaActual;
    }


    public void doEliminar() {
        tipoAgendaDAO.eliminar(tipoAgendaActual);
        tiposAgenda = tipoAgendaDAO.buscarTodos(); // Actualizar lista de tipos de agenda
    }

    public void doNuevo() {
        tipoAgendaActual = new TipoAgenda(); // Tipo de agenda vacio

    }

    public void doEditar(TipoAgenda tipoAgenda) {
        tipoAgendaActual = tipoAgenda;   // Otra alternativa: volver a refrescarlos desde el DAO
    }

    public void doGuardarNuevo() {
        // Crea un nuevo tipo Agenda
        tipoAgendaActual = tipoAgendaDAO.crear(tipoAgendaActual);
        // Actualiza lista de tipos agenda a mostrar
        tiposAgenda = tipoAgendaDAO.buscarTodos();

    }

    public void doGuardarEditado() {
        // Actualiza un tipo agenda
        tipoAgendaActual = tipoAgendaDAO.actualizar(tipoAgendaActual);
        // Actualiza lista de tipos de gaenda a mostrar
        tiposAgenda = tipoAgendaDAO.buscarTodos();
    }


    public String doVolver() {
        return "../index?faces-redirect=true";
    }

}
