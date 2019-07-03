/*
Created By William Freire  
Github: SanoKei
Started: 3/14/19
Finished: 4/19/19
*/
import java.util.*;
class Main{
	public static void main(String[] args) {
		int choice = 0;
		Scanner input = new Scanner(System.in);
		//I swear if somebody asks me how i cleared the screen. 
		//well its right here. System.out.println("\033[H\033[2J"); youre welcome
		do{
			System.out.print("Oligopoly:\n1) Start\n2) Rules\n3) Credit\nEnter: \n");
			try {
				choice = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("That is not a number.");
			}
			switch(choice){
				case 1: game Game = new game(); Game.play(); break;
				case 2: System.out.println("\nRules:\nEvery player owns a company and is able to buy shares and other assets from other players though contracts. Once everyone has played that counts as one \'orbit\'.\nOnce an orbit is complete there is a period of time where every player watches as major events that they took part in during their turn effects the price of their stock and the stock of others.\nThe game is won once one player collects all the assets and businesses of all the other players.\nThe game does not have a board but does represent some aspects of Monopoly(tm).\n"); break;
				case 3: System.out.println("\nCredit:\nEverything: William Freire\nThat's it.\n"); break;
				default: System.out.println("\nPlease input something valid.\n");
			}
		}while(choice != 1);
	}
}