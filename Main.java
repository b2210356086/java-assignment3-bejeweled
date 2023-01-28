import java.io.IOException;

public class Main 
{
	
	public static void main(String[] args) throws IOException 
	{	
		InputReader reader = new InputReader(args[1], args[0], "leaderboard.txt");
		reader.read(); //Reads input files
		Game game = new Game(reader.getGrid(),reader.getCommands(),reader.getLeaderboard());
		game.startGame(); //Starts and plays the game
	}

}