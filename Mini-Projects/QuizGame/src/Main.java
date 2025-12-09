import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        String[][] questions = {
                {
                        "Which scientific principle allows airplanes to generate lift?",
                        "1. Bernoulli's principle",
                        "2. Newton's third law",
                        "3. Ohm's law",
                        "4. Pascal's principle"
                },
                {
                        "Which ancient civilization built the city of Machu Picchu?",
                        "1. Mayan",
                        "2. Incan",
                        "3. Aztec",
                        "4. Olmec"
                },
                {
                        "In computer science, what does the term 'Big O notation' describe?",
                        "1. The beauty of code formatting",
                        "2. The growth rate of an algorithm's complexity",
                        "3. The memory size of a compiled program",
                        "4. The efficiency of CPU architecture"
                },
                {
                        "Which gas is responsible for absorbing most ultraviolet radiation in Earth's atmosphere?",
                        "1. Carbon dioxide",
                        "2. Nitrogen",
                        "3. Ozone",
                        "4. Argon"
                },
                {
                        "Who wrote the dystopian novel '1984'?",
                        "1. Aldous Huxley",
                        "2. George Orwell",
                        "3. Isaac Asimov",
                        "4. Ray Bradbury"
                },
                {
                        "Which planet has the largest volcanic mountain in the solar system?",
                        "1. Earth",
                        "2. Jupiter",
                        "3. Venus",
                        "4. Mars"
                }
        };

        int[] correctAnswers = { 1, 2, 2, 3, 2, 4 };
        int questionIndex = 0;
        int correctQuestions = 0;
        int totalQuestions = questions.length;

        System.out.print("----QUIZ GAME----");
        for(String[] question : questions){
            for(String text : question){
                System.out.println(text);
            }
            System.out.print("Your guess (1-4): ");
            int answer = scanner.nextInt();
            if (answer == correctAnswers[questionIndex]){
                correctQuestions++;
                System.out.println("----------------");
                System.out.println("CORRECT!");
                System.out.println("----------------");
            }
            else{
                System.out.println("----------------");
                System.out.println("INCORRECT!");
                System.out.println("----------------");
            }
            questionIndex++;
        }
        System.out.printf("You got %d/%d", correctQuestions,totalQuestions);
    }
}

