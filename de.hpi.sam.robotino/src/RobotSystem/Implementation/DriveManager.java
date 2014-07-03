package RobotSystem.Implementation;

//import RobotExecute.RobotExecute.*;
import RobotSystem.Interfaces.New.IDriveManager;
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
	public boolean isBumped() {
		return bumped;
	}
}