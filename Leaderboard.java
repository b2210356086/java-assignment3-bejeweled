import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;


public class Leaderboard 
{
	
	private LinkedHashMap<String,Integer> leaderboard;
	private String name;
	private int score;
	private FileWriter writer1; //Writer for monitoring.txt
	private FileWriter writer2; //Writer for leaderboard.txt
	
	public Leaderboard(LinkedHashMap<String,Integer> leaderboard, String name, int score, FileWriter writer1)
	{
		this.leaderboard = leaderboard;
		this.name = name;
		this.score = score;
		this.writer1 = writer1;
		try 
		{
			this.writer2 = new FileWriter(new File("leaderboard.txt"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Prints the leaderboard for leaderboard.txt
	public void printLeaderboard() throws IOException
	{
		for (Map.Entry<String, Integer> entry : leaderboard.entrySet())
		{
			writer2.write(entry.getKey() + " " + entry.getValue() + "\n");
		}
		writer2.close();
	}
	//
	
	//Prints the rank functionality for monitoring.txt
	public void printRank() throws IOException
	{
		Comparator<Map.Entry<String,Integer>> comparator = new Comparator<Map.Entry<String,Integer>>()
		{
			@Override
			public int compare(Map.Entry<String, Integer> person1, Map.Entry<String, Integer> person2)
			{
				return -person1.getValue().compareTo(person2.getValue());
			}
		};
		
		ArrayList<Map.Entry<String,Integer>> leaderboardList = new ArrayList<>(leaderboard.entrySet());
		Collections.sort(leaderboardList, comparator); //Sorts the leaderboard according to scores in descending order
		for(int i = 0; i < leaderboardList.size(); i++)
		{
			if (leaderboardList.get(i).getKey().equals(name))
			{
				int rank = i+1;
				writer1.write("Your rank is " + rank + "/" + leaderboardList.size());
				if (rank == 1)
				{
					writer1.write(", your score is " + (score-leaderboardList.get(i+1).getValue()) + " points higher than " + leaderboardList.get(i+1).getKey() + "\n\n");
				}
				else if (rank == leaderboardList.size())
				{
					writer1.write(", your score is " + (leaderboardList.get(i-1).getValue()-score) + " points lower than " + leaderboardList.get(i-1).getKey() + "\n\n");
				}
				else
				{
					writer1.write(", your score is " + (leaderboardList.get(i-1).getValue()-score) + " points lower than " + leaderboardList.get(i-1).getKey() + " and " + (score-leaderboardList.get(i+1).getValue()) + " points higher than " + leaderboardList.get(i+1).getKey() + "\n\n");
				}
			}
		}
	}
	//
}
