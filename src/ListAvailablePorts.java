import gnu.io.*;

import java.util.*;
import java.io.InputStream;

public class ListAvailablePorts implements Runnable, SerialPortEventListener {

	static CommPortIdentifier portId;
	static Enumeration portList;

	InputStream inputStream;
	SerialPort serialPort;
	Thread readThread;
	byte[] readBuffer;

	public static ArrayList listSerialPorts() {

		Enumeration ports = CommPortIdentifier.getPortIdentifiers();
		ArrayList portList = new ArrayList();
		String portArray[] = null;
		while (ports.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) ports.nextElement();
			if (currPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				portList.add(currPortId.getName());
			}
		}
		portArray = (String[]) portList.toArray(new String[portList.size()]);

		return portList;
	}

	public static CommPortIdentifier getPortId() {
		return portId;
	}

	public static void setPortId(CommPortIdentifier portId) {
		ListAvailablePorts.portId = portId;
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}