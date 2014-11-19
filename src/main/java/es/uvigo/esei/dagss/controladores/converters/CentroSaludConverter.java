/*
 Proyecto Java EE, DAGSS-2014/15
 */
package es.uvigo.esei.dagss.controladores.converters;

import es.uvigo.esei.dagss.dominio.daos.CentroSaludDAO;
import es.uvigo.esei.dagss.dominio.entidades.CentroSalud;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ribadas
 */
@FacesConverter(value = "centroSaludConverter", forClass = CentroSalud.class)
public class CentroSaludConverter extends GenericoConverter<CentroSalud> {
    @Inject
    CentroSaludDAO dao;

    @Override
    protected Long recuperarId(CentroSalud entidad) {
        return entidad.getId();
    }

    @Override
    protected CentroSalud recuperarEntidad(Long id) {
        return dao.buscarPorId(id);
    }
}
