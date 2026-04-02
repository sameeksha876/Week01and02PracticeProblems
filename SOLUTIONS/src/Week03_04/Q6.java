package Week03_04;

import java.util.*;

public class Q6 {

    // Linear search
    static boolean linearSearch(int arr[], int target){

        for(int i=0;i<arr.length;i++){

            if(arr[i]==target)
                return true;
        }

        return false;
    }

    // Binary search insertion position
    static int binaryInsertion(int arr[], int target){

        int low=0;
        int high=arr.length-1;

        while(low<=high){

            int mid=(low+high)/2;

            if(arr[mid]==target)
                return mid;

            if(arr[mid]<target)
                low=mid+1;

            else
                high=mid-1;
        }

        return low;
    }

    // Floor value
    static int floor(int arr[],int target){

        int floorValue=-1;

        for(int i=0;i<arr.length;i++){

            if(arr[i]<=target)
                floorValue=arr[i];
        }

        return floorValue;
    }

    // Ceiling value
    static int ceiling(int arr[],int target){

        for(int i=0;i<arr.length;i++){

            if(arr[i]>=target)
                return arr[i];
        }

        return -1;
    }

    public static void main(String args[]){

        int risks[]={10,25,50,100};

        int target=30;

        System.out.println("Linear search found: "+linearSearch(risks,target));

        System.out.println("Insertion index: "+binaryInsertion(risks,target));

        System.out.println("Floor value: "+floor(risks,target));

        System.out.println("Ceiling value: "+ceiling(risks,target));
    }
}