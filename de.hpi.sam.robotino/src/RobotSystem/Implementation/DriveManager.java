package RobotSystem.Implementation;

//import RobotExecute.RobotExecute.*;
import Datatypes.Added.RoomPoint;
import Datatypes.Added.RoomPointDoor;
import Datatypes.Added.RoomPointIssuingPoint;
import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IDriveManager;
import de.cpslab.robotino.actuator.interfaces.IRobotinoWheels;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class DriveManager implements IDriveManager { // = IDrive

	private boolean bumped;
	private WarehouseRobot robot = null;
	private WarehouseRepresentation repres = null;
	private Order curOrder = null;
	private Cart curCart = null;
	
	
	public DriveManager(WarehouseRobot r, WarehouseRepresentation repres) {
		robot = r;
		this.repres = repres;
	}
	
	public Order getCurOrder() {
		return curOrder;
	}

	public void setCurOrder(Order curOrder) {
		this.curOrder = curOrder;
	}
	
	public void drive(Position position) {
		if (curCart != null)
			robot.transportToPositionAvoidingObstacles(position);
		else
			robot.driveToPositionAvoidingObstacles(position);
	}
	
	public void drive(Route route) {
		// Driving route
		for (RoomPoint point : route.getRoomPoints()) {
			if(curCart != null) 
				robot.transportToPositionAvoidingObstacles(point.getLocation());
			else
				robot.driveToPositionAvoidingObstacles(point.getLocation());
			// Note the driven to points as explored
			try {
				// check if RoomPoint is a door
				RoomPointDoor door = (RoomPointDoor) point;
				repres.doorExplored(door.getDoor());
			}
			catch (ClassCastException e) { 
			try {
				// check if RoomPoint is an issuingPoint
				RoomPointIssuingPoint issuingPoint = (RoomPointIssuingPoint) point;
				repres.issuingPointExplored(issuingPoint.getIssuingPoint());
			}
			catch (ClassCastException c) { 
				System.out.println("Found unprocessed roompoint");
			}
			}
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