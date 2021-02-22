package konyvtar.lists;
import konyvtar.book.Book;

import java.io.*;
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
            keywordList.add(randomGenerateString2());
            keywordList.add(randomGenerateString2());

            books[i] = new Book();
            books[i].id = i;
            books[i].title = randomGenerateString2();
            books[i].author = randomGenerateString2();
            books[i].publisher = randomGenerateString2();
            books[i].publishedDate = randomGenerateNumber();
            books[i].keywords = keywordList;
            books[i].accessable = true;

            bookList.add(books[i]);
        }

    }

    public String randomGenerateString2() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();

    }

    public int randomGenerateNumber(){
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    // inserts a new book at the end of the list
    public void addBooktoList(Book book){
        book.setId(bookList.size());
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

//TODO{ Have to implement a switch case if we want to
// change only one argument, or need a lot of if }
    public void changeDataOfBookByID(int id){
        for(Book book : bookList){
            if(book.getId() == id){
                Scanner scan = new Scanner(System.in);
                System.out.println("Add title: ");
                book.setTitle(scan.nextLine());
                System.out.println("Add author:");
                book.setAuthor(scan.nextLine());
                System.out.println("Add publisher:");
                book.setPublisher(scan.nextLine());
                System.out.println("Add published date:");
                book.setPublishedDate(scan.nextInt());
                System.out.println("Add keywords: ");
                ArrayList<String> bookListKeywords = new ArrayList();
                bookListKeywords.add(scan.nextLine());
                bookListKeywords.add(scan.nextLine());
                book.setKeywords(bookListKeywords);
                System.out.println("Book is accessable?:");
                book.setAccessable(scan.nextBoolean());
            }
        }
    }

    public String getBookNameFromList(int id) {
        String name = null;
        for (Book book : bookList) {
            if (book.getId() == id) {
                name = book.getTitle();
            }
        }
        System.out.println("The searched book name: " + name);
        return name;
    }

    public String getBookAuthorFromList(int id) {
        String name = null;
        for (Book book : bookList) {
            if (book.getId() == id) {
                name = book.getAuthor();
            }
        }
        System.out.println("The searched book author: " + name);
        return name;
    }

    public String getBookPublisherFromList(int id) {
        String name = null;
        for (Book book : bookList) {
            if (book.getId() == id) {
                name = book.getPublisher();
            }
        }
        System.out.println("The searched book publisher: " + name);
        return name;
    }

    public int getBookPublishedDateFromList(int id) {
        int publishedDate = 0;
        for (Book book : bookList) {
            if (book.getId() == id) {
                publishedDate = book.getPublishedDate();
            }
        }
        System.out.println("The searched book published date: " + publishedDate);
        return publishedDate;
    }

    public ArrayList<String> getBookKeywordsFromList(int id){
        ArrayList<String> keywords = new ArrayList();
        for(Book book : bookList){
            if(book.getId() == id){
                keywords = book.getKeywords();
            }
        }
        System.out.println("The searched book keywords: " + keywords);
        return keywords;
    }

    public void isBookAccessableFromList(int id){
        boolean right = false;
        for(Book book : bookList){
            if(book.getId() == id){
                System.out.println("The book is accessable");
                right = book.isAccessable();
            }
        }
    }

    public void writeIntoFile(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\tavkozles.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(bookList);
            out.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in: \n tavkozles.txt ");
        }catch(IOException i){
            i.printStackTrace();
        }
    }

    public void readFromFile(){
        try {
            FileInputStream fileIn = new FileInputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\tavkozles.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            bookList = (ArrayList<Book>) in.readObject();
            int counter = 0;
            for(Book book : bookList){
                counter++;
                System.out.println("\n" + counter +".Book:");
                System.out.println("ID:" + book.getId());
                System.out.println("Title:"+ book.getTitle());
                System.out.println("Author:" + book.getAuthor());
                System.out.println("Publisher:" +book.getPublisher());
                System.out.println("Published date:" +book.getPublishedDate());
                System.out.println("Keywords:" + book.getKeywords());
                System.out.println("Accessable:" + book.isAccessable());
            }
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
        //for (Book book : bookList) {
        //    System.out.println(book);
        //}
    }
}
