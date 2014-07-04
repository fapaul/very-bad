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
import java.util.concurrent.atomic.AtomicBoolean;

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

	private OrderManagement orderManagemet = new OrderManagement();
	private ServerCommunication serverComm = new ServerCommunication();
	private float energyConsumption = 1.0f;
	private List<RobotinoID> robots = new ArrayList<RobotinoID>();
	private Set<Order> pendingOrders = new HashSet<Order>();
	private Set<Order> inprogressOrders = new HashSet<Order>();
	private AtomicBoolean running = new AtomicBoolean(false);
	private Server server;
	
	public ServerManager() {
		startServer();
	}
	
	public boolean isRunning() {
		return running.get();
	}
	
	public void startServer() {
		running.set(true);
		start();
	}
	
	public void stopServer() {
		// Let the server finish the last action
		// ...
		
		running.set(false);
	}

	void handleMessage(StatusMessage message) {
		switch (message.getTypeOfMessage()) {
		case ROBOT_REGISTER:
			RobotinoID robot = (RobotinoID)message.getContent();
			robots.add(robot);
			System.out.println("Registered " + robot.getName() + ".");
			break;
		case ROBOT_FINISH:
			Order order = (Order)message.getContent();
			inprogressOrders.remove(order);
			orderManagemet.finishOrder(order, order.getCartArea().getCartPositions().get(0)); // Let robot send final cart position instead
			System.out.println("Finished an order.");
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
		// Request order times
		Map<RobotinoID, Date> orderTimes = new HashMap<RobotinoID, Date>();
		for (RobotinoID robot : robots) {
			StateType.robot status = serverComm.requestRobotStatus(robot);
			if (status == StateType.robot.IDLE || status == StateType.robot.EXPLORING)
				orderTimes.put(robot, serverComm.requestOrderTime(order, robot));
		}
		
		// Get robot with best time
		RobotinoID best = null;
		for (Entry<RobotinoID, Date> orderTime : orderTimes.entrySet()) {
			if (best == null)
				best = orderTime.getKey();
			else if (orderTime.getValue().before(orderTimes.get(best)))
					best = orderTime.getKey();
		}
		return best;
	}
	
	void fetchOrders() {
		// Add messages that we don't track already
		for (Order order : orderManagemet.getOrderList())
			if (!inprogressOrders.contains(order) && !pendingOrders.contains(order))
				pendingOrders.add(order);
	}

	void updateLoop() {
		// Read message
		if (serverComm.hasMessage())
			handleMessage(serverComm.readMessage());
		
		// Fetch orders from order management
		fetchOrders();
		
		// Send out an order
		if (!pendingOrders.isEmpty()) {
			Order order = pendingOrders.iterator().next();
			RobotinoID robot = chooseRobot(order);
			if (robot != null) {
				System.out.println("Sent out an order.");
				serverComm.sendOrderStart(robot);
				inprogressOrders.add(order);
				pendingOrders.remove(order);
			}
		}
	}
	
	@Override
	public void run() {
		while(running.get()) {
			updateLoop();
			try {
				Thread.sleep(100); // Calculate sleep time from energy consumption
			}
			catch (InterruptedException e) {
				System.out.println("Error while server thread was sleeping.");
				running.set(false);
			}
		}
		
	}
}
