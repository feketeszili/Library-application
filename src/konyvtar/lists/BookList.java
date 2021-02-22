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

    public String randomGenerateString(){
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
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

    /* This method changes the name of the specified book
     * The input is the original name of the book, but my
     * constructor creates 20 object(Book) with random generated
     * attributes so I had to implement another method to
     * get that books name, and in the main class I need a
     * String variable to put that in, so I can give the
     * original name with it for the method.
     */

    //bemenetnek egy masik konyvet is meg kell adni, s gyakorlatilag azt cserelem ki
    public void changeDataOfBookByID(int id){
        for(Book book : bookList){
            if(book.getId() == id){
                Book newBook = new Book();

                newBook.id = id;
                System.out.println("Add title: ");
                Scanner scan = new Scanner(System.in);
                newBook.title = scan.nextLine();
                System.out.println("Add author:");
                newBook.author = scan.nextLine();
                System.out.println("Add publisher:");
                newBook.publisher = scan.nextLine();
                System.out.println("Add published date:");
                newBook.publishedDate = scan.nextInt();
                System.out.println("Add keywords: ");
                ArrayList<String> newBookListKeywords = new ArrayList();
                newBookListKeywords.add(scan.nextLine());
                newBookListKeywords.add(scan.nextLine());
                newBook.keywords = newBookListKeywords;
                newBook.accessable = true;

                book.title = newBook.getTitle();
                book.author = newBook.getAuthor();
                book.publisher = newBook.getPublisher();
                book.publishedDate = newBook.getPublishedDate();
                book.keywords = newBook.getKeywords();
                book.accessable = true;
            }
            System.out.println(book.getTitle());
        }
    }

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
    // method, more description is above
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

    // write out every attributes
    public void readFromFile(){
        try {
            FileInputStream fileIn = new FileInputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\tavkozles.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            bookList = (ArrayList<Book>) in.readObject();
            for(Book book : bookList){
                System.out.println(book.getId());
                System.out.println(book.getTitle());
                System.out.println(book.getAuthor());
                System.out.println(book.getPublisher());
                System.out.println(book.getPublishedDate());
                System.out.println(book.getKeywords());
                System.out.println(book.isAccessable());
                System.out.println("\n Next book:");
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
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}
