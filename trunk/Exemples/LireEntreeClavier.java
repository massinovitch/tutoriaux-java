import java.io.Console;
import java.util.Scanner;

public class LireEntreeClavier {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un mot :");
		String str = sc.nextLine(); // permet de récupérer ce qui a été saisi, pour lire un entier : int str = sc.nextInt();
		System.out.println("Vous avez saisi : " + str);

	}
}