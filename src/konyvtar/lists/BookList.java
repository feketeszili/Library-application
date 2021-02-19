package konyvtar.lists;
import konyvtar.book.Book;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BookList implements java.io.Serializable {

    public ArrayList<Book>  bookList;

    public BookList() {

        // array object that can hold 20 reference,
        // books doesn't exists yet, this array is empty
        // so i have to create the books in the loop
        Book [] books = new Book[20];
        this.bookList = new ArrayList<>();
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
    *
    * UPDATE1:If i won't use BREAK the function will crash, maybe
    * because in the first point the lists size is bigger,
    * but after deletion, however, it is one smaller and
    * still works with the original value
    *
    * UPDATE2: the problem was that i added 3 more object to the
    * end of list which were nulls, and the program couldn't such
    * thing with them, and crashed when arrived at the first null
    *  object
    *
    */
    public void deleteBookFromList(int id){
        for( int i = 0 ; i < bookList.size() ; ++i){
            if (bookList.get(i).getId() == id ) {
                bookList.remove(i);
                break;
            }
        }
    }

    public void displayTheList(){
        int nrOfBooks = 0;
        for (Book book : bookList) {
            System.out.println(book);
            nrOfBooks++;
        }
        System.out.println("number of books:" + nrOfBooks);
    }

    /* This method changes the name of the specified book
     * The input is the original name of the book, but my
     * constructor creates 20 object(Book) with random generated
     * attributes so I had to implement another method to
     * get that books name, and in the main class I need a
     * String variable to put that in, so I can give the
     * original name with it for the method.
     */
    public void changeDataOfBook(String originalName){
        for (Book book : bookList) {
            if (book.getTitle().equals(originalName)) {
                Scanner scan = new Scanner(System.in);
                String newName = scan.nextLine();
                book.setTitle(newName);
            }
            System.out.println(book.getTitle());
        }
    }

    // this method was implented to test the changeDataOfBook
    // method, more description is abovee
    public String getBookNameFromList(int id) {
        String name = null;
        for (Book book : bookList) {
            if (book.getId() == id) {
                name = book.getTitle();
            }
        }
        return name;
    }
}
