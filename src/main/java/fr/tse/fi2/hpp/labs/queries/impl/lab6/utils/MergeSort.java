package fr.tse.fi2.hpp.labs.queries.impl.lab6.utils;

import java.util.Random;

public class MergeSort {

	//obtenir la liste inclus les valeurs aleatoires
	public static int[] genererAleatoire(int n){
		int[] tableau = new int[n];
		Random random = new Random();

		for(int i=0 ; i<tableau.length ; i++){
			int valeur = random.nextInt();
			tableau[i] = valeur;
		}

		return tableau;
	}
	
	public static int[] trier (int[] liste){

		int len = liste.length;

		if (len > 1){

			int[] sousListe1 = trier(cutArrayList(liste, 0, len/2));
			int[] sousListe2 = trier(cutArrayList(liste, len/2, len));
			
			int a = 0;
			int b = 0;

			for(int i = 0 ; i<len ; i++){

				if( a<sousListe1.length && ((b >= sousListe2.length) || (sousListe1[a] < sousListe2[b]) )){
					liste[i] = sousListe1[a];
					a++;
				} else if(b < sousListe2.length){

					liste[i] = sousListe2[b];
					b++;
				}		
			}			
		}
		return liste;
	}

	public static int[] trierInsertionSort (int[] liste){

		int len = liste.length;

		if (len > 20){

			int[] sousListe1 = trierInsertionSort(cutArrayList(liste, 0, len/2));
			int[] sousListe2 = trierInsertionSort(cutArrayList(liste, len/2, len));
			
			int a = 0;
			int b = 0;

			for(int i = 0 ; i<len ; i++){

				if( a<sousListe1.length && ((b >= sousListe2.length) || (sousListe1[a] < sousListe2[b]) )){
					liste[i] = sousListe1[a];
					a++;
				} else if(b < sousListe2.length){

					liste[i] = sousListe2[b];
					b++;
				}		
			}			
		} else {
			InsertionSort(liste);
		}

		return liste;
	}

	public static int[] cutArrayList(int[] liste, int debut, int fin){
		int[] tab = new int[(fin - debut)];

		int j = 0;
		for(int i=debut ; i<fin ; i++){
			tab[j] = liste[i];	
			j++;
		}
		return tab;
	}

	public static void InsertionSort(int[] num)
	{
		int j;                  // the number of items sorted so far
		int key;                // the item to be inserted
		int i; 

		for (j = 1; j < num.length; j++)    // Start with 1 (not 0)
		{
			key = num[ j ];
			for(i = j - 1; (i >= 0) && (num[ i ] > key); i--)  
			{
				num[ i+1 ] = num[ i ];
			}
			num[ i+1 ] = key;    // Put the key in its proper location
		}
	}

	
	public static void main(String[] args) {

		int[] liste = MergeSort.genererAleatoire(10000);

		System.out.println("La liste originale : ");
		for(int i=0 ; i<liste.length ; i++){
			System.out.println(liste[i]);
		}

		int[] listeTrie = MergeSort.trier(liste);

		System.out.println("La nouvelle liste après sort");
		for(int i=0 ; i<listeTrie.length ; i++){
			System.out.println(listeTrie[i]);
		}

	}
	

}
