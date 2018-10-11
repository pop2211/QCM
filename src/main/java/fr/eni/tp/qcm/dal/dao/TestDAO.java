package fr.eni.tp.qcm.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.tp.qcm.bo.Test;

public interface TestDAO extends GenericDAO<Test, Integer>{

	Test resultSetToTest(ResultSet resultSet) throws SQLException;

}
