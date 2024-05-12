import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    private static final int QUESTION_TIME_LIMIT = 30;
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static boolean[] userAnswers;

    private static String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'To Kill a Mockingbird'?"
    };

    private static String[][] options = {
            {"A. London", "B. Paris", "C. Rome", "D. Berlin"},
            {"A. Mars", "B. Jupiter", "C. Saturn", "D. Earth"},
            {"A. Harper Lee", "B. Mark Twain", "C. J.K. Rowling", "D. Charles Dickens"}
    };

    private static char[] correctAnswers = {'B', 'A', 'A'};

    public static void main(String[] args) {
        userAnswers = new boolean[questions.length];
        startQuiz();
    }

    private static void startQuiz() {
        final Timer timer = new Timer(); 
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ":");
            System.out.println(questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.\n");
                    askQuestion(scanner, timer);
                }
            };
            timer.schedule(task, QUESTION_TIME_LIMIT * 1000); 

            askQuestion(scanner, timer);

            timer.cancel(); 
            
        }

        displayResult();
    }

    private static void askQuestion(Scanner scanner, Timer timer) {
        System.out.print("Your answer (enter A, B, C, or D): ");
        String userAnswer = scanner.nextLine().toUpperCase();

        if (userAnswer.length() == 1 && "ABCD".contains(userAnswer)) {
            char answer = userAnswer.charAt(0);
            int currentQuestion = currentQuestionIndex++;
            if (answer == correctAnswers[currentQuestion]) {
                System.out.println("Correct!");
                score++;
                userAnswers[currentQuestion] = true;
            } else {
                System.out.println("Incorrect!");
                userAnswers[currentQuestion] = false;
            }
        } else {
            System.out.println("Invalid input. Please enter A, B, C, or D.");
        }

        System.out.println();
    }

    private static void displayResult() {
        System.out.println("Quiz finished!");
        System.out.println("Your score: " + score + "/" + questions.length);
        System.out.println("Summary of answers:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + (userAnswers[i] ? "Correct" : "Incorrect"));
        }
    }
}
