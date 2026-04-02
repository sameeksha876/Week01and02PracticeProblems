package Week01_02;

import java.util.*;


class Transaction {
    int id;
    int amount;
    String merchant;


    public Transaction(int id, int amount, String merchant) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
    }
}


public class Q2 {


    public static void findTwoSum(List<Transaction> transactions, int target) {


        HashMap<Integer, Transaction> map = new HashMap<>();


        for (Transaction t : transactions) {


            int complement = target - t.amount;


            if (map.containsKey(complement)) {


                System.out.println(
                        "Pair found: " +
                                map.get(complement).id + " + " + t.id);
            }


            map.put(t.amount, t);
        }
    }


    public static void detectDuplicates(List<Transaction> transactions) {


        HashMap<String, List<Integer>> map = new HashMap<>();


        for (Transaction t : transactions) {


            String key = t.amount + "_" + t.merchant;


            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t.id);
        }


        for (String key : map.keySet()) {


            if (map.get(key).size() > 1) {
                System.out.println("Duplicate: " + key + " → " + map.get(key));
            }
        }
    }


    public static void main(String[] args) {


        List<Transaction> list = new ArrayList<>();


        list.add(new Transaction(1,500,"StoreA"));
        list.add(new Transaction(2,300,"StoreB"));
        list.add(new Transaction(3,200,"StoreC"));


        findTwoSum(list,500);
        detectDuplicates(list);
    }
}
