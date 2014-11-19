/*
 Proyecto Java EE, DAGSS-2014/15
 */
package es.uvigo.esei.dagss.controladores.converters;

import es.uvigo.esei.dagss.dominio.daos.GenericoDAO;
import java.lang.reflect.ParameterizedType;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author ribadas
 */
public abstract class GenericoConverter<T> implements Converter {
    protected abstract Long recuperarId(T entidad);
    protected abstract T recuperarEntidad(Long id);
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return recuperarEntidad(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        T entidad = (T) value;
        return Long.toString(recuperarId(entidad));
    }
}
