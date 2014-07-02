package RobotSystem.Implementation;

//import RobotExecute.RobotExecute.*;
import java.util.Date;

import RobotSystem.Interfaces.New.IDriveManager;
import RobotSystem.Interfaces.New.IRobotExecute;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.cpslab.robotino.*;
import de.cpslab.robotino.actuator.interfaces.IRobotinoWheels;
import de.cpslab.robotino.environment.Position;

public class DriveManager implements IRobotinoWheels, IRobotExecute, IDriveManager { // = IDrive

	private boolean bumped;

	@Override
	public void drive(Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxSpeed() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public void drive(int forwardSpeed, int sideSpeed, int rotate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void driveForward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void driveForward(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void driveBackward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void driveBackward(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void driveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void driveRight(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void driveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void driveLeft(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpeed(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accelerate(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decelerate(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void brake() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnRightAt(int degree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnLeftAt(int degree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean driveToPosition(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean driveToPositionAvoidingObstacles(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}