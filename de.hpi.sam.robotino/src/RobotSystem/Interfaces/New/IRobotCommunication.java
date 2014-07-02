import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public interface IRobotCommunication {

	boolean hasMessage();
	
	Message readMessage(); //check Message-Type if ok or replace
	
	void sendRobotStatus(RobotStatus status);
	
	void sendOrderTime(Date duration);
	
	void sendPosition(Position position);
	
	void sendOrderFinish(Order order);
	
	float getWorkload();

}