package konyvtar.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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


import javax.swing.*;
import java.awt.*;

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
    public TextField addUserName;
    public TextField addUserAddress;
    public TextField addUserEmail;
    public TextField addUserPhone;
    public TextField changeUserID;
    public TextField changeUserName;
    public TextField changeUserAddress;
    public TextField changeUserEmail;
    public TextField changeUserPhone;
    public TextField deleteUserID;
    //---------------------------------Librarian Menu Items TextFields------------------------------
    public TextField addLibrarianName;
    public TextField addLibrarianAddress;
    public TextField addLibrarianEmail;
    public TextField addLibrarianPhone;
    public TextField addLibrarianPassword;
    public TextField changeLibrarianID;
    public TextField changeLibrarianName;
    public TextField changeLibrarianAddress;
    public TextField changeLibrarianEmail;
    public TextField changeLibrarianPhone;
    public TextField changeLibrarianPassword;
    public TextField deleteLibrarianID;
    //---------------------------------Loan Menu Items TextFields-----------------------------------
    public TextField addLoanBookID;
    public TextField addLoanUserID;
    public TextField addLoanLibrarianID;
    public TextField changeLoanID;
    public TextField deleteLoanID;
    //---------------------------------Buttons------------------------------------------------------
    @FXML
    public Button buttonDeleteBook;
    @FXML
    public Button buttonAddBook;
    @FXML
    public Button buttonChangeBook;
    @FXML
    public Button buttonAddUser;
    @FXML
    public Button buttonChangeUser;
    @FXML
    public Button buttonDeleteUser;
    @FXML
    public Button buttonAddLibrarian;
    @FXML
    public Button buttonChangeLibrarian;
    @FXML
    public Button buttonDeleteLibrarian;
    @FXML
    public Button buttonAddLoan;
    @FXML
    public Button buttonChangeLoan;
    @FXML
    public Button buttonDeleteLoan;

    public void updateTableViewForBooks(ActionEvent actionEvent) {
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

    public void updateTableViewForUsers(ActionEvent actionEvent) {
        ObservableList<User> users = FXCollections.observableArrayList(
                userList.returnList());
        userID.setCellValueFactory(new PropertyValueFactory<>("id"));
        userName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        userAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        userPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        userTable.setItems(users);
    }

    public void updateTableViewForLibrarians(ActionEvent actionEvent) {
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

    public void updateTableViewForLoans(ActionEvent actionEvent) {
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
        updateTableViewForBooks(actionEvent);
    }

    public void loadUsersFromXMLIntoTableView(ActionEvent actionEvent) {
        userList.readXMLFile();
        updateTableViewForUsers(actionEvent);
    }

    public void loadLibrariansFromXMLIntoTableView(ActionEvent actionEvent) {
        librarianList.readXMLFile();
        updateTableViewForLibrarians(actionEvent);
    }

    public void loadLoansFromXMLIntoTableView(ActionEvent actionEvent) {
        loanList.readXMLFile();
        updateTableViewForLoans(actionEvent);
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
        ArrayList<String> items = new ArrayList<>(Arrays.asList(str.split(",")));
        book.setKeywords(items);
        book.setAccessable(Boolean.parseBoolean(addAccessable.getText()));
        bookList.addBooktoList(book);
        bookList.writeXMLFile();
        Stage stage = (Stage) buttonDeleteBook.getScene().getWindow();
        stage.close();
    }

    public void deleteBook(){
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
        Stage stage = (Stage) buttonDeleteBook.getScene().getWindow();
        stage.close();
        //loadBooksFromXMLIntoListView(actionEvent);
    }

    //TODO implement a version where you can click a row and opens a window with the current data
    //TODO implement those functions while you click a Delete,Add etc. button the window will close instantly
    //MouseEvent mouseEvent
    public void changeBookData(ActionEvent actionEvent) {
        Book book = bookTable.getSelectionModel().getSelectedItem();
       /* changeBookID = new TextField();
        changeBookTitle = new TextField();
        changeBookAuthor = new TextField();
        changeBookPublisher = new TextField();
        changeBookPublishedDate = new TextField();
        changeBookKeywords = new TextField();
        changeBookAccessable = new TextField();
        String s = book.getTitle();
        changeBookID.setText(Integer.toString(book.getID()));
        changeBookTitle.setText(s);
        changeBookAuthor.setText(book.getAuthor());
        changeBookPublisher.setText(book.getPublisher());
        changeBookPublishedDate.setText(Integer.toString(book.getPublishedDate()));
        changeBookKeywords.setText(book.getKeywords().toString());
        changeBookAccessable.setText(Boolean.toString(book.isAccessable()));*/
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("changeBookData.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 525);
            Stage stage = new Stage();
            stage.setTitle("Change Book Data");
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
                    ArrayList<String> items = new ArrayList<>(Arrays.asList(str.split(",")));
                    book.setKeywords(items);
                }
                if(!changeBookAccessable.getText().isEmpty())
                    book.setAccessable(Boolean.parseBoolean(changeBookAccessable.getText()));
            }
        }
        bookList.writeXMLFile();
        Stage stage = (Stage) buttonChangeBook.getScene().getWindow();
        stage.close();
    }

    public void addUser(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addUser.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 349, 367);
            Stage stage = new Stage();
            stage.setTitle("Add new User");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }

    }

    public void addUserButton(ActionEvent actionEvent) {
        User user = new User();
        user.setId(userList.size());
        user.setName(addUserName.getText());
        user.setAddress(addUserAddress.getText());
        user.setEmail(addUserEmail.getText());
        user.setPhone(Integer.parseInt(addUserPhone.getText()));
        userList.addUserToList(user);
        userList.writeXMLFile();
        Stage stage = (Stage) buttonAddUser.getScene().getWindow();
        stage.close();
    }

    public void changeUser(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("changeUser.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 398, 466);
            Stage stage = new Stage();
            stage.setTitle("Add new User");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }
    }

    public void changeUserButton(ActionEvent actionEvent){
        for(User user: userList.returnList()){
            if(user.getId() == Integer.parseInt(changeUserID.getText())){
                if(!changeUserName.getText().isEmpty())
                    user.setName(changeUserName.getText());
                if(!changeUserAddress.getText().isEmpty())
                    user.setAddress(changeUserAddress.getText());
                if(!changeUserEmail.getText().isEmpty())
                    user.setEmail(changeUserEmail.getText());
                if(!changeUserPhone.getText().isEmpty())
                    user.setPhone(Integer.parseInt(changeUserPhone.getText()));
            }
        }
        userList.writeXMLFile();
        Stage stage = (Stage) buttonChangeUser.getScene().getWindow();
        stage.close();
    }

    public void deleteUser(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("deleteUser.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            Stage stage = new Stage();
            stage.setTitle("Delete User");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }
    }

    public void deleteUserButton(ActionEvent actionEvent){
        int id = Integer.parseInt(deleteUserID.getText());
        userList.deleteUserFromList(id);
        userList.writeXMLFile();
        Stage stage = (Stage) buttonDeleteUser.getScene().getWindow();
        stage.close();
    }

    public void addLibrarian(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addLibrarian.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 349, 367);
            Stage stage = new Stage();
            stage.setTitle("Add new User");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }
    }

    public void addLibrarianButton(ActionEvent actionEvent){
        Librarian librarian = new Librarian();
        librarian.setId(librarianList.size());
        librarian.setName(addLibrarianName.getText());
        librarian.setAddress(addLibrarianAddress.getText());
        librarian.setEmail(addLibrarianEmail.getText());
        librarian.setPhone(Integer.parseInt(addLibrarianPhone.getText()));
        librarian.setPassword(addLibrarianPassword.getText());
        librarianList.addLibrarianToList(librarian);
        librarianList.writeXMLFile();
        Stage stage = (Stage) buttonAddLibrarian.getScene().getWindow();
        stage.close();
    }

    public void changeLibrarian(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("changeLibrarian.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 398, 466);
            Stage stage = new Stage();
            stage.setTitle("Change Librarian");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }
    }

    public void changeLibrarianButton(ActionEvent actionEvent){
        for(Librarian librarian: librarianList.returnList()){
            if(librarian.getId() == Integer.parseInt(changeLibrarianID.getText())){
                if(!changeLibrarianName.getText().isEmpty())
                    librarian.setName(changeLibrarianName.getText());
                if(!changeLibrarianAddress.getText().isEmpty())
                    librarian.setAddress(changeLibrarianAddress.getText());
                if(!changeLibrarianEmail.getText().isEmpty())
                    librarian.setEmail(changeLibrarianEmail.getText());
                if(!changeLibrarianPhone.getText().isEmpty())
                    librarian.setPhone(Integer.parseInt(changeLibrarianPhone.getText()));
                if(!changeLibrarianPassword.getText().isEmpty())
                    librarian.setPassword(changeLibrarianPassword.getText());
            }
        }
        librarianList.writeXMLFile();
        Stage stage = (Stage) buttonChangeLibrarian.getScene().getWindow();
        stage.close();
    }

    public void deleteLibrarian(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("deleteLibrarian.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            Stage stage = new Stage();
            stage.setTitle("Delete Librarian");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }
    }

    public void deleteLibrarianButton(ActionEvent actionEvent){
        int id = Integer.parseInt(deleteLibrarianID.getText());
        librarianList.deleteLibrarianFromList(id);
        librarianList.writeXMLFile();
        Stage stage = (Stage) buttonDeleteLibrarian.getScene().getWindow();
        stage.close();
    }

    public void addLoan(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addLoan.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 349, 367);
            Stage stage = new Stage();
            stage.setTitle("Add new Loan");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }

    }

    public void addLoanButton(ActionEvent actionEvent) {
        Loan loan = new Loan();
        loan.setLoanID(loanList.size());
        loan.setBookID(Integer.parseInt(addLoanBookID.getText()));
        for (Book book : bookList.returnList()) {
            if (book.getID() == loan.getLoanID()) {
                book.setAccessable(false);
                bookList.writeXMLFile();
            }
        }
        loan.setUserID(Integer.parseInt(addLoanUserID.getText()));
        loan.setLibrarianID(Integer.parseInt(addLoanLibrarianID.getText()));
        loan.setLoanDate(LocalDate.now());
        loan.setLoanDateExpires(loan.loanDate.plusWeeks(2));
        loanList.addLoanToList(loan);
        loanList.writeXMLFile();
        Stage stage = (Stage) buttonAddLoan.getScene().getWindow();
        stage.close();
    }

    public void changeLoan(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("changeLoan.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 349, 367);
            Stage stage = new Stage();
            stage.setTitle("Change Loan");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }
    }

    public void changeLoanButton(ActionEvent actionEvent){
        for(Loan loan: loanList.returnList()){
            if(loan.getLoanID() == Integer.parseInt(changeLoanID.getText())){
                loan.setLoanDateExpires(loan.loanDateExpires.plusWeeks(2));
            }
        }
        loanList.writeXMLFile();
        Stage stage = (Stage) buttonChangeLoan.getScene().getWindow();
        stage.close();
    }

    public void deleteLoan(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("deleteLoan.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            Stage stage = new Stage();
            stage.setTitle("Delete Loan");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

        }
    }

    public void deleteLoanButton(ActionEvent actionEvent){
        int id = Integer.parseInt(deleteLoanID.getText());
        for(Loan loan: loanList.returnList()){
            if(loan.getLoanID() == id) {
                int bookID = id;
                for(Book book : bookList.returnList()){
                    if(book.getID() == bookID) {
                        book.setAccessable(true);
                        bookList.writeXMLFile();
                    }
                }
            }
        }
        loanList.deleteLoanFromList(id);
        loanList.writeXMLFile();
        Stage stage = (Stage) buttonDeleteLoan.getScene().getWindow();
        stage.close();

    }
}
