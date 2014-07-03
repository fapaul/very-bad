package RobotSystem.Implementation;

//import RobotExecute.RobotExecute.*;
import java.util.Date;
import java.util.List;

import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IExplorationManager;
import RobotSystem.Interfaces.New.IRouteFinder;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class ExplorationManager implements IExplorationManager {
//DriverManager
	
	private boolean done;
	private Route currentRoute;
	private WarehouseRepresentation representation;
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
		return representation.getExplorationStatus(room) == 100;
	}	
}