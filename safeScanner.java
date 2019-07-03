import java.util.*;
public class safeScanner {
    public int safenextInt(String prompt){
        Scanner input = new Scanner(System.in);
        boolean valid;
        int choice = 0;
        do{
            valid = true;
            System.out.print(prompt);
            try {
                choice = Integer.parseInt(input.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println(choice + " is not a valid input please input a number and try again\n");
                valid = false;
            }
        }while(!valid);
        return choice;
    }
    public double safenextDouble(String prompt){
        Scanner input = new Scanner(System.in);
        boolean valid;
        double choice = 0f;
        do{
            valid = true;
            System.out.print(prompt);
            try {
                choice = Double.parseDouble(input.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println(choice + " is not a valid input please input a number and try again\n");
                valid = false;
            }
        }while(!valid);
        return choice;
    }
}
