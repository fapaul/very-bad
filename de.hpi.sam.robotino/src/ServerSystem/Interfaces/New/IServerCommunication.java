package ServerSystem.Interfaces.New;

import java.util.Date;
import java.util.List;

import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.order.Order;

public interface IServerCommunication {

	StateType.robot requestRobotStatus(RobotinoID robot);
	
	Date requestOrderTime(Order order, RobotinoID robot);
	
	Position requestPosition(RobotinoID robot);
	
	void sendSleep(RobotinoID robot);
	
	void sendWakeup(RobotinoID robot);
	
	void sendOrderStart(RobotinoID robot);
	
	boolean hasMessage();
	
	boolean hasMessage(StateType.message type);
	
	StatusMessage readMessage();
	
	StatusMessage readMessage(StateType.message type);
}