package src.ui;
import src.actions.Action;
import src.character.Combatant;
import src.character.Player;
import src.items.Item;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Menu {

    private final Scanner scanner;
    private CombatLogger combatLogger;

    //My constructor for Menu
    public Menu(CombatLogger combatlogger){
        this.scanner = new Scanner(System.in);
        this.combatLogger = combatLogger;
    }

    public String promptClassSelection()
    {
        int choice = -1;
        while (choice < 1 || choice > 2)
        {
            System.out.println("Select your Class:");
            System.out.println("1. Warrior (High HP, Shield Bash)");
            System.out.println("2. Wizard (Arcane Blast, Scaling Attack)");
            System.out.println("Enter choice (1 or 2): ");
            choice = getValidInput();

            if (choice == 1) return "Warrior";
            if (choice == 2) return "Wizard";
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
        //Worst case scenario, to break out safely
        return null;
    }


    public int promptDifficultySelection()
    {
        int choice = -1;
        while (choice < 1 || choice > 3)
        {
            System.out.println("Select Difficulty:");
            System.out.println("1. Easy (3 Goblins)");
            System.out.println("2. Medium (Goblin and Wolves + Backup)");
            System.out.println("3. Hard (More Enemies + Backup)");
            System.out.println("Enter Choice (1-3): ");

            choice = getValidInput();

            if (choice >= 1 && choice <= 3) return choice;

            //error checking for invalid input, using the getValidInput()
            System.out.println("Invalid choice. Please enter 1, 2 or 3");
        }

        //If all else fails, it will fall back to "Easy difficulty here."
        return 1;
    }

    //The reason why Player player is accepted evn though each class is different is as all of them are child classes of Player class.
    //Hence, it is allowed that Player player works, as all users will automatically have the player methods, regardless of type.
    public Item promptItemSelection(Player player)
    {
        List <Item> inventory = player.getInventory(); //I am getting from the Player class
        if (inventory.isEmpty())
        {
            System.out.println("You have no items left!");
            return null;
        }

        int choice = -1;
        while (choice < 1 || choice > inventory.size() + 1) // This is for checking out of range of inventory.
        {
            System.out.println("Select an item to use: ");
            for (int i = 0; i < inventory.size(); i++)
            {
                System.out.println((i+1) + ". " + inventory.get(i).getName()); //getting methods from inventory class
            }
            System.out.println((inventory.size() + 1) + ". Cancel");

            System.out.println("Enter choice: ");
            choice = getValidInput();

            if (choice >= 1 && choice <= inventory.size())
            {
                return inventory.get(choice-1); //For indexing
            }
            else if (choice == inventory.size() + 1)
            {
                return null; //User canceled item selection
            }

            System.out.println("Invalid choice");

        }
        return null;
    }
    public Action promptActionChoice(Player player)
    {
        int choice = -1;

        while (choice<1 || choice > 4)
        {
            System.out.println("Choose your Action:");
            System.out.println("1. Basic Attack");
            System.out.println("2. Defend");
            System.out.println("3. Item");
            System.out.println("4. Special Skill");

            System.out.println("Enter Choice: ");
            choice = getValidInput();


            //Work in progress
            /*
            switch (choice)
            {
                case 1:
                    //over in these cases, I need to implement the action classes, which have not been done yet
            }

             */
        }
        return null;
    }


    //helper class for finding input (used it as it is easy to put try/except outside for code checking)
    //This is useful as every selection class has the checking, so putting a class for checking allows it to be called easily
    //reusing one class of code
    private int getValidInput()
    {
        try {
            int input = scanner.nextInt();
            scanner.nextLine(); //This consumes the leftover newline character
            return input;
        }
        catch (InputMismatchException error)
        {
            scanner.nextLine(); //Consuming leftover line again
            return -1; //This is because in the promptClassSelection, the loop has to continue running if choice != 1 or 2
        }
    }
}