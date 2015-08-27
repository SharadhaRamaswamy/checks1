package edu.scu.dp.smartcals.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//import com.mysql.fabric.xmlrpc.base.Array;


import javax.swing.JScrollPane;
import javax.xml.ws.handler.MessageContext.Scope;

import edu.scu.dp.smartcals.admin.AdminOperations;
import edu.scu.dp.smartcals.admin.AdminOperationsImpl;
import edu.scu.dp.smartcals.admin.Alert;
import edu.scu.dp.smartcals.admin.AlertListener;
import edu.scu.dp.smartcals.admin.RevenueTableController;
import edu.scu.dp.smartcals.admin.RevenueTableModel;
import edu.scu.dp.smartcals.constants.VMLocationType;
import edu.scu.dp.smartcals.exception.AdminOperationsException;
import edu.scu.dp.smartcals.exception.DatabaseInitializationException;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.InventoryModel;
import edu.scu.dp.smartcals.model.NutritionalInfoModel;
import edu.scu.dp.smartcals.model.ProductModel;
import edu.scu.dp.smartcals.test.TestTable;
import edu.scu.dp.smartcals.vm.LoginCheckPointStrategy;
import edu.scu.dp.smartcals.vm.Beverage;
import edu.scu.dp.smartcals.vm.Product;
import edu.scu.dp.smartcals.vm.Snack;
import edu.scu.dp.smartcals.vm.VMController;
import edu.scu.dp.smartcals.vm.VendingMachine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Sharadha Ramaswamy 
 * @author Nisha
 */
