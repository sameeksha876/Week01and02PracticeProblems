package Week01_02;

import java.util.*;


class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    Map<String, Integer> queries = new HashMap<>();
}


public class Q7 {


    private TrieNode root = new TrieNode();


    // Global query frequency
    private HashMap<String, Integer> frequencyMap = new HashMap<>();


    // Insert search query
    public void insertQuery(String query) {


        frequencyMap.put(query,
                frequencyMap.getOrDefault(query, 0) + 1);


        TrieNode node = root;


        for (char c : query.toCharArray()) {


            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);


            node.queries.put(query, frequencyMap.get(query));
        }
    }


    // Search prefix
    public List<String> search(String prefix) {


        TrieNode node = root;


        for (char c : prefix.toCharArray()) {


            if (!node.children.containsKey(c))
                return new ArrayList<>();


            node = node.children.get(c);
        }


        // Top 10 using max heap
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>(
                        (a, b) -> b.getValue() - a.getValue());


        pq.addAll(node.queries.entrySet());


        List<String> results = new ArrayList<>();


        int count = 0;


        while (!pq.isEmpty() && count < 10) {
            results.add(pq.poll().getKey());
            count++;
        }


        return results;
    }


    // Update frequency when new search happens
    public void updateFrequency(String query) {
        insertQuery(query);
    }


    // Test
    public static void main(String[] args) {


        Q7 system = new Q7();


        system.insertQuery("java tutorial");
        system.insertQuery("javascript");
        system.insertQuery("java download");
        system.insertQuery("java tutorial");
        system.insertQuery("java 21 features");


        System.out.println("Suggestions for 'jav':");


        List<String> results = system.search("jav");


        for (String r : results) {
            System.out.println(r);
        }


        system.updateFrequency("java 21 features");
        system.updateFrequency("java 21 features");
    }
}
