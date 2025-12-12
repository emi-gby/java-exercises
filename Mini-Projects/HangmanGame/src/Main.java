import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        String [] hangmanStages = HangmanString.getString();
        StringBuilder guessedLetters = new StringBuilder();

        String filePath = "Words.txt";
        int numAttempts = 1;

        String randomWord = getRandomWord(filePath);
        ArrayList<String> hiddenWord = new ArrayList<>();

        System.out.println("--------------------------");
        System.out.println("-------Hangman Game-------");
        System.out.println("--------------------------");
        System.out.println(hangmanStages[0]);

        //add symbols to arraylist according to word's length
        for(int i=0; i < randomWord.length(); i++){
            hiddenWord.add(" __ ");  // //
        }



        while(numAttempts <= 6 && hiddenWord.contains(" __ ")){
            // print hidden word
            System.out.print("Word: ");
            for(String symbol: hiddenWord){
                System.out.print(symbol);
            }
            System.out.println();


            System.out.print("Guess a letter: ");
            String userLetter = scanner.next().toLowerCase();

            ArrayList<Integer> returnedIndexes = getIndexLetter(randomWord,userLetter);

            if(guessedLetters.toString().toLowerCase().contains(userLetter)){
                System.out.println("You already guessed the letter "+ userLetter);
                continue;
            }

            if (returnedIndexes.isEmpty()){
                System.out.println("Letter not Found. :(");
                System.out.println(hangmanStages[numAttempts]);
                numAttempts++;
            }
            else{
                System.out.println("Letter "+ userLetter+" is in the word!");
                for(int index : returnedIndexes){

                    hiddenWord.set(index, " "+userLetter);
                }
            }
            guessedLetters.append(userLetter.toUpperCase());
            System.out.println("Guessed letters: " + guessedLetters);
        }

        if(numAttempts>=7){
            System.out.printf("\nYOU LOSE!\nThe word was %s", randomWord);
        }
        else{
            System.out.println(hangmanStages[numAttempts]);
            System.out.printf("\nYOU WON!\nThe word was %s", randomWord);
        }

        scanner.close();


    }
    static String getRandomWord(String filePath){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            Random random = new Random();
            String line;
            StringBuilder wordsString = new StringBuilder();

            while((line = reader.readLine()) != null){
                wordsString.append(line);
            }
            String [] wordsArray = wordsString.toString().split(", "); // //

            int randomInt = random.nextInt(1,wordsArray.length);
            return wordsArray[randomInt];
        }
        catch(FileNotFoundException e){
            System.out.println("Could not locate file location");
            return "Quit";    // //
        }
        catch(IOException e){
            System.out.println("Could not read file");
            return "Quit";    // //
        }
    }
    static ArrayList<Integer> getIndexLetter(String randomWord, String userLetter){
        int indexOfLetter = 0; //count
        //Logic created for identifying repeated letters
        String [] lettersFromWord = randomWord.split("");
        ArrayList<Integer> returnedIndexes = new ArrayList<>();
        for(String letter : lettersFromWord){
            if(letter.equals(userLetter)){
                returnedIndexes.add(indexOfLetter);
            }

            indexOfLetter++;
        }

        return returnedIndexes;
    }
}
