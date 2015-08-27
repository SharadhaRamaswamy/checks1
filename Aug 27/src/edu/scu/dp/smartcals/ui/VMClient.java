package edu.scu.dp.smartcals.ui;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


/**
 * @author Nisha Narayanaswamy
 * 
 * VMClient class is the main view for the vending machine GUI. 
 *
 */

public class VMClient extends JFrame {

	private static JPanel pnlContainer;
	private static GridBagLayout gridLayout;
	
	public VMClient(){

		pnlContainer = new JPanel();
		gridLayout = new GridBagLayout();		
		
		createAndShowGUI();
		pnlContainer.setLayout(gridLayout);
		pnlContainer.setBackground(Color.CYAN);
		pnlContainer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
				Color.RED, Color.RED));
	}
	
	private void createAndShowGUI() {		
		this.setTitle("SmartCals Vending Machine");		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setContentPane(pnlContainer);			
	}
	 
	public void addPanels(JPanel pnlChild){
		pnlContainer.add(pnlChild);
		pnlChild.setSize(this.getWidth(), this.getHeight());
		pnlChild.setVisible(true);
		pnlChild.requestFocusInWindow();
		this.setVisible(true);	
	}

}
