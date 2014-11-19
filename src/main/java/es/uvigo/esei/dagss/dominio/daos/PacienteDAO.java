/*
 Proyecto Java EE, DAGSS-2014
 */
package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
@LocalBean
public class PacienteDAO extends GenericoDAO<Paciente> {

    public Paciente buscarPorDNI(String dni) {
        Query q = em.createQuery("SELECT p FROM Paciente AS p "
                + "  WHERE p.dni = :dni");
        q.setParameter("dni", dni);
        return filtrarResultadoUnico(q);
    }

    public Paciente buscarPorTarjetaSanitaria(String tarjetaSanitaria) {
        Query q = em.createQuery("SELECT p FROM Paciente AS p "
                + "  WHERE p.numeroTarjetaSanitaria = :tarjetaSanitaria");
        q.setParameter("tarjetaSanitaria", tarjetaSanitaria);

        return filtrarResultadoUnico(q);
    }

    public Paciente buscarPorNumeroSeguridadSocial(String numeroSeguridadSocial) {
        Query q = em.createQuery("SELECT p FROM Paciente AS p "
                + "  WHERE p.numeroSeguridadSocial = :numeroSeguridadSocial");
        q.setParameter("numeroSeguridadSocial", numeroSeguridadSocial);

        return filtrarResultadoUnico(q);
    }

    public List<Paciente> buscarTodos() {
        Query q = em.createQuery("SELECT p FROM Paciente AS p");
        return q.getResultList();
    }

    public Long contarTodos() {
        Query q = em.createQuery("SELECT count(p) FROM Paciente AS p");
        return (Long) q.getSingleResult();
    }

    public List<Paciente> buscarPorNombre(String patron) {
        Query q = em.createQuery("SELECT p FROM Paciente AS p "
                + "  WHERE (p.nombre LIKE :patron) OR "
                + "        (p.apellidos LIKE :patron)");
        q.setParameter("patron","%"+patron+"%");        
        return q.getResultList();
    }

    public List<Paciente> buscarPorLocalidad(String localidad) {
        Query q = em.createQuery("SELECT p FROM Paciente AS p "
                + "  WHERE p.direccion.localidad LIKE :patron");
        q.setParameter("patron","%"+localidad+"%");        
        return q.getResultList();
    }

    // Completar aqui
}
