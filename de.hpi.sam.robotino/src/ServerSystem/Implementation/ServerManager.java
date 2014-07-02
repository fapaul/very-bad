package ServerSystem.Implementation;

import java.util.Date;
import java.util.List;

import Datatypes.Added.RobotStatusType;
import ServerSystem.Interfaces.New.IServerCommunication;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.interfaces.IOrderManagement;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.OrderManagement;
import de.hpi.sam.warehouse.stock.CartPosition;
import de.cpslab.robotino.*;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;

public class ServerManager implements Runnable {

	OrderManagement 	orderManager;
	ServerCommunication serverComm;
	
	float energyConsumption;
	RobotinoID[] robots;
	Order[] pendingOrders;
	Order[] inprogressOrders;
	
	
	
	void handleMessage(Message message) {
	
	}
	
	RobotinoID chooseRobot(Order order) {
	
		return null;
	}

	void updateLoop() {
		 
	}
	
	/*
	 * Uses the function updateLoop 
	 *  (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}