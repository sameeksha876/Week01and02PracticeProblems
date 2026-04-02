package Week01_02;

import java.util.*;

class TransactionRecord {
    int id;
    int amount;
    String merchant;

    public TransactionRecord(int id, int amount, String merchant) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
    }
}

public class Q9 {

    public static void findTwoSum(List<TransactionRecord> transactions, int target) {

        HashMap<Integer, TransactionRecord> map = new HashMap<>();

        for (TransactionRecord t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                TransactionRecord other = map.get(complement);

                System.out.println("Pair found: " + other.id + " + " + t.id);
            }

            map.put(t.amount, t);
        }
    }

    public static void detectDuplicates(List<TransactionRecord> transactions) {

        HashMap<String, List<Integer>> map = new HashMap<>();

        for (TransactionRecord t : transactions) {

            String key = t.amount + "_" + t.merchant;

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t.id);
        }

        for (String key : map.keySet()) {

            if (map.get(key).size() > 1) {
                System.out.println("Duplicate: " + key + " -> " + map.get(key));
            }
        }
    }

    public static void main(String[] args) {

        List<TransactionRecord> list = new ArrayList<>();

        list.add(new TransactionRecord(1, 500, "StoreA"));
        list.add(new TransactionRecord(2, 300, "StoreB"));
        list.add(new TransactionRecord(3, 200, "StoreC"));
        list.add(new TransactionRecord(4, 300, "StoreB"));

        findTwoSum(list, 500);
        detectDuplicates(list);
    }
}