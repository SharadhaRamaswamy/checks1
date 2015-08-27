package edu.scu.dp.smartcals.test;



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.scu.dp.smartcals.constants.DbType;
import edu.scu.dp.smartcals.dao.impl.SmartCardDaoImpl;
import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;
import edu.scu.dp.smartcals.dao.interfaces.SmartCardDao;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.SmartCardModel;
import edu.scu.dp.smartcals.model.SmartCardModelInterface;

public class SmartCardTest {

	private static SmartCardDao smctDao;
	

	@BeforeClass
	public static void init() throws IOException, ClassNotFoundException {
	
		FileInputStream f = new FileInputStream("db.properties");
		Properties properties = new Properties();
		properties.load(f);
		DatabaseFactory factory = DatabaseFactory.getFactory(properties,
				DbType.MYSQL);
		
		//smctDao = new SmartCardDaoImpl(factory);
		smctDao = SmartCardDaoImpl.getInstance(factory);
	}

	@Test
	public void testGetProductsById() throws SQLException, EmptyResultException {
		/*SmartCardModel smct = smctDao.buySmartCard();
		System.out.println(smct.getBalance());*/
		SmartCardModelInterface smct = smctDao.loadSmartCard(100001, 50.0);
		System.out.println(smct.getBalance());
	
	}
}
