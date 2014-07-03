package RobotSystem.Interfaces.New;

import java.util.Date;

import Datatypes.Added.StateType;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.order.Order;

public interface IRobotCommunication {

	boolean hasMessage();
	
	Message readMessage(); //check Message-Type if ok or replace
	
	void sendRobotStatus(StateType.robot status);
	
	void sendOrderTime(Date duration);
	
	void sendPosition(Position position);
	
	void sendOrderFinish(Order order);
	
	float getWorkload();
	
	void requestForRobots();

}