// Name: Nitzan Saar
// USC NetID: 8106373693
// CSCI455 PA2
// Fall 2024
import java.util.ArrayList;


/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/

public class Bookshelf {

   /**
      Representation invariant:
      All books need to be >= 0
      ArrayList represents the heights of books in a bookshelf

   */
   
   // <add instance variables here>
   private ArrayList<Integer> bookshelf = new ArrayList<>();

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      assert isValidBookshelf();
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      this.bookshelf = pileOfBooks;
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      if (height < 0)
      {
         System.out.println("cant add negative numbers");

      }
      bookshelf.add(0, height);
      assert isValidBookshelf();

      //System.out.printf("added %d to the front of the bookshelf\n", height);
      
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      if (height < 0)
      {
         System.out.println("cant add negative numbers");

      }
      bookshelf.add(height);
      assert isValidBookshelf();

      //System.out.printf("added %d to the end of the Bookshelf\n", height);

   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      int res = bookshelf.get(0);
      bookshelf.remove(0);
      assert isValidBookshelf();

      return res;
      
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      int res = bookshelf.get(bookshelf.size() - 1);
      bookshelf.remove(bookshelf.size() - 1);
      assert isValidBookshelf();
      return res;    
   }

   /*
    * Gets the height of the book at the given position.
    * 
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      
      return bookshelf.get(position);
      
   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      
      return bookshelf.size(); 
   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      String res = "";
      for (int i = 0; i < bookshelf.size(); i++)
      {
         if (i != 0 && i != bookshelf.size() - 1)
         {
            res += bookshelf.get(i) + ", ";
         }
         else if (i == 0 && bookshelf.size() > 1)
         {
            res += bookshelf.get(i) + ", ";
         }
         else
         {
            res += bookshelf.get(i);
         }
      }
      assert isValidBookshelf();

      return res;

   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      if (bookshelf.size() == 0)
      {
         return true;
      }
      int curr = bookshelf.get(0);
      for (int i = 1; i < bookshelf.size(); i++)
      {
         if (bookshelf.get(i) < curr)
         {
            return false;
         }
         curr = bookshelf.get(i);
      }
      return true;
   }

   /**
    * Representation Invariant: All Books must be >= 0
    */
   public boolean isValidBookshelf() {
      if (bookshelf.size() == 0)
      {
         return true;
      }
      if (bookshelf == null) 
      {
        return false;
      }

      for (int height : bookshelf)
      {
         if (height <= 0)
         {
            return false;
         }
      }
      return true; 

   }

   public boolean hasNegative() {
      for (int book : bookshelf) {
         if (book < 0) {
            return true;
         }
      }
      return false;
   }

}
