
package RobotSystem.Implementation;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import Datatypes.Added.StateType;
import Datatypes.Added.StateType.robot;
import Datatypes.Added.StatusMessage;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.CommunicationID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;


public class RobotManager extends Thread {
	
	@SuppressWarnings("unused")
	private int BATTERYTHRESHOLD = 10;
	
	private Order currentOrder;
	private OrderManager orderManager;
	private ExplorationManager exploreManger;
	private RobotCommunication robComm;
	private WarehouseRobot wareRobot;
	private WarehouseRepresentation repre = new WarehouseRepresentation();
	private StateType.robot status = robot.IDLE;
	private AtomicBoolean running = new AtomicBoolean(false);
	
	public RobotManager(WarehouseRobot warehouseRobot) {
		wareRobot = warehouseRobot;
		robComm = new RobotCommunication(wareRobot);
		orderManager = new OrderManager(wareRobot, repre);
		exploreManger = new ExplorationManager(wareRobot, repre);
	}
	
	public boolean isRunning() {
		return running.get();
	}
	
	public void startRobot() {
		running.set(true);
		this.start();
	}
	
	private void updateLoop() {
		// If WLAN is available we can share informaitons
		// for(CommunicationID other : wareRobot.scanForCommunicationPartnerInRange()) {
		//     wareRobot.requestAndMergeExplorationInfo(wareRobot.getRoomFor(wareRobot.getCurrentPosition()), new RobotinoID(other.));
		// }
		
		// If sleeping only care about wakeup messages
		// ...
		
		if (robComm.hasMessage())
			handleMessage(robComm.readMessage());
		else
			exploreManger.explorationStart();
		
		// If finished order, notify server and set status to explore or idle
		// ...
		
		// Would be better to not break out of update loop completely
		if (wareRobot.isBumperActivated())
			running.set(false);
	}
	
	private void handleMessage(StatusMessage message) {
		switch (message.getTypeOfMessage()) {
		case SERVER_STATUS:
			robComm.sendRobotStatus(getStatus());
			break;
		case SERVER_ORDERTIME:
			currentOrder = (Order)message.getContent();
			Date duration = orderManager.calculateOrderTime(currentOrder);
			robComm.sendOrderTime(duration);
			break;
		case SERVER_ORDER:
			setStatus(robot.EXECUTING);
			orderManager.orderStart(currentOrder);
			break;
		case SERVER_SLEEP:
			setStatus(robot.SLEEPING);
			break;
		case SERVER_WAKEUP:
			setStatus(robot.IDLE);
			startRobot();
			break;
		case SERVER_POSITION:
			Position position = wareRobot.getCurrentPosition();
			robComm.sendPosition(position);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void run() {
		while (running.get()) {
			updateLoop();
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				System.out.println("Error while robot thread was sleeping.");
				running.set(false);
			}
		}
		
		wareRobot.brake();
		System.out.println("Robot bumped.");
	}
	
	private StateType.robot getStatus() {
		return this.status;
	}
	
	private void setStatus(StateType.robot status) {
		this.status = status;
	}
}
