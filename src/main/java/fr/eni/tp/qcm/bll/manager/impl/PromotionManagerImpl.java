package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.PromotionManager;
import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Promotion;
import fr.eni.tp.qcm.dal.dao.PromotionDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class PromotionManagerImpl implements PromotionManager{
	
	private PromotionDAO promotionDAO = DAOFactory.promotionDAO();
    
    private static PromotionManagerImpl instance;
    
    private PromotionManagerImpl() {
        
    }
    
    public static PromotionManagerImpl getInstance() {
        if(instance == null) {
            instance = new PromotionManagerImpl();
        }
        return instance;
    }

	@Override
	public List<Promotion> findAll() throws ManagerException {
		List<Promotion> promotions = null;    
        try {
        	promotions = promotionDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return promotions;
	}

	@Override
	public Promotion findOne(Integer id) throws ManagerException, ElementNotFoundException {
		Promotion promotion = null;    
        try {
            ValidationUtil.checkNotNull(id);
            promotion = promotionDAO.selectById(id);
            if(promotion == null) {
                throw new ElementNotFoundException("La promotion n'existe pas", null);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas �tre null", e);
        }
        return promotion;
	}

}
