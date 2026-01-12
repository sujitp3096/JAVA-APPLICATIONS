import java.util.*;

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] questions = {
            "1) What is the capital of India?",
            "2) Which one is the largest planet?",
            "3) Who invented Java?",
            "4) Which keyword is used to create objects?",
            "5) Which data type stores true/false?"
        };
        String[][] options = {
            {"A) Mumbai", "B) Delhi", "C) Kolkata", "D) Chennai"},
            {"A) Earth", "B) Jupiter", "C) Venus", "D) Mars"},
            {"A) Dennis Ritchie", "B) James Gosling", "C) Bill Gates", "D) Elon Musk"},
            {"A) new", "B) class", "C) try", "D) obj"},
            {"A) int", "B) float", "C) boolean", "D) char"}
        };

        char[] answers = {'B','B','B','A','C'};
        int score = 0;

        System.out.println("=== Simple Quiz Application ===\n");

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            for (String opt : options[i]) {
                System.out.println(opt);
            }

            System.out.print("\nYour answer: ");
            char userAns = Character.toUpperCase(sc.next().charAt(0));

            if (userAns == answers[i]) {
                System.out.println("✔ Correct!\n");
                score++;
            } else {
                System.out.println("✘ Wrong! Correct answer is: " + answers[i] + "\n");
            }
        }

        System.out.println("=== Quiz Completed! ===");
        System.out.println("Your Score: " + score + " / " + questions.length);

        sc.close();
    }
}

