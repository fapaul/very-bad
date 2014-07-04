import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import RobotSystem.Implementation.RobotCommunication;
import RobotSystem.Implementation.RobotManager;
import de.hpi.sam.warehouse.Server;
import de.hpi.sam.warehouse.WarehouseRobot;


public class RobotBehavior implements Runnable
{
	WarehouseRobot robot;
	RobotManager robotManager;

	public RobotBehavior(WarehouseRobot robot)
	{
		this.robot = robot;
		robotManager = new RobotManager(robot);
	}

	@Override
	public void run()
	{
		robotManager.startRobot();
	}
}
