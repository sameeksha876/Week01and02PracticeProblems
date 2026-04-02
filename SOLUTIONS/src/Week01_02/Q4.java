package Week01_02;

import java.util.*;


public class Q4 {


    // n-gram → set of document IDs
    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();


    // document → list of its n-grams
    private HashMap<String, List<String>> documentNgrams = new HashMap<>();


    private int N = 5; // 5-gram


    // Add document to database
    public void addDocument(String docId, String text) {


        List<String> ngrams = extractNgrams(text);
        documentNgrams.put(docId, ngrams);


        for (String ngram : ngrams) {


            ngramIndex.putIfAbsent(ngram, new HashSet<>());
            ngramIndex.get(ngram).add(docId);
        }
    }


    // Extract n-grams
    private List<String> extractNgrams(String text) {


        String[] words = text.toLowerCase().split("\\s+");
        List<String> ngrams = new ArrayList<>();


        for (int i = 0; i <= words.length - N; i++) {


            StringBuilder sb = new StringBuilder();


            for (int j = 0; j < N; j++) {
                sb.append(words[i + j]).append(" ");
            }


            ngrams.add(sb.toString().trim());
        }


        return ngrams;
    }


    // Analyze new document for plagiarism
    public void analyzeDocument(String docId, String text) {


        List<String> ngrams = extractNgrams(text);


        HashMap<String, Integer> matchCount = new HashMap<>();


        for (String ngram : ngrams) {


            if (ngramIndex.containsKey(ngram)) {


                for (String existingDoc : ngramIndex.get(ngram)) {


                    matchCount.put(existingDoc,
                            matchCount.getOrDefault(existingDoc, 0) + 1);
                }
            }
        }


        System.out.println("Extracted " + ngrams.size() + " n-grams");


        for (Map.Entry<String, Integer> entry : matchCount.entrySet()) {


            int matches = entry.getValue();
            double similarity = (matches * 100.0) / ngrams.size();


            System.out.println(
                    "Found " + matches + " matching n-grams with "
                            + entry.getKey()
                            + " → Similarity: "
                            + String.format("%.2f", similarity) + "%"
            );


            if (similarity > 60) {
                System.out.println("PLAGIARISM DETECTED with " + entry.getKey());
            }
        }
    }


    // Test program
    public static void main(String[] args) {


        Q4 detector = new Q4();


        String essay1 = "machine learning is a field of artificial intelligence that focuses on learning from data";
        String essay2 = "machine learning is a field of artificial intelligence used to analyze data patterns";


        detector.addDocument("essay_089.txt", essay1);


        detector.analyzeDocument("essay_123.txt", essay2);
    }
}
