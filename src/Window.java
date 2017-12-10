import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Window {

	private JFrame frame;
	private StringBuilder temp = RS232Example.PORTS;
	private StringBuilder portsDirs = RS232Example.PORTS_DIRS_ALL;
	private StringBuilder portDir = RS232Example.PORT_DIR_ONE;
	private JButton btnstate;
	private JComboBox<String> comboBox;
	private JToggleButton[] tglbtn1, tglbtn2;
	private JButton tglOption;
	private JButton[] btnstate1, btnstate2;
	private String dupa, dupa2;
	private JTextArea selectOption;
	private JComboBox<String> menu;
	private int number;

	private int state;
	private final String portTab[] = { "33", "42", "43", "44", "45", "46", "", "", "18", "19", "47", "48", "49", "12",
			"32", "31", "30", "29", "28", "27", "", "", "", "17", "16", "15", "14", "13" };
	private final String pinsTab[] = { "23", "26", "27", "28", "29", "30", "", "", "14", "15", "31", "32", "33", "8",
			"22", "21", "20", "19", "18", "17", "", "", "", "13", "12", "11", "10", "9" };
	private JLabel lblimage;
	private static final String NOT_SELECTABLE_OPTION = " - Select an Option - ", PORT_DIR_ONE = "PORT\\DIR_ONE",
			PORTS_DIRS_ALL = "PORT\\DIR_ALL", SEND_READ_BYTE = "SEND\\READ BYTE";
	private JTextField errorlabel;

	public Window() {
		initialize();

	}

	private void initialize() {
		frame = new JFrame("ROZPIERDLATOR v0.4");
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, dim.width / 2, dim.height - 100);
		ImageIcon img = new ImageIcon("C:\\Users\\MaTEO\\Desktop\\USART_Communication\\logo.png");
		frame.setLocationRelativeTo(null);
		frame.setIconImage(img.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		errorlabel = new JTextField("");
		errorlabel.setBounds(400, 747, 153, 30);
		errorlabel.setEnabled(false);
		errorlabel.setFont(new Font("Serif", Font.PLAIN, 14));
		errorlabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(errorlabel);

		selectOption = new JTextArea();
		initializeSelectOption();

		menu = new JComboBox<String>();
		initializeMenu();

		comboBox = new JComboBox<String>();
		initializeComboBox();

		tglbtn1 = new JToggleButton[14];
		initializeTglbutton1();

		tglbtn2 = new JToggleButton[14];
		initializeTglbutton2();

		btnstate1 = new JButton[14];
		initializeBtnstate1();

		btnstate2 = new JButton[14];
		initializeBtnstate2();

		lblimage = new JLabel("");
		lblimage.setBounds(frame.getWidth() / 2 - 121, frame.getHeight() / 2 - 400, 242, 646);
		lblimage.setIcon(new ImageIcon("C:\\Users\\MaTEO\\Desktop\\USART_Communication\\atmega.png"));
		frame.getContentPane().add(lblimage);

		// frame.getContentPane().add(new JLabel(new
		// ImageIcon("C:\\Users\\MaTEO\\Desktop\\USART_Communication\\atmega.png")));
	}

	private void initializeComboBox() {
		comboBox.setModel(new DefaultComboBoxModel<String>() {

			boolean selectionAllowed = true;

			@Override
			public void setSelectedItem(Object anObject) {
				if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
					super.setSelectedItem(anObject);
				} else if (selectionAllowed) {
					selectionAllowed = false;
					super.setSelectedItem(anObject);
				}
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox = (JComboBox) e.getSource();
				if (comboBox.getSelectedItem().toString() != NOT_SELECTABLE_OPTION) {
					dupa = comboBox.getSelectedItem().toString();
					dupa2 = dupa.substring(1, dupa.length() - 1);

					try {
						RS232Example ex = new RS232Example();
						ex.connect(dupa2);
					} catch (Exception exc) {
						exc.printStackTrace();
					}
				} else {

				}
			}
		});
		comboBox.setBounds(557, 839, 161, 40);
		frame.getContentPane().add(comboBox);
		ArrayList lista = new ListAvailablePorts().listSerialPorts();
		String portArray[] = null;
		ArrayList lista2 = lista;
		portArray = (String[]) lista2.toArray(new String[lista2.size()]);
		comboBox.addItem(NOT_SELECTABLE_OPTION);
		comboBox.addItem(lista2.toString());
	}

	private void initializeMenu() {
		menu.setModel(new DefaultComboBoxModel<String>() {

			boolean selectionAllowed = true;

			@Override
			public void setSelectedItem(Object anObject) {
				if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
					super.setSelectedItem(anObject);
				} else if (selectionAllowed) {
					selectionAllowed = false;
					super.setSelectedItem(anObject);
				}
			}
		});

		menu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				menu = (JComboBox) e.getSource();

				if (menu.getSelectedItem().toString() == NOT_SELECTABLE_OPTION) {
					state = 0;

				} else if (menu.getSelectedItem().toString() == PORT_DIR_ONE) {
					state = 1;

				} else if (menu.getSelectedItem().toString() == PORTS_DIRS_ALL) {

					state = 2;
				}
			}
		});
		menu.setBounds(124, 110, 145, 40);
		frame.getContentPane().add(menu);

		menu.addItem(NOT_SELECTABLE_OPTION);
		menu.addItem(PORT_DIR_ONE);
		menu.addItem(PORTS_DIRS_ALL);
		menu.addItem(SEND_READ_BYTE);
	}

	private void initializeTglbutton1() {
		for (int i = 0; i < tglbtn1.length; i++) {
			tglbtn1[i] = new JToggleButton("P" + (i + 1));
			tglbtn1[i].setBounds(frame.getWidth() / 2 - 201, (frame.getHeight() / 2 - 378) + i * 44, 50, 45);
			frame.getContentPane().add(tglbtn1[i]);
			tglbtn1[i].addItemListener(new ItemListener() {
				private int anonVar;

				@Override
				public void itemStateChanged(ItemEvent e) {
					switch (state) {
					case 1:
						if (e.getStateChange() == ItemEvent.SELECTED) {

							portsDirs.setCharAt(Integer.parseInt(pinsTab[anonVar]), '1');

							CommPortSender.send(new ProtocolImpl().getMessage(portsDirs.toString()));
							btnstate1[anonVar].setBackground(Color.GREEN);
							// System.out.println("i: " + anonVar);
						} else if (e.getStateChange() == ItemEvent.DESELECTED) {
							portsDirs.setCharAt(Integer.parseInt(pinsTab[anonVar]), '0');

							CommPortSender.send(new ProtocolImpl().getMessage(portsDirs.toString()));
							btnstate1[anonVar].setBackground(Color.RED);
						}

						break;
					case 2:
						if (getNumber() == 0) {
							System.out.println("xd");
							portsDirs.setCharAt(6, '0');
						} else if (getNumber() == 1) {
							System.out.println("xd2");
							portsDirs.setCharAt(6, '1');
						} else {
							System.out.println("jebac");
						}
						if (e.getStateChange() == ItemEvent.SELECTED) {

							portsDirs.setCharAt(Integer.parseInt(pinsTab[anonVar]), '1');

							CommPortSender.send(new ProtocolImpl().getMessage(portsDirs.toString()));
							btnstate1[anonVar].setBackground(Color.GREEN);
							// System.out.println("i: " + anonVar);
						} else if (e.getStateChange() == ItemEvent.DESELECTED) {
							portsDirs.setCharAt(Integer.parseInt(pinsTab[anonVar]), '0');

							CommPortSender.send(new ProtocolImpl().getMessage(portsDirs.toString()));
							btnstate1[anonVar].setBackground(Color.RED);
						}
						break;
					}

				}

				private ItemListener init(int var) {
					anonVar = var;
					return this;
				}
			}.init(i));

		}
	}

	private void initializeTglbutton2() {
		int j = 28;
		for (int i = 0; i < tglbtn2.length; i++) {

			tglbtn2[i] = new JToggleButton("P" + j);
			tglbtn2[i].setBounds(frame.getWidth() / 2 + 141, (frame.getHeight() / 2 - 378) + i * 44, 50, 45);
			frame.getContentPane().add(tglbtn2[i]);
			j--;
			tglbtn2[i].addItemListener(new ItemListener() {
				private int anonVar;

				@Override
				public void itemStateChanged(ItemEvent e) {
					switch (state) {
					case 1:
						if (e.getStateChange() == ItemEvent.SELECTED) {

							portsDirs.setCharAt(Integer.parseInt(pinsTab[anonVar+14]), '1');

							CommPortSender.send(new ProtocolImpl().getMessage(portsDirs.toString()));
							btnstate2[anonVar].setBackground(Color.GREEN);
							// System.out.println("i: " + anonVar);
						} else if (e.getStateChange() == ItemEvent.DESELECTED) {
							portsDirs.setCharAt(Integer.parseInt(pinsTab[anonVar+14]), '0');

							CommPortSender.send(new ProtocolImpl().getMessage(portsDirs.toString()));
							btnstate2[anonVar].setBackground(Color.RED);
						}

						break;
					case 2:
						if (getNumber() == 0) {
							System.out.println("xd");
							portsDirs.setCharAt(6, '0');
						} else if (getNumber() == 1) {
							System.out.println("xd2");
							portsDirs.setCharAt(6, '1');
						} else {
							System.out.println("jebac");
						}
						if (e.getStateChange() == ItemEvent.SELECTED) {

							portsDirs.setCharAt(Integer.parseInt(pinsTab[anonVar+14]), '1');

							CommPortSender.send(new ProtocolImpl().getMessage(portsDirs.toString()));
							btnstate2[anonVar].setBackground(Color.GREEN);
							// System.out.println("i: " + anonVar);
						} else if (e.getStateChange() == ItemEvent.DESELECTED) {
							portsDirs.setCharAt(Integer.parseInt(pinsTab[anonVar+14]), '0');

							CommPortSender.send(new ProtocolImpl().getMessage(portsDirs.toString()));
							btnstate2[anonVar].setBackground(Color.RED);
						}
						break;
					}

				}

				private ItemListener init(int var) {
					anonVar = var;
					return this;
				}
			}.init(i));
		}
	}

	private void initializeSelectOption() {
		selectOption.setBounds(14, 110, 100, 30);
		frame.getContentPane().add(selectOption);
		

		selectOption.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					number = Integer.parseInt(selectOption.getText());
				} catch (NullPointerException exc) {

				} catch (NumberFormatException exc) {

				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					number = Integer.parseInt(selectOption.getText());
				} catch (NullPointerException exc) {

				} catch (NumberFormatException exc) {

				}

			}
		});
	}

	private void initializeBtnstate1() {
		for (int i = 0; i < tglbtn1.length; i++) {
			btnstate1[i] = new JButton();
			btnstate1[i].setBounds(frame.getWidth() / 2 - 141, (frame.getHeight() / 2 - 365) + i * 44, 15, 15);
			// btnstate1[i].setSelected(false);
			// btnstate1[i].setDisabledIcon(btnstate1[i].getIcon());
			btnstate1[i].setBackground(Color.RED);
			frame.getContentPane().add(btnstate1[i]);
			btnstate1[i].addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

				}
			});
		}
	}

	private void initializeBtnstate2() {
		for (int i = 0; i < btnstate2.length; i++) {
			btnstate2[i] = new JButton();
			btnstate2[i].setBounds(frame.getWidth() / 2 + 121, (frame.getHeight() / 2 - 365) + i * 44, 15, 15);
			btnstate2[i].setSelected(false);
			btnstate2[i].setDisabledIcon(btnstate2[i].getIcon());
			btnstate2[i].setBackground(Color.RED);
			frame.getContentPane().add(btnstate2[i]);
			btnstate2[i].addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

				}
			});
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
					// new RS232Example().connect(dupa2);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
