package ServerSystem.Interfaces.New;

import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

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