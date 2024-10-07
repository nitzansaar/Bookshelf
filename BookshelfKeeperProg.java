// Name: Nitzan Saar
// USC NetID: 8106373693
// CSCI455 PA2
// Fall 2024

//The BookshelfKeeper maintains a Bookshelf whose books are kept in increasing order by height by the keeper, 
// and allows the client to (1) pick a book from the shelf, given its position or 
//(2) put a book on the shelf, given the height of the book.
/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class BookshelfKeeperProg {
    public static void main(String[] args) {
        final int EXIT_NUM = 0;
        Bookshelf bs;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter initial arrangement of books followed by newline:");
        String input = scanner.nextLine().trim();
        String[] numbers = input.split("\\s+");
        ArrayList<Integer> al = new ArrayList<>();
        if (!input.isEmpty()) {
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                al.add(num);
            }
        } 
        bs = new Bookshelf(al);
        if (!bs.isSorted()) {
            System.out.println("ERROR: Heights must be specified in non-decreasing order.\nExiting Program.");
            System.exit(EXIT_NUM);
        } else if (bs.hasNegative()) {
            System.out.println("ERROR: Height of a book must be positive.\nExiting Program.");
            System.exit(EXIT_NUM);
        }
        BookshelfKeeper bk = new BookshelfKeeper(bs);
        System.out.println(bk.toString());
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
        do {
            input = scanner.nextLine().trim();
            String[] commands = input.split("\\s+");
            if (commands[0].equalsIgnoreCase("put")) {
                if (Integer.parseInt(commands[1]) < 0) {
                    System.out.println("ERROR: Height of a book must be positive.\nExiting Program.");
                    System.exit(EXIT_NUM);
                }
                bk.putHeight(Integer.parseInt(commands[1]));
            } else if (commands[0].equalsIgnoreCase("pick")) {
                if (Integer.parseInt(commands[1]) > bk.getNumBooks()) {
                    System.out.println("ERROR: Entered pick operation is invalid on this shelf.\nExiting Program.");
                    System.exit(EXIT_NUM);
                }
                bk.pickPos(Integer.parseInt(commands[1]));
            } else if (input.equalsIgnoreCase("end")) {
                System.out.println("Exiting Program.");
                System.exit(EXIT_NUM);
            } else {
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.Exiting Program.\n");
            }
            System.out.println(bk.toString());


        } while (!input.equalsIgnoreCase("end"));
        System.out.println("Exiting Program.");


    }
}
