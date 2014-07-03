
package RobotSystem.Implementation;

import java.util.Date;

import Datatypes.Added.StateType;
import Datatypes.Added.StateType.robot;
import Datatypes.Added.StatusMessage;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;


public class RobotManager extends Thread{


	private int BATTERYTHRESHOLD;
	private RobotinoID id;
	private Order currentOrder;
	private Position curentPos;
	private OrderManager orderManager;
	private ExplorationManager explorationManager;
	private	ChargingManager chargingManager;
	private RobotCommunication robComm;
	private WarehouseRobot wareRobot;
	private WarehouseRepresentation resp;
	private StateType.robot status;
	int MAX_MESSAGE_ONCE = 20;
	int MAX_ORDER_ONCE = 20;
	
	boolean running = false;
	
	public RobotManager(WarehouseRobot warehouseRobot){
		status = robot.IDLE;
		wareRobot = warehouseRobot;
		resp = new WarehouseRepresentation();
		
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void startRobot(){
		running = true;
		this.start();
	}
	
	private void updateLoop() {
		
		for (int i = 0; i < MAX_MESSAGE_ONCE && robComm.hasMessage();i++){
			handleMessage(robComm.readMessage());
		}
		
	}
	
	private void handleMessage(Message message) {
		StatusMessage robMess = (StatusMessage) message;
		
		switch (robMess.getTypeOfMessage()){
		case SERVER_ORDERTIME:
			Order order = (Order)robMess.getContent();
			Date duration = orderManager.calculateOrderTime(order);
			robComm.sendOrderTime(duration);
			break;
		
		case SERVER_POSITION:
			Position position = wareRobot.getCurrentPosition();
			robComm.sendPosition(position);
			break;
		
		case SERVER_SLEEP:
			setStatus(robot.SLEEPING);
			break;
		
		case SERVER_ORDER:
			orderManager.orderStart();
			break;
		
		case SERVER_WAKEUP:
			setStatus(robot.IDLE);
			startRobot();
			break;
			
		default:
			break;
			
		}
	}
	
	private StateType.robot getStatus() {
		return this.status;
	}
	
	private void setStatus(StateType.robot status) {
		this.status = status;
	}



}
