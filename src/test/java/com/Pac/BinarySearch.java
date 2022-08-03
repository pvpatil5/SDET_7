package com.Pac;

public class BinarySearch {

	public static void main(String[] args) {

		int arr[]= {1,2,3,4,5,6};
//		     li          mi         hi
		int li=0;//		 li    mi     hi

		int hi=arr.length-1;

		int mi =li+hi/2; //3

		int exp=5;
		while(exp<mi)
		{
			if(arr[mi]==exp) {

				System.out.println("Exp number is at"+mi);
				break;
			}

			else if(arr[mi]>=exp) {
				hi= mi-1;
				System.out.println();

			}

			else if(arr[mi]<=exp) 
			{
				li= mi+1;	
			}
		}
	}

}
