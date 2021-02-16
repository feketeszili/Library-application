package konyvtar.loan;
import konyvtar.book.Book;
import konyvtar.person.User;
import konyvtar.person.Librarian;

public class Loan {

    public int loanID;
    public int bookID;
    public int userID;
    public int librarianID;
    public int loanDate;
    public int loanDateExpires;

    /* loanDateExpire attributum helyett meg lehet oldani,
    * hogy hozzaadunk a loanDate-hez egy bizonyos idot,
    * es igy megkapjuk a datumot, viszont nem sima szamokkal,
    * hanem valid idovel kell dolgozni
    */

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

    public int getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(int loanDate) {
        this.loanDate = loanDate;
    }

    public int getLoanDateExpires() {
        return loanDateExpires;
    }

    public void setLoanDateExpires(int loanDateExpires) {
        this.loanDateExpires = loanDateExpires;
    }
}
