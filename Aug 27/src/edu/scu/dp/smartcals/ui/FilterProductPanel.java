package edu.scu.dp.smartcals.ui;
/**
 * @author Sharadha Ramaswamy
 */
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class FilterProductPanel extends JPanel {
	
	private JLabel productIdLbl;
	private JLabel productNameLbl;
	private JLabel productPriceLbl;
	
	public FilterProductPanel(String id,String name,String price) {
		productIdLbl = new JLabel(id);
		productNameLbl = new JLabel(name);
		productPriceLbl = new JLabel(price);
		setLayout(new GridLayout(3,1));
		setBorder(BorderFactory.createRaisedBevelBorder());
		setBackground(Color.cyan);
		add(productIdLbl);
		add(productNameLbl);
		add(productPriceLbl);
	}
}