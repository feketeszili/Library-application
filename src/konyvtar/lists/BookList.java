package konyvtar.lists;
import konyvtar.book.Book;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class BookList implements java.io.Serializable {

    public ArrayList<Book>  bookList;

    public BookList() {

        Book [] books = new Book[20];

        // creates 20 books with random attributes
        for(int i = 0;  i < 20 ; ++i){
            // Have to create an arraylist to save keywords in it
            ArrayList<String> keywordList = new ArrayList();
            keywordList.add(randomGenerateString());
            keywordList.add(randomGenerateString());
            //adding values for attributes
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
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }

    public int randomGenerateNumber(){
        Random rand = new Random();
        int randNr = rand.nextInt(1000);

        return randNr;
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
}
