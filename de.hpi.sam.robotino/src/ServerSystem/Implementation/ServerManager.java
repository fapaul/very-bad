import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public class ServerManager implements IServerCommunication, IOrderManagement{

	float energyConsumption;
	RobotinoID[] robots;
	Order[] pendingOrders;
	Order[] inprogressOrders;
	
	void updateLoop() {
	
	}
	
	void handleMessage(Message message) {
	
	}
	
	RobotinoID chooseRobot(Order order) {
	
		return null;
	}
	
	

}