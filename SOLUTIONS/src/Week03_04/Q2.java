package Week03_04;

import java.util.*;

class Client{

    String name;
    int riskScore;
    double accountBalance;

    Client(String name,int riskScore,double accountBalance){

        this.name=name;
        this.riskScore=riskScore;
        this.accountBalance=accountBalance;
    }

    public String toString(){

        return name+" : "+riskScore;
    }
}

public class Q2 {

    static void bubbleSort(Client arr[]){

        int n=arr.length;

        for(int i=0;i<n-1;i++){

            for(int j=0;j<n-i-1;j++){

                if(arr[j].riskScore > arr[j+1].riskScore){

                    Client temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    static void insertionSort(Client arr[]){

        for(int i=1;i<arr.length;i++){

            Client key=arr[i];

            int j=i-1;

            while(j>=0 && arr[j].riskScore < key.riskScore){

                arr[j+1]=arr[j];
                j--;
            }

            arr[j+1]=key;
        }
    }

    static void topRisk(Client arr[]){

        System.out.println("Top risks:");

        for(int i=0;i<Math.min(10,arr.length);i++)
            System.out.println(arr[i]);
    }

    public static void main(String args[]){

        Client arr[]={

                new Client("A",20,5000),
                new Client("B",50,8000),
                new Client("C",80,3000)

        };

        bubbleSort(arr);

        System.out.println("Bubble sorted:");
        System.out.println(Arrays.toString(arr));

        insertionSort(arr);

        System.out.println("Insertion sorted:");
        System.out.println(Arrays.toString(arr));

        topRisk(arr);
    }
}