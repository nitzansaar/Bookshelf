import java.util.ArrayList;

public class BookshelfTester
{
   public static void main(String[] args)
   {
    
      System.out.println("Testing Bookshelf");
      //first constructor
      Bookshelf bs = new Bookshelf();
      System.out.println("Printing empty bookshelf");
      System.out.println(bs.toString());
     
      ArrayList<Integer> list = new ArrayList<>();
      list.add(10);
      list.add(14);
      list.add(19);
      //second constructor
      Bookshelf bs2 = new Bookshelf(list);
      System.out.println("Printing non empty Bookshelf: ");
      //toString method
      System.out.println(bs2.toString());
    //   System.out.println(bs2.getHeight(0));
    BookshelfKeeper bk = new BookshelfKeeper(bs2);
    System.out.println(bk.bookshelf.getHeight(0));
      
      //testing addLast (1)
      bs2.addLast(22);
      System.out.println("22 should be added to the end of the Bookshelf");
      System.out.println(bs2.toString());
      
      //testing addFront (2)
      bs2.addFront(9);
      System.out.println("9 should be the first element of the Bookshelf");
      System.out.println(bs2.toString());
      bs2.addFront(6);
      System.out.println("6 should now be the first element of the Bookshelf");
      System.out.println(bs2.toString());
      
      //Testing removeFront (3)
      bs2.removeFront();
      System.out.println("6 should be removed from the list");
      System.out.println(bs2.toString());
      bs2.removeFront();
      System.out.println("9 should be removed from the front");
      System.out.println(bs2.toString());
      
      //Testing removeLast (4)
      bs2.removeLast();
      System.out.println("22 should be removed from the end of the list");
      System.out.println(bs2.toString());
      
      //Testing getHeight (5)
      System.out.println("the height at index 0 should be 9");
      System.out.println(bs2.getHeight(0));

      //Testing size (6)
      System.out.println("The size should be 3");
      System.out.println(bs2.size());
      
      //Tested isSorted (7)
      System.out.println("Should be true: " + bs2.isSorted());
      bs2.addLast(1);
      System.out.println("Should be false: " + bs2.isSorted());
      bs2.removeLast();
      System.out.println("Should be true: " + bs2.isSorted());
      
     
      
      
      
      
   }
}