package ServerSystem.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import RobotSystem.Implementation.OrderManager;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.CommunicationID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.Server;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.OrderManagement;

public class ServerManager extends Thread {

	OrderManagement 	orderManagemet;
	ServerCommunication serverComm;
	Position robotPos;
	StateType.robot robotState;
	Date orderTime;
	Server server;
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
		serverComm = new ServerCommunication();
		orderManagemet = new OrderManagement(); 
		server = Server.INSTANCE;
		robots = new ArrayList<RobotinoID>(); 
		
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
			System.out.println("Order from " + curOrder.getDate() + " finished");
			break;
		case ROBOT_REGISTER:
			RobotinoID robot = (RobotinoID) servMess.getContent();
			System.out.println("Registered Robot " + robot.getID() + " at server");
			robots.add(robot);
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
		for(Order order : orderManagemet.getOrderList()) {
			if(!inprogressOrders.contains(order))
				pendingOrders.add(order);
		}
	}

	void updateLoop() {
		 // Check all messages
		//System.out.println("there are any new Messages " + (serverComm.hasMessage()));
		for (int i = 0; i < MAX_MESSAGE_ONCE && serverComm.hasMessage(); i++) {
			handleMessage(serverComm.readMessage());
		}
		fetchOrders();
		
		// Send out an order
		for(int i = 0; i < MAX_ORDER_ONCE && !pendingOrders.isEmpty(); i++) {
			Order order = pendingOrders.iterator().next();
			System.out.println("ServerManager: Sending order " + order.getDate() + " to robot");
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