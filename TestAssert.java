import java.util.ArrayList;


public class TestAssert
{
   public static void main(String[] args)
   {
      ArrayList<Integer> list = new ArrayList<>();
      Bookshelf bookshelf = new Bookshelf(list);
      bookshelf.addFront(10);
      bookshelf.addFront(9);
      bookshelf.addFront(8);
      bookshelf.addFront(7);
      bookshelf.addFront(6);
      System.out.printf("Should be true: %b\n", bookshelf.isValidBookshelf());
      bookshelf.addFront(50);
      System.out.printf("Should be false: %b\n", bookshelf.isValidBookshelf());
      bookshelf.removeFront();
      System.out.printf("Should be true: %b\n", bookshelf.isValidBookshelf());
      bookshelf.addLast(-5);
      System.out.printf("Should be false: %b\n", bookshelf.isValidBookshelf());




   }
}