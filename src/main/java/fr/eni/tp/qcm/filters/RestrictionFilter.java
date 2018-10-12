package fr.eni.tp.qcm.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFilter implements Filter {
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException, ServletException {
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        String path = ((HttpServletRequest) request).getServletPath();
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
        	if (!path.equals("/login") &&
        		!path.equals("/logout") &&
        		!path.equals("/connexion") &&
        		!path.startsWith("/css") && 
        		!path.startsWith("/js") &&
        		!path.startsWith("jsp/commons/")
        		) {
        		response.sendRedirect( request.getContextPath() + "/connexion");
        	}
        	else {
        		chain.doFilter( request, response );
        	}
        } else {
            chain.doFilter( request, response );
        }
    }

    public void destroy() {
    }

}
