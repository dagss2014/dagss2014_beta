/*
 Proyecto Java EE, DAGSS-2014/15
 */
package es.uvigo.esei.dagss.controladores.converters;

import es.uvigo.esei.dagss.dominio.daos.CentroSaludDAO;
import es.uvigo.esei.dagss.dominio.daos.TipoAgendaDAO;
import es.uvigo.esei.dagss.dominio.entidades.CentroSalud;
import es.uvigo.esei.dagss.dominio.entidades.TipoAgenda;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ribadas
 */
@FacesConverter(value = "tipoAgendaConverter", forClass = TipoAgenda.class)
public class TipoAgendaConverter extends GenericoConverter<TipoAgenda> {

    @Inject
    TipoAgendaDAO dao;

    @Override
    protected Long recuperarId(TipoAgenda entidad) {
        return entidad.getId();
    }

    @Override
    protected TipoAgenda recuperarEntidad(Long id) {
        return dao.buscarPorId(id);
    }

}
