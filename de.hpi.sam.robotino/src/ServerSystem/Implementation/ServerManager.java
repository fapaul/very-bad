package ServerSystem.Implementation;

import java.util.Date;
import java.util.List;

import Datatypes.Added.RobotStatusType;
import ServerSystem.Interfaces.New.IServerCommunication;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.interfaces.IOrderManagement;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.CartPosition;
import de.cpslab.robotino.*;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;

public class ServerManager implements IServerCommunication, IOrderManagement{

	float energyConsumption;
	RobotinoID[] robots;
	Order[] pendingOrders;
	Order[] inprogressOrders;
	
	void updateLoop() {
	
	}
	
	void handleMessage(Message message) {
	
	}
	
	RobotinoID chooseRobot(Order order) {
	
		return null;
	}

	@Override
	public List<Order> getOrderList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean finishOrder(Order order, CartPosition cartPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RobotStatusType requestRobotStatus(RobotinoID robot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date requestOrderTime(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position requestPosition(RobotinoID root) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendSleep(RobotinoID robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendWakeup(RobotinoID robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendOrderStart(RobotinoID robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Message readMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}