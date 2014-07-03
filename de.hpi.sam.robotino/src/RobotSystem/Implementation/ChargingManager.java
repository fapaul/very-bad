package RobotSystem.Implementation;

import java.util.Date;
import java.util.List;

import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IRobotExecute;
import RobotSystem.Interfaces.New.IRouteFinder;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

public class ChargingManager implements IRobotExecute {
//DriverManager

	private boolean done;
	private Route currentRoute;

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