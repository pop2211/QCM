package fr.eni.tp.qcm.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.tp.qcm.bo.Theme;

public interface ThemeDAO extends GenericDAO<Theme, Integer>{

	Theme resultSetToTheme(ResultSet resultSet) throws SQLException;

}
