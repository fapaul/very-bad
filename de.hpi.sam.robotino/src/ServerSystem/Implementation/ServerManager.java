package ServerSystem.Implementation;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.OrderManagement;

public class ServerManager extends Thread {

	OrderManagement 	orderManager;
	ServerCommunication serverComm;
	Position robotPos;
	StateType.robot robotState;
	Date orderTime;
	// Maximum number of messages and orders worked at at once
	int MAX_MESSAGE_ONCE = 20;
	int MAX_ORDER_ONCE = 20;
	float energyConsumption;
	List<RobotinoID> robots;
	Set<Order> pendingOrders;
	Set<Order> inprogressOrders;
	
	boolean running = false;
	
	public ServerManager() {
		pendingOrders = new HashSet<Order>();
		inprogressOrders = new HashSet<Order>();
		startServer();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void startServer() {
		running = true;
		this.start();
	}
	
	public void stopServer() {
		// Let the server finish the last action
		running = false;
	}

	void handleMessage(Message message) {
		StatusMessage servMess = (StatusMessage) message;
		
		switch (servMess.getTypeOfMessage()) {
		case ROBOT_FINISH:
			//finalize order progressing
			Order curOrder = (Order)servMess.getContent();
			inprogressOrders.remove(curOrder);
			break;

		case ROBOT_CHARGING:
//			//TODO perhaps remove ROBOT_CHARGING
			break;
		case SERVER_WAKEUP:
			startServer();
			break;
		case SERVER_SLEEP:
			break;
		default:
			break;
		}
	}
	
	private RobotinoID chooseRobot(Order order) {
		Map<RobotinoID, Date> times = new HashMap<RobotinoID, Date>();
		
		for (RobotinoID robot : robots) {
			// Might lead to infinite waiting
			// TODO Fix this
			times.put(robot, serverComm.requestOrderTime(order, robot));
		}
		
		// Get robot with best time
		RobotinoID best = null;
		for(Entry<RobotinoID, Date> entry : times.entrySet()) {
			if(best == null)
				best = entry.getKey();
			else
				if(entry.getValue().before(times.get(best)))
					best = entry.getKey();
		}
		return best;
	}
	
	void fetchOrders() {
		// If a order is not in progress it must be pending
		for(Order order : orderManager.getOrderList()) {
			if(!inprogressOrders.contains(order))
				pendingOrders.add(order);
		}
	}

	void updateLoop() {
		 // Check all messages
		for (int i = 0; i < MAX_MESSAGE_ONCE && serverComm.hasMessage(); i++) {
			handleMessage(serverComm.readMessage());
		}
		fetchOrders();
		
		// Send out an order
		for(int i = 0; i < MAX_ORDER_ONCE && !pendingOrders.isEmpty(); i++) {
			Order order = pendingOrders.iterator().next();
			serverComm.sendOrderStart(chooseRobot(order));
			inprogressOrders.add(order);
			pendingOrders.remove(order);
		}
	}
	
	/*
	 * Uses the function updateLoop 
	 *  (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// Calls the update loop and idles the thread for a while
		
		while(running) {
			updateLoop();
			
			try
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e)
			{
				System.out.println("Error while ServerManger was sleeping");
				running = false;
			}
		}
		
	}
}