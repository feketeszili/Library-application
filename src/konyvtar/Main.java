package konyvtar;

import konyvtar.book.Book;
import konyvtar.lists.BookList;
import konyvtar.lists.LibrarianList;
import konyvtar.lists.LoanList;
import konyvtar.lists.UserList;
import konyvtar.loan.Loan;


import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {


        BookList bookList = new BookList();
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
        //bookList.changeDataOfBookByID(1);
        bookList.writeIntoFile();
        bookList.readFromFile();

        UserList userList = new UserList();
        userList.writeIntoFile();
        LibrarianList librarianList = new LibrarianList();
        librarianList.writeIntoFile();

        Scanner scan = new Scanner(System.in);
        Loan loan = new Loan();
        loan.loanID = 0;

        System.out.println("\nAdd book ID:");
        loan.bookID = scan.nextInt();
        bookList.changeDataOfBookByID(loan.bookID);

        System.out.println("\nAdd User ID");
        loan.userID = scan.nextInt();

        System.out.println("\nAdd librarian ID:");
        loan.librarianID = scan.nextInt();

        loan.loanDate = LocalDate.now();
        System.out.println("Date:"+ loan.loanDate);

        loan.loanDateExpires = loan.loanDate.plusWeeks(2);
        System.out.println("Date expires:" + loan.loanDateExpires);

        LoanList loanList = new LoanList();
        loanList.addLoanToList(loan);
        loanList.writeIntoFile();
        loanList.readFromFile();

    }

}
