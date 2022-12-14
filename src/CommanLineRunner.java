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

import java.util.Arrays;
import java.util.Scanner;

public class CommanLineRunner {

	Scanner sc = new Scanner(System.in);
	char gameQuit = 'n'; //sc.next().charAt(0);
	String chosenWord = "none";
	//char chosenChar = 'n';
	String guessedWord = "";
	
	public CommanLineRunner() throws FileNotFoundException, IOException, ParseException {
		this.chosenWord = chooseWord();
		System.out.println("The chosen word is: " + chosenWord);
		//this.chosenChar = chooseChar(chosenWord);
	}
	
	public static void main(String[] args) throws Exception{
		
		CommanLineRunner runner = new CommanLineRunner();
		runner.gameQuit = runner.getInitInput(runner.sc);
		
		
		while (runner.gameQuit == 'Y' || runner.gameQuit == 'y') {
			int counter = 6;
			
			runner.playOneGame();
			//boolean didYouWin = false;
//			while (!didYouWin && counter > 0) {
//				didYouWin = runner.playOneGame();
//				System.out.println("Sorry thats not correct. You have: " + counter + "Tries left" );
//				
//				System.out.println("Please enter your guess word (5 Letters)");
//				runner.guessedWord = runner.sc.next();
//				
//				while (!validateLegalGuess(runner.guessedWord)) {
//					System.out.println("Please follow the input rules");
//					runner.guessedWord = runner.sc.next();
//				}
//				counter++;
//			}
			
			runner.gameQuit = runner.playAnotherGame(runner);
			
			if (runner.gameQuit == 'Y' || runner.gameQuit == 'y') {
				runner.chosenWord = runner.chooseWord();
				System.out.println("The new word is: " + runner.chosenWord);
			}

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
		//char newChar = chosenString.charAt(randNo);
		return chosenString.charAt(randNo);
	}
	
	public char getInitInput(Scanner sc) {
		
		System.out.println("Would you like to play Wordle? Y / N");
		return sc.next().charAt(0);
	}
	
	public boolean playOneGame() {
		boolean didYouWin = false;
		System.out.println("Playing one game");
		System.out.println("Please enter your guess word (5 Letters)");
		this.guessedWord = this.sc.next();
		while (!validateLegalGuess(guessedWord)) {
			System.out.println("Please follow the input rules");
			this.guessedWord = this.sc.next();
		}
		didYouWin = enterGuessingLoop();
		if(didYouWin) { 
			System.out.println("Congrats you won!");
		}
		else {
			System.out.println("Sorry that is not correct. The word was: " + this.chosenWord);
		}
		
		//System.out.println("The word caugth was: " + this.guessedWord);
		
		return didYouWin;
	}
	
	public char playAnotherGame(CommanLineRunner currentGame) {
		System.out.println("Game finished. Would you like to play another game of Wordle? Y / N");
		return currentGame.sc.next().charAt(0);
	}
	
	public static boolean validateLegalGuess(String guess) {
		if (guess.length() != 5) return false;
		if (guess.matches("[^A-Za-z]")) return false;
		
		return true;
	}
	
	public boolean compareGuessToStoredWord() {
		if (this.chosenWord.equals(this.guessedWord)) {
			return true;
		}	
		return false;
	}
	
	public String getFeedbackOnGuess() {
		char[] correctGuessesCorrectSpot = {' ',' ',' ',' ',' '};
		char[] correctGuessIncorrectSpot = {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
		int correctGuessIncorrectSpotIndex = 0;		
		// Correct gueses in correct position
		System.out.println("Your guess was: " + this.guessedWord);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (this.chosenWord.charAt(i) == this.guessedWord.charAt(j)) {
					if (i == j) {
						correctGuessesCorrectSpot[i] = this.chosenWord.charAt(i);
					}
					else {
						correctGuessIncorrectSpot[correctGuessIncorrectSpotIndex]=
								this.guessedWord.charAt(j);
						correctGuessIncorrectSpotIndex++;
					}
				}
			}	
		}
		//System.out.println("Your feed back is: " + Arrays.toString(correctGuesses));
		//String retCorrectGCS = new String(correctGuessesCorrectSpot);
		//System.out.println("HERE" + retCorrectGCS);
		String retCorrctGIC = new String(correctGuessIncorrectSpot);
		
		String retUniqueIncorrect = "";

		//System.out.println("HERE2" + retCorrctGIC);
		return  "The guesses in the correct spot are: \t" + Arrays.toString(correctGuessesCorrectSpot) + "\n" +
				"The guesses in the incorrect spot are: \t" + retCorrctGIC;

	}
	
	public boolean enterGuessingLoop() {
		int noOfGuesses = 6;
		boolean didYouWin = false;
		
		didYouWin = doSingleGuess();
		while ((noOfGuesses > 0) || didYouWin) {
			noOfGuesses--;
			if(didYouWin) return true;
			else {
				System.out.println("Sorry you didnt win: Number of tries left: " + noOfGuesses);
				
				System.out.println("Please enter your guess word (5 Letters)");
				this.guessedWord = this.sc.next();
				while (!validateLegalGuess(guessedWord)) {
					System.out.println("Please follow the input rules");
					this.guessedWord = this.sc.next();
				}
				
				didYouWin = doSingleGuess();
			}
		}
		return doSingleGuess();
	}
	
	public boolean doSingleGuess() {
		boolean guessedResult = false;
		String guessedFeedback = "";
		guessedResult = compareGuessToStoredWord();
		if (guessedResult) {
			System.out.println("You guessed correctly");
			return true;
		}
		else {
			System.out.println("You guessed incorrectly. Below is the feedback\n");
			guessedFeedback = getFeedbackOnGuess();

		}
		System.out.println("Your feed back is: \n" + guessedFeedback + "\n");
		
		return false;
	}
}