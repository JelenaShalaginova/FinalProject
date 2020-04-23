package FinalProject;
import java.util.Scanner;
import java.util.Random;


/*
Task: to create a game Hangman which invites the player to guess the secret word provided by computer
entering letter by letter until the whole word is guessed or until lives are over. Each time the game
is finished system should ask user whether he/she would like to continue. If yes - the game starts again, 
if no - system shows previous games statistics.
 */

public class Hangman01 {
	
	public static void main (String [] args) {
		
		//declare objects
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		//introduce string array containing word library
		String [] SecretWord = {"woodpecker", "eagle", "pelican", "condors", "owl", "goose", "hawk",
				"heron", "sparrow", "flamingo", "stork", "falcon", "duck", "pigeon", "skylark",
				"turkey", "albatross", "hummingbird", "bittern"};
		
		int won = 0;
		int lost = 0;
		
		//declare boolean variable
		boolean GameContinues = true;
		
		//main loop where the game takes place
		while (GameContinues) {
			System.out.println("Welcome to the Hangman game!\n\nThis game is about birds!\nLet's begin!\n");
			
			//retrieving a random word from the array 
			//and breaking down the word into individual characters
			char[] randomWord = SecretWord[random.nextInt(SecretWord.length)].toCharArray();
			
			//indicate amount of lives player has
			int lives = randomWord.length;
			
			//create an empty char [] to store players input
			char[] hiddenWord = new char[lives];
			
			//encrypt all letters in random word to underscores
			for (int i = 0; i < hiddenWord.length; i++) {
			hiddenWord[i] = '_';
			}
			
			//declare boolean value
			boolean playerGuessed = false;
			
			//while loop 
			while (!playerGuessed && lives>0) {
			System.out.println("Secret Word: ");
			printArray(hiddenWord);
			System.out.println("Number of lives left " + lives);
			System.out.println("Enter one letter: ");
			//store players input in variable "letter" and convert the input into one character
			//if player inputs more than one letter
			char letter = scanner.nextLine().charAt(0);
			//converting inputed letter to lower case
			letter = Character.toLowerCase(letter);

			if (letter == '_') {
				GameContinues = false;
				playerGuessed = true;
				
			}else {
				boolean isLetterFound = false;
				//check whether letter is found in the word and
				//substitute "_" with player input
				for (int i = 0; i < randomWord.length; i++) {
					if (randomWord[i] == letter) {
						hiddenWord[i] = letter;
						isLetterFound = true;
						
				}
			}
			if (!isLetterFound) {
				lives--;
			}
				
			if (isTheWordGuessed(hiddenWord)) {
				playerGuessed = true;
				System.out.println("Congratulations you won!!!");
				System.out.println("Secret word is: ");
				printArray(randomWord);
				System.out.println("======================================");
				won++;
			}
			
			}
				
		}
			
		//output in case player ran out of guesses 	
		if(!playerGuessed) {
			System.out.println("Sorry, you have ran out of guesses!");
			System.out.println("Secret word is: ");
			printArray(randomWord);
			lost++;
		}
		
		
		//output asking if the player wants to continue playing
			System.out.println("Do you want to play another game (yes/no)");
			String newGame = scanner.nextLine();
			if (newGame.equals("no")) {
				GameContinues = false;
			
		}
			
		}//End of GameContinues
		
		System.out.println("Game Over!");
		System.out.println("Games won: " + won);
		System.out.println("Games lost: " + lost);
		
		
	}//end main
	 
		public static void printArray(char[] array) {
			for (int i = 0; i<array.length; i++) {
				System.out.print(array[i] + " "); 
			}
			System.out.println();
			}	
		
		public static boolean isTheWordGuessed(char[]array) {
			for (int i = 0; i<array.length; i++) {
			if(array[i] == '_') return false;
		}
			return true;
	
	}
	
}//end class
