package fr.eni.tp.qcm.bll.manager.impl;

import fr.eni.tp.qcm.bll.manager.ReponseTirageManager;
import fr.eni.tp.qcm.bo.ReponseTirage;
import fr.eni.tp.qcm.dal.dao.ReponseTirageDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class ReponseTirageManagerImpl implements ReponseTirageManager{

	private ReponseTirageDAO reponseTirageDAO = DAOFactory.reponseTirageDAO();
    
    private static ReponseTirageManagerImpl instance;
    
    private ReponseTirageManagerImpl() {
        
    }
    
    public static ReponseTirageManagerImpl getInstance() {
        if(instance == null) {
            instance = new ReponseTirageManagerImpl();
        }
        return instance;
    }

	@Override
	public ReponseTirage saveOne(ReponseTirage reponseTirage) throws ManagerException, FunctionalException {
		
		try {
	        ValidationUtil.checkNotNull(reponseTirage);
	      	System.out.println("insert");
	      	reponseTirageDAO.insert(reponseTirage);
		} catch (DaoException e) {
			throw new ManagerException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new ManagerException("La reponse tirage n'est pas valide", e);
		}
	
	  return reponseTirage;
	}
    
}
