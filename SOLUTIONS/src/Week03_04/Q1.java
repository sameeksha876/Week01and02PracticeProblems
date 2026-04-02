// Week03 & Week04 Practice Problems
// Q1 – Transaction Fee Sorting
package Week03_04;

import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id,double fee,String timestamp){
        this.id=id;
        this.fee=fee;
        this.timestamp=timestamp;
    }

    public String toString(){
        return id+" : "+fee+" @ "+timestamp;
    }
}

public class Q1 {

    // Bubble sort by fee
    static void bubbleSort(ArrayList<Transaction> list){

        int n=list.size();

        for(int i=0;i<n-1;i++){

            boolean swapped=false;

            for(int j=0;j<n-i-1;j++){

                if(list.get(j).fee > list.get(j+1).fee){

                    Transaction temp=list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);

                    swapped=true;
                }
            }

            if(!swapped)
                break;
        }
    }

    // Insertion sort by fee + timestamp
    static void insertionSort(ArrayList<Transaction> list){

        for(int i=1;i<list.size();i++){

            Transaction key=list.get(i);

            int j=i-1;

            while(j>=0 && list.get(j).fee > key.fee){

                list.set(j+1,list.get(j));
                j--;
            }

            list.set(j+1,key);
        }
    }

    static void highFee(ArrayList<Transaction> list){

        System.out.println("High fee transactions:");

        for(Transaction t:list){

            if(t.fee>50)
                System.out.println(t);
        }
    }

    public static void main(String[] args) {

        ArrayList<Transaction> list=new ArrayList<>();

        list.add(new Transaction("id1",10.5,"10:00"));
        list.add(new Transaction("id2",25.0,"09:30"));
        list.add(new Transaction("id3",5.0,"10:15"));

        System.out.println("Bubble Sort:");

        bubbleSort(list);

        System.out.println(list);

        System.out.println("Insertion Sort:");

        insertionSort(list);

        System.out.println(list);

        highFee(list);
    }
}