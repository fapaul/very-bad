package RobotSystem.Implementation;

import java.util.Date;
import java.util.List;

import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IRobotExecute;
import RobotSystem.Interfaces.New.IRouteFinder;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.environment.Path;
import de.hpi.sam.warehouse.interfaces.IStockroom;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.OrderItem;
import de.hpi.sam.warehouse.order.ProductType;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.CartPosition;
import de.hpi.sam.warehouse.stock.Door;
import de.hpi.sam.warehouse.stock.IssuingPoint;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

public class OrderManager implements IRobotExecute {
// DriverManager, ExplorationManager

	private boolean done;
	private Route currentSourceRoute = null;
	private Route currentDestinationRoute = null;
	private Order currentOrder = null;
	private Cart currentCart = null;
	private WarehouseRobot robot = null;
	
	public OrderManager(WarehouseRobot r) {
		robot = r;
	}
	
	private Route chooseOrderRoute(Route[] routes, Order order) {
	
		return null;
	}
	
	private Route chooseRoute(Route[] routes) {
	
		return null;
	}

	@Override
	public void orderStart() {
		System.out.println("starting order");
		// No order nothing to do //TODO an error message might be good
		if(currentOrder == null)
			return;
		// Getting to the cart area and the position of the first cart
		robot.driveToPositionAvoidingObstacles(currentOrder.getCartArea().getCartPositions().get(0));
		if(isBumped()) {
			System.out.println("Getting to cart area failed");
			return;
		}
		
		// Get the cart
		currentCart = robot.takeCart(currentOrder.getCartArea().getCartPositions().get(0));
		if(currentCart == null) {
			System.out.println("Error retrieving cart for CartArea");
			return;
		}
		
		for (OrderItem orderItem : currentOrder.getOrderItems()) {
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
			robot.load(orderItem.getQuantity(), nearest, currentCart);
		}
		System.out.println("finished order");
		// TODO for testing we return to the current cart area
		robot.driveToPositionAvoidingObstacles(currentOrder.getCartArea().getCartPositions().get(0));
		currentCart = null;		
	}

	@Override
	public boolean orderDone() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}
}