/*
 Práctica Java EE 7, DAGSS 2013/14 (ESEI, U. de Vigo)
 */
package es.uvigo.esei.dagss.dominio.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario implements Serializable {

    @Column(length = 25)
    String login;

    @Column(length = 75)
    String nombre;

    @Version
    Long version;

    public Administrador() {
        super();
        this.tipoUsuario = TipoUsuario.ADMINISTRADOR;  // Es necesario hacerlo explícitamente?
    }

    public Administrador(String login, String nombre) {
        super();
        this.tipoUsuario = TipoUsuario.ADMINISTRADOR;  // Es necesario hacerlo explícitamente?

        this.login = login;
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
