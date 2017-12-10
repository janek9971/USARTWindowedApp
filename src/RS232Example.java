import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class RS232Example {

	public static final StringBuilder PORTS = new StringBuilder(
			"PORTS=PORTB,00000000,PORTC,00000000,PORTD,00000000\r\n");

	public static final StringBuilder PORTS_DIRS_ALL = new StringBuilder(
			"PORTS=0,00000000,00000000,00000000\r\n");
	
	
	public static final StringBuilder PORT_DIR_ONE= new StringBuilder(
			"PIN=--,-,-\r\n");
	
	
	
	public void connect(String portName) throws Exception {

		String nazwaportu = portName.substring(0, 3);
		if (nazwaportu.equals("COM")) {

			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

			if (portIdentifier.isCurrentlyOwned()) {
				System.out.println("Port in use!");
			} else {
				// points who owns the port and connection timeout
				SerialPort serialPort = (SerialPort) portIdentifier.open("RS232Example", 3000);

				// setup connection parameters
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				// setup serial port writer
				CommPortSender.setWriterStream(serialPort.getOutputStream());

				// setup serial port reader
				CommPortReceiver nazwa=	new CommPortReceiver(serialPort.getInputStream());
				nazwa.start();
				System.out.println(nazwa+ "chuj");
				System.out.println("PRZECHUJ");
			}
		} else {
			System.err.println("BRAWO IDIOTO!");

		}

	}

}