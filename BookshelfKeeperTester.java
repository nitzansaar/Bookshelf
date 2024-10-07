import java.util.ArrayList;

public class BookshelfKeeperTester {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        // bookshelf: [1, 3, 4, 8, 11, 14, 15, 18]
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(8);
        list.add(11);
        list.add(14);
        list.add(15);
        list.add(18);
        Bookshelf bookshelf = new Bookshelf(list);
        BookshelfKeeper bookshelfKeeper = new BookshelfKeeper(bookshelf);
        System.out.println("testing");
        System.out.println(bookshelfKeeper.putHeight(9));
        System.out.println(bookshelfKeeper.toString());
        bookshelfKeeper.putHeight(20);
        System.out.println(bookshelf.toString());
        bookshelfKeeper.putHeight(10);
        System.out.println(bookshelf.toString());
        System.out.println(bookshelfKeeper.getTotalOperations());
        System.out.println(bookshelfKeeper.pickPos(2));
        System.out.println(bookshelfKeeper.toString());
        System.out.println(bookshelfKeeper.getTotalOperations());
        // bookshelfKeeper.pickPos(11);
        // System.out.println(bookshelfKeeper.bookshelf.toString());
        // System.out.println(bookshelfKeeper.pickPos(3));
        

    }
}