public class MonitoringStationView extends javax.swing.JPanel implements
		AlertListener {

	private VMController vmController;

	// aparna - start-8/22
	private AdminOperations admin;

	private List<Long> vmIds;
	// aparna - end

	private List<Alert> alerts;
	// Variables declaration - do not modify
	private javax.swing.JButton btnAddInventory;
	private javax.swing.JButton btnAddNutriInfo;
	private javax.swing.JButton btnAddProd;
	private javax.swing.JButton btnDeleteInventory;
	private javax.swing.JButton btnDeleteNutriInfo;
	private javax.swing.JButton btnDeleteProd;
	private javax.swing.JButton btnSearchProduct;
	private javax.swing.JButton btnSearchInventory;
	private javax.swing.JButton btnSearchNutriInfo;
	private javax.swing.JButton btnUpdateInventory;
	private javax.swing.JButton btnUpdateNutriInfo;
	private javax.swing.JButton btnUpdateProd;
	private javax.swing.JLabel lblAlerts;
	private javax.swing.JLabel lblCalories;
	private javax.swing.JLabel lblCholestrol;
	private javax.swing.JLabel lblDietaryFiber;
	private javax.swing.JLabel lblInvenPrice;
	private javax.swing.JLabel lblInvenProdID;
	private javax.swing.JLabel lblIron;
	private javax.swing.JLabel lblNutriProdID;
	private javax.swing.JLabel lblOtherStats;
	private javax.swing.JLabel lblProductCategory;
	private javax.swing.JLabel lblProductID;
	private javax.swing.JLabel lblProductName;
	private javax.swing.JLabel lblProductPrice;
	private javax.swing.JLabel lblProtein;
	private javax.swing.JLabel lblQuantity;
	private javax.swing.JLabel lblSaturatedFat;
	private javax.swing.JLabel lblServingSize;
	private javax.swing.JLabel lblSmartTag;
	private javax.swing.JLabel lblSodium;
	private javax.swing.JLabel lblSugars;
	private javax.swing.JLabel lblTotalCarbs;
	private javax.swing.JLabel lblTotalFat;
	private javax.swing.JLabel lblTransFat;
	private javax.swing.JLabel lblVendingMachineID;
	private javax.swing.JPanel pnlAlerts;
	private javax.swing.JPanel pnlInventory;
	private javax.swing.JPanel pnlNutriInfo;
	private javax.swing.JPanel pnlOtherStats;
	private javax.swing.JPanel pnlProduct;
	private javax.swing.JPanel pnlRevenueStat;
	private javax.swing.JPanel pnlViewVM;
	private javax.swing.JRadioButton radioAll;
	private javax.swing.ButtonGroup radioBtnGroup;
	private javax.swing.JRadioButton radioVM1;
	private javax.swing.JRadioButton radioVM2;
	private javax.swing.JScrollPane scrollTable;
	private javax.swing.JTable tblRevenue;
	private javax.swing.JTextField txtCalories;
	private javax.swing.JTextField txtCholestrol;
	private javax.swing.JTextField txtDietaryFiber;
	private javax.swing.JTextField txtInvenPrice;
	private javax.swing.JTextField txtInvenProdID;
	private javax.swing.JTextField txtIron;
	private javax.swing.JTextField txtNutriProdID;
	private javax.swing.JTextField txtProductCategory;
	private javax.swing.JTextField txtProductID;
	private javax.swing.JTextField txtProductName;
	private javax.swing.JTextField txtProductPrice;
	private javax.swing.JTextField txtProtein;
	private javax.swing.JTextField txtQuantity;
	private javax.swing.JTextField txtSaturatedFat;
	private javax.swing.JTextField txtServingSize;
	private javax.swing.JTextField txtSmartTag;
	private javax.swing.JTextField txtSodium;
	private javax.swing.JTextField txtSugars;
	private javax.swing.JTextField txtTotalCarbs;
	private javax.swing.JTextField txtTotalFat;
	private javax.swing.JTextField txtTransFat;
	private javax.swing.JTextField txtVendingMachineID;

	private VMRadioButtonActionListener vmButtonActionListener;

	// start - Nisha - 8/23
	private RevenueTableController revenueTableController;
	// end - Nisha - 8/23

	// code change-Aparna 08/23

	private ProductOperationsActionListener prodOpActionListener;
	
	
	//nisha - 8/24
	private NutriInfoOperationsActionListener nutriOpActionListener;
	//end -Nisha

	// End of variables declaration
	/**
	 * Creates new form MonitoringStationDashboard
	 */
	public MonitoringStationView(VMController vmController) {
		this.vmController = vmController;
		// aparna - start 8/22
		admin = AdminOperationsImpl.getInstance();

		//aparna -08/24
		admin.addAlertListeners(this);
		alerts = new ArrayList<>();
		
		// aparna - end
		initComponents();

		// Nisha - 8/24
		if (revenueTableController == null)
			revenueTableController = new RevenueTableController(vmController);
		
	}

	/**
	 * Code change-Aparna 8/18 Updates User Interface with the out of stock
	 * alert
	 */
	@Override
	public void update(Alert alert) {
		// TODO set the label with the alert received
		alerts.add(alert);
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<html>");
		for(Alert alrt : alerts) {
			strBuilder.append("<br>" + alrt.getMessage());
		}
		lblAlerts.setText(strBuilder.toString());
		// lblAlerts.setForeground(ColorModel.getRGBdefault().getRed());

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		radioBtnGroup = new javax.swing.ButtonGroup();
		pnlViewVM = new javax.swing.JPanel();
		radioAll = new javax.swing.JRadioButton();
		radioVM1 = new javax.swing.JRadioButton();
		radioVM2 = new javax.swing.JRadioButton();
		pnlAlerts = new javax.swing.JPanel();
		lblAlerts = new javax.swing.JLabel();
		pnlRevenueStat = new javax.swing.JPanel();
		scrollTable = new javax.swing.JScrollPane();
		tblRevenue = new javax.swing.JTable();
		pnlOtherStats = new javax.swing.JPanel();
		lblOtherStats = new javax.swing.JLabel();
		pnlProduct = new javax.swing.JPanel();
		lblProductID = new javax.swing.JLabel();
		lblProductCategory = new javax.swing.JLabel();
		lblProductName = new javax.swing.JLabel();
		lblProductPrice = new javax.swing.JLabel();
		txtProductID = new javax.swing.JTextField();
		txtProductCategory = new javax.swing.JTextField();
		txtProductName = new javax.swing.JTextField();
		txtProductPrice = new javax.swing.JTextField();
		btnSearchProduct = new javax.swing.JButton();
		btnAddProd = new javax.swing.JButton();
		btnUpdateProd = new javax.swing.JButton();
		btnDeleteProd = new javax.swing.JButton();
		pnlInventory = new javax.swing.JPanel();
		lblInvenProdID = new javax.swing.JLabel();
		lblVendingMachineID = new javax.swing.JLabel();
		lblInvenPrice = new javax.swing.JLabel();
		txtInvenProdID = new javax.swing.JTextField();
		txtVendingMachineID = new javax.swing.JTextField();
		txtQuantity = new javax.swing.JTextField();
		txtInvenPrice = new javax.swing.JTextField();
		lblQuantity = new javax.swing.JLabel();
		btnSearchInventory = new javax.swing.JButton();
		btnAddInventory = new javax.swing.JButton();
		btnUpdateInventory = new javax.swing.JButton();
		btnDeleteInventory = new javax.swing.JButton();
		pnlNutriInfo = new javax.swing.JPanel();
		lblNutriProdID = new javax.swing.JLabel();
		txtNutriProdID = new javax.swing.JTextField();
		lblServingSize = new javax.swing.JLabel();
		lblCalories = new javax.swing.JLabel();
		lblTotalFat = new javax.swing.JLabel();
		lblSaturatedFat = new javax.swing.JLabel();
		txtServingSize = new javax.swing.JTextField();
		txtCalories = new javax.swing.JTextField();
		txtTotalFat = new javax.swing.JTextField();
		txtSaturatedFat = new javax.swing.JTextField();
		lblTransFat = new javax.swing.JLabel();
		lblCholestrol = new javax.swing.JLabel();
		lblSodium = new javax.swing.JLabel();
		lblTotalCarbs = new javax.swing.JLabel();
		lblDietaryFiber = new javax.swing.JLabel();
		txtTransFat = new javax.swing.JTextField();
		txtCholestrol = new javax.swing.JTextField();
		txtSodium = new javax.swing.JTextField();
		txtTotalCarbs = new javax.swing.JTextField();
		txtDietaryFiber = new javax.swing.JTextField();
		lblSugars = new javax.swing.JLabel();
		lblProtein = new javax.swing.JLabel();
		lblIron = new javax.swing.JLabel();
		lblSmartTag = new javax.swing.JLabel();
		txtSugars = new javax.swing.JTextField();
		txtProtein = new javax.swing.JTextField();
		txtIron = new javax.swing.JTextField();
		txtSmartTag = new javax.swing.JTextField();
		btnSearchNutriInfo = new javax.swing.JButton();
		btnAddNutriInfo = new javax.swing.JButton();
		btnUpdateNutriInfo = new javax.swing.JButton();
		btnDeleteNutriInfo = new javax.swing.JButton();

		setAutoscrolls(true);
		setLayout(new java.awt.GridBagLayout());

		pnlViewVM.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Select Vending Machine(s)",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 1, 14))); // NOI18N
		pnlViewVM.setName("ViewVM"); // NOI18N

		// aparna - change-8/22
		pnlViewVM.setLayout(new FlowLayout());

		radioBtnGroup.add(radioAll);
		radioAll.setText("All VM");
		radioAll.setActionCommand("ALL");
		pnlViewVM.add(radioAll);

		vmButtonActionListener = new VMRadioButtonActionListener();
		radioAll.addActionListener(vmButtonActionListener);
		// dynamically load vending machine radio buttons
		loadVendingMachinesRadioPanel();

		// code change -Aparna 08/23
		// add actionlistener for add product button

		prodOpActionListener = new ProductOperationsActionListener();
		btnAddProd.addActionListener(prodOpActionListener);
		btnUpdateProd.addActionListener(prodOpActionListener);
		btnDeleteProd.addActionListener(prodOpActionListener);
		btnSearchProduct.addActionListener(prodOpActionListener);
		btnAddProd.setActionCommand("ADD_PRODUCT");
		btnUpdateProd.setActionCommand("UPDATE_PRODUCT");
		btnDeleteProd.setActionCommand("DELETE_PRODUCT");
		btnSearchProduct.setActionCommand("SEARCH_PRODUCT");
		//code change-Aparna 08/23

		// --------------------------------aparna -8/22
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
		gridBagConstraints.weightx = 0.2;
		add(pnlViewVM, gridBagConstraints);

		pnlAlerts.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Alerts!",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 0,
						0))); // NOI18N
		pnlAlerts.setPreferredSize(new java.awt.Dimension(561, 78));

		lblAlerts.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		lblAlerts.setForeground(new java.awt.Color(255, 102, 102));
		lblAlerts
				.setText("All alerts from Observer shld be displayed here by appending text.");
		lblAlerts.setName("Alerts"); // NOI18N

		javax.swing.GroupLayout pnlAlertsLayout = new javax.swing.GroupLayout(
				pnlAlerts);
		pnlAlerts.setLayout(pnlAlertsLayout);
		pnlAlertsLayout.setHorizontalGroup(pnlAlertsLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				lblAlerts, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		pnlAlertsLayout.setVerticalGroup(pnlAlertsLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				lblAlerts, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
				javax.swing.GroupLayout.PREFERRED_SIZE));

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
		gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
		gridBagConstraints.weightx = 0.4;
		gridBagConstraints.weighty = 0.2;
		add(pnlAlerts, gridBagConstraints);

		pnlRevenueStat.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Revenue Statistics",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0,
						153))); // NOI18N

		
		//start - nisha - 8/24
		scrollTable.setName("scrollTable"); // NOI18N
		tblRevenue.setName("Revenue"); // NOI18N
		scrollTable.setViewportView(tblRevenue);		
		//end - Nisha - 8/24

		 javax.swing.GroupLayout pnlRevenueStatLayout = new javax.swing.GroupLayout(
				 pnlRevenueStat);
		 pnlRevenueStat.setLayout(pnlRevenueStatLayout);
		 pnlRevenueStatLayout.setHorizontalGroup(pnlRevenueStatLayout
				 .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				 .addGroup(
						 pnlRevenueStatLayout.createSequentialGroup()
						 .addContainerGap()
						 .addComponent(scrollTable,
								 javax.swing.GroupLayout.PREFERRED_SIZE, 660,
								 javax.swing.GroupLayout.PREFERRED_SIZE)
								 .addContainerGap(114, Short.MAX_VALUE)));
		 pnlRevenueStatLayout.setVerticalGroup(pnlRevenueStatLayout
				 .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				 .addGroup(
						 javax.swing.GroupLayout.Alignment.TRAILING,
						 pnlRevenueStatLayout.createSequentialGroup()
						 .addContainerGap(16, Short.MAX_VALUE)
						 .addComponent(scrollTable,
								 javax.swing.GroupLayout.PREFERRED_SIZE, 267,
								 javax.swing.GroupLayout.PREFERRED_SIZE)
								 .addContainerGap()));

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
		gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.weighty = 0.5;
		add(pnlRevenueStat, gridBagConstraints);

		pnlOtherStats.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Other Statistics",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 1, 14))); // NOI18N

		lblOtherStats.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblOtherStats.setName("OtherStats"); // NOI18N

		javax.swing.GroupLayout pnlOtherStatsLayout = new javax.swing.GroupLayout(
				pnlOtherStats);
		pnlOtherStats.setLayout(pnlOtherStatsLayout);
		pnlOtherStatsLayout.setHorizontalGroup(pnlOtherStatsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(lblOtherStats,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		pnlOtherStatsLayout.setVerticalGroup(pnlOtherStatsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(lblOtherStats,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 154,
						Short.MAX_VALUE));

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.weightx = 0.4;
		add(pnlOtherStats, gridBagConstraints);

		pnlProduct.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Product",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 1, 14))); // NOI18N
		pnlProduct.setLayout(new java.awt.GridBagLayout());

		lblProductID.setText("Product ID");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlProduct.add(lblProductID, gridBagConstraints);

		lblProductCategory.setText("Product Category");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlProduct.add(lblProductCategory, gridBagConstraints);

		lblProductName.setText("Product Name");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlProduct.add(lblProductName, gridBagConstraints);

		lblProductPrice.setText("Product Price");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlProduct.add(lblProductPrice, gridBagConstraints);

		txtProductID.setColumns(14);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		pnlProduct.add(txtProductID, gridBagConstraints);

		txtProductCategory.setColumns(14);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlProduct.add(txtProductCategory, gridBagConstraints);

		txtProductName.setColumns(14);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlProduct.add(txtProductName, gridBagConstraints);

		txtProductPrice.setColumns(14);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlProduct.add(txtProductPrice, gridBagConstraints);

		btnSearchProduct.setText("Search");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 10;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		pnlProduct.add(btnSearchProduct, gridBagConstraints);

		btnAddProd.setText("Add Product");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 10;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		pnlProduct.add(btnAddProd, gridBagConstraints);

		btnUpdateProd.setText("Update Product");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 10;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		pnlProduct.add(btnUpdateProd, gridBagConstraints);

		btnDeleteProd.setText("Delete Product");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 10;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		pnlProduct.add(btnDeleteProd, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.weighty = 0.4;
		add(pnlProduct, gridBagConstraints);

		pnlInventory.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Inventory",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 1, 14))); // NOI18N
		pnlInventory.setLayout(new java.awt.GridBagLayout());

		lblInvenProdID.setText("Product ID");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(lblInvenProdID, gridBagConstraints);

		lblVendingMachineID.setText("Vending Machine ID");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(lblVendingMachineID, gridBagConstraints);

		lblInvenPrice.setText("Price");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(lblInvenPrice, gridBagConstraints);

		txtInvenProdID.setColumns(14);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(txtInvenProdID, gridBagConstraints);

		txtVendingMachineID.setColumns(14);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(txtVendingMachineID, gridBagConstraints);

		txtInvenPrice.setColumns(14);
		/*txtQuantity.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtQuantityActionPerformed(evt);
			}
		});*/
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(txtInvenPrice, gridBagConstraints);

		
		txtQuantity.setColumns(14);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(txtQuantity, gridBagConstraints);

		lblQuantity.setText("Quantity");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(lblQuantity, gridBagConstraints);

		btnSearchInventory.setText("Search");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 10;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(btnSearchInventory, gridBagConstraints);
		
		btnSearchInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnSearchInventoryActionPerformed(evt);
            }
        });

		btnAddInventory.setText("Add Inventory");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 10;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(btnAddInventory, gridBagConstraints);
		
		btnAddInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAddInventoryActionPerformed(evt);
            }
        });

		btnUpdateInventory.setText("Update Inventory");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 10;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(btnUpdateInventory, gridBagConstraints);
		
		btnUpdateInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnUpdateInventoryActionPerformed(evt);
            }
        });

		btnDeleteInventory.setText("Delete Inventory");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 10;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlInventory.add(btnDeleteInventory, gridBagConstraints);
		
		btnDeleteInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnDeleteInventoryActionPerformed(evt);
            }
        });


		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.weighty = 0.4;
		add(pnlInventory, gridBagConstraints);

		pnlNutriInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Nutritional Information",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 1, 14))); // NOI18N
		pnlNutriInfo.setAutoscrolls(true);
		pnlNutriInfo.setLayout(new java.awt.GridBagLayout());

		lblNutriProdID.setText("Product ID");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblNutriProdID, gridBagConstraints);

		txtNutriProdID.setColumns(10);
		txtNutriProdID.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtNutriProdIDActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtNutriProdID, gridBagConstraints);

		lblServingSize.setText("Serving Size");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblServingSize, gridBagConstraints);

		lblCalories.setText("Calories");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblCalories, gridBagConstraints);

		lblTotalFat.setText("Total Fat");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblTotalFat, gridBagConstraints);

		lblSaturatedFat.setText("Saturated Fat");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 11;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblSaturatedFat, gridBagConstraints);

		txtServingSize.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtServingSize, gridBagConstraints);

		txtCalories.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtCalories, gridBagConstraints);

		txtTotalFat.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtTotalFat, gridBagConstraints);

		txtSaturatedFat.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 11;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtSaturatedFat, gridBagConstraints);

		lblTransFat.setText("Trans Fat");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 8;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblTransFat, gridBagConstraints);

		lblCholestrol.setText("Cholestrol");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 8;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblCholestrol, gridBagConstraints);

		lblSodium.setText("Sodium");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 8;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblSodium, gridBagConstraints);

		lblTotalCarbs.setText("Total Carbs");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 8;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblTotalCarbs, gridBagConstraints);

		lblDietaryFiber.setText("Dietary Fiber");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 8;
		gridBagConstraints.gridy = 11;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblDietaryFiber, gridBagConstraints);

		txtTransFat.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 10;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtTransFat, gridBagConstraints);

		txtCholestrol.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 10;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtCholestrol, gridBagConstraints);

		txtSodium.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 10;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtSodium, gridBagConstraints);

		txtTotalCarbs.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 10;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtTotalCarbs, gridBagConstraints);

		txtDietaryFiber.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 10;
		gridBagConstraints.gridy = 11;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtDietaryFiber, gridBagConstraints);

		lblSugars.setText("Sugars");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 15;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblSugars, gridBagConstraints);

		lblProtein.setText("Protein");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 15;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblProtein, gridBagConstraints);

		lblIron.setText("Iron");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 15;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblIron, gridBagConstraints);

		lblSmartTag.setText("Smart Tag");
		lblSmartTag.setToolTipText("Please provide comma seperated values");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 15;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(lblSmartTag, gridBagConstraints);

		txtSugars.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 17;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtSugars, gridBagConstraints);

		txtProtein.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 17;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtProtein, gridBagConstraints);

		txtIron.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 17;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtIron, gridBagConstraints);

		txtSmartTag.setColumns(10);
		txtSmartTag.setToolTipText("Please provide comma seperated values");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 17;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(txtSmartTag, gridBagConstraints);

		btnSearchNutriInfo.setText("Search");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 15;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(btnSearchNutriInfo, gridBagConstraints);
		
		btnAddNutriInfo.setText("Add Nutritional Info");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 15;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(btnAddNutriInfo, gridBagConstraints);

		btnUpdateNutriInfo.setText("Update Nutritional Info");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 15;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(btnUpdateNutriInfo, gridBagConstraints);

		btnDeleteNutriInfo.setText("Delete Nutritional Info");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 15;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		pnlNutriInfo.add(btnDeleteNutriInfo, gridBagConstraints);
		
		//start - Nisha - 8/24
		//add action listeners for Nutri InFo operations
		nutriOpActionListener = new NutriInfoOperationsActionListener();
		btnSearchNutriInfo.addActionListener(nutriOpActionListener);
		btnAddNutriInfo.addActionListener(nutriOpActionListener);
		btnUpdateNutriInfo.addActionListener(nutriOpActionListener);
		btnDeleteNutriInfo.addActionListener(nutriOpActionListener);
		btnSearchNutriInfo.setActionCommand("SEARCH_NUTRI");
		btnAddNutriInfo.setActionCommand("ADD_NUTRI");
		btnUpdateNutriInfo.setActionCommand("UPDATE_NUTRI");
		btnDeleteNutriInfo.setActionCommand("DELETE_NUTRI");
		//end- nisha
		

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.weightx = 0.8;
		gridBagConstraints.weighty = 0.4;
		add(pnlNutriInfo, gridBagConstraints);
	}// </editor-fold>

	/**
	 * Generating Radio button dynamically
	 * 
	 * @param vmText
	 * @param vmIdActionCommand
	 */
	// code change-Aparna -8/22
	private void addToVendingMachineRadioGroupPanel(String vmText,
			String vmIdActionCommand) {
		JRadioButton vmRadioButton = new JRadioButton(vmText);
		vmRadioButton.setActionCommand(vmIdActionCommand);
		vmRadioButton.addActionListener(vmButtonActionListener);
		radioBtnGroup.add(vmRadioButton);
		pnlViewVM.add(vmRadioButton);

	}

	private void loadVendingMachinesRadioPanel() {
		List<VendingMachine> vendingMachines = vmController
				.getAllVendingMachines();
		vmIds = new ArrayList<>();
		for (VendingMachine vm : vendingMachines) {
			vmIds.add(vm.getVendingMachineId());
			String vmText = vm.getLocationType() + "@" + vm.getLocation() + "(" + vm.getVendingMachineId() + ")";
			addToVendingMachineRadioGroupPanel(vmText, vm.getVendingMachineId()
					+ "");
		}
	}

	private void btnSearchInventoryActionPerformed(java.awt.event.ActionEvent evt) {
		InventoryModel invProduct;
		//System.out.println(lblInvenProdID.getText());
		if(!txtInvenProdID.getText().isEmpty()){
			long prodId = Long.parseLong(txtInvenProdID.getText());
			invProduct = admin.searchInventory(prodId);
			txtInvenPrice.setText(Double.toString(invProduct.getProductPrice()));
			txtQuantity.setText(Integer.toString(invProduct.getqty()));
			txtVendingMachineID.setText(Long.toString(invProduct.getVendingMachineId()));
			txtInvenProdID.setEditable(false);
		}
		else
			JOptionPane.showMessageDialog(null, "Product Id Empty");
	}
	
	private void btnUpdateInventoryActionPerformed(java.awt.event.ActionEvent evt){
	
		long prodId = Long.parseLong(txtInvenProdID.getText());
		double price = Double.parseDouble(txtInvenPrice.getText());
		int vendMachId = Integer.parseInt(txtVendingMachineID.getText());
		int qty = Integer.parseInt(txtQuantity.getText());
		boolean status = admin.modifyInventory(prodId,price,vendMachId,qty);
		if(status)
		{
			JOptionPane.showMessageDialog(null, "Updated Successfully!");
			txtInvenProdID.setText("");
			txtInvenPrice.setText("");
			txtVendingMachineID.setText("");
			txtQuantity.setText("");
			txtInvenProdID.setEditable(true);
		}
		else
			JOptionPane.showMessageDialog(null, "Not Successful,Try again");
	}
	
	private void btnDeleteInventoryActionPerformed(java.awt.event.ActionEvent evt){
		if(!txtInvenProdID.getText().isEmpty()){
			long prodId = Long.parseLong(txtInvenProdID.getText());
			long vmId = Long.parseLong(txtVendingMachineID.getText());
			boolean status = admin.deleteInventory(prodId,vmId);
			if(status)
			{
				JOptionPane.showMessageDialog(null, "Deleted Successfully!");
				txtInvenProdID.setText("");
				txtInvenPrice.setText("");
				txtVendingMachineID.setText("");
				txtQuantity.setText("");
			}
			else
				JOptionPane.showMessageDialog(null, "Not Successful,Try again");
		}
	}
	
	private void btnAddInventoryActionPerformed(java.awt.event.ActionEvent evt){
		if((!txtInvenProdID.getText().isEmpty()) && (!txtInvenPrice.getText().isEmpty()) && (!txtVendingMachineID.getText().isEmpty()) && (!txtQuantity.getText().isEmpty())){
			int prodId = Integer.parseInt(txtInvenProdID.getText());
			double price = Double.parseDouble(txtInvenPrice.getText());
			int vendMachId = Integer.parseInt(txtVendingMachineID.getText());
			int qty = Integer.parseInt(txtQuantity.getText());
			boolean result = admin.addInventoryData(prodId,price,vendMachId,qty);
			if(result)
			{
				JOptionPane.showMessageDialog(null, "Inserted Successfully!");
				txtInvenProdID.setText("");
				txtInvenPrice.setText("");
				txtVendingMachineID.setText("");
				txtQuantity.setText("");
			}
			else
				JOptionPane.showMessageDialog(null, "Not Successful,Try again");
		}
	}
	// -----------------code change-Aparna -8/22

