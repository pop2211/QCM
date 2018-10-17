package fr.eni.tp.qcm.ihm.controler.authentification;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.qcm.ihm.model.ConnexionForm;


public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/connexion").forward( request, response );
    }
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
    	/* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();

        /* Traitement de la requète et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        /* Récupération de la session depuis la requète */
        HttpSession session = request.getSession();
        
		if ( form.getErreurs().isEmpty() && utilisateur != null ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            response.sendRedirect( request.getContextPath() + "/accueil");
        } 
		else {
        	session.setAttribute( ATT_SESSION_USER, null );
            request.setAttribute( ATT_FORM, form );
            this.getServletContext().getRequestDispatcher("/connexion").forward( request, response );
        }
    }
}
