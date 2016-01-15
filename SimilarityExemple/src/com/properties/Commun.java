package com.properties;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Commun {

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
	
	//renvoyer la position et la valeur du min
	public static DistanceConcept min(DistanceConcept[] array, int typeMethodEgaliteScore) {
		int cptOccurenceMin = 1;//occurence 
		// Validates input
		if (array == null) {
			throw new IllegalArgumentException("The Array must not be null");
		} else if (array.length == 0) {
			throw new IllegalArgumentException("Array cannot be empty.");
		}
		// Finds and returns min
		DistanceConcept min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i].getValue() < min.getValue()) {
				min = array[i];
				cptOccurenceMin = 1;
			} else if (array[i].getValue() == min.getValue()) {
				cptOccurenceMin++;
			}
		}
		if (cptOccurenceMin > 1 && (typeMethodEgaliteScore == 1)) {
			min.setValue(1);
		}

		return min;
	}
	
	//renvoyer la position et la valeur du min
	public static Distance2Concepts min(Distance2Concepts[] array) {
		// Validates input
		if (array == null) {
			throw new IllegalArgumentException("The Array must not be null");
		} else if (array.length == 0) {
			throw new IllegalArgumentException("Array cannot be empty.");
		}

		Distance2Concepts min = array[0];
		for(int i = 0; i < array.length; i++){
			if (array[i].getValue() < min.getValue()) {
				min = array[i];
			}
		}

		return min;
	}	

	//renvoyer la position et la valeur du min
	public static Distance2Concepts min(Distance2Concepts[][] array) {
		// Validates input
		if (array == null) {
			throw new IllegalArgumentException("The Array must not be null");
		} else if (array.length == 0) {
			throw new IllegalArgumentException("Array cannot be empty.");
		}

		Distance2Concepts min = array[0][0];
		for(int i = 0; i < array.length; i++){
			   for(int j = 0; j < array[i].length; j++){
					if (array[i][j].getValue() < min.getValue()) {
						min = array[i][j];
					}
			   }
		}

		return min;
	}
	
	//renvoyer la position et la valeur du max
	public static int posMax(int[] array) {
		// Validates input
		if (array == null) {
			throw new IllegalArgumentException("The Array must not be null");
		} else if (array.length == 0) {
			throw new IllegalArgumentException("Array cannot be empty.");
		}
		// Finds and returns max
		int max = array[0];
		int cpt = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
				cpt = i;
			}
		}

		return cpt;
	}
	
	//lit un entier à partir du clavier et le retourne
	public static int getInt() {
		int result = 0;
		boolean intInput = false;
		while (!intInput) {//on continue à demander un entier jusqu'à ça soit bon
			try {
				Scanner sc = new Scanner(System.in);
				System.out.print("     Veuillez saisir un 1 ou 2 pour choisir le type de la méthode : ");
				result = sc.nextInt();
				if ( (result != 1) && (result != 2) ) {
					intInput = false;
				} else {
					intInput = true;					
				}
			} catch (InputMismatchException e) {
				intInput = false;
			}
			
		}
		return result;
	}
	
	//lit un entier à partir du clavier et le retourne
	public static float getFloat() {
		float result = 0;
		boolean intInput = false;
		while (!intInput) {//on continue à demander un entier jusqu'à ça soit bon
			try {
				Scanner sc = new Scanner(System.in);
				System.out.print("     Veuillez saisir un nombre réel : ");
				result = sc.nextFloat();
				intInput = true;					
			} catch (InputMismatchException e) {
				intInput = false;
			}
			
		}
		return result;
	}	
}
