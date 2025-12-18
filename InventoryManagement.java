public class InventoryManagement {

    public static void main(String[] args) {

        String[] items = {
                "Sword",
                "Shield",
                "Health Potion",
                "Magic Ring",
                "Armor"
        };

        int[] weight = {10, 15, 5, 3, 20};     // Backpack weight
        int[] value = {60, 50, 30, 40, 80};   // Item usefulness

        int capacity = 25; // Backpack capacity
        int n = items.length;

        int[][] dp = new int[n + 1][capacity + 1];

        // 0/1 Knapsack logic
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            value[i - 1] + dp[i - 1][w - weight[i - 1]]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("===== RPG Inventory Management =====");
        System.out.println("Backpack Capacity: " + capacity);

        int w = capacity;
        int totalWeight = 0;
        int totalValue = 0;

        System.out.println("\nItems Selected:");
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println(
                        "- " + items[i - 1] +
                        " | Weight: " + weight[i - 1] +
                        " | Value: " + value[i - 1]
                );
                totalWeight += weight[i - 1];
                totalValue += value[i - 1];
                w -= weight[i - 1];
            }
        }

        System.out.println("\nTotal Weight Used: " + totalWeight);
        System.out.println("Total Usefulness Value: " + totalValue);
    }
}
