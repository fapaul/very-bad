package RobotSystem.Implementation;

import java.util.Date;

import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IRobotExecute;
import RobotSystem.Interfaces.New.IRouteFinder;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

public class ChargingManager implements IRobotExecute, IRouteFinder {
//DriverManager

	private boolean done;
	private Route currentRoute;
	@Override
	public Route[] calculateCartAreaRoutes(Position from) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Route[] calculateCartAreaRoutes(Position from, Order order) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Route[] calculateIssuingPointsRoutes(Position from, Order order) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Route calculateExplorationRoute(Position from, Stockroom room) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Route calculateChargingRoute(Position from) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getDistance(Route route) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public StockroomID[] getNearRooms() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void orderStart() {
		// TODO Auto-generated method stub
		
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