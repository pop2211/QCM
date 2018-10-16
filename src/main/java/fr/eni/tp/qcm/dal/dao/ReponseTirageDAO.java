package fr.eni.tp.qcm.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.tp.qcm.bo.ReponseTirage;

public interface ReponseTirageDAO  extends GenericDAO<ReponseTirage, Integer>{

	ReponseTirage resultSetToReponseTirage(ResultSet resultSet) throws SQLException;

}
