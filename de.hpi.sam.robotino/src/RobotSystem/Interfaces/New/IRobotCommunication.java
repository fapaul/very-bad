package RobotSystem.Interfaces.New;

import java.util.Date;
import java.util.List;

import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.order.Order;

public interface IRobotCommunication {

	boolean hasMessage();
	
	StatusMessage readMessage();
	
	void sendRobotStatus(StateType.robot status);
	
	void sendOrderTime(Date duration);
	
	void sendPosition(Position position);
	
	void sendOrderFinish(Order order);
	
	float getWorkload();
}
