package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.qcm.bo.Promotion;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.dal.dao.PromotionDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class PromotionDAOImpl implements PromotionDAO{
	
	private static final String SELECT_ALL_PROMOTIONS_QUERY = "SELECT * FROM PROMOTION";
	private static final String SELECT_ONE_PROMOTION_QUERY = "SELECT * FROM PROMOTION where idPromotion= ?";
	
	private static PromotionDAOImpl instance;
    
    private PromotionDAOImpl() {
        
    }
    
    public static PromotionDAOImpl getInstance() {
        if(instance == null) {
            instance = new PromotionDAOImpl();
        }
        return instance;
    }

	@Override
	public Promotion insert(Promotion element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Promotion element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Promotion selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Promotion promotion = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_ONE_PROMOTION_QUERY);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	promotion = resultSetToPromotion(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return promotion;
	}

	@Override
	public List<Promotion> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Promotion> promotions = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_PROMOTIONS_QUERY);

            while (resultSet.next()) {
            	promotions.add(resultSetToPromotion(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return promotions;
	}
	
	public Promotion resultSetToPromotion(ResultSet resultSet) throws SQLException {
        
		Promotion promotion = new Promotion();
		promotion.setIdPromotion(resultSet.getInt("idPromotion"));
		promotion.setLibellePromotion(resultSet.getString("libellePromotion"));

        return promotion;
        
    }

}
