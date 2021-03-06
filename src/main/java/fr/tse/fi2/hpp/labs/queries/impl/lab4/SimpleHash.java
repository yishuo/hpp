package fr.tse.fi2.hpp.labs.queries.impl.lab4;

/**
 * @author lv
 *
 */
public class SimpleHash {
	 
	     private int cap;
	     private int seed;

	     public SimpleHash(int cap, int seed) {
	         this.cap = cap;
	         this.seed = seed;
	     }

	     public int hash(String value) {
	         int result = 0;
	         int len = value.length();
	         for (int i = 0; i < len; i++) {
	            result = seed * result + value.charAt(i);
	         }
	         return (cap-1) & result;
	         //return result;
	     }
}
