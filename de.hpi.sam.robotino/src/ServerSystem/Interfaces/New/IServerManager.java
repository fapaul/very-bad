import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public interface IServerManager  {

	RobotinoID[] getRobots();
	
	void removeRobot(RobotinoID robot);
	
	void getRobotStatus(RobotinoID robot);
	
	Position getRobotPosition(RobotinoID robot);
	
	void addRobot(RobotinoID robot);
	
	void setEnergyConsumption(float energyConsumption);
	
	void startup();
	
	void shutdown();
	
	void addOrder(Order order);
	
	void removeOrder(Order order);
	
	void getOrderAmount();

}