/*
 Práctica Java EE 7, DAGSS 2013/14 (ESEI, U. de Vigo)
 */

package es.uvigo.esei.dagss.dominio.entidades;

/**
 *
 * @author ribadas
 */
public enum TipoTurno {
    MANANA ("MAÑANA"), 
    TARDE  ("TARDE");
    
    private final String etiqueta;

    private TipoTurno(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }        
}
