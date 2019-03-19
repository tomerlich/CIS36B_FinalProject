import java.util.ArrayList;
import java.util.Scanner;

public class ChestTestClass {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String answer;
        System.out.print("Would you like to open this chest? Enter (Y/N): ");
        answer = input.nextLine();

        ArrayList<Item> items;
        Chest chest = new Chest();
        chest.fillChest();
        items = chest.getChestItems();

        for(int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).toString() + "\n");
        }

        if(answer.equalsIgnoreCase("Y")) {

            chest.openChest();


        }


    }




}
