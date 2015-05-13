package fr.tse.fi2.hpp.labs.queries.impl.lab6.utils;

public class MergeSort
{
	public static void merge(int a[],int pl,int mid,int pr)
	{
		int temp[]=new int[pr-pl+1];
		int i=0,left=pl,right=mid+1;
		while(left<=mid && right<=pr)
		{
			temp[i++]=a[left]<a[right] ? a[left++]:a[right++];
		}
		while(left<=mid)
		{
			temp[i++]=a[left++];
		}
		while(right<=pr)
		{
			temp[i++]=a[right++];
		}
		for(int j=0,k=pl;k<=pr;j++,k++)
		{
			a[k]=temp[j];
		}

	}
	public static void sort(int a[],int left,int right)
	{
		int mid;
		if(left<right)
		{
			mid=(left+right)/2;
			sort(a,left,mid);
			sort(a,mid+1,right);
			merge(a,left,mid,right);
		}
	}
/*	
 *public static void main(String args[])
	{
		int a[]={26, 5, 98, 108, 28, 99, 100, 56, 34, 1 };
		MergeSort.sort(a, 0, a.length-1);
		for(int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
		}
	}
*/	
}