package konyvtar;

import konyvtar.book.Book;
import konyvtar.lists.BookList;


import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
/*
        Book book = new Book();
        book.id = 1;
        book.title = "Lord of the Rings";
        book.publisher = "Scientia";
        book.publishedDate = 2001;

        book.keywords = new ArrayList<>();
        book.keywords.add("fantasy");
        book.keywords.add("oscar");
        book.keywords.add("tolkien");
        book.accessable = true;

        System.out.println(book.getId());
        book.author = "J.R.R Tolkien";
        System.out.println(book.getAuthor());
        System.out.println(book.getPublishedDate());
        System.out.println(book.getKeywords());
//------------------writing into file------------------
       try{
            FileOutputStream  fileOutputStream = new FileOutputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\tavkozles.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(book);
            out.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in: \n tavkozles.txt ");
        }catch(IOException i){
            i.printStackTrace();
        }
       */
//------------------reading----------------------
    /*    Book book = null;
        try {
            FileInputStream fileIn = new FileInputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\tavkozles.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            book = (Book) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Book class not found");
            c.printStackTrace();
            return;
        }
        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());
        System.out.println(book.getPublisher());
        System.out.println(book.getPublishedDate());
        System.out.println(book.getKeywords());
    */
        BookList bookList = new BookList();
        //--------testing the add method---------
        ArrayList<String> keyWords = new ArrayList<>();
        keyWords.add("fantasy");
        keyWords.add("scifi");
        Book book = new Book(
                -1,
                "Lord of the rings",
                "J.R.R Tolkien",
                "Scientia",
                1976,
                keyWords,
                true);
        bookList.addBooktoList(book);
       // bookList.displayTheList();
        //--------- testing the delete method--------
/*
        bookList.displayTheList();
        bookList.deleteBookFromList(19);
        bookList.displayTheList();
*/

        //------ testing the changing the name of book from the list------
       // bookList.changeDataOfBookByID(5);

        //-------write into a file with method---------
        bookList.writeIntoFile();

        //-------read from file---------
        bookList.readFromFile();
        //for(int i =  0 ; i < 10 ; ++i){
          //  bookList.getBookNameFromList(i);
        //}

    }

}
