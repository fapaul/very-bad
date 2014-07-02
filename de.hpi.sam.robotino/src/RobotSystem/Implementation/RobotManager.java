import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public class RobotManager extends RobotStatus implements IRobotCommunication, IBattery, INorthStar, IRobotExecute {

	private int BATTERYTHRESHOLD;
	private RobotinoID id;
	private RobotStatus status;
	private Order currentOrder;
	private Position curentPos;
	
	private void updateLoop() {
	
	}
	
	private void handleMessage(Message message) {
	
	}
	
	private RobotStatus getStatus() {
	
		return void;
	}
	
	private void setStatus(RobotStatus status) {
	
	}
}