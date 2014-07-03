package ServerSystem.Implementation;

import Datatypes.Added.StateType;
import ServerSystem.Interfaces.New.IAdminControl;
import ServerSystem.Interfaces.New.IOrderAccess;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.OrderManagement;
import de.hpi.sam.warehouse.stock.StockroomManagement;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

public class AccessManager<RobotBehavior> implements IOrderAccess, IAdminControl{

    WarehouseManagement wm;
    StockroomManagement sm;
    Server serv;
    RobotBehavior behavior;

    private OrderManagement om = new OrderManagement();
    private float energyConsumption;
    
    @Override
    public void startup() {
        wm = WarehouseManagement.INSTANCE;
        sm = StockroomManagement.INSTANCE;
        serv = Server.INSTANCE;
    }

    @Override
    public void shutdown() {
        // TODO Auto-generated method stub  
    }
    
    @Override
	public void setEnergyConsumption(float energyConsumption) {
		this.energyConsumption = energyConsumption;
	}

    @Override
    public void addRobot(RobotinoID robot) {
        WarehouseRobot newRobot = new WarehouseRobot(robot);
        newRobot.register();
        // TODO rework the handover of RobotBehavior
        newRobot.setBehavior((Runnable) behavior);
        
        wm.addRobot(newRobot);
        newRobot.connectToSimulator();
        
        try {
            // give some time to connect
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            return;
        }

        newRobot.start();
    }

    @Override
    public void removeRobot(RobotinoID robot) {
        WarehouseRobot newRobot = new WarehouseRobot(robot);
        newRobot.delete();
    }

    @Override
    public RobotinoID[] getRobots() {
        // TODO
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
        WarehouseRobot newRobot = new WarehouseRobot(robot);
        return newRobot.getCurrentPosition();
    }

    // not possible with given methods
    @Override
    public void addOrder(Order order) {
        // TODO
    }
    @Override
    public void removeOrder(Order order) {
        // TODO
    }

    @Override
    public int getOrderAmount() {
        // return amount of orders in our queue
        return serv.getOrderList().size();
    }
    
    // set RobotBehavior for Robot
    // TODO find a better way to get RobotBehavior from the default-package
    public void setBehavior(RobotBehavior behavior) {
        this.behavior = behavior;
    }  

}

