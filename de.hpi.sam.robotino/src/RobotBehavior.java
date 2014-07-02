import de.hpi.sam.warehouse.WarehouseRobot;

public class RobotBehavior implements Runnable
{

	WarehouseRobot	robot;

	public RobotBehavior(WarehouseRobot robot)
	{
		this.robot = robot;
	}

	@Override
	public void run()
	{
		// TODO Implement the behavior of the robots
		System.out.println(this.robot.getID().getName() + " Starting...");
		
		this.robot.driveBackward();
		while (!this.robot.isBumperActivated())
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				this.robot.brake();
			}
		}
		this.robot.brake();
		System.out.println(this.robot.getID().getName() + " done.");
	}
}
