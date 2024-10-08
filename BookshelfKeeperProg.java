// Name: Nitzan Saar
// USC NetID: 8106373693
// CSCI455 PA2
// Fall 2024

// The BookshelfKeeper maintains a Bookshelf whose books are kept in increasing order by height by the keeper, 
// and allows the client to (1) pick a book from the shelf, given its position or 
// (2) put a book on the shelf, given the height of the book.

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class BookshelfKeeperProg
 *
 * This is the main class for running the BookshelfKeeper program. It allows users to input the initial 
 * arrangement of books on a bookshelf and perform "put" and "pick" operations to modify the bookshelf
 * through a BookshelfKeeper object. The program ensures valid inputs and keeps the bookshelf sorted 
 * in non-decreasing order.
 */
public class BookshelfKeeperProg {
    public final static int EXIT_NUM = 0;

    /**
     * Main method of the BookshelfKeeper program.
     * It interacts with the user to receive the initial arrangement of books and handles "put" and "pick"
     * operations until the user types "end" to exit the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        // Create a BookshelfKeeper object to manage the bookshelf
        BookshelfKeeper bk = new BookshelfKeeper(setUpBookshelf(scanner));
        System.out.println(bk.toString());
        
        // Instructions for user input
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
        String input;
        boolean isRunning = true;  // Control flag for loop

        // Main loop to process user commands
        while (isRunning) {
            input = scanner.nextLine().trim();
            String[] commands = input.split("\\s+");

            // Handle user input and update isRunning flag
            isRunning = handleOptions(commands, bk);
            
            if (isRunning) {
                // Print the current state of the BookshelfKeeper after valid commands
                System.out.println(bk.toString());
            }
        }

        // Program exit message
        System.out.println("Exiting Program.");
    }

    /**
     * Helper method that sets up the initial Bookshelf based on user input.
     * It prompts the user to enter a sequence of book heights, validates the input,
     * and returns a Bookshelf object initialized with those books.
     *
     * @param scanner Scanner object to read user input.
     * @return a Bookshelf object with the initial arrangement of books.
     */
    private static Bookshelf setUpBookshelf(Scanner scanner) {
        Bookshelf bs;        
        // Prompt user for initial arrangement of books
        System.out.println("Please enter initial arrangement of books followed by newline:");
        String input = scanner.nextLine().trim();
        String[] numbers = input.split("\\s+");
        ArrayList<Integer> al = new ArrayList<>();
        
        // Parse the input into an ArrayList of integers
        if (!input.isEmpty()) {
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                al.add(num);
            }
        }
        // Create a Bookshelf object with the initial arrangement
        bs = new Bookshelf(al);
        
        // Validate the initial bookshelf arrangement (sorted and positive heights)
        if (!bs.isSorted()) {
            System.out.println("ERROR: Heights must be specified in non-decreasing order.\nExiting Program.");
            System.exit(EXIT_NUM);
        } else if (bs.hasNegative()) {
            System.out.println("ERROR: Height of a book must be positive.\nExiting Program.");
            System.exit(EXIT_NUM);
        }
        return bs;
    }

    /**
     * Handles user commands ("put", "pick", "end") and performs the corresponding actions
     * on the BookshelfKeeper object. Validates input and returns a boolean to indicate whether
     * the program should continue running.
     *
     * @param commands The split user input as a string array.
     * @param bk The BookshelfKeeper object that manages the bookshelf operations.
     * @return true if the program should continue running, false if it should exit.
     */
    private static boolean handleOptions(String[] commands, BookshelfKeeper bk) {
        try {
            // Handle "put" command
            if (commands[0].equalsIgnoreCase("put")) {
                if (Integer.parseInt(commands[1]) < 0) {
                    System.out.println("ERROR: Height of a book must be positive.");
                    return false; // Stop the loop
                }
                bk.putHeight(Integer.parseInt(commands[1]));
            
            // Handle "pick" command
            } else if (commands[0].equalsIgnoreCase("pick")) {
                if (Integer.parseInt(commands[1]) >= bk.getNumBooks()) {
                    System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                    return false; // Stop the loop
                }
                bk.pickPos(Integer.parseInt(commands[1]));
            
            // Handle "end" command
            } else if (commands[0].equalsIgnoreCase("end")) {
                return false; // This will stop the loop

            // Handle invalid command
            } else {
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                return false; // Stop the loop
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid input format.");
            return false; // Stop the loop
        }
        
        // Continue the loop
        return true;
    }
}