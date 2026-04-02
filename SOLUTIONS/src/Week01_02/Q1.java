package Week01_02;

import java.util.*;


public class Q1 {


    // Stores username -> userId
    private HashMap<String, Integer> users = new HashMap<>();


    // Stores username -> number of attempts
    private HashMap<String, Integer> attemptFrequency = new HashMap<>();


    // Constructor with some existing users
    public Q1() {
        users.put("john_doe", 101);
        users.put("admin", 1);
        users.put("alex", 102);
    }


    // Check if username is available
    public boolean checkAvailability(String username) {


        // track attempt frequency
        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);


        return !users.containsKey(username);
    }


    // Suggest alternative usernames
    public List<String> suggestAlternatives(String username) {


        List<String> suggestions = new ArrayList<>();


        // append numbers
        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;


            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }


        // replace underscore with dot
        if (username.contains("_")) {
            String alt = username.replace("_", ".");
            if (!users.containsKey(alt)) {
                suggestions.add(alt);
            }
        }


        return suggestions;
    }


    // Get most attempted username
    public String getMostAttempted() {


        String maxUser = null;
        int maxAttempts = 0;


        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {


            if (entry.getValue() > maxAttempts) {
                maxAttempts = entry.getValue();
                maxUser = entry.getKey();
            }
        }


        return maxUser + " (" + maxAttempts + " attempts)";
    }


    // Add user when registration completes
    public void registerUser(String username, int userId) {
        users.put(username, userId);
    }


    // Testing
    public static void main(String[] args) {


        Q1 checker = new Q1();


        System.out.println(checker.checkAvailability("john_doe")); // false
        System.out.println(checker.checkAvailability("jane_smith")); // true


        System.out.println(checker.suggestAlternatives("john_doe"));


        // simulate attempts
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");


        System.out.println(checker.getMostAttempted());
    }
}
