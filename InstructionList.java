package Homeworks;

public class InstructionList
{
	private String savedCommands = "";

	//This method takes as input a String newCommand, a String arguments
	//and appends to savedCommands the string newCommand + "(" + arguments + ")"
	public String addCommand(String newCommand, String arguments)
	{
		if (arguments.startsWith("."))
		{
			return "Cannot modify a file that starts with . as this is a critical file!";
		}
	
		this.savedCommands = this.savedCommands + newCommand + "(" + arguments + ");";
		return "Successfully added.";
	}

	//This checks to see if there are any instructions left to be executed. In the original version
	//this can be done by looking to see if there are any ; in the String savedCommands
	public boolean hasNext()
	{
		return this.savedCommands.indexOf(";") > 0;
	}

	//This method should return the next instruction that is available.
	//Currently it does this by looking for the first ; in the string savedCommands.
	//It then returns the String savedCommands up until the first ; and updates
	//saved commands to remove the first command up until the ; so that the command does
	//not get executed multiple times.
	public String getNextInstruction()
	{
		int semicolonIndex = this.savedCommands.indexOf(";");
		String instruction = this.savedCommands.substring(0, semicolonIndex);
		this.savedCommands = this.savedCommands.substring(instruction.length() + 1);
		return instruction;
	}
	
	//This method returns a String[] of all the instructions left to be executed.
	//split does this very easily in the initial case.
	public String[] getLoadedInstructions()
	{
		return this.savedCommands.split(";");
	}
}
