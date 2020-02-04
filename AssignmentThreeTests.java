package Homeworks;

public class AssignmentThreeTests
{
	public static void main(String[] args) throws Exception
	{
		testTxtFile();
		testHardDrive();
		testComputer();
		testInstructionList();
	}
	
	public static void testTxtFile()
	{
		System.out.println("Testing TxtFile.java .....");
		TxtFile testFile = new TxtFile("foo", "bar");
		assertEquals("foo", testFile.getName(), "Testing getName()");
		assertEquals("bar", testFile.getData(), "Testing getData()");
		testFile.setName("foo2");
		assertEquals("foo2", testFile.getName(), "Testing getName after setName");
		testFile.setData("bar2");
		assertEquals("bar2", testFile.getData(), "Testing getData after setData");
		testFile.appendData("more");
		assertEquals("bar2more", testFile.getData(), "Testing getData after appendData");
	}
	
	public static void testHardDrive() throws Exception
	{
		System.out.println("Testing HardDrive.java .....");
		HardDrive drive = new HardDrive();
		drive.addFile("name", "dan pomerantz");
		assertEquals(true, drive.exists("name"), "Testing exists after adding a file");
		assertEquals(false, drive.exists("dan pomerantz"), "Testing exists after NOT adding a file");
		drive.addFile("address", "montreal");
		assertEquals(0, drive.indexOfFile("name"), "Testing indexOfFile");
		assertEquals(1, drive.indexOfFile("address"), "Testing indexOfFile");
		assertEquals("dan pomerantz", drive.getContent("name"), "Testing getContent");
		drive.appendFile("name", ", course lecturer");
		assertEquals("dan pomerantz, course lecturer", drive.getContent("name"), "Testing getContent after append");
		
		drive.renameFile("name", "full name");
		assertEquals(0, drive.indexOfFile("full name"), "Testing indexOfFile after renaming");
		assertEquals(-1, drive.indexOfFile("name"), "Testing indexOfFile to make sure after renaming that the old file is gone.");
		
		String[] files = drive.listFiles();
		assertEquals(2, files.length, "Testing that the count of files returned is correct");
		assertEquals("full name", files[0], "Testing listFiles");
		assertEquals("address", files[1], "Testing listFiles");
		
		drive.deleteFile("full name");
		assertEquals(-1, drive.indexOfFile("full name"), "Testing indexOfFile after deleting a file");
		
		try
		{
			drive.deleteFile("foo");
		}
		catch (Exception e)
		{
			System.out.println("There was a problem in deleting a file that does exist. The error was:");
			throw e;
		}
	}
	
	public static void testComputer()
	{
		System.out.println("Testing Computer.java to make sure it doesn't crash.....");
		Computer computer = new Computer(); //boy I wish it were actually that easy to make a new Computer :)
		computer.turnOff();
		computer.turnOn();
		computer.installOS();
	}
	
	public static void testInstructionList()
	{
		System.out.println("Testing InstructionList.java .....");
		InstructionList list = new InstructionList();
		list.addCommand("add", "filename,data");
		assertEquals("add(filename,data)", list.getNextInstruction(), "Testing getNextInstruction() after adding");
		
		list.addCommand("add", "file2, data2");
		list.addCommand("add", "file3, data3");
		String[] loadedTwoInstructions = list.getLoadedInstructions();
		assertEquals(2, loadedTwoInstructions.length, "Testing that more than one instruction can be stored and then accessed.");
		assertEquals(true, list.hasNext(), "Testing hasNext() when there is an instruction");
		list.getNextInstruction();
		list.getNextInstruction();
		assertEquals(false, list.hasNext(), "Testing hasNext() when there are no instructions");
		
		list.addCommand("add", "filename2,data);delete(.operating_system");
		String[] loadedInstructions = list.getLoadedInstructions();
		assertEquals(1, loadedInstructions.length, "Testing that instructions are properly separated");
	}
	
	private static void assertEquals(String expected, String actual, String message)
	{
		if (expected.equals(actual))
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL : Expected: " + expected + " , Actual: " + actual + " , Test type: " + message);
		}
	}
	
	private static void assertEquals(boolean expected, boolean actual, String message)
	{
		if (expected == actual)
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL : Expected: " + expected + " , Actual: " + actual + " , Test type: " + message);
		}
	}
	
	private static void assertEquals(int expected, int actual, String message)
	{
		if (expected == actual)
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL : Expected: " + expected + " , Actual: " + actual + " , Test type: " + message);
		}
	}
}
