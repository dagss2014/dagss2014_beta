/*
 Proyecto Java EE, DAGSS-2013
 */
package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Farmacia;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
@LocalBean
public class FarmaciaDAO extends GenericoDAO<Farmacia> {

    public Farmacia buscarPorNIF(String nif) {
        Query q = em.createQuery("SELECT f FROM Farmacia AS f "
                + "  WHERE f.nif = :nif");
        q.setParameter("nif", nif);

        return filtrarResultadoUnico(q);
    }

    // Completar aqui
}
