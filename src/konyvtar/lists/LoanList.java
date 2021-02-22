package konyvtar.lists;

import konyvtar.loan.Loan;
import konyvtar.person.User;

import java.io.*;
import java.util.ArrayList;

public class LoanList implements java.io.Serializable{
    /* public int loanID;
    public int bookID;
    public int userID;
    public int librarianID;
    public LocalDate loanDate;
    public LocalDate loanDateExpires;*/

    public ArrayList<Loan> loanList;

    public LoanList(){
        this.loanList = new ArrayList<>();
    }

    public void addLoanToList(Loan loan){
        this.loanList.add(loan);
    }

    // if you call this method you must change the books accessable attribute too
    public void deleteLoanFromList(int id){
        for(int i = 0 ; i < loanList.size() ; ++i){
            if( loanList.get(i).getLoanID() == id){
                loanList.remove(i);
                break;
            }
        }
    }

    public void displayLoanList(){
        int nrOfLoans = 0;
        for(Loan loan : loanList){
            System.out.println(loan);
            nrOfLoans++;
        }
        System.out.println("number of loans:" + nrOfLoans);
    }

    public void addTwoMoreWeeksToExpireDate(int id){
        for(Loan loan : loanList){
            if(loan.getLoanID() == id)
                loan.loanDateExpires.plusWeeks(2);
        }
    }

    public void writeIntoFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\loanlist.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(loanList);
            out.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in: \n loanlist.txt ");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void readFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\loanlist.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loanList = (ArrayList<Loan>) in.readObject();
            int counter = 0;
            for (Loan loan : loanList) {
                counter++;
                System.out.println("\n" + counter + ".Loan:");
                System.out.println("Loan ID:" + loan.getLoanID());
                System.out.println("Book ID:" + loan.getBookID());
                System.out.println("Librarian ID:" + loan.getLibrarianID());
                System.out.println("User ID:" + loan.getUserID());
                System.out.println("Loan Date:" + loan.getLoanDate());
                System.out.println("Loan Expire Date:" + loan.getLoanDateExpires());
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
    }

}
