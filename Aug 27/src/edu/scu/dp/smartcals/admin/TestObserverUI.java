/**
 * 
 */
package edu.scu.dp.smartcals.admin;

import javax.swing.JFrame;

import edu.scu.dp.smartcals.constants.VMLocationType;
import edu.scu.dp.smartcals.constants.VMStatus;
import edu.scu.dp.smartcals.model.VendingMachineModel;
import edu.scu.dp.smartcals.ui.MonitoringStationView;
import edu.scu.dp.smartcals.vm.VendingMachine;
import edu.scu.dp.smartcals.vm.VendingMachineFactory;

/**
 * @author Aparna Ganesh
 *
 */
public class TestObserverUI extends JFrame{

	
	public static void main(String args[]) {
		TestObserverUI frame = new TestObserverUI();
		MonitoringStationView view = new MonitoringStationView(null);
		
		VendingMachineModel vmModel = new VendingMachineModel();
		
		vmModel.setType(VMLocationType.SCHOOL);
		
		vmModel.setLocation("Sunnyvale");
		
		vmModel.setVendingMachineId(1000);

		vmModel.setStatus(VMStatus.ACTIVE);
		
		VendingMachineFactory vendingMachineFactory = VendingMachineFactory.getFactory(vmModel.getType());

		VendingMachine vendingMachine = vendingMachineFactory.createVendingMachine(vmModel);
		
		//Add in Admin controller------------------------------
		AdminOperationsImpl admin = AdminOperationsImpl.getInstance();
		admin.addAlertListeners(view);
		//------------------------------------------------------
		
		//After Buy module, if qty is 0, do below in VMController
		vendingMachine.addListener(admin);
		
		frame.setContentPane(view);
		frame.pack();
		frame.setVisible(true);
		
		vendingMachine.notifyOutOfStock(100, vmModel.getVendingMachineId());
		
	}
}
