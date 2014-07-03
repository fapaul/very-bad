import java.util.ArrayList;
import java.util.List;

import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;
import Datatypes.Added.CartSource;
import Datatypes.Added.RoomPoint;
import Datatypes.Added.Route;
import RobotSystem.Implementation.*;
import de.cpslab.robotino.environment.Position;
import de.cpslab.robotino.sensor.interfaces.*;
import de.hpi.sam.warehouse.stock.*;

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
		WarehouseRepresentation wr = new WarehouseRepresentation();
		RouteFinder rf = new RouteFinder(this.robot,  wr);
		Position pos = rf.getPosition();
		DriveManager driveMan = new DriveManager(this.robot);	
	System.out.println("test " + robot.getCurrentPosition().getXPosition() + "\t" + robot.getCurrentPosition().getZPosition());
		StockroomID sID = wr.getRoomFor(robot.getCurrentPosition());
		System.out.println("Stockroom is:" + sID.getID().toString());
		StockroomManagement StockManager = StockroomManagement.INSTANCE;
		List<CartArea> Areas = StockManager.getCartAreas(); 
		
		CartArea Area = Areas.get(0); 
		int AreaPosX = Area.getCartPositions().get(0).getXPosition();
		int AreaPosY = Area.getCartPositions().get(0).getZPosition();
		Position AreaPos = new Position(AreaPosX, AreaPosY); 
		
		System.out.println(robot.getCurrentPosition().getXPosition());
		driveMan.drive(AreaPos);
		//System.out.println(robot.getCurrentPosition().getXPosition());
		CartPosition CartPos = Area.getCartPositions().get(0);
		//this.robot.takeCart(CartPos);
		StockroomID stock = wr.getRoomFor((Position) CartPos); 
		CartSource src = new CartSource(Area, stock);
		src.interact(null , this.robot); 
		System.out.println("yeeah");
		driveMan.drive(new Position(100, 100));
		System.out.println("after drive");
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
