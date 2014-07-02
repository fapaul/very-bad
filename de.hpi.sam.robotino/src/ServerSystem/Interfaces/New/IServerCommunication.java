import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public interface IServerCommunication {

	RobotStatus requestRobotStatus(RobotinoID robot);
	
	Date requestOrderTime(Order order);
	
	Position requestPosition(RobotinoID root);
	
	void sendSleep(RobotinoID robot);
	
	void sendWakeup(RobotinoID robot);
	
	void sendOrderStart(RobotinoID robot);
	
	boolean hasMessage();
	
	Message readMessage();	//check Message

}