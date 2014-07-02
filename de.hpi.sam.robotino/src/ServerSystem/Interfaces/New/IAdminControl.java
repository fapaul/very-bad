import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public interface IAdminControl {

	void startup();
	
	void stutdown();
	
	float setEnergyConsumption();
	
	void addRobot(RobotinoID robot);
	
	void removeRobot(RobotinoID robot);
	
	RobotinoID[] getRobots();
	
	RobotinoID[] getRobots(RobotStatus status);
	
	RobotStatus getRobotStatus(RobotinoID robot);
	
	Position getRobotPosition(RootinoID robot);

}