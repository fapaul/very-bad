package RobotSystem.Implementation;

//import RobotExecute.RobotExecute.*;
import java.util.Date;
import java.util.List;

import Datatypes.Added.RoomPoint;
import Datatypes.Added.RoomPointDoor;
import Datatypes.Added.RoomPointIssuingPoint;
import Datatypes.Added.Route;
import RobotSystem.Implementation.RouteFinder;
import RobotSystem.Interfaces.New.IExplorationManager;
import RobotSystem.Interfaces.New.IRobotExecute;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.environment.IWarehouseEnvironment;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Door;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class ExplorationManager implements IExplorationManager {
//DriverManager
	
	private boolean done;
	private Route currentRoute;
	private WarehouseRepresentation representation;
	private RouteFinder rf;
	private DriveManager dm;
	private WarehouseRobot robot;
	
	public ExplorationManager(WarehouseRobot robot, WarehouseRepresentation representation) {
		this.robot = robot;
		rf = new RouteFinder(robot, representation);
		dm = new DriveManager(robot);
		this.representation = representation;		
	}
	
	public Date calculateExplorationTime(StockroomID room) {
		// TODO assumption: distance in mm and cast from double to int
		int distance = (int) rf.getDistance(rf.calculateExplorationRoute(rf.getPosition(), room));
		
		// time in milli seconds
		int time = distance/IWarehouseEnvironment.SAFETY_SPEED * 1000;
		Date date = new Date(time);
		
		return date;
	}
	
	public void explorationStart(StockroomID room) {
		Route route = rf.calculateExplorationRoute(rf.getPosition(), room);
		List<RoomPoint> rp = route.getRoomPoints();
		System.out.printf("\nRoompoint-Anzahl ist: %d\n", rp.size());
		for (RoomPoint r : rp) {
			System.out.println(r.getLocation().getXPosition());
		}
		for (RoomPoint r : rp) {
			dm.drive(r.getLocation());			// TODO checken
			
			// save exploration status
			try {
				// check if RoomPoint is a door
				RoomPointDoor door = (RoomPointDoor)r;
				representation.doorExplored(door.getDoor());
			}
			catch (Exception e) { }
			try {
				// check if RoomPoint is an issuingPoint
				RoomPointIssuingPoint issuingPoint = (RoomPointIssuingPoint)r;
				representation.issuingPointExplored(issuingPoint.getIssuingPoint());
			}
			catch (Exception e) { }
			
		}
	}
	
	public boolean isExplored(StockroomID room) {
		return robot.getExplorationStatus(room) == 100;
	}

	
	public void explorationStart() {
		List<StockroomID> stockrooms = representation.getStockrooms();
		StockroomID unexploredRoom;
		for (StockroomID sID : stockrooms) {
			if (!isExplored(sID)) {
				unexploredRoom = sID;
				explorationStart(unexploredRoom);
				break;
			}
		}
	}

	public boolean explorationDone() {
		return this.done;
	}

	
	public boolean hasUnexploredRooms() {
		for(StockroomID id : representation.getStockrooms())
			if(representation.getExplorationStatus(id) < 100)
				return true;
		return false;
	}
}