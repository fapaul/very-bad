package RobotSystem.Interfaces.New;

import java.util.Date;

import Datatypes.Added.RobotStatusType;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.cpslab.robotino.*;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;

public interface IRobotCommunication {

	boolean hasMessage();
	
	Message readMessage(); //check Message-Type if ok or replace
	
	void sendRobotStatus(RobotStatusType status);
	
	void sendOrderTime(Date duration);
	
	void sendPosition(Position position);
	
	void sendOrderFinish(Order order);
	
	float getWorkload();

}