package es.uvigo.esei.dagss.controladores.autenticacion;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Filtro de URL para evitar el acceso a las "zonas provadas" de otros tipos de usuarios por
 * parte de usuarios no autenticados o usuarios autenticado de otro tipo
 * @author ribadas
 */
@WebFilter(filterName = "FiltroAutenticacion",
        urlPatterns = {"/faces/administrador/privado/*",
            "/faces/farmacia/privado/*",
            "/faces/medico/privado/*",
            "/faces/paciente/privado/*",})
public class FiltroAutenticacion implements Filter {

    @Inject
    AutenticacionControlador autenticacionControlador;

    public FiltroAutenticacion() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        boolean aceptarPeticion = true;

        if (!autenticacionControlador.isUsuarioAutenticado()) {
            // No se trata de un usuario autenticado
            aceptarPeticion = false;
        } else {
            // Comprobar que el tipo de la zona web a la que se quiere acceder 
            // se corresponde con el tipo de usuario autenticado en la aplicación

            String tipoUsuarioAutenticado = autenticacionControlador.getUsuario().getTipoUsuario().getEtiqueta().toLowerCase();

            // Recuperar el path de la URL y extraer el tipo
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String tipoUsuarioURL = extraerTipoUsuarioURL(httpServletRequest.getPathInfo());

            if (!tipoUsuarioAutenticado.equalsIgnoreCase(tipoUsuarioURL)) {
                aceptarPeticion = false;
            }

            if (aceptarPeticion) {
                // Dejar continuar la petición
                chain.doFilter(request, response);
            } else {
                // Finalizar la sesión del usuario (¿demasiado extrema?)
                httpServletRequest.getSession().invalidate();
                
                // TODO: loguear el intento de acceso
                
                // Redirir a la página de login del tipo de usuario que corresponda
                String contextPath = httpServletRequest.getContextPath();
                ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/" + tipoUsuarioURL + "/login.xhtml");
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig
    ) {
    }

    private String extraerTipoUsuarioURL(String path) {
        String tipoURL = "";

        Pattern patron = Pattern.compile(".*/(.+)/privado/.*");
        Matcher matcher = patron.matcher(path);

        if (matcher.find()) {
            tipoURL = matcher.group(1);
        }
        return tipoURL;
    }
}
