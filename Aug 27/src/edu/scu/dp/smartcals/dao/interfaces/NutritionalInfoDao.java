package edu.scu.dp.smartcals.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.NutritionalInfoModel;

/**
 * @author Nisha Narayanaswamy
 * 
 *         Data Access Object for Nutritional Infotmation
 *
 */
public interface NutritionalInfoDao {

	/**
	 * @param prodID
	 *            ProductID to be searched against
	 * @return
	 * @throws SQLException
	 * @throws EmptyResultException
	 */
	public NutritionalInfoModel getNutriInfo(long prodID) throws SQLException,
			EmptyResultException;

	public boolean addNutriInfo(ArrayList<String> dataValues)
			throws SQLException;

	public boolean updateNutriInfo(ArrayList<String> dataValues)
			throws SQLException;

	public boolean deleteNutriInfo(long prodID) throws SQLException;
	
	public String getSmartTag(long prodID) throws SQLException, EmptyResultException;
	
	public String getCalories(long prodId) throws SQLException,EmptyResultException;


}

