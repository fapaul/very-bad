
package RobotSystem.Implementation;

import java.util.Date;

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
	private int BATTERYTHRESHOLD;
	private Order currentOrder;
	private OrderManager orderManager;
	private ExplorationManager exploreMang;
	private RobotCommunication robComm;
	private WarehouseRobot wareRobot;
	private WarehouseRepresentation repre;
	private StateType.robot status;
	private final int MAX_MESSAGE_ONCE = 20;
	
	private boolean running = false;
	
	public RobotManager(WarehouseRobot warehouseRobot){
		status = robot.IDLE;
		wareRobot = warehouseRobot;
		repre = new WarehouseRepresentation();
		orderManager = new OrderManager(warehouseRobot, repre);
		exploreMang = new ExplorationManager(wareRobot, repre);
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void startRobot(){
		running = true;
		this.start();
	}
	
	private void updateLoop() {
		if(!robComm.hasMessage()) {
			// If nothing to do start the exploration
			exploreMang.explorationStart();
		}
		else
			for (int i = 0; i < MAX_MESSAGE_ONCE && robComm.hasMessage();i++){
				System.out.println("got message");
				handleMessage(robComm.readMessage());
			}
		
	}
	
	private void handleMessage(Message message) {
		StatusMessage robMess = (StatusMessage) message;
		
		switch (robMess.getTypeOfMessage()){
		case SERVER_ORDERTIME:
			Order order = (Order)robMess.getContent();
			currentOrder = order;
			Date duration = orderManager.calculateOrderTime(order);
			robComm.sendOrderTime(duration);
			break;
		case SERVER_POSITION:
			Position position = wareRobot.getCurrentPosition();
			robComm.sendPosition(position);
			break;
		case SERVER_SLEEP:
			this.status = robot.SLEEPING;
			setStatus(robot.SLEEPING);
			break;
		case SERVER_ORDER:
			this.status = robot.EXECUTING;
			orderManager.orderStart(currentOrder);
			this.status = robot.IDLE;
			break;
		case SERVER_WAKEUP:
			setStatus(robot.IDLE);
			startRobot();
			break;
		default:
			break;
			
		}
	}
	
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
				System.out.println("Error while Robot " + wareRobot.getID() + " was waiting");
				running = false;
			}
		}
		
	}
	
	private StateType.robot getStatus() {
		return this.status;
	}
	
	private void setStatus(StateType.robot status) {
		this.status = status;
	}



}
