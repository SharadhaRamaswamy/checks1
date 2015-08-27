package edu.scu.dp.smartcals.dao.interfaces;

/**
 * @author Sharadha Ramaswamy
 */
import java.sql.SQLException;

import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.SmartCardModel;
import edu.scu.dp.smartcals.model.SmartCardModelInterface;

public interface SmartCardDao {
	public SmartCardModel getSmartCardId(long id) throws SQLException, EmptyResultException;
	
	public SmartCardModelInterface buySmartCard() throws SQLException, EmptyResultException;
	
	public SmartCardModelInterface loadSmartCard(long SmartCalCardNumber,double balance) throws SQLException,EmptyResultException;
	
	public SmartCardModelInterface updateSmartCard(long SmartCalCardNumber,double balance) throws SQLException,EmptyResultException;
	
	public void checkBalance(SmartCardModel smtcd) throws SQLException;
	
	public SmartCardModelInterface checkValidity(Long SmartCalCardNumber) throws SQLException, EmptyResultException;
}