/*	private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}*/

	private void txtNutriProdIDActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	
	/**
	 * Loads best selling products for a given VMId
	 * 
	 * @param vmIds
	 */
	// code change-Aparna -8/22
	private void loadBestSellingPanel(final List<Long> vmIds) {
		StringBuilder bestSellingBuilder = new StringBuilder();
		bestSellingBuilder.append("<html>");
		for (Long vmId : vmIds) {
			List<Product> products;
			try {
				products = admin.getBestSellingProduct(vmId);
			} catch (AdminOperationsException e) {
				e.printStackTrace();
				return;
			}

			for (Product product : products) {
				bestSellingBuilder.append("<br>VM ID: " + vmId
						+ " Product ID :" + product.getProductID() + " Name: "
						+ product.getProductName() + "("
						+ product.getProdCategory() + ")");
			}

		}
		bestSellingBuilder.append("</html>");
		lblOtherStats.setText(bestSellingBuilder.toString());
		pnlOtherStats.revalidate();
	}

	class VMRadioButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Populate values for appropriate panels
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("ALL")) {
				// TODO populate values in appropriate panels for all vending
				// machines
				loadBestSellingPanel(vmIds);
				
				//start  - Nisha - 8/24
				//load Revenue Stats for all VM for logged in user
				revenueTableController.selectUserDisplayOption("ALL");	
				try {
					tblRevenue.setModel(revenueTableController.getModel().createAndFetchModelData());
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//end - Nisha

			} else {
				loadBestSellingPanel(Arrays.asList(new Long(Long
						.parseLong(actionCommand))));
				
				//nisha - 8/24
				//load Revenue Stats for particular VM for logged in user
				revenueTableController.selectUserDisplayOption(actionCommand);	
				try {
					
					tblRevenue.setModel(revenueTableController.getModel().createAndFetchModelData());
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}				
				//end - Nisha
			}		
		}
	}


	// -------------------------- code change-Aparna -8/22

	// code change-Aparna 08/23
	
	private void cleanPanelContents() {
		txtProductCategory.setText("");
		txtProductName.setText("");
		txtProductPrice.setText("");
		txtProductID.setText("");
		
	}
	/**
	 * Generic ActionListener class for all Admin button Action Listener related
	 * to products
	 */

	class ProductOperationsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("ADD_PRODUCT")) {
				Product product = null;

				if (validate()) {

					if (txtProductCategory.getText().equalsIgnoreCase(
							"BEVERAGE")) {
						product = new Beverage();
						product.setProdCategory(txtProductCategory.getText());
						product.setProductID(Long.parseLong(txtProductID
								.getText()));
						product.setProductName(txtProductName.getText());
						product.setProductPrice(Double
								.parseDouble(txtProductPrice.getText()));
					} else if (txtProductCategory.getText().equalsIgnoreCase(
							"SNACK")) {
						product = new Snack();
						product.setProdCategory(txtProductCategory.getText());
						product.setProductID(Long.parseLong(txtProductID
								.getText()));
						product.setProductName(txtProductName.getText());
						product.setProductPrice(Double
								.parseDouble(txtProductPrice.getText()));
					} else if (txtProductCategory.getText().equalsIgnoreCase(
							"CANDY")) {
						product = new Beverage();
						product.setProdCategory(txtProductCategory.getText());
						product.setProductID(Long.parseLong(txtProductID
								.getText()));
						product.setProductName(txtProductName.getText());
						product.setProductPrice(Double
								.parseDouble(txtProductPrice.getText()));
					} else {
						// do nothing
					}
					try {
						admin.addNewProduct(product);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(null,
							"Product Added successfully!");
					cleanPanelContents();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid entry");
				}

			}
	//TODO SEARCH PRODUCT BY ID
			if(actionCommand.equals("SEARCH_PRODUCT")) {
				if(!txtProductID.getText().isEmpty()) {
					long productId = Long.parseLong(txtProductID.getText());
					try {
						ProductModel productModel = admin.getProduct(productId);
						txtProductCategory.setText(productModel.getCategory().toString());
						txtProductName.setText(productModel.getProductName());
						txtProductPrice.setText(Double.toString(productModel.getProductPrice()));
						
					}
					catch (AdminOperationsException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage());
							return;
					}
					
					
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Invalid entry");
				}
			}
			
			if (actionCommand.equals("UPDATE_PRODUCT")) {
				Product product = null;
				if (validate()) {

					if (txtProductCategory.getText().equalsIgnoreCase("BEVERAGE")) {
						product = new Beverage();
						product.setProdCategory(txtProductCategory.getText());
						product.setProductID(Long.parseLong(txtProductID.getText()));
						product.setProductName(txtProductName.getText());
						product.setProductPrice(Double.parseDouble(txtProductPrice.getText()));
					} else if (txtProductCategory.getText().equalsIgnoreCase("SNACK")) {
						product = new Snack();
						product.setProdCategory(txtProductCategory.getText());
						product.setProductID(Long.parseLong(txtProductID.getText()));
						product.setProductName(txtProductName.getText());
						product.setProductPrice(Double.parseDouble(txtProductPrice.getText()));
					} else if (txtProductCategory.getText().equalsIgnoreCase("CANDY")) {
						product = new Beverage();
						product.setProdCategory(txtProductCategory.getText());
						product.setProductID(Long.parseLong(txtProductID
								.getText()));
						product.setProductName(txtProductName.getText());
						product.setProductPrice(Double
								.parseDouble(txtProductPrice.getText()));
					} else {
						// do nothing
					}
					try {
						admin.updateProduct(product, Long.parseLong(txtProductID.getText()));
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(null,
							"Product Updated successfully!");
					cleanPanelContents();
					txtProductID.setEditable(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "Invalid entry");
				}
			}

			if(actionCommand.equals("DELETE_PRODUCT")) {
				if(!txtProductID.getText().isEmpty()) {
					long productId = Long.parseLong(txtProductID.getText());
					try {
						admin.deleteProduct(productId);
					} catch (AdminOperationsException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Unable to delete product id "+productId);
							return;
					}
					JOptionPane.showMessageDialog(null, "Product Deleted !");
					txtProductID.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid entry");
				}
			}

		}

		private boolean validate() {
			if (txtProductCategory.getText().equals("")
					|| txtProductID.getText().equals("")
					|| txtProductPrice.getText().equals("")
					|| txtProductName.getText().equals("")) {
				return false;
			}
			return true;
		}
	
	}
	
	// start - Nisha - 8/24
	class NutriInfoOperationsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String actionCommand = e.getActionCommand();

			if(validateData()){

				if(actionCommand.equals("SEARCH_NUTRI")) {

					try {
						String allNutriValues = admin.searchNutriInfo(Long.parseLong(txtNutriProdID.getText())).formatData();
						String[] splitNutriValues = allNutriValues.trim().split(", ");
						for(String element : splitNutriValues){
							if(element.startsWith("Product")){
								int start = element.indexOf(":");
								txtNutriProdID.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Serving")){
								int start = element.indexOf(":");
								txtServingSize.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Calorie")){
								int start = element.indexOf(":");
								txtCalories.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Total Fat")){
								int start = element.indexOf(":");
								txtTotalFat.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Saturated Fat")){
								int start = element.indexOf(":");
								txtSaturatedFat.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Trans Fat")){
								int start = element.indexOf(":");
								txtTransFat.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Cholestrol")){
								int start = element.indexOf(":");
								txtCholestrol.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Sodium")){
								int start = element.indexOf(":");
								txtSodium.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Total Carbs")){
								int start = element.indexOf(":");
								txtTotalCarbs.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Dietary Fiber")){
								int start = element.indexOf(":");
								txtDietaryFiber.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Sugar")){
								int start = element.indexOf(":");
								txtSugars.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Protein")){
								int start = element.indexOf(":");
								txtProtein.setText(element.substring(start + 2, element.length()));
							}
							else if(element.startsWith("Iron")){
								int start = element.indexOf(":");
								txtIron.setText(element.substring(start + 2, element.length()));
							}							
							else if(element.startsWith("Smart Tag")){
								int start = element.indexOf(":");
								txtSmartTag.setText(element.substring(start + 2, element.length()));
							}

						}

					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

				if (actionCommand.equals("ADD_NUTRI")) {
					NutritionalInfoModel nutriModel = new NutritionalInfoModel.NutriBuilder(
							Long.parseLong(txtNutriProdID.getText()),
							txtCalories.getText(), txtSmartTag.getText())
							.servingSize(txtServingSize.getText())
							.totalFat(txtTotalFat.getText())
							.saturatedFat(txtSaturatedFat.getText())
							.transFat(txtTransFat.getText())
							.cholestrol(txtCholestrol.getText())
							.sodium(txtSodium.getText())
							.totalCarbs(txtTotalCarbs.getText())
							.dietaryFiber(txtDietaryFiber.getText())
							.sugars(txtSugars.getText())
							.protein(txtProtein.getText())
							.iron(txtIron.getText()).buildNutriInfo();

					try {
						boolean addStatus = admin.addNewNutriInfo(nutriModel
								.listAttributeValues());
						if (addStatus == true) {
							JOptionPane.showMessageDialog(null,
									"Nutritional Info Added!");
							this.clearData();
						} 

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,
								"Unable to add nutritional info for "
										+ txtNutriProdID.getText() + "\n" + e1.getMessage());
					}
				}
				
				if (actionCommand.equals("DELETE_NUTRI")) {

					try {
						boolean addStatus = admin.deleteNutriInfo(Long
								.parseLong(txtNutriProdID.getText()));
						if (addStatus == true) {
							JOptionPane.showMessageDialog(null,
									"Nutritional Info deleted!");
							this.clearData();
						}
						

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,
								"Unable to delete nutritional info for "
										+ txtNutriProdID.getText() + "\n" + e1.getMessage());
						
					}
				}
				
				if(actionCommand.equals("UPDATE_NUTRI")){
					NutritionalInfoModel nutriModel = new NutritionalInfoModel.
							NutriBuilder(Long.parseLong(txtNutriProdID.getText()),txtCalories.getText(), txtSmartTag.getText())
							.servingSize(txtServingSize.getText())
							.totalFat(txtTotalFat.getText())
							.saturatedFat(txtSaturatedFat.getText())
							.transFat(txtTransFat.getText())
							.cholestrol(txtCholestrol.getText())
							.sodium(txtSodium.getText())
							.totalCarbs(txtTotalCarbs.getText())
							.dietaryFiber(txtDietaryFiber.getText())
							.sugars(txtSugars.getText())
							.protein(txtProtein.getText())
							.iron(txtIron.getText()).buildNutriInfo();

					try {
						boolean addStatus = admin.updateNewNutriInfo(nutriModel.listAttributeValues());
						if (addStatus == true) {
							JOptionPane.showMessageDialog(null,
									"Nutritional Info updated!");
							this.clearData();
						} 

					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Unable to update nutritional info for "
										+ txtNutriProdID.getText() + "\n" + e1.getMessage());
					}
				}
			}


		}

		private boolean validateData(){
			if(txtNutriProdID.getText().equals("")){
				txtNutriProdID.setBackground(Color.YELLOW);
				txtNutriProdID.setToolTipText("Enter ProductID");
				return false;
			}
			
			return true;
		}
		private void clearData(){
			txtNutriProdID.setText("");
			txtServingSize.setText("");
			txtCalories.setText("");
			txtTotalFat.setText("");
			txtSaturatedFat.setText("");
			txtTransFat.setText("");
			txtCholestrol.setText("");
			txtSodium.setText("");
			txtTotalCarbs.setText("");
			txtDietaryFiber.setText("");
			txtSugars.setText("");
			txtProtein.setText("");
			txtIron.setText("");
			txtSmartTag.setText("");
		}
	}

	//end - nisha - 8/24
}

