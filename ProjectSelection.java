import java.util.*;

public class ProjectSelection {

    public static void main(String[] args) {

        String[] projects = {
                "AI Chatbot",
                "E-commerce Website",
                "Mobile App",
                "Cloud Migration",
                "Data Analytics Tool"
        };
        int[] cost = {40, 60, 30, 50, 20};     // Investment required
        int[] profit = {100, 120, 90, 110, 70}; // Expected ROI

        int budget = 100;
        int n = projects.length;

        int[][] dp = new int[n + 1][budget + 1];

        // 0/1 Knapsack logic
        for (int i = 1; i <= n; i++) {
            for (int b = 0; b <= budget; b++) {
                if (cost[i - 1] <= b) {
                    dp[i][b] = Math.max(
                            dp[i - 1][b],
                            profit[i - 1] + dp[i - 1][b - cost[i - 1]]
                    );
                } else {
                    dp[i][b] = dp[i - 1][b];
                }
            }
        }

        System.out.println("===== Project Selection System =====");
        System.out.println("Total Budget: $" + budget);

        // Backtracking to find selected projects
        int b = budget;
        int totalCost = 0;
        int totalProfit = 0;

        System.out.println("\nSelected Projects:");
        for (int i = n; i > 0; i--) {
            if (dp[i][b] != dp[i - 1][b]) {
                System.out.println(
                        "- " + projects[i - 1] +
                        " | Cost: $" + cost[i - 1] +
                        " | Profit: $" + profit[i - 1]
                );
                totalCost += cost[i - 1];
                totalProfit += profit[i - 1];
                b -= cost[i - 1];
            }
        }

        System.out.println("\nTotal Investment: $" + totalCost);
        System.out.println("Total Expected Profit: $" + totalProfit);
    }
}

