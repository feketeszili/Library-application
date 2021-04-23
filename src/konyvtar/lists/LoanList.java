package konyvtar.lists;

import konyvtar.book.Book;
import konyvtar.loan.Loan;
import konyvtar.person.User;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanList implements java.io.Serializable{
    /* public int loanID;
    public int bookID;
    public int userID;
    public int librarianID;
    public LocalDate loanDate;
    public LocalDate loanDateExpires;*/

    public ArrayList<Loan> loanList;

    public List<Loan> returnList(){
        return loanList;
    }
    public int size() {
        return loanList.size();
    }

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

    public void writeXMLFile(){
        try{
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("LoanList");
            doc.appendChild(root);

            for (Loan loan : loanList) {

                Element loans = doc.createElement("Loans");
                root.appendChild(loans);

                Attr attr =  doc.createAttribute("loanID");
                attr.setValue(String.valueOf(loan.getLoanID()));
                loans.setAttributeNode(attr);

                Element bookID = doc.createElement("bookID");
                bookID.appendChild(doc.createTextNode(String.valueOf(loan
                        .getBookID())));
                loans.appendChild(bookID);

                Element userID = doc.createElement("userID");
                userID.appendChild(doc.createTextNode(String.valueOf(loan
                        .getUserID())));
                loans.appendChild(userID);

                Element librarianID = doc.createElement("librarianID");
                librarianID.appendChild(doc.createTextNode(String.valueOf(loan
                        .getLibrarianID())));
                loans.appendChild(librarianID);

                Element loanDate = doc.createElement("loanDate");
                loanDate.appendChild(doc.createTextNode(String.valueOf(loan.getLoanDate())));
                loans.appendChild(loanDate);

                Element loanDateExpires = doc.createElement("loanDateExpires");
                loanDateExpires.appendChild(doc.createTextNode(String.valueOf(loan.getLoanDateExpires())));
                loans.appendChild(loanDateExpires);


            }
            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            aTransformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            try {
                // location and name of XML file you can change as per need
                FileWriter fos = new FileWriter("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\kolcsonlista.xml");
                StreamResult result = new StreamResult(fos);
                aTransformer.transform(source, result);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (
                TransformerException ex) {
            System.out.println("Error outputting document");

        } catch (
                ParserConfigurationException ex) {
            System.out.println("Error building document");
        }
    }

    public void readXMLFile() {
        try {
            File fXmlFile = new File("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\kolcsonlista.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Loans");

            System.out.println("----------------------------");

            loanList.clear();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                            Loan loan = new Loan();
                            loan.setLoanID(Integer.parseInt(eElement.getAttribute("loanID")));
                            loan.setBookID(Integer.parseInt(eElement.getElementsByTagName("bookID").item(0).getTextContent()));
                            loan.setUserID(Integer.parseInt(eElement.getElementsByTagName("userID").item(0).getTextContent()));
                            loan.setLibrarianID(Integer.parseInt(eElement.getElementsByTagName("librarianID").item(0).getTextContent()));
                            loan.setLoanDate(LocalDate.parse(eElement.getElementsByTagName("loanDate").item(0).getTextContent()));
                            loan.setLoanDateExpires(LocalDate.parse(eElement.getElementsByTagName("loanDateExpires").item(0).getTextContent()));
                        loanList.add(loan);

                    System.out.println("Loan id:" + eElement.getAttribute("loanID"));
                    System.out.println("Book id:" + eElement.getElementsByTagName("bookID").item(0).getTextContent());
                    System.out.println("User id:" + eElement.getElementsByTagName("userID").item(0).getTextContent());
                    System.out.println("Librarian id:" + eElement.getElementsByTagName("librarianID").item(0).getTextContent());
                    System.out.println("Loan Date:" + eElement.getElementsByTagName("loanDate").item(0).getTextContent());
                    System.out.println("Loan Date Expires:" + eElement.getElementsByTagName("loanDateExpires").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
