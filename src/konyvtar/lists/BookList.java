package konyvtar.lists;
import konyvtar.book.Book;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class BookList implements java.io.Serializable {

    public ArrayList<Book>  bookList;

    public BookList() {

        // array object that can hold 20 reference,
        // books doesn't exists yet, this array is empty
        // so i have to create the books in the loop
        Book [] books = new Book[20];
        bookList = new ArrayList<>();
        for(int i = 0;  i < 20 ; ++i){
            // Have to create an arraylist to save keywords in it
            ArrayList<String> keywordList = new ArrayList<>();
            keywordList.add(randomGenerateString());
            keywordList.add(randomGenerateString());

            books[i] = new Book();
            books[i].id = i;
            books[i].title = randomGenerateString();
            books[i].author = randomGenerateString();
            books[i].publisher = randomGenerateString();
            books[i].publishedDate = randomGenerateNumber();
            books[i].keywords = keywordList;
            books[i].accessable = true;

            bookList.add(books[i]);
        }

    }

    public String randomGenerateString(){
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    public int randomGenerateNumber(){
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    public void addBooktoList(Book book){
        this.bookList.add(book);

    }

    /* Deletes one element(book) by his own id.
    * This function walks through on the list in a loop
    * and gets book by book from their position in list
    * and also comapares the current books id with
    * with the input id, if it's similar it's gonna be
    * deleted. And the i+1 book moves back to i position
    * where we deleted the specified book.
    */
    public void deleteBookFromList(int id){
        for( int i = 0 ; i < bookList.size() ; ++i){
            if (bookList.get(i).getId() == id )
                bookList.remove(i);
        }
    }

    public void displayTheList(){
        int i;
        for( i = 0 ; i < bookList.size() ; ++i) {
            System.out.println(bookList.get(i));
        }
        System.out.println(i);
        //for (Book book : bookList) {
          //  System.out.println(book);
        //}
    }
}
