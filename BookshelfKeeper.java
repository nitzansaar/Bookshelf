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
public class BookshelfKeeper {

   /**
    * Representation invariant:
    * 1. The bookshelf is always sorted in non-decreasing order by height.
    * 2. All books have positive heights.
    * 3. totalOps and currentOps are always non-negative.
    */
   
   // <add instance variables here>
   Bookshelf bookshelf;
   Bookshelf tempBookshelf;
   int totalOps;
   int currentOps;


   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      bookshelf = new Bookshelf();
      tempBookshelf = new Bookshelf();
      totalOps = 0;
      int currentOps = 0;
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      bookshelf = sortedBookshelf;
      tempBookshelf = new Bookshelf();
      totalOps = 0;
   }

/**
 * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
 * after picking up the book.
 * 
 * Returns the number of calls to mutators on the contained bookshelf used to complete this
 * operation. This must be the minimum number to complete the operation.
 * 
 * PRE: 0 <= position < getNumBooks()
 */
public int pickPos(int position) {
   int res = 0;
   int halfwayPoint = bookshelf.size() / 2;
   int i;
   
   // If position is closer to the end, use removeLast, else use removeFront
   if (position >= halfwayPoint) {
      // Closer to the back
      i = bookshelf.size() - 1;
      while (i > position) {
         // Move books to temporary shelf until we reach the position
         tempBookshelf.addFront(bookshelf.getHeight(i));
         bookshelf.removeLast();  // remove from the back
         i--;
         res++;
      }
      // Remove the book at the position
      bookshelf.removeLast(); 
      res++;

      // Put back books from the temporary shelf
      while (tempBookshelf.size() > 0) {
         bookshelf.addLast(tempBookshelf.getHeight(0)); // Add back from the front of the temp shelf
         tempBookshelf.removeFront();
         res++;
      }
   } else {
      // Closer to the front
      i = 0;
      while (i < position) {
         // Move books to temporary shelf until we reach the position
         tempBookshelf.addLast(bookshelf.getHeight(0));
         bookshelf.removeFront();  // remove from the front
         i++;
         res++;
      }
      // Remove the book at the position
      bookshelf.removeFront(); 
      res++;

      // Put back books from the temporary shelf
      while (tempBookshelf.size() > 0) {
         bookshelf.addFront(tempBookshelf.getHeight(tempBookshelf.size() - 1)); // Add back from the end of the temp shelf
         tempBookshelf.removeLast();
         res++;
      }
   }
   totalOps += res;
   currentOps = res;
   return res;
}

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {
      int res = 0;
      if (bookshelf.size() == 0) {
         bookshelf.addFront(height);
         res++;
         totalOps += res;
         currentOps = res;
         return res;

      }
      if (shouldRemoveFromEnd(height)) {
         while (bookshelf.getHeight(bookshelf.size() - 1) > height) {
            tempBookshelf.addFront(bookshelf.getHeight(bookshelf.size() - 1));
            bookshelf.removeLast();
            res++;
         }
         bookshelf.addLast(height);
         res++;
         while (tempBookshelf.size() != 0) {
            bookshelf.addLast(tempBookshelf.getHeight(0));
            tempBookshelf.removeFront();
            res++;
         }
      } else {
         while (bookshelf.getHeight(0) < height) {
            tempBookshelf.addLast(bookshelf.getHeight(0));
            bookshelf.removeFront();
            res++;
         }
         bookshelf.addFront(height);
         res++;
         while (tempBookshelf.size() != 0) {
            bookshelf.addFront(tempBookshelf.getHeight(tempBookshelf.size() - 1));
            tempBookshelf.removeLast();
            res++;
         }

      }

      totalOps += res;
      currentOps = res;
      return res;

   }


   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      
       return totalOps;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      
       return bookshelf.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      return "[" + bookshelf.toString() + "] " + currentOps + " " + totalOps;
      
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * The data is valid if:
    * 1. The books in the bookshelf are sorted in non-decreasing order.
    * 2. All books have positive heights.
    * 3. Total number of operations (totalOps) is non-negative.
    */
   private boolean isValidBookshelfKeeper() {
      // Check that the books are in non-decreasing order and heights are positive
      for (int i = 0; i < bookshelf.size() - 1; i++) {
         if (bookshelf.getHeight(i) > bookshelf.getHeight(i + 1) || bookshelf.getHeight(i) <= 0) {
            return false;
         }
      }
      
      // Check the last book's height to ensure it's positive (no next book to compare against)
      if (bookshelf.size() > 0 && bookshelf.getHeight(bookshelf.size() - 1) <= 0) {
         return false;
      }
      // All conditions satisfied

      return totalOps >= 0;
   }
   

   // add any other private methods here
   /*
    * Helper class for putHeight
    */
   private boolean shouldRemoveFromEnd(int height) {
      int i = 0;
      int halfwayPoint = bookshelf.size() / 2;
      while (i < bookshelf.size() && bookshelf.getHeight(i) < height) {
         i++;
      }
      return i > halfwayPoint;

   }


}