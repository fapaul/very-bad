package Datatypes.Added;

import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

import de.cpslab.robotino.actuator.communication.Message;

public class StatusMessage extends Message {
	
	Object content;
	MessageTypeof.t typeOfMessage;
	
	public StatusMessage(MessageTypeof.t type) {
		super();
		typeOfMessage = type;
		content = null;
	}
	
	public StatusMessage(MessageTypeof.t type, Object con) {
		super();
		typeOfMessage = type;
		content = con;
	}
	
	// Set and get the type of a message
	public MessageTypeof.t getTypeOfMessage() {
		return typeOfMessage;
	}

	public void setTypeOfMessage(MessageTypeof.t typeOfMessage) {
		this.typeOfMessage = typeOfMessage;
	}

	// Set and get contenct
	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}	
}