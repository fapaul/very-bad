import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import RobotSystem.Implementation.ExplorationManager;
import RobotSystem.Implementation.RobotCommunication;
import RobotSystem.Implementation.RobotManager;
import de.hpi.sam.warehouse.Server;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class RobotBehavior implements Runnable
{
	private WarehouseRobot	robot;
	private ExplorationManager explMang;
	
	public RobotBehavior(WarehouseRobot robot)
	{
		this.robot = robot;
		
		RobotCommunication robotCom = new RobotCommunication(this.robot);
		robotCom.sendRobotRegistration(robot.getID());
		explMang = new ExplorationManager(robot, new WarehouseRepresentation());
	}

	@Override
	public void run()
	{
		while (!this.robot.isBumperActivated())
		{
		//	System.out.println("Sender ID: " + robot.getCommunicationID().getID() + ", Server ID: " + Server.serverID.getID());	
		//	System.out.println("Sending ...");
			explMang.explorationStart();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				this.robot.brake();
			}
		}
		
		this.robot.brake();
		System.out.println(this.robot.getID().getName() + " bumped.");
	}
}
