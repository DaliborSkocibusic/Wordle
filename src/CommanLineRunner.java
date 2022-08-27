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
//    


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CommanLineRunner {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String path = "word-list.json";
//
//		Object obj = null;
//		try {
			Object obj = new JSONParser().parse(new FileReader(path));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		JSONObject jo = (JSONObject) obj;
//		
////		JSONTokener reader = Json.createReader(new FileReader(path));
////		
////		
////		JSONPointer jsonPointer = new JSONPointer(path);
////		JSONString jsonString = (JSONString) jsonPointer.getValue(jsonStructure);
//		
//		// TODO Auto-generated method stub

		System.out.println("Game Running");
	}

}
