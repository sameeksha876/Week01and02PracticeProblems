package Week03_04;

import java.util.*;

class Asset{

    String name;
    double returnRate;
    double volatility;

    Asset(String name,double returnRate,double volatility){

        this.name=name;
        this.returnRate=returnRate;
        this.volatility=volatility;
    }

    public String toString(){

        return name+" : "+returnRate+"% , vol="+volatility;
    }
}

public class Q4 {

    // Merge
    static void merge(Asset arr[],int left,int mid,int right){

        int n1=mid-left+1;
        int n2=right-mid;

        Asset L[]=new Asset[n1];
        Asset R[]=new Asset[n2];

        for(int i=0;i<n1;i++)
            L[i]=arr[left+i];

        for(int j=0;j<n2;j++)
            R[j]=arr[mid+1+j];

        int i=0,j=0,k=left;

        while(i<n1 && j<n2){

            if(L[i].returnRate <= R[j].returnRate){

                arr[k]=L[i];
                i++;
            }
            else{

                arr[k]=R[j];
                j++;
            }
            k++;
        }

        while(i<n1){
            arr[k]=L[i];
            i++;
            k++;
        }

        while(j<n2){
            arr[k]=R[j];
            j++;
            k++;
        }
    }

    // Merge sort
    static void mergeSort(Asset arr[],int left,int right){

        if(left<right){

            int mid=(left+right)/2;

            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);

            merge(arr,left,mid,right);
        }
    }

    // Partition
    static int partition(Asset arr[],int low,int high){

        double pivot=arr[high].returnRate;

        int i=low-1;

        for(int j=low;j<high;j++){

            if(arr[j].returnRate >= pivot){

                i++;

                Asset temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }

        Asset temp=arr[i+1];
        arr[i+1]=arr[high];
        arr[high]=temp;

        return i+1;
    }

    // Quick sort descending
    static void quickSort(Asset arr[],int low,int high){

        if(low<high){

            int pi=partition(arr,low,high);

            quickSort(arr,low,pi-1);
            quickSort(arr,pi+1,high);
        }
    }

    public static void main(String args[]){

        Asset arr[]={

                new Asset("AAPL",12,5),
                new Asset("TSLA",8,7),
                new Asset("GOOG",15,4)

        };

        System.out.println("Merge Sort (Ascending Return):");

        mergeSort(arr,0,arr.length-1);

        System.out.println(Arrays.toString(arr));

        System.out.println("Quick Sort (Descending Return):");

        quickSort(arr,0,arr.length-1);

        System.out.println(Arrays.toString(arr));
    }
}