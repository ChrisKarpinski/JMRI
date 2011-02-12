// SerialInterfaceScaffold.java

package jmri.jmrix.powerline;

import java.util.Vector;

/**
 * Stands in for the SerialTrafficController class
 * @author			Bob Jacobsen Copyright 2004, 2007, 2008
 * Converted to multiple connection
 * @author kcameron Copyright (C) 2011
 * @version			$Revision: 1.6 $
 */

public class SerialTrafficControlScaffold extends SerialTrafficController {
	public SerialTrafficControlScaffold() {
		if (log.isDebugEnabled()) log.debug("setting instance: "+this);
		setInstance();
	}

	// override some SerialTrafficController methods for test purposes

	public boolean status() {
		return true;
	}

	/**
	 * record messages sent, provide access for making sure they are OK
	 */
	public Vector<SerialMessage> outbound = new Vector<SerialMessage>();  // public OK here, so long as this is a test class
	public void sendSerialMessage(SerialMessage m, SerialListener reply) {
		if (log.isDebugEnabled()) log.debug("sendSerialMessage ["+m+"]");
		// save a copy
		outbound.addElement(m);
		// we don't return an echo so that the processing before the echo can be
		// separately tested
	}

	// test control member functions

	/**
	 * forward a message to the listeners, e.g. test receipt
	 */
	protected void sendTestMessage(SerialMessage m, SerialListener l) {
		// forward a test message to NceListeners
		if (log.isDebugEnabled()) log.debug("sendTestMessage    ["+m+"]");
		notifyMessage(m, l);
		return;
	}

	/*
	* Check number of listeners, used for testing dispose()
	*/

	public int numListeners() {
		return cmdListeners.size();
	}

	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SerialTrafficControlScaffold.class.getName());

}
