import java.util.*;

public class RecipeGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] ingredients = {
            "Tomato", "Potato", "Chicken", "Egg", "Rice", 
            "Milk", "Cheese", "Bread", "Pasta", "Onion"
        };
        String[] recipes = {
            "Tomato Soup",
            "Potato Fry",
            "Chicken Curry",
            "Egg Omelette",
            "Fried Rice",
            "Milk Shake",
            "Cheese Sandwich",
            "Garlic Bread",
            "Pasta Alfredo",
            "Onion Pakoda"
        };
        System.out.println("---- Recipe Generator ----");
        System.out.println("Enter any ingredient:");

        String input = sc.nextLine().toLowerCase();

        boolean found = false;

        for (int i = 0; i < ingredients.length; i++) {
            if (ingredients[i].toLowerCase().contains(input)) {
                System.out.println("Suggested Recipe: " + recipes[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No recipe found for this ingredient.");
        }

        sc.close();
    }
}


