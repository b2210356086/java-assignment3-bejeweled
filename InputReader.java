import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class InputReader 
{
	
	private String commandFile;
	private String gridFile;
	private String leaderboardFile;
	private Scanner scannerGrid;
	private Scanner scannerCommand;
	private Scanner scannerLeaderboard;
	private ArrayList<ArrayList<Jewel>> grid = new ArrayList<>();
	private ArrayList<String> commands = new ArrayList<>();
	private LinkedHashMap<String,Integer> leaderboard = new LinkedHashMap<>();
	
	public InputReader(String commandFile, String gridFile, String leaderboardFile)
	{
		this.commandFile = commandFile;
		this.gridFile = gridFile;
		this.leaderboardFile = leaderboardFile;
	}
	
	//Reads input files and puts the info into useful Collection types
	public void read()
	{
		try
		{
			scannerGrid = new Scanner(new File(gridFile));
			scannerCommand = new Scanner(new File(commandFile));
			scannerLeaderboard = new Scanner(new File(leaderboardFile));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}
		
		//Builds the game grid by creating categorized jewel objects
		String[] mathJewels = {"/", "-", "+", "\\", "|"};
		String[] letterJewels = {"D", "S", "T", "W"};
		while (scannerGrid.hasNext())
		{
			ArrayList<Jewel> jewelLine = new ArrayList<>();
			String[] line = scannerGrid.nextLine().split(" ");
			for (String icon : line)
			{
				if(Arrays.asList(mathJewels).contains(icon))
				{
					jewelLine.add(new MathJewel(icon));
				}
				else if(Arrays.asList(letterJewels).contains(icon))
				{
					jewelLine.add(new LetterJewel(icon));
				}
			}
			this.grid.add(jewelLine);
		}
		//
		
		while (scannerCommand.hasNext())
		{
			String line = scannerCommand.nextLine();
			this.commands.add(line);
		}
		
		while (scannerLeaderboard.hasNext())
		{
			String[] line = scannerLeaderboard.nextLine().split(" ");
			this.leaderboard.put(line[0],Integer.parseInt(line[1]));
		}
	}
	//
	
	//GETTER-SETTERS
	public ArrayList<ArrayList<Jewel>> getGrid()
	{
		return this.grid;
	}
	
	public ArrayList<String> getCommands()
	{
		return this.commands;
	}
	
	public LinkedHashMap<String,Integer> getLeaderboard()
	{
		return this.leaderboard;
	}
	//
}