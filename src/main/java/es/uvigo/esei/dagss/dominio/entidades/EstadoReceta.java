/*
 Práctica Java EE 7, DAGSS 2013/14 (ESEI, U. de Vigo)
 */
package es.uvigo.esei.dagss.dominio.entidades;

public enum EstadoReceta {

    GENERADA ("GENERADA"), 
    SERVIDA  ("SERVIDA"), 
    ANULADA  ("ANULADA");

    private final String etiqueta;

    private EstadoReceta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
