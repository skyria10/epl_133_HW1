package Homeworks;

public class Computer
{
	private HardDrive hardDrive;
	private InstructionList instructionList;
	private boolean isOn;

	private static final String COMPUTER_OFF_ERROR = "The computer is not on so you can't perform that operation!";
	private static final String CORRUPT_OPERATING_SYSTEM_ERROR = "The operating system on the SIMULATED computer is corrupt! You have to reformat the drive and start again!";
	private static String[] commands = {"add", "rename", "delete", "appendFile", "list", "showFile"};
	
	public Computer() 
	{
		this.hardDrive = new HardDrive();
		this.instructionList = new InstructionList();
		this.isOn = false;
	}

	public void turnOff()
	{
		this.isOn=false;
	}

	public void turnOn()
	{
		this.isOn=true;
	}
	
	public void installOS(){
		hardDrive=new HardDrive();
		hardDrive.addFile(".operating system","WINDOWS 10");
	}
	
	//This method returns a list of valid commands. It is used by the UI to
	//display the options.
	public static String[] getValidCommands()
	{
		String[] duplicateCommandList = new String[commands.length];
		for (int i = 0; i < duplicateCommandList.length; i++)
		{
			duplicateCommandList[i] = Computer.commands[i];
		}
		
		return duplicateCommandList;
	}
	
	//This method returns a list of all the loaded instructions as a String[] . The
	//method is called by the UI to display the loaded options
	public String[] getLoadedInstructions()
	{
		return this.instructionList.getLoadedInstructions();
	}

	//This method will add a new instruction to the InstructionList
	public String addInstruction(String instruction, String arguments)
	{
		if (!this.isOn)
		{
			return Computer.COMPUTER_OFF_ERROR;
		}

		return this.instructionList.addCommand(instruction, arguments);
	}

	//This method will execute all commands stored currently in the InstructionList
	public String executeCommands()
	{
		if (!this.hardDrive.exists(".operating_system"))
		{
			return Computer.CORRUPT_OPERATING_SYSTEM_ERROR;
		}
	
		if (!this.isOn)
		{
			return Computer.COMPUTER_OFF_ERROR;
		}

		String message = "";
		while (this.instructionList.hasNext())
		{
			message += executeCommand(this.instructionList.getNextInstruction());
		}

		return message;
	}
	
	//This method will execute the applicable commands. command needs to be parsed in order to figure out
	//which part of it is the argument and which part is the command itself.
	private String executeCommand(String command)
	{
		//arguments is everything after the first ( and before the last )
		if (command.indexOf('(') < 0 || command.lastIndexOf(')') < 0)
		{
			return "Syntax error in command";
		}
		
		String arguments = command.substring(command.indexOf('(') + 1, command.lastIndexOf(')'));
		String commandType = command.substring(0, command.indexOf('('));
		if (commandType.equals("add"))
		{
			return addFile(arguments);
		}
		else if (commandType.equals("delete"))
		{
			return deleteFile(arguments);
		}
		else if (commandType.equals("list"))
		{
			return listFiles();
		}
		else if (commandType.equals("rename"))
		{
			return renameFile(arguments);
		}
		else if (commandType.equals("appendFile"))
		{
			return appendFile(arguments);
		}
		else if (commandType.equals("showFile"))
		{
			return getFileContents(arguments);
		}
		else
		{
			return "Command " + command + " not found";
		}
	}

	private String getFileContents(String file)
	{
		String data = this.hardDrive.getContent(file);
		if (data == null)
		{
			return "File " + file + " not found\n";
		}
		else
		{
			return data;
		}
	}

	private String addFile(String arguments)
	{
		String[] splitArgs = arguments.split(",");
		if (splitArgs.length != 2)
		{
			return "Format: add: file, data. Example, to create a file foo with data blah, type add(foo,blah)";
		}
		
		return this.hardDrive.addFile(splitArgs[0], splitArgs[1]) ? "Added file " + splitArgs[0] + "\n": "Error adding file " + splitArgs + "\n";
	}
	
	private String renameFile(String arguments)
	{
		String[] splitArgs = arguments.split(",");
		if (splitArgs.length != 2)
		{
			return "Format: rename: oldname, newName Example, rename(foo,blah)";
		}
		
		return this.hardDrive.renameFile(splitArgs[0], splitArgs[1]) ? "Renamed file " + splitArgs[0] + "\n": "Error renaming file " + splitArgs + "\n";
	}
	
	private String appendFile(String arguments)
	{
		String[] splitArgs = arguments.split(",");
		if (splitArgs.length != 2)
		{
			return "Format: append: file, newContent Example, append(foo,blah)";
		}
		
		return this.hardDrive.appendFile(splitArgs[0], splitArgs[1]) ? "Appeneded file " + splitArgs[0] + "\n": "Error appending to file " + splitArgs + "\n";
	}
	
	private String deleteFile(String arguments)
	{
		if (this.hardDrive.deleteFile(arguments))
		{
			return "Deleted " + arguments + "\n";
		}
		else
		{
			return "Error deleting file " + arguments + "\n";
		}
	}

	private String listFiles()
	{
		String[] filenames = this.hardDrive.listFiles();
		String fileList = "";
		for (int i=0; i < filenames.length; i++)
		{
			fileList += filenames[i] + "\n";
		}
		
		return fileList;
	}
}
