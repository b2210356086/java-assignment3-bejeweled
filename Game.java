import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Game 
{
	
	private ArrayList<ArrayList<Jewel>> grid;
	private ArrayList<String> commands;
	private LinkedHashMap<String,Integer> oldLeaderboard;
	private FileWriter writer1; //Writer for monitoring.txt
	
	public Game(ArrayList<ArrayList<Jewel>> grid, ArrayList<String> commands, LinkedHashMap<String,Integer> leaderboard)
	{
		this.grid = grid;
		this.commands = commands;
		this.oldLeaderboard = leaderboard;
		try 
		{
			this.writer1 = new FileWriter(new File("monitoring.txt"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Plays the game, Biggest method for controlling deleting,scoring,printing mechanisms
	public void startGame() throws IOException
	{
		writer1.write("Game grid:\n\n");
		printGrid();
		int totalScore = 0; //Total Score
		for (String command:commands)
		{
			int score = 0; //Score for single round
			writer1.write("Select coordinate or enter E to end the game: " + command + "\n\n");
			if (command.equals("E"))
			{
				writer1.write("Total score: " + totalScore + " points\n\n");
				String name = commands.get(commands.size()-1);
				writer1.write("Enter name: " + name + "\n\n");
				oldLeaderboard.put(name, totalScore);
				Leaderboard leaderboard = new Leaderboard(oldLeaderboard, name, totalScore, writer1);
				leaderboard.printLeaderboard(); //Outputs the Leaderboard.txt file
				leaderboard.printRank(); //Prints the output line with rank in it
				writer1.write("Good bye!\n");
				writer1.close();
				break;
			}
			else
			{
				int row = Integer.parseInt(command.split(" ")[0]);
				int column = Integer.parseInt(command.split(" ")[1]);
				//Handles the Invalid Coordinate situation by continuing with the next line of commands
				if (row > grid.size()-1 || column > grid.get(0).size()-1 || grid.get(row).get(column) instanceof EmptyJewel)
				{
					writer1.write("Please enter a valid coordinate\n\n");
					continue;
				}
				//
				Jewel target = grid.get(row).get(column); //The jewel with given coordinates via commands file
				ArrayList<String> matchList = new ArrayList<>();
				for (String type : target.getMatchType())
				{
					matchList.add(type);
				}
				boolean isMatched = false; //Stops the match searching loop when a match is found.
				//Loop that changes Num-Pad search direction
				for (int i = 0 ; i < target.getMatchDirection().length && !isMatched ; i++)
				{
					try
					{
						//Switch-Case that checks the matches in Num-Pad directions one by one until a match is found.
						switch(target.getMatchDirection()[i])
						{
							case 1:
								if (matchList.contains(grid.get(row+1).get(column-1).getIcon()) && matchList.contains(grid.get(row+2).get(column-2).getIcon())) // Condition for Correct Jewel Match
								{
									if(target.getIcon().equals("W") && (!grid.get(row+1).get(column-1).getIcon().equals(grid.get(row+2).get(column-2).getIcon())) && (!grid.get(row+1).get(column-1).getIcon().equals("W")&&!grid.get(row+2).get(column-2).getIcon().equals("W"))) //Condition for WWX-WXW-XWW type matches
									{
										;
									}
									else
									{
										score += deleteAndScore(row, column, 1, -1, score); //Deletes jewels and increases score
										isMatched = true; //Stops the iteration because a match is found and deleted
										dropJewels(); //Fills the grid blanks by dropping jewels
										printGrid(); //Prints the grid
										writer1.write("Score: " + score + " points\n\n");
									}
									break;
								}
								break;
							case 2:
								if (matchList.contains(grid.get(row+1).get(column).getIcon()) && matchList.contains(grid.get(row+2).get(column).getIcon()))
								{
									if(target.getIcon().equals("W") && (!grid.get(row+1).get(column).getIcon().equals(grid.get(row+2).get(column).getIcon())) && (!grid.get(row+1).get(column).getIcon().equals("W")&&!grid.get(row+2).get(column).getIcon().equals("W")))
									{
										;
									}
									else
									{	
										score += deleteAndScore(row, column, 1, 0, score);
										isMatched = true;
										dropJewels();
										printGrid();
										writer1.write("Score: " + score + " points\n\n");
									}
									break;
								}
								break;
							case 3:
								if (matchList.contains(grid.get(row+1).get(column+1).getIcon()) && matchList.contains(grid.get(row+2).get(column+2).getIcon()))
								{
									if(target.getIcon().equals("W") && (!grid.get(row+1).get(column+1).getIcon().equals(grid.get(row+2).get(column+2).getIcon())) && (!grid.get(row+1).get(column+1).getIcon().equals("W")&&!grid.get(row+2).get(column+2).getIcon().equals("W")))
									{
										;
									}
									else
									{	
										score += deleteAndScore(row, column, 1, 1, score);
										isMatched = true;
										dropJewels();
										printGrid();
										writer1.write("Score: " + score + " points\n\n");
									}
									break;
								}
								break;
							case 4:
								if (matchList.contains(grid.get(row).get(column-1).getIcon()) && matchList.contains(grid.get(row).get(column-2).getIcon()))
								{
									if(target.getIcon().equals("W") && (!grid.get(row).get(column-1).getIcon().equals(grid.get(row).get(column-2).getIcon())) && (!grid.get(row).get(column-1).getIcon().equals("W")&&!grid.get(row).get(column-2).getIcon().equals("W")))
									{
										;
									}
									else
									{	
										score += deleteAndScore(row, column, 0, -1, score);
										isMatched = true;
										dropJewels();
										printGrid();
										writer1.write("Score: " + score + " points\n\n");
									}
									break;
								}
								break;
							case 6:
								if (matchList.contains(grid.get(row).get(column+1).getIcon()) && matchList.contains(grid.get(row).get(column+2).getIcon()))
								{
									if(target.getIcon().equals("W") && (!grid.get(row).get(column+1).getIcon().equals(grid.get(row).get(column+2).getIcon())) && (!grid.get(row).get(column+1).getIcon().equals("W")&&!grid.get(row).get(column+2).getIcon().equals("W")))
									{
										;
									}
									else
									{	
										score += deleteAndScore(row, column, 0, 1, score);
										isMatched = true;
										dropJewels();
										printGrid();
										writer1.write("Score: " + score + " points\n\n");
									}
									break;
								}
								break;
							case 7:
								if (matchList.contains(grid.get(row-1).get(column-1).getIcon()) && matchList.contains(grid.get(row-2).get(column-2).getIcon()))
								{
									if(target.getIcon().equals("W") && (!grid.get(row-1).get(column-1).getIcon().equals(grid.get(row-2).get(column-2).getIcon())) && (!grid.get(row-1).get(column-1).getIcon().equals("W")&&!grid.get(row-2).get(column-2).getIcon().equals("W")))
									{
										;
									}
									else
									{	
										score += deleteAndScore(row, column, -1, -1, score);
										isMatched = true;
										dropJewels();
										printGrid();
										writer1.write("Score: " + score + " points\n\n");
									}
									break;
								}
								break;
							case 8:
								if (matchList.contains(grid.get(row-1).get(column).getIcon()) && matchList.contains(grid.get(row-2).get(column).getIcon()))
								{
									if(target.getIcon().equals("W") && (!grid.get(row-1).get(column).getIcon().equals(grid.get(row-2).get(column).getIcon())) && (!grid.get(row-1).get(column).getIcon().equals("W")&&!grid.get(row-2).get(column).getIcon().equals("W")))
									{
										;
									}
									else
									{	
										score += deleteAndScore(row, column, -1, 0, score);
										isMatched = true;
										dropJewels();
										printGrid();
										writer1.write("Score: " + score + " points\n\n");
									}
									break;
								}
								break;
							case 9:
								if (matchList.contains(grid.get(row-1).get(column+1).getIcon()) && matchList.contains(grid.get(row-2).get(column+2).getIcon()))
								{
									if(target.getIcon().equals("W") && (!grid.get(row-1).get(column+1).getIcon().equals(grid.get(row-2).get(column+2).getIcon())) && (!grid.get(row-1).get(column+1).getIcon().equals("W")&&!grid.get(row-2).get(column+2).getIcon().equals("W")))
									{
										;
									}
									else
									{	
										score += deleteAndScore(row, column, -1, 1, score);
										isMatched = true;
										dropJewels();
										printGrid();
										writer1.write("Score: " + score + " points\n\n");
									}
									break;
								}
								break;
						}
						//
					}
					catch (Exception e)
					{
						continue;
					}
				}
				//
			}
		if(score == 0)
		{
			printGrid();
			writer1.write("Score: " + score + " points\n\n");
		}
		totalScore += score;
		}
	}
	//
	
	//Fills the blanks of the grid after deletion of jewels
	public void dropJewels()
	{
		for (int i = 0; i < grid.get(0).size(); i++)
		{
			int emptyCount = 0;
			for (int j = 0; j < grid.size(); j++)
			{
				if (grid.get(j).get(i).getIcon().equals(" "))
				{
					emptyCount++;
					if (!(emptyCount == grid.size()))
					{
						for (int y = grid.size()-1 ; y > -1 ; y--)
						{
							for (int x = 0 ; x < grid.get(0).size(); x++)
							{
								if(grid.get(y).get(x).getIcon().equals(" "))
								{
									for (int row = y; row > 0; row--)
									{
										grid.get(row).set(x, grid.get(row-1).get(x));
									}
									grid.get(0).set(x, new EmptyJewel());
								}
							}
						}
					}
				}
			}
		}
	}
	//
	
	//Deletes 3 jewels going in 2 different row and column directions (- , +), fills those blanks with EmptyJewels, and returns total jewel value which is later added to the score.
	public int deleteAndScore(int row, int column, int rowChange, int columnChange, int score)
	{
		int value = grid.get(row).get(column).getValue() + grid.get(row+rowChange).get(column+columnChange).getValue() + grid.get(row+2*rowChange).get(column+2*columnChange).getValue();
		grid.get(row).set(column, new EmptyJewel());
		grid.get(row+rowChange).set(column+columnChange, new EmptyJewel());
		grid.get(row+2*rowChange).set(column+2*columnChange, new EmptyJewel());
		return value;
	}
	//
	
	//Prints the grid
	public void printGrid() throws IOException
	{
		for (ArrayList<Jewel> jewelLine : grid)
		{
			for(Jewel jewel: jewelLine)
			{
				if (jewel.equals(jewelLine.get(jewelLine.size()-1)))
				{
					writer1.write(jewel+"");
				}
				else
				{
					writer1.write(jewel+" ");
				}
			}
			writer1.write("\n");
		}
		writer1.write("\n");
	}
	//
}