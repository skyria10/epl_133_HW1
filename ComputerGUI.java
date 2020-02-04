package Homeworks;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ComputerGUI extends JFrame {
	private Computer computer;
	private JTextField errorMessage = new JTextField(50);
	private JTextArea cpuCommandsLoaded;
	private JTextArea cpuOutput;
	private String selectedCommandType;
	private JTextField selectedCommandArgument = new JTextField(40);
	private JTextField computerState = new JTextField(30);

	//create a window and leave it open
 	public static void main(String[] args)
	{
		ComputerGUI window = new ComputerGUI();
		window.setVisible(true);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	//initialize GUI components
	public ComputerGUI()
	{
		this.computer = new Computer();
		this.setTitle("Computer Simulator");
		this.setContentPane(generateGui());
		this.pack();
	}

	//this method is used to generate borders with both colour and text
	private TitledBorder createBorder(String text, Color color)
	{
		Border lineBorder = BorderFactory.createLineBorder(color);
		return new TitledBorder(BorderFactory.createTitledBorder(lineBorder, text));
	}

	//this method generates the main GUI panel
	private JPanel generateGui()
	{
		JPanel gui = new JPanel();
		gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));

		JPanel computerOptions = this.generateComputerOptions();
		gui.add(computerOptions);

		JPanel commandOptions = this.generateCommandOptions();
		gui.add(commandOptions);
		
		JPanel computerOutput = this.generateExecutePanel();
		gui.add(computerOutput);

		return gui;
	}
	
	//This method generates the panel of the buttons
	//Computer options will have 3 buttons : 1)turn on, 2)turn off, 3)reformat drive
	private JPanel generateComputerOptions()
	{
		JPanel computerOptions = new JPanel();
		computerOptions.setLayout(new FlowLayout());
		
		//turn on button
		JButton turnOnButton = new JButton("Turn on computer");
		turnOnButton.addActionListener(new TurnOnButtonListener());
		computerOptions.add(turnOnButton);
	
		//turn off button
		JButton turnOffButton = new JButton("Shut down");
		turnOffButton.addActionListener(new TurnOffButtonListener());
		computerOptions.add(turnOffButton);
		
		//format drive
		JButton installOS = new JButton("Install OS");
		installOS.addActionListener(new InstallOSButtonListener());
		
		computerState.setText("Off, no OS installed!");
		
		computerOptions.add(installOS);
		computerOptions.add(turnOnButton);
		computerOptions.add(turnOffButton);
		computerOptions.add(computerState);
		
		computerOptions.setBorder(createBorder("Computer Buttons", Color.blue));
		
		return computerOptions;
	}
	
	//This method generates the panel containing the ability to add commands and display stored commands
	private JPanel generateCommandOptions()
	{
		JPanel commandOptions = new JPanel();
		commandOptions.setLayout(new BoxLayout(commandOptions, BoxLayout.Y_AXIS));
		
		JPanel enterCommandsPanel = new JPanel();
		enterCommandsPanel.setLayout(new FlowLayout());
		
		JComboBox commandList = new JComboBox(Computer.getValidCommands());
		commandList.setSelectedIndex(0);
		this.selectedCommandType = (String)commandList.getSelectedItem();
		commandList.addActionListener(new SelectionChangedListener());
		enterCommandsPanel.add(commandList);
		enterCommandsPanel.add(this.selectedCommandArgument);
		JButton addCommandsButton = new JButton("Add commands");
		addCommandsButton.addActionListener(new AddInstructionButtonListener());
		enterCommandsPanel.add(addCommandsButton);
		enterCommandsPanel.add(errorMessage);
		
		commandOptions.add(enterCommandsPanel);
				
		cpuCommandsLoaded = new JTextArea("", 10, 50);
		commandOptions.add(cpuCommandsLoaded);
		cpuCommandsLoaded.setBorder(createBorder("Commands", Color.green));

		JScrollPane scrollPane = new JScrollPane(cpuCommandsLoaded, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		commandOptions.add(scrollPane, BorderLayout.CENTER);

		commandOptions.setBorder(createBorder("Enter Commands", Color.blue));
		
		return commandOptions;
	}
	
	//this method generates the panel that shows the computers execution
	private JPanel generateExecutePanel()
	{
		JPanel executePanel = new JPanel();
		executePanel.setLayout(new BoxLayout(executePanel, BoxLayout.Y_AXIS));
		
		JButton executeCommandsButton = new JButton("Execute commands");
		executeCommandsButton.addActionListener(new ExecuteInstructionsButtonListener());
		executePanel.add(executeCommandsButton);
		
		cpuOutput = new JTextArea("", 10, 50);
		cpuOutput.setBorder(createBorder("CPU Output", Color.red));		
		executePanel.add(cpuOutput);
		
		JScrollPane scrollPane = new JScrollPane(cpuOutput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		executePanel.add(scrollPane, BorderLayout.CENTER);
		
		executePanel.setBorder(createBorder("Run commands", Color.blue));
		
		return executePanel;
	}

	//this listener is used to detect a change in the select window
	class SelectionChangedListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JComboBox comboBox = (JComboBox)e.getSource();
			selectedCommandType = (String)comboBox.getSelectedItem();
		}
	}

	//this listener is used when the turn on button is hit
	class TurnOnButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			computerState.setText("Computer is on");
			computer.turnOn();
		}
	}

	//this listener is used when the turn off button is hit
	class TurnOffButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		    computerState.setText("Computer is off");
			computer.turnOff();
		}
	}
	
	//this listener is used when the InstallOS button is hit
	class InstallOSButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			computerState.setText("Computer is now reformated");
			computer.installOS();
		}
	}

	//this listener is used when the AddInstructionButton is hit
	class AddInstructionButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String message = computer.addInstruction(selectedCommandType, selectedCommandArgument.getText());
			errorMessage.setText(message);
			updateInstructions();
		}
	}

	//this listener is activated when the ExecuteInstructions button is hit
	class ExecuteInstructionsButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String message = computer.executeCommands();
			cpuOutput.setText(message);
			updateInstructions();
		}
	}
	
	private void updateInstructions()
	{
			cpuCommandsLoaded.setText("");
			String[] loaded = computer.getLoadedInstructions();

			for (int i = 0; i < loaded.length; i++)
			{
				cpuCommandsLoaded.append(loaded[i] + "\n");
			}
			
			selectedCommandArgument.setText("");	
	}
}