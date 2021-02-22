package konyvtar.loan;
import java.time.LocalDate;

public class Loan implements java.io.Serializable  {

    public int loanID;
    public int bookID;
    public int userID;
    public int librarianID;
    public LocalDate loanDate;
    public LocalDate loanDateExpires;

    public Loan() {
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(int librarianID) {
        this.librarianID = librarianID;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getLoanDateExpires() {
        return loanDateExpires;
    }

    public void setLoanDateExpires(LocalDate loanDateExpires) {
        this.loanDateExpires = loanDateExpires;
    }
}
