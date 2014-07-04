package RobotSystem.Implementation;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Datatypes.Added.StateType;
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

	private Queue<StatusMessage> incoming = new LinkedList<StatusMessage>();
	private CommunicationID server = Server.serverID; 
	private WarehouseRobot robot;
	
	private List <CommunicationID> robots;
	private List<StockroomID> explorableStockrooms;
	
	public RobotCommunication(WarehouseRobot robot){ 
		this.robot = robot;
		sendRobotRegistration();
	}
	
	public void exchangeInformation() {
		for(int j = 0; j < explorableStockrooms.size(); j++) {
			for (int i = 0; i < robots.size(); i++) {
				if (robots.get(i) == server)
					i++;
				else
					robot.requestAndMergeExplorationInfo(explorableStockrooms.get(j), (RobotinoID)robots.get(i));
			}
		}
	}
	
	public void sendRobotRegistration() {
		StatusMessage message = new StatusMessage(StateType.message.ROBOT_REGISTER, robot.getCommunicationID());
		robot.sendMessage(server, message);
	}
	
	@Override
	public void sendRobotStatus(StateType.robot status) {
		StatusMessage message = new StatusMessage(StateType.message.ROBOT_STATUS, status);
		robot.sendMessage(server, message);	
	}

	@Override
	public void sendOrderTime(Date duration) {
		StatusMessage message = new StatusMessage(StateType.message.ROBOT_ORDERTIME, duration);
		robot.sendMessage(server, message);		
	}
	
	@Override
	public void sendPosition(Position position) {
		StatusMessage message = new StatusMessage(StateType.message.ROBOT_POSITION, position);
		robot.sendMessage(server, message);
	}

	@Override
	public void sendOrderFinish(Order order) {
		StatusMessage message = new StatusMessage(StateType.message.ROBOT_FINISH, order);
		robot.sendMessage(server, message);
	}

	@Override
	public float getWorkload() {
		return 0.0f;
	}
	
	@Override
	public boolean hasMessage() { 
		List<Message> messages = robot.receiveMessages();
		if (messages.size() > 0) {
			System.out.println("Robot received a message.");
			for (Message message : messages) {
				try {
					incoming.add((StatusMessage)message);
				}
				catch (Exception e) {
					System.out.println("Robot received invalid message. " + e);
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public StatusMessage readMessage() {
		return incoming.poll();
	}
}
