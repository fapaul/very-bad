package RobotSystem.Implementation;

//import RobotExecute.RobotExecute.*;
import java.util.Date;

import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IExplorationManager;
import RobotSystem.Interfaces.New.IRobotExecute;
import RobotSystem.Interfaces.New.IRouteFinder;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.environment.IWarehouseEnvironment;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

public class ExplorationManager implements IRobotExecute, IWarehouseEnvironment, IRouteFinder, IExplorationManager {
//DriverManager
	
	private boolean done;
	private Route currentRoute;
	
	private StockroomID chooseExplorationRoom() {
		
		return null;
	}

	@Override
	public Date calculateExplorationTime(StockroomID room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void explorationStart(StockroomID room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExplored(StockroomID room) {
		// TODO Auto-generated method stub
		return false;
	}

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
	public int getExplorationStatus(StockroomID stock) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean requestAndMergeExplorationInfo(StockroomID stock,
			RobotinoID robot) {
		// TODO Auto-generated method stub
		return false;
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