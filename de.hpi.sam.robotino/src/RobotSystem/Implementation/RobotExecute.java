package RobotSystem.Implementation;

import java.util.Date;
import java.util.List;

import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.environment.IWarehouseEnvironment;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.OrderItem;
import de.hpi.sam.warehouse.stock.IssuingPoint;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;
import Datatypes.Added.RoomPoint;
import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IRobotExecute;
import RobotSystem.Implementation.*;



public class RobotExecute implements IRobotExecute{

	private DriveManager dm;
	private ChargingManager cm;
	private ExplorationManager em;
	private OrderManager om;
	private RobotManager rm;
	private Order tmpOrder;
	WarehouseRobot robot;
	WarehouseRepresentation representation;

	RobotExecute(WarehouseRobot r, WarehouseRepresentation wr) {

		this.robot = r;
		this.representation = wr;
		dm = new DriveManager(r);
		cm = new ChargingManager();
		em = new ExplorationManager(r, wr);
		om = new OrderManager(r,  wr);
		rm = new RobotManager(r);
	}


	@Override
	public void orderStart() {
		om.orderStart(tmpOrder);		
	}

	@Override
	public boolean orderDone() {
		return om.orderDone();
	}

	@Override
	public Date calculateOrderTime(Order order) {
		tmpOrder = order;
		return om.calculateOrderTime(order);
	}

	@Override
	public void explorationStart() {
		em.explorationStart();
	}

	@Override
	public boolean explorationDone() {
		return em.explorationDone();
	}


	public boolean isExplored(StockroomID room) {
		return em.isExplored(room);
	}

	@Override
	public boolean hasUnexploredRooms() {
		return em.hasUnexploredRooms();
	}

	@Override
	public void chargingStart() {
		cm.chargingStart();		
	}

	@Override
	public boolean chargingDone() {
		return cm.chargingDone();
	}

	@Override
	public boolean isBumped() {
		return dm.isBumped();
	}

}
