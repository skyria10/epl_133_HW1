package Homeworks;

public class Instruction {
	private String command;
	private String arguments;
	
	//This constructor takes two Strings as input : theCommand and theArguments. It sets
	//this.command equal to theCommand and this.arguments equal to theArguments
	public Instruction(String theCommand, String theArguments)
	{
		this.command = theCommand;
		this.arguments = theArguments;
	}
	
	//This method returns a String of the command represented by this Instruction
	public String getCommand()
	{
		return this.command;
	}
	
	//This method returns a String of the arguments represented by this Instruction. The String does
	//not normally contain any parenthesis, unless of course the arguments themselves had parenthesis in them.
	public String getArguments()
	{
		return this.arguments;
	}
}
