package fr.eni.tp.qcm.ihm.controler.resultat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.EpreuveManager;
import fr.eni.tp.qcm.bll.manager.QuestionTirageManager;
import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.dal.dao.EpreuveDAO;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.qcm.utils.AppConstants;
import fr.eni.tp.qcm.utils.Result;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class ResultatGen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EpreuveDAO epreuveDao = DAOFactory.epreuveDAO();
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	private TestDAO testDao = DAOFactory.testDAO();
    
    public ResultatGen() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("epreuveId", "1");
		Integer idEpreuve = Integer.parseInt(session.getAttribute("epreuveId").toString());
		Result result = null;
		
		try {
			result = epreuveDao.GetResult(idEpreuve);
			Float scoreTotal = result.getTotal();
			String niveau = AppConstants.niveau.ACQUIS.toString();
			
			//update de l'état de l'épreuve
			Epreuve epreuve = epreuveManager.findOne(idEpreuve);
			epreuve.setEtat(AppConstants.StatutEpreuve.TERMINE.toString());
			epreuve.setNoteObtenue(scoreTotal);
			
			//calcul du niveau
			Integer seuilBas = epreuve.getTest().getSeuilBas();
			Integer seuilHaut = epreuve.getTest().getSeuilHaut();
			if(scoreTotal < seuilBas) {
				niveau = AppConstants.niveau.NON_ACQUIS.toString();
			} else if(scoreTotal < seuilHaut){
				niveau = AppConstants.niveau.EN_COURS_ACQUISITION.toString();
			}
			epreuve.setNiveauObtenu(niveau);
			
			epreuveDao.update(epreuve);
			
			request.setAttribute("epreuve", epreuve);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/resultatDetail").forward(request, response);
			
			
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result.getTotal());
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
