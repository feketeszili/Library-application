package konyvtar.lists;
import konyvtar.book.Book;
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
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    public void changeDataOfBookByID(int id) throws Exception {
        for(Book book : bookList){
            if(book.getId() == id){
                boolean active = true;
                while(active) {
                    System.out.println("\nWhat do you want to change?\n" +
                            "Title\n" +
                            "Author\n" +
                            "Publisher\n" +
                            "Published Date\n" +
                            "Keywords\n" +
                            "Accessable");
                    Scanner scan = new Scanner(System.in);
                    String input = scan.nextLine();
                    switch (input) {
                        case "Title":
                            System.out.println("Add title: ");
                            book.setTitle(scan.nextLine());
                            System.out.println("Do you want to change another attribute? \nyes/no");
                            input = scan.nextLine();
                            active = input.equals("yes");
                            break;
                        case "Author":
                            System.out.println("Add author:");
                            book.setAuthor(scan.nextLine());
                            System.out.println("Do you want to change another attribute? \nyes/no");
                            input = scan.nextLine();
                            active = input.equals("yes");
                            break;
                        case "Publisher":
                            System.out.println("Add Publisher: ");
                            book.setPublisher(scan.nextLine());
                            System.out.println("Do you want to change another attribute? \nyes/no");
                            input = scan.nextLine();
                            active = input.equals("yes");
                            break;
                        case "Published Date":
                            System.out.println("Add Published Date: ");
                          /*  input = scan.nextLine();
                            int x = Integer.parseInt(input);
                            book.setPublishedDate(x);*/
                            book.setPublishedDate(scan.nextInt());
                            System.out.println("Do you want to change another attribute? \nyes/no\n");
                            input = scan.next();
                            active = input.equals("yes");
                            break;
                        case "Keywords":
                            System.out.println("Add keywords:");
                            ArrayList<String> bookListKeywords = new ArrayList();
                            bookListKeywords.add(scan.nextLine());
                            book.setKeywords(bookListKeywords);
                            System.out.println("Do you want to change another attribute? \nyes/no");
                            input = scan.nextLine();
                            active = input.equals("yes");
                            break;
                        case "Accessable":
                            System.out.println("Change Accessable:\n true/false");
                            book.setAccessable(scan.nextBoolean());
                            System.out.println("Do you want to change another attribute? \nyes/no");
                            input = scan.next();
                            active = input.equals("yes");
                            break;
                        default:
                            throw new Exception("Unknown component type");
                    }
                }
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

    public void writeXMLFile(){
        try{
        DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = dFact.newDocumentBuilder();
        Document doc = build.newDocument();

        Element root = doc.createElement("BookList");
        doc.appendChild(root);

        //Element Books = doc.createElement("Books");
        //root.appendChild(Books);

        for (Book book : bookList) {

            Element Books = doc.createElement("Books");
            root.appendChild(Books);

            Attr attr =  doc.createAttribute("id");
            attr.setValue(String.valueOf(book.getId()));
            Books.setAttributeNode(attr);

            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(String.valueOf(book
                    .getTitle())));
            Books.appendChild(title);

            Element author = doc.createElement("author");
            author.appendChild(doc.createTextNode(String.valueOf(book
                    .getAuthor())));
            Books.appendChild(author);

            Element publisher = doc.createElement("publisher");
            publisher.appendChild(doc.createTextNode(String.valueOf(book
                    .getPublisher())));
            Books.appendChild(publisher);

            Element publishedDate = doc.createElement("publishedDate");
            publishedDate.appendChild(doc.createTextNode(String.valueOf(book
                    .getPublishedDate())));
            Books.appendChild(publishedDate);

            Element keywords = doc.createElement("keywords");
            keywords.appendChild(doc.createTextNode(String.valueOf(book
                    .getKeywords())));
            Books.appendChild(keywords);

            Element accessable = doc.createElement("accessable");
            accessable.appendChild(doc.createTextNode(String.valueOf(book
                    .isAccessable())));
            Books.appendChild(accessable);

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
            FileWriter fos = new FileWriter("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\konyvlista.xml");
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

    /*
    * I have to save the data into the list when I
    * read it from the XML file, because this is the only
    * way to remain the original data. So when I read it,
    * I also set the specific data to the specific object,
    * and there will be no problem with the random generated
    * attributes anymore.
     */
    public void readXMLFile() {
        try {
            File fXmlFile = new File("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\konyvlista.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Books");

            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                   for(Book book : bookList){
                       if(book.getId() == Integer.parseInt(eElement.getAttribute("id"))){
                            book.setId(Integer.parseInt(eElement.getAttribute("id")));
                            book.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                            book.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                            book.setPublisher(eElement.getElementsByTagName("publisher").item(0).getTextContent());
                            // convert string into integer
                            book.setPublishedDate(Integer.parseInt(eElement.getElementsByTagName("publishedDate").item(0).getTextContent()));
                            // convert a String into an ArrayList
                            ArrayList<String> myList = new ArrayList<String>(Arrays.asList(eElement.getElementsByTagName("keywords").item(0).getTextContent().split(",")));
                            book.setKeywords(myList);
                            // convert String into boolean
                            book.setAccessable(Boolean.parseBoolean(eElement.getElementsByTagName("accessable").item(0).getTextContent()));
                        }
                   }
                    System.out.println("Book id:" + eElement.getAttribute("id"));
                    System.out.println("Book title:" + eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Book author:" + eElement.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Book publisher:" + eElement.getElementsByTagName("publisher").item(0).getTextContent());
                    System.out.println("Book published date:" + eElement.getElementsByTagName("publishedDate").item(0).getTextContent());
                    System.out.println("Book keywords:" + eElement.getElementsByTagName("keywords").item(0).getTextContent());
                    System.out.println("Book accessable:" + eElement.getElementsByTagName("accessable").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeIntoFile(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\booklist.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(bookList);
            out.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in: \n booklist.txt ");
        }catch(IOException i){
            i.printStackTrace();
        }
    }

    public void readFromFile(){
        try {
            FileInputStream fileIn = new FileInputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\booklist.txt");
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
