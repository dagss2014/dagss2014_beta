/*
 Proyecto Java EE, DAGSS-2014
 */
package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
@LocalBean
public class MedicoDAO extends GenericoDAO<Medico> {

    public Medico buscarPorDNI(String dni) {
        Query q = em.createQuery("SELECT m FROM Medico AS m "
                + "  WHERE m.dni = :dni");
        q.setParameter("dni", dni);

        return filtrarResultadoUnico(q);
    }

    public Medico buscarPorNumeroColegiado(String numeroColegiado) {
        Query q = em.createQuery("SELECT m FROM Medico AS m "
                + "  WHERE m.numeroColegiado = :numeroColegiado");
        q.setParameter("numeroColegiado", numeroColegiado);
        
        return filtrarResultadoUnico(q);
    }

    public List<Paciente> buscarPorNombre(String patron) {
        Query q = em.createQuery("SELECT m FROM Medico AS m "
                + "  WHERE (m.nombre LIKE :patron) OR "
                + "        (m.apellidos LIKE :patron)");
        q.setParameter("patron","%"+patron+"%");        
        return q.getResultList();
    }

    // Completar aqui
}
