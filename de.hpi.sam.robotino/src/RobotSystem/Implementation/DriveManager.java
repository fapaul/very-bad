package RobotSystem.Implementation;

//import RobotExecute.RobotExecute.*;
import java.util.Date;

import RobotSystem.Interfaces.New.IDriveManager;
import RobotSystem.Interfaces.New.IRobotExecute;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.OrderItem;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.IssuingPoint;

public class DriveManager implements IRobotExecute, IDriveManager { // = IDrive

	private boolean bumped;
	private WarehouseRobot robot = null;
	private Order curOrder = null;
	private Cart curCart = null;
	
	public Order getCurOrder() {
		return curOrder;
	}

	public void setCurOrder(Order curOrder) {
		this.curOrder = curOrder;
	}

	public DriveManager(WarehouseRobot r) {
		robot = r;
	}
	
	public void drive(Position position) {
		robot.driveToPositionAvoidingObstacles(position);		
	}

	@Override
	public void setSpeed(int speed) {
		robot.setSpeed(speed);
	}

	@Override
	public int getMaxSpeed() {
		return robot.MAX_MOVEMENT_SPEED;
	}

	@Override
	public void orderStart() {
		System.out.println("starting order");
		// No order nothing to do //TODO an error message might be good
		if(curOrder == null)
			return;
		// Getting to the cart area and the position of the first cart
		robot.driveToPositionAvoidingObstacles(curOrder.getCartArea().getCartPositions().get(0));
		if(isBumped()) {
			System.out.println("Getting to cart area failed");
			return;
		}
		
		// Get the cart
		curCart = robot.takeCart(curOrder.getCartArea().getCartPositions().get(0));
		if(curCart == null) {
			System.out.println("Error retrieving cart for CartArea");
			return;
		}
		
		for (OrderItem orderItem : curOrder.getOrderItems()) {
			System.out.println("processing orderitem");
			if( robot.getIssuingPoints(orderItem.getProductType()).size() < 1) {
				System.out.println("Error: No issuings points for order item");
				return;
			}
			// Find nearest issuing points 
			
			int robotX, robotZ;
			robotX = robot.getCurrentPosition().getXPosition();
			robotZ = robot.getCurrentPosition().getZPosition();
			IssuingPoint nearest = robot.getIssuingPoints(orderItem.getProductType()).get(0);
			for(IssuingPoint point : robot.getIssuingPoints(orderItem.getProductType())) {
				if(point.getXPosition() - robotX + point.getZPosition() - robotZ  < nearest.getXPosition() - robotX + nearest.getZPosition() - robotZ) {
					nearest = point;
				}
			}
			
			// Drive to issuingpoint and retrieve items
			robot.driveToPositionAvoidingObstacles(nearest);
			robot.load(orderItem.getQuantity(), nearest, curCart);
		}
		System.out.println("finished order");
		// TODO for testing we return to the current cart area
		robot.driveToPositionAvoidingObstacles(curOrder.getCartArea().getCartPositions().get(0));
		curCart = null;
	}

	@Override
	public boolean orderDone() {
		return curCart != null;
	}

	@Override
	public Date calculateOrderTime(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void explorationStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean explorationDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasUnexploredRooms() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void chargingStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean chargingDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBumped() {
		return bumped;
	}
}