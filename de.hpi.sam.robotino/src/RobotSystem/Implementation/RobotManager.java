package RobotSystem.Implementation;

import java.util.Date;

import Datatypes.Added.StateType;
import RobotSystem.Interfaces.New.IRobotCommunication;
import RobotSystem.Interfaces.New.IRobotExecute;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.cpslab.robotino.sensor.interfaces.IBattery;
import de.cpslab.robotino.sensor.interfaces.INorthStar;
import de.hpi.sam.warehouse.order.Order;

public class RobotManager implements IRobotCommunication, IRobotExecute {

	private int BATTERYTHRESHOLD;
	private RobotinoID id;
	private StateType.robot status;
	private Order currentOrder;
	private Position curentPos;
	
	private void updateLoop() {
	
	}
	
	private void handleMessage(Message message) {
	
	}
	
	private StateType.robot getStatus() {
	
		return null;
	}
	
	private void setStatus(StateType.robot status) {
	
	}

	@Override
	public void orderStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean orderDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date calculateOrderTime(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void explorationStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean explorationDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasUnexploredRooms() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void chargingStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean chargingDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBumped() {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public void sendRobotStatus(StateType.robot status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendOrderTime(Date duration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPosition(Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendOrderFinish(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getWorkload() {
		// TODO Auto-generated method stub
		return 0;
	}
}