package ServerSystem.Interfaces.New;

import java.util.Date;

import Datatypes.Added.RobotStatusType;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.cpslab.robotino.*;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;

public interface IServerCommunication {

	RobotStatusType requestRobotStatus(RobotinoID robot);
	
	Date requestOrderTime(Order order);
	
	Position requestPosition(RobotinoID root);
	
	void sendSleep(RobotinoID robot);
	
	void sendWakeup(RobotinoID robot);
	
	void sendOrderStart(RobotinoID robot);
	
	boolean hasMessage();
	
	Message readMessage();	//check Message

}