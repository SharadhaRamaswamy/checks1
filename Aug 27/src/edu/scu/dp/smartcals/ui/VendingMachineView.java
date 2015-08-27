package edu.scu.dp.smartcals.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.scu.dp.smartcals.vm.VMController;
import edu.scu.dp.smartcals.vm.VendingMachine;

/**
 * @author Nisha Narayanaswamy 
 * 
 * VendingMachineView class collates the views for ProdCategory, ProdListing and DetailsView classes
 */
public class VendingMachineView extends JPanel {
	
	private VMController vmController;
	
	//code change - Aparna
	// Holds the vending machine object for this vending machine view 
	private VendingMachine vendingMachine;
	private VMProdCategory vmProdCategory;
	
	private VMDetails_View vmDetailsView;
	private GridBagLayout gridLayout;
	private GridBagConstraints gridConstraints;
	
	
	public VendingMachineView(VMController vmController){
		this.vmController = vmController;
		gridLayout = new GridBagLayout();
		gridConstraints = new GridBagConstraints();
		
		//test code-Aparna 08/25
		gridConstraints.gridwidth = 2;
		gridConstraints.weightx = 1.0;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		//----------------------------------
		
		//code change in Aparna
		vmProdCategory = new VMProdCategory(this);	
		vmDetailsView = new VMDetails_View(this);
		addVMSubPanels();		
	}

	/**
	 * add all 3 inner panels ProdCategory, ProdListing and DetailsView  
	 */
	public void addVMSubPanels() {	
	
		this.setLayout(gridLayout);		
		
		//add Product category selection
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.weightx = 0.2;
		gridConstraints.fill = GridBagConstraints.BOTH;
		this.add(vmProdCategory, gridConstraints);
		

		/*//add Product selection
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		gridConstraints.weightx = 0.2;
		gridConstraints.weighty = 1;
		gridConstraints.fill = GridBagConstraints.BOTH;
		sectionProdSelect.setBackground(Color.PINK);
		pnlContainer.add(sectionProdSelect, gridConstraints);  //** placeholder code */

		//add display panel in bottom
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 2;	
		gridConstraints.weightx = 0.2;
		gridConstraints.weighty = 1;
		gridConstraints.fill = GridBagConstraints.BOTH;
		this.add(vmDetailsView, gridConstraints);
		
	}

	/**
	 * Get VendingMachine object
	 * @return
	 */
	public VendingMachine getVendingMachine() {
		return vendingMachine;
	}

	public void setVendingMachine(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}
	
	public VMController getVMController() {
		return vmController;
	}
	
	//Sharadha
	public VMDetails_View getVMDetails_View(){
		return vmDetailsView;
	}

	public VMProdCategory getVMProdCategory(){
		return vmProdCategory;
	}
}
