// Name: Nitzan Saar
// USC NetID: 8106373693
// CSCI455 PA2
// Fall 2024

import java.util.ArrayList;

/**
 * Class Bookshelf
 * Implements the idea of arranging books into a bookshelf. Books on a bookshelf can only be accessed 
 * in a specific way so that books don’t fall down. You can add or remove a book only when it’s on 
 * one of the ends of the shelf. However, you can look at any book on a shelf by giving its location 
 * (starting at 0). Books are identified only by their height; two books of the same height can be 
 * thought of as two copies of the same book.
 */
public class Bookshelf {

   /**
    * Representation invariant:
    * All books need to have heights >= 0.
    * ArrayList represents the heights of books in a bookshelf.
    */
   
   // Instance variable to store the heights of books on the bookshelf
   private ArrayList<Integer> bookshelf = new ArrayList<>();

   /**
    * Creates an empty Bookshelf object with no books.
    */
   public Bookshelf() {
      // assert isValidBookshelf();
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks.
    * 
    * @param pileOfBooks ArrayList containing the initial heights of books.
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers representing 
    * the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      this.bookshelf = pileOfBooks;
      // assert isValidBookshelf();
   }

   /**
    * Inserts a book with the specified height at the start of the Bookshelf, i.e., 
    * it will end up at position 0.
    * 
    * @param height the height of the book to be inserted at the front of the shelf.
    * 
    * PRE: height > 0 (height of the book is always positive).
    */
   public void addFront(int height) {
      if (height < 0) {
         System.out.println("Cannot add negative numbers.");
      }
      bookshelf.add(0, height);
      assert isValidBookshelf();
   }

   /**
    * Inserts a book with the specified height at the end of the Bookshelf.
    * 
    * @param height the height of the book to be added at the end of the shelf.
    * 
    * PRE: height > 0 (height of the book is always positive).
    */
   public void addLast(int height) {
      if (height < 0) {
         System.out.println("Cannot add negative numbers.");
      }
      bookshelf.add(height);
      assert isValidBookshelf();
   }

   /**
    * Removes the book at the start of the Bookshelf and returns the height of the removed book.
    * 
    * @return the height of the book removed from the front of the shelf.
    * 
    * PRE: this.size() > 0 i.e., can only be called on non-empty Bookshelf.
    */
   public int removeFront() {
      int res = bookshelf.get(0);
      bookshelf.remove(0);
      assert isValidBookshelf();
      return res;
   }

   /**
    * Removes the book at the end of the Bookshelf and returns the height of the removed book.
    * 
    * @return the height of the book removed from the end of the shelf.
    * 
    * PRE: this.size() > 0 i.e., can only be called on non-empty Bookshelf.
    */
   public int removeLast() {
      int res = bookshelf.get(bookshelf.size() - 1);
      bookshelf.remove(bookshelf.size() - 1);
      assert isValidBookshelf();
      return res;    
   }

   /**
    * Gets the height of the book at the given position.
    * 
    * @param position the position of the book on the shelf (starting at 0).
    * @return the height of the book at the specified position.
    * 
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      return bookshelf.get(position);
   }

   /**
    * Returns the number of books on this Bookshelf.
    * 
    * @return the total number of books on the shelf.
    */
   public int size() {
      return bookshelf.size(); 
   }

   /**
    * Returns a string representation of this Bookshelf. The string contains the height of all
    * books on the bookshelf, in the order they are arranged on the shelf, using the format: 
    * "[7, 33, 5, 4, 3]".
    * 
    * @return a string representation of the bookshelf.
    */
   public String toString() {
      String res = "";
      for (int i = 0; i < bookshelf.size(); i++) {
         if (i != 0 && i != bookshelf.size() - 1) {
            res += bookshelf.get(i) + ", ";
         } else if (i == 0 && bookshelf.size() > 1) {
            res += bookshelf.get(i) + ", ";
         } else {
            res += bookshelf.get(i);
         }
      }
      assert isValidBookshelf();
      return res;
   }

   /**
    * Returns true if the books on this Bookshelf are in non-decreasing order.
    * 
    * @return true if the bookshelf is sorted in non-decreasing order, false otherwise.
    * 
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      if (bookshelf.size() == 0) {
         return true;
      }
      int curr = bookshelf.get(0);
      for (int i = 1; i < bookshelf.size(); i++) {
         if (bookshelf.get(i) < curr) {
            return false;
         }
         curr = bookshelf.get(i);
      }
      return true;
   }

   /**
    * Checks if the bookshelf is in a valid state.
    * The bookshelf is valid if all books have heights greater than or equal to 0.
    * 
    * @return true if the bookshelf is valid, false otherwise.
    */
   public boolean isValidBookshelf() {
      if (bookshelf.size() == 0) {
         return true;
      }
      if (bookshelf == null) {
        return false;
      }

      for (int height : bookshelf) {
         if (height <= 0) {
            return false;
         }
      }
      return true; 
   }

   /**
    * Checks if the bookshelf contains any books with negative heights.
    * 
    * @return true if the bookshelf has any negative height books, false otherwise.
    */
   public boolean hasNegative() {
      for (int book : bookshelf) {
         if (book < 0) {
            return true;
         }
      }
      return false;
   }

}