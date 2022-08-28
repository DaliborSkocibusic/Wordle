//# Project: Wordle
//
//## MVP
//
//-   Recreate a simplified version of the game Wordle to be played in a Java console application
//-   The game should be able to randomly select a 5-letter word from the provided word list
//-   The user will be able to enter a guess word that is also 5 characters long
//-   For each letter, the application will tell the user if that letter is correct, right letter in the wrong position, or wrong letter
//-   After the user guesses 6 times incorrectly, the game is over and the user loses
//-   If the user guesses the word correctly, the game is over and the user wins
//-   In addition you must implement one of the following extensions, or an extension of your own design as approved by a coach
//
//### Extensions
//
//-   Read the word list directly from the file when the application starts
//-   Create a history file that keeps track of user wins/losses and how many letters they guessed it in
//-   When the game finishes and the secret word is shown, also display the dictionary definition for that word 
//    -   Hint: Use a free API (https://dictionaryapi.dev/)
//-   Generate an output of the word/guesses and copy it to the user's clipboard so they can share it on socials
//    -   Bonus: Use emojis

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Scanner;

public class CommanLineRunner {

	Scanner sc = new Scanner(System.in);
	char gameQuit = 'n'; //sc.next().charAt(0);
	String chosenWord = "none";
	char chosenChar = 'n';
	

	public CommanLineRunner() throws FileNotFoundException, IOException, ParseException {
		this.chosenWord = chooseWord();
		this.chosenChar = chooseChar(chosenWord);
		
	}
	
	public static void main(String[] args) throws Exception{
		
		CommanLineRunner runner = new CommanLineRunner();
		runner.gameQuit = runner.getInitInput(runner.sc);
		
		
		while (runner.gameQuit == 'Y' || runner.gameQuit == 'y') {
			runner.playOneGame();
			runner.gameQuit = runner.playAnotherGame(runner);

		}
		System.out.println("You choose " + runner.gameQuit + " The game has finished.");
	}
	
	public static String chooseWord() throws FileNotFoundException, IOException, ParseException {
		String path = "word-list.json";
		
		Object obj = new JSONParser().parse(new FileReader(path));
		Object var = obj.getClass();
		int randChoice = (int) (Math.random() * 236);
		//String chosenWord = obj.
		JSONArray rec = (JSONArray) obj;
		String retStr = (String) rec.get(randChoice);
		return retStr;
	}
	
	public static char chooseChar(String chosenString) {
		
		int randNo = (int) (Math.random() * 5);
		char newChar = chosenString.charAt(randNo);
		
		return chosenString.charAt(randNo);
	}
	
	public char getInitInput(Scanner sc) {
		
		System.out.println("Would you like to play Wordle? Y / N");
		return sc.next().charAt(0);

	}
	
	public void playOneGame() {
		System.out.println("Playing one game");
	}
	
	public char playAnotherGame(CommanLineRunner currentGame) {
		System.out.println("Game finished. Would you like to play another game of Wordle? Y / N");
		return currentGame.sc.next().charAt(0);
	}
}