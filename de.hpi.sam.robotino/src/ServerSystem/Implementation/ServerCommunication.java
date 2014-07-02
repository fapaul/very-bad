package ServerSystem.Implementation;

import java.util.Date;
import java.util.List;
import java.util.Queue;

import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import ServerSystem.Interfaces.New.IServerCommunication;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.communication.WarehouseCommunicationServer;
import de.hpi.sam.warehouse.order.Order;

public class ServerCommunication implements  IServerCommunication {

	private Queue<Message> incoming; // = new LinkedList..
	private WarehouseCommunicationServer serverCommServ; 
		
	@Override
	public StateType.robot requestRobotStatus(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_STATUS);
		serverCommServ.sendMessage(robot, messToSend);
		
		// It's assumed he sends a message with his status
		StateType.robot answer = null;
		while(answer == null) {
			// Wait for message
			List<Message> rec = serverCommServ.receiveMessages(robot);
			if(rec.size() == 0) continue;
			for (Message message : rec) {
				// TODO test if working
				StatusMessage statMes = (StatusMessage) message;
				
				if(statMes.getTypeOfMessage() == StateType.message.ROBOT_STATUS) 
					answer = (Datatypes.Added.StateType.robot) statMes.getContent();
				else
					incoming.add(message);
			}
		}
		
		return answer;
	}

	@Override
	public Date requestOrderTime(Order order, RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_ORDERTIME);
		serverCommServ.sendMessage(robot, messToSend);
		
		// It's assumed he sends a message with his status
		Date answer = null;
		while(answer == null) {
			// Wait for message
			List<Message> rec = serverCommServ.receiveMessages(robot);
			if(rec.size() == 0) continue;
			for (Message message : rec) {
				// TODO test if working
				StatusMessage statMes = (StatusMessage) message;
				
				if(statMes.getTypeOfMessage() == StateType.message.ROBOT_ORDERTIME) 
					answer = (Date) statMes.getContent();
				else
					incoming.add(message);
			}
		}
		
		return answer;
	}

	@Override
	public Position requestPosition(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_POSITION);
		serverCommServ.sendMessage(robot, messToSend);
		
		// It's assumed he sends a message with his status
		Position answer = null;
		while(answer == null) {
			// Wait for message
			List<Message> rec = serverCommServ.receiveMessages(robot);
			if(rec.size() == 0) continue;
			for (Message message : rec) {
				// TODO test if working
				StatusMessage statMes = (StatusMessage) message;
				
				if(statMes.getTypeOfMessage() == StateType.message.ROBOT_POSITION) 
					answer = (Position) statMes.getContent();
				else
					incoming.add(message);
			}
		}
		
		return answer;
	}

	@Override
	public void sendSleep(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_SLEEP);
		serverCommServ.sendMessage(robot, messToSend);
	}

	@Override
	public void sendWakeup(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_WAKEUP);
		serverCommServ.sendMessage(robot, messToSend);
	}

	@Override
	public void sendOrderStart(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(StateType.message.SERVER_ORDER);
		// TODO add order message 
		serverCommServ.sendMessage(robot, messToSend);
	}

	@Override
	public boolean hasMessage() {
		int oldLength = incoming.size();
		incoming.addAll(serverCommServ.receiveMessages());
		return oldLength < incoming.size();
	}

	@Override
	public Message readMessage() {
		return incoming.poll();
	}
	

}