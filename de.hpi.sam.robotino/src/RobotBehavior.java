import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import RobotSystem.Implementation.RobotCommunication;
import de.hpi.sam.warehouse.Server;
import de.hpi.sam.warehouse.WarehouseRobot;

public class RobotBehavior implements Runnable
{
	WarehouseRobot	robot;

	public RobotBehavior(WarehouseRobot robot)
	{
		this.robot = robot;
		
		RobotCommunication robotCom = new RobotCommunication(this.robot);
		robotCom.sendRobotRegistration(robot.getID());	
	}

	@Override
	public void run()
	{
		while (!this.robot.isBumperActivated())
		{
		//	System.out.println("Sender ID: " + robot.getCommunicationID().getID() + ", Server ID: " + Server.serverID.getID());	
		//	System.out.println("Sending ...");
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
