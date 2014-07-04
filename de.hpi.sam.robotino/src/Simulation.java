import ServerSystem.Implementation.ServerManager;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.log.RobotLogger;
import de.hpi.sam.warehouse.Server;
import de.hpi.sam.warehouse.WarehouseManagement;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.StockroomManagement;

/**
 * This is the simulation class that would be provided by our chair for the
 * simulation contest. This is an exemplary implementation with three robots in
 * the warehouse and can be changed for the contest.
 * 
 * Please implement the RobotBehavior class accordingly. Please do NOT change
 * this simulation class. If you need additional classes to realize your
 * modeling approach, please pack all source classes (including the
 * RobotinoBehavior class, excluding this simulation class) in ONE jar file
 * named according you group number. Please document your code.
 * 
 * @author "Sebastian Waetzoldt"
 * 
 */
public class Simulation
{

	/**
	 * Simulation entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		WarehouseManagement wm = WarehouseManagement.INSTANCE;
		StockroomManagement sm = StockroomManagement.INSTANCE;
		Server server = Server.INSTANCE;
		server.register();
			
		RobotLogger.setGlobalLogLevel("ALL"); // OFF
		
		// Add two robots
		WarehouseRobot robot1 = new WarehouseRobot(new RobotinoID("Robot 1", "127.0.0.1:8080"));
		robot1.register();
		robot1.setBehavior(new RobotBehavior(robot1));
		
		// WarehouseRobot robot2 = new WarehouseRobot(new RobotinoID("Robot 2", "127.0.0.1:8081"));
		// robot2.setBehavior(new RobotBehavior(robot2));
		// Register at server
		
		wm.addRobot(robot1);
		// wm.addRobot(robot2);

		robot1.connectToSimulator();
		// robot2.connectToSimulator();
		
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			return;
		}
		
		ServerManager servMan = new ServerManager();
		robot1.start();
		// robot2.start();
	}
}