package com;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test {


	public static void main(String...strings){
		         
		int n = 5;         
		String str="####";     
		System.out.printf("%6s%n",str);
		/*for (int i=0;i<n;i++){          
		   System.out.printf("%"+n+"s%n",str);         
		   str=str+"#";     
		}*/
		
		
		
		//String input = "Practice makes perfect. you'll only get Perfect by practice. just practice!";
		String input = "Every book is a quotation; and every house is a quotation out of all forests, and mines, and stone quarries; and every man is a quotation from all his ancestors.";
		
		/*replaceAll special char from input string with space*/ 
		input = input.replaceAll("[^a-zA-Z0-9]", " ");
		String[] stringArr = input.split(" ");
		
		/*making all words in lower case*/
		for(int i=0; i<stringArr.length; i++){
			stringArr[i] = stringArr[i].toLowerCase();
		}
		
		/*pick the all unique words from strings*/
		Set<String> mySet = new HashSet<>(Arrays.asList(stringArr));
		
		
		String[][] strArr = new String[mySet.size()][2];
		int i=0;
		
		/*count the each unique words occurrences*/
		for(String key : mySet){
			int occurrance = 0;		
			for(String matchKey : stringArr){
				if(key.equalsIgnoreCase(matchKey)){
					occurrance++;
				}
			}
			strArr[i][0] = key; 
			strArr[i][1] = String.valueOf(occurrance);
			i++;
		}
		
		Test.sort2DArray(strArr);
		
		/*Print sorted 2D array*/
		for(int m=0; m<strArr.length ;m++){
			System.out.println(strArr[m][0]+"  "+strArr[m][1]);
		}
	}
	
	private static void sort2DArray(String[][] string2DArray) {
		
		//Arrays.sort(string2DArray,(a,b) -> a[1].compareTo(b[1])==0? a[0].compareTo(b[0]) : 0-(a[1].compareTo(b[1])));
		int maxInt;
		String maxKey;
		String maxValue;
		int flag = 0;

		/* Sort the 2D Array */
		for (int m = 0; m < string2DArray.length; m++) {
			maxInt = Integer.parseInt(string2DArray[m][1]);
			for (int n = 0; n < string2DArray.length; n++) {

				 /*swap on the basis of word's occurrences 
*/				if (maxInt > Integer.parseInt(string2DArray[n][1])) {
					maxKey = string2DArray[m][0];
					maxValue = string2DArray[m][1];
					string2DArray[m][0] = string2DArray[n][0];
					string2DArray[m][1] = string2DArray[n][1];
					string2DArray[n][0] = maxKey;
					string2DArray[n][1] = maxValue;
					maxInt = Integer.parseInt(string2DArray[n][1]);
				} else {

					/* swap on the basis of word */
					if (maxInt == Integer.parseInt(string2DArray[n][1])) {
						flag = string2DArray[m][0].compareToIgnoreCase(string2DArray[n][0]);
						if (flag > 0) {
							maxKey = string2DArray[m][0];
							maxValue = string2DArray[m][1];
							string2DArray[m][0] = string2DArray[n][0];
							string2DArray[m][1] = string2DArray[n][1];
							string2DArray[n][0] = maxKey;
							string2DArray[n][1] = maxValue;
						}
					}
				}
			}
		}
	}
}
