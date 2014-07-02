package ServerSystem.Implementation;

import Datatypes.Added.StateType;
import ServerSystem.Interfaces.New.IAdminControl;
import ServerSystem.Interfaces.New.IOrderAccess;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.order.Order;

public class AccessManager implements IOrderAccess, IAdminControl{

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float setEnergyConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addRobot(RobotinoID robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRobot(RobotinoID robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RobotinoID[] getRobots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RobotinoID[] getRobots(StateType.robot status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateType.robot getRobotStatus(RobotinoID robot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getRobotPosition(RobotinoID robot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOrderAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}

