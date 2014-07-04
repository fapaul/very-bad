package RobotSystem.Implementation;

import java.util.Date;
import java.util.List;
import java.util.Queue;

import Datatypes.Added.StateType;
import Datatypes.Added.StateType.message;
import Datatypes.Added.StatusMessage;
import RobotSystem.Interfaces.New.IRobotCommunication;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.CommunicationID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.actuator.communication.RobotinoWLanAdapter;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.Server;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.StockroomID;

public class RobotCommunication implements IRobotCommunication {

	private CommunicationID server;
	private Queue<Message> incoming;		// = new LinkedList<Message>;
	private List <CommunicationID> robots;
	private RobotinoWLanAdapter robotComm;
	private WarehouseRobot warehouseRobot;
	private List<StockroomID> explorableStockrooms;

	
	
	public RobotCommunication(WarehouseRobot robot){
		server = Server.INSTANCE.serverID; 
		warehouseRobot = robot;
	}
	
	public void exchangeInformation(){
		for(int j = 0; j < explorableStockrooms.size();j++ ){
			for (int i = 0; i < robots.size(); i++){
				if (robots.get(i) == server){
					i++;
				}else{
					warehouseRobot.requestAndMergeExplorationInfo(explorableStockrooms.get(j), (RobotinoID)robots.get(i));
				}

			}
		}
	}

	@Override
	public boolean hasMessage() {
		fetchMessage();
		return incoming.size() > 0;
	}

	@Override
	public Message readMessage() {
		return incoming.poll();
	}
	
	public void sendRobotRegistration(RobotinoID robID){
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_REGISTER);
		messToSend.setContent(robID);
		warehouseRobot.sendMessage(server, messToSend);
	}
	@Override
	public void sendRobotStatus(StateType.robot status) {
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_STATUS);
		messToSend.setContent(status);
		warehouseRobot.sendMessage(server, messToSend);	
	}

	@Override
	public void sendOrderTime(Date duration) {
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_ORDERTIME);
		messToSend.setContent(duration);
		System.out.println(messToSend.getID());
		System.out.println(server);
		warehouseRobot.sendMessage(server, messToSend);		
	}
	
	@Override
	public void sendPosition(Position position) {
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_POSITION);
		messToSend.setContent(position);
		warehouseRobot.sendMessage(server,  messToSend);
	}

	@Override
	public void sendOrderFinish(Order order) {
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_FINISH);
		messToSend.setContent(order);
		warehouseRobot.sendMessage(server, messToSend);
	}

	@Override
	public float getWorkload() {
		return 0;
	}
	
	public void fetchMessage(){
		incoming.addAll(robotComm.receiveMessages());
	}

}