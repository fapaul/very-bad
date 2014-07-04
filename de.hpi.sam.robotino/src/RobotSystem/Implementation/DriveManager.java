package RobotSystem.Implementation;

//import RobotExecute.RobotExecute.*;
import Datatypes.Added.RoomPoint;
import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IDriveManager;
import de.cpslab.robotino.actuator.interfaces.IRobotinoWheels;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Cart;

public class DriveManager implements IDriveManager { // = IDrive

	private boolean bumped;
	private WarehouseRobot robot = null;
	private Order curOrder = null;
	private Cart curCart = null;
	
	public DriveManager(WarehouseRobot r) {
		robot = r;
	}
	
	public Order getCurOrder() {
		return curOrder;
	}

	public void setCurOrder(Order curOrder) {
		this.curOrder = curOrder;
	}
	
	public void drive(Position position) {
		System.out.println("Bumped status " +isBumped());
		if(curCart != null)
			robot.transportToPositionAvoidingObstacles(position);
		else
			robot.driveToPositionAvoidingObstacles(position);
		//robot.driveToPositionAvoidingObstacles(position);		
	}
	
	public void drive(Route route) {
		// Driving route
		for (RoomPoint point : route.getRoomPoints()) {
			if(curCart != null)
				robot.transportToPositionAvoidingObstacles(point.getLocation());
			else
				robot.driveToPositionAvoidingObstacles(point.getLocation());
		}
	}

	public Cart getCurCart() {
		return curCart;
	}

	public void setCurCart(Cart curCart) {
		this.curCart = curCart;
	}

	@Override
	public void setSpeed(int speed) {
		robot.setSpeed(speed);
	}

	@Override
	public int getMaxSpeed() {
		//return robot.MAX_MOVEMENT_SPEED;
		return IRobotinoWheels.MAX_MOVEMENT_SPEED;
	}

	@Override
	public boolean isBumped() {
		return this.bumped;
	}

}