package konyvtar.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import konyvtar.book.Book;
import konyvtar.lists.*;
import konyvtar.lists.BookList;
import javafx.application.Platform;
import konyvtar.loan.Loan;
import konyvtar.person.Librarian;
import konyvtar.person.User;


import java.awt.*;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Controller {

    public static BookList bookList = new BookList();
    public static UserList userList = new UserList();
    public static LibrarianList librarianList = new LibrarianList();
    public static LoanList loanList = new LoanList();

    //----------------------------------------- TableViews for lists-----------------------------------------
    public TableView<Book> bookTable;
    public TableColumn<Book, Integer> bookID;
    public TableColumn<Book, String> bookTitle;
    public TableColumn<Book, String> bookAuthor;
    public TableColumn<Book, String> bookPublisher;
    public TableColumn<Book, Integer> bookPublishedDate;
    public TableColumn<Book, ArrayList> bookKeywords;
    public TableColumn<Book, Boolean> bookAcessable;

    public TableView<User> userTable;
    public TableColumn<User, Integer> userID;
    public TableColumn<User, String> userName;
    public TableColumn<User, String> userAddress;
    public TableColumn<User, String> userEmail;
    public TableColumn<User, Integer> userPhone;

    public TableView<Librarian> librarianTable;
    public TableColumn<Librarian, Integer> librarianID;
    public TableColumn<Librarian, String> librarianName;
    public TableColumn<Librarian, String> librarianAddress;
    public TableColumn<Librarian, String> librarianEmail;
    public TableColumn<Librarian, Integer> librarianPhone;
    public TableColumn<Librarian, String> librarianPassword;

    public TableView<Loan> loanTable;
    public TableColumn<Loan, Integer> loanID;
    public TableColumn<Loan, Integer> bookID2;
    public TableColumn<Loan, Integer> userID2;
    public TableColumn<Loan, Integer> librarianID2;
    public TableColumn<Loan, LocalDate> loanDate;
    public TableColumn<Loan, LocalDate> loanDateExpire;

    //------------------------------- Book Menu Items Textfields--------------------------------
    public TextField addTitle;
    public TextField addAuthor;
    public TextField addPublisher;
    public TextField addPublishedDate;
    public TextField addKeywords;
    public TextField addAccessable;
    public TextField deleteBook;
    public TextField changeBookID;
    public TextField changeBookTitle;
    public TextField changeBookAuthor;
    public TextField changeBookPublisher;
    public TextField changeBookPublishedDate;
    public TextField changeBookKeywords;
    public TextField changeBookAccessable;

    //---------------------------------User Menu Items Textfields----------------------------------
    //---------------------------------Librarian Menu Items TextFields------------------------------
    //---------------------------------Loan Menu Items TextFields-----------------------------------

    public void updateTableViewForBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList(
                bookList.returnList());
        bookID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        bookPublishedDate.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        bookKeywords.setCellValueFactory(new PropertyValueFactory<>("Keywords"));
        bookAcessable.setCellValueFactory(new PropertyValueFactory<>("accessable"));
        bookTable.setItems(books);
    }

    public void updateTableViewForUsers() {
        ObservableList<User> users = FXCollections.observableArrayList(
                userList.returnList());
        userID.setCellValueFactory(new PropertyValueFactory<>("id"));
        userName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        userAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        userPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        userTable.setItems(users);
    }

    public void updateTableViewForLibrarians() {
        ObservableList<Librarian> librarians = FXCollections.observableArrayList(
                librarianList.returnList());
        librarianID.setCellValueFactory(new PropertyValueFactory<>("id"));
        librarianName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        librarianAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        librarianEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        librarianPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        librarianPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        librarianTable.setItems(librarians);
    }

    public void updateTableViewForLoans() {
        ObservableList<Loan> loans = FXCollections.observableArrayList(
                loanList.returnList());
        loanID.setCellValueFactory(new PropertyValueFactory<>("loanID"));
        bookID2.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        userID2.setCellValueFactory(new PropertyValueFactory<>("userID"));
        librarianID2.setCellValueFactory(new PropertyValueFactory<>("librarianID"));
        loanDate.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        loanDateExpire.setCellValueFactory(new PropertyValueFactory<>("loanDateExpires"));
        loanTable.setItems(loans);
    }

    public void loadBooksFromXMLIntoListView(ActionEvent actionEvent) {
        bookList.readXMLFile();
        updateTableViewForBooks();
    }

    public void loadUsersFromXMLIntoTableView(ActionEvent actionEvent) {
        userList.readXMLFile();
        updateTableViewForUsers();
    }

    public void loadLibrariansFromXMLIntoTableView(ActionEvent actionEvent) {
        librarianList.readXMLFile();
        updateTableViewForLibrarians();
    }

    public void loadLoansFromXMLIntoTableView(ActionEvent actionEvent) {
        loanList.readXMLFile();
        updateTableViewForLoans();
    }

    public void addBook(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addbook.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            Stage stage = new Stage();
            stage.setTitle("Add new Book");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }

    }

    public void addBookButton(ActionEvent actionEvent) {
        Book book = new Book();
        book.setID(bookList.size());
        book.setTitle(addTitle.getText());
        book.setAuthor(addAuthor.getText());
        book.setPublisher(addPublisher.getText());
        book.setPublishedDate(Integer.parseInt(addPublishedDate.getText()));
        String str = addKeywords.getText();
        ArrayList<String> items = new ArrayList<>(Arrays.asList(str.split(", ")));
        book.setKeywords(items);
        book.setAccessable(Boolean.parseBoolean(addAccessable.getText()));
        bookList.addBooktoList(book);
        bookList.writeXMLFile();
    }

    public void deleteBook(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("deletebook.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            Stage stage = new Stage();
            stage.setTitle("Delete Book");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }
    }

    public void deleteBookButton(ActionEvent actionEvent){
        int id = Integer.parseInt(deleteBook.getText());
        bookList.deleteBookFromList(id);
        bookList.writeXMLFile();
    }

    public void changeBookData(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("changeBookData.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            Stage stage = new Stage();
            stage.setTitle("Delete Book");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }
    }

    public void changeBookDataButton(ActionEvent actionEvent){
        for(Book book: bookList.returnList()){
            if(book.getID() == Integer.parseInt(changeBookID.getText())){
                if(!changeBookTitle.getText().isEmpty())
                    book.setTitle(changeBookTitle.getText());
                if(!changeBookAuthor.getText().isEmpty())
                    book.setAuthor(changeBookAuthor.getText());
                if(!changeBookAuthor.getText().isEmpty())
                    book.setPublisher(changeBookPublisher.getText());
                if(!changeBookPublishedDate.getText().isEmpty())
                    book.setPublishedDate(Integer.parseInt(changeBookPublishedDate.getText()));
                if(!changeBookKeywords.getText().isEmpty()){
                    String str = changeBookKeywords.getText();
                    ArrayList<String> items = new ArrayList<>(Arrays.asList(str.split(", ")));
                    book.setKeywords(items);
                }
                if(!changeBookAccessable.getText().isEmpty())
                    book.setAccessable(Boolean.parseBoolean(changeBookAccessable.getText()));
            }
        }
        bookList.writeXMLFile();
    }

}
