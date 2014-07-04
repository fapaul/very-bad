package ServerSystem.Implementation;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import ServerSystem.Interfaces.New.IServerCommunication;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.Server;
import de.hpi.sam.warehouse.order.Order;

public class ServerCommunication implements  IServerCommunication {

	private Queue<StatusMessage> incoming = new LinkedList<StatusMessage>();
	private Server server = Server.INSTANCE;

	@Override
	public StateType.robot requestRobotStatus(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_STATUS);
		server.sendMessage(robot, messToSend);

		while (!hasMessage(StateType.message.ROBOT_STATUS)); // Problem is that position message might not be on top since multiple can be received at once
		StatusMessage response = readMessage();
		try {
			StateType.robot status = (StateType.robot) response.getContent();
			return status;
		}
		catch (Exception e) {
			System.out.println("Message content is no valid position. " + e);
			return null;
		}
	}

	@Override
	public Date requestOrderTime(Order order, RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_ORDERTIME);
		server.sendMessage(robot, messToSend);
		// It's assumed he sends a message with his status
		// Receive answer
		while (!hasMessage(StateType.message.ROBOT_ORDERTIME)); // Problem is that position message might not be on top since multiple can be received at once
		StatusMessage response = readMessage();
		try {
			Date date = (Date)response.getContent();
			return date;
		}
		catch (Exception e) {
			System.out.println("Message content is no valid position. " + e);
			return null;
		}
	}
	
	@Override
	public Position requestPosition(RobotinoID robot) {
		// Send request
		StatusMessage request = new StatusMessage(StateType.message.SERVER_POSITION);
		server.sendMessage(robot, request);
		
		// Receive answer
		while (!hasMessage(StateType.message.ROBOT_POSITION));
		StatusMessage response = readMessage(StateType.message.ROBOT_POSITION);
		try {
			Position position = (Position)response.getContent();
			return position;
		}
		catch (Exception e) {
			System.out.println("Message content is no valid position. " + e);
			return null;
		}
	}

	@Override
	public void sendSleep(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_SLEEP);
		server.sendMessage(robot , messToSend);
	}

	@Override
	public void sendWakeup(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_WAKEUP);
		server.sendMessage(robot, messToSend);
	}

	@Override
	public void sendOrderStart(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_ORDER);
		server.sendMessage(robot, messToSend);
	}

	@Override
	public boolean hasMessage() { 
		List<Message> messages = server.receiveMessages();
		if(messages == null)
			return false;
				
		if (messages.size() > 0) {
			System.out.println("Server received a message.");
			for (Message message : messages) {
				try {
					incoming.add((StatusMessage)message);
				}
				catch (Exception e) {
					System.out.println("Server received invalid message. " + e);
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean hasMessage(StateType.message type) {
		List<Message> messages = server.receiveMessages();
		boolean found = false;
		if (messages.size() > 0) {
			System.out.println("Server received a message.");
			for (Message message : messages) {
				try {
					StatusMessage casted = (StatusMessage)message;
					if (casted.getTypeOfMessage() == type)
						found = true;
					incoming.add(casted);
				}
				catch (Exception e) {
					System.out.println("Server received invalid message. " + e);
				}
			}
			return found;
		}
		return false;
	}

	@Override
	public StatusMessage readMessage() {
		return incoming.poll();
	}
	
	@Override
	public StatusMessage readMessage(StateType.message type) { // TODO update interface
		List<StatusMessage> polled = new LinkedList<StatusMessage>();
		StatusMessage message = null;
		while (incoming.size() > 0) {
			message = incoming.poll();
			polled.add(message);
			if (message.getTypeOfMessage() == type)
				break;
			else
				message = null;
		}
		incoming.addAll(polled);
		return message;
	}
}
