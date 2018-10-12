package fr.eni.tp.qcm.ihm.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.UtilisateurManager;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public final class ConnexionForm {
	private UtilisateurManager utilisateurManager = ManagerFactory.utilisateurManager();
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "password";
    private static final String CHAMP_INVALIDE = "invalide";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateur = new Utilisateur();
        Utilisateur utilConnect = null;

        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setPassword( motDePasse );
		
        /* Initialisation du résultat global de la validation. */
        resultat = "Email ou mot de passe invalide";
        if ( erreurs.isEmpty()) {
        	try {
        		utilConnect = utilisateurManager.Connexion(utilisateur.getEmail(), utilisateur.getPassword());
    		} catch (ManagerException e) {
    			e.printStackTrace();
    		}
        	if(utilConnect != null) {
        		resultat = "Succès de la connexion.";
        	}
        }
        return utilConnect;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail( String email ) throws Exception {
        if ( email != null) { 
        		if (!email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
        			throw new Exception( "Merci de saisir une adresse mail valide." );
        		}
        } else {
        	throw new Exception( "Merci de saisir votre email." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
