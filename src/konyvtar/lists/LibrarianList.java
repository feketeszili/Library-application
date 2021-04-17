package konyvtar.lists;

import konyvtar.book.Book;
import konyvtar.person.Librarian;
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
import java.util.*;

public class LibrarianList implements java.io.Serializable {

    public ArrayList<Librarian> librarianList;
    public List<Librarian> returnList(){
        return librarianList;
    }

    public LibrarianList() {
        Librarian[] librarian = new Librarian[20];
        this.librarianList = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            librarian[i] = new Librarian();
            librarian[i].id = i;
            librarian[i].name = randomGenerateString2();
            librarian[i].address = randomGenerateString2();
            librarian[i].email = randomGenerateString2();
            librarian[i].phone = randomGenerateNumber();
            librarian[i].password = randomGenerateString2();
            librarianList.add(librarian[i]);
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

    public int randomGenerateNumber() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    public void addLibrarianToList(Librarian librarian) {
        librarian.setId(librarianList.size());
        this.librarianList.add(librarian);
    }

    public void deleteLibrarianFromList(int id) {
        for (int i = 0; i < librarianList.size(); ++i) {
            if (librarianList.get(i).getId() == id) {
                librarianList.remove(i);
                break;
            }
        }
    }

    public void displayTheList() {
        int nrOfLibrarians = 0;
        for (Librarian librarian : librarianList) {
            System.out.println(librarian);
            nrOfLibrarians++;
        }
        System.out.println("number of users:" + nrOfLibrarians);
    }

    public void changeDataofLibrarian(int id) {
        for (Librarian librarian : librarianList) {
            if (librarian.getId() == id) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Add name:");
                librarian.setName(scan.nextLine());
                System.out.println("Add address:");
                librarian.setAddress(scan.nextLine());
                System.out.println("Add email:");
                librarian.setEmail(scan.nextLine());
                System.out.println("Add phone:");
                librarian.setPhone(scan.nextInt());
                System.out.println("Add password:");
                librarian.setPassword(scan.nextLine());
            }
        }
    }

    public String getLibrarianNameFromList(int id) {
        String name = null;
        for (Librarian librarian : librarianList) {
            if (librarian.getId() == id)
                name = librarian.getName();
        }
        System.out.println("The searched Librarian name: " + name);
        return name;
    }

    public String getLibrarianAddressFromList(int id) {
        String address = null;
        for (Librarian librarian : librarianList) {
            if (librarian.getId() == id)
                address = librarian.getAddress();
        }
        System.out.println("The searched Users address: " + address);
        return address;
    }

    public String getLibrarianEmailFromList(int id) {
        String email = null;
        for (Librarian librarian : librarianList) {
            if (librarian.getId() == id)
                email = librarian.getEmail();
        }
        System.out.println("The searched Users email: " + email);
        return email;
    }

    public int getLibrarianPhoneFromList(int id) {
        int phone = 0;
        for (Librarian librarian : librarianList) {
            if (librarian.getId() == id) {
                phone = librarian.getPhone();
            }
        }
        System.out.println("The searched phone number:" + phone);
        return phone;
    }

    public String getLibrarianPasswordFromList(int id){
        String password = null;
        for(Librarian librarian : librarianList){
            if(librarian.getId() == id)
                password = librarian.getPassword();
        }
        System.out.println("The searched password:" + password);
        return password;
    }

    public void writeIntoFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\librarianlist.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(librarianList);
            out.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in: \n librarianlist.txt ");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void readFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\librarianlist.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            librarianList = (ArrayList<Librarian>) in.readObject();
            int counter = 0;
            for (Librarian librarian : librarianList) {
                counter++;
                System.out.println("\n" + counter + ".User:");
                System.out.println("ID:" + librarian.getId());
                System.out.println("Name:" + librarian.getName());
                System.out.println("Address:" + librarian.getAddress());
                System.out.println("Email:" + librarian.getEmail());
                System.out.println("Phone:" + librarian.getPhone());
                System.out.println("Password:" + librarian.getPassword());
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

            Element root = doc.createElement("LibrarianList");
            doc.appendChild(root);

            for (Librarian librarian : librarianList) {

                Element Librarians = doc.createElement("Librarians");
                root.appendChild(Librarians);

                Attr attr =  doc.createAttribute("id");
                attr.setValue(String.valueOf(librarian.getId()));
                Librarians.setAttributeNode(attr);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(String.valueOf(librarian
                        .getName())));
                Librarians.appendChild(name);

                Element address = doc.createElement("address");
                address.appendChild(doc.createTextNode(String.valueOf(librarian
                        .getAddress())));
                Librarians.appendChild(address);

                Element email = doc.createElement("email");
                email.appendChild(doc.createTextNode(String.valueOf(librarian
                        .getEmail())));
                Librarians.appendChild(email);

                Element phone = doc.createElement("phone");
                phone.appendChild(doc.createTextNode(String.valueOf(librarian
                        .getPhone())));
                Librarians.appendChild(phone);

                Element password = doc.createElement("password");
                password.appendChild(doc.createTextNode(String.valueOf(librarian
                        .getPassword())));
                Librarians.appendChild(password);

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
                FileWriter fos = new FileWriter("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\konyvtaroslista.xml");
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
            File fXmlFile = new File("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\konyvtaroslista.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Librarians");

            System.out.println("----------------------------");

            librarianList.clear();
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Librarian librarian = new Librarian();
                    librarian.setId(Integer.parseInt(eElement.getAttribute("id")));
                    librarian.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    librarian.setAddress(eElement.getElementsByTagName("address").item(0).getTextContent());
                    librarian.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());// convert string into integer
                    librarian.setPhone(Integer.parseInt(eElement.getElementsByTagName("phone").item(0).getTextContent()));
                    librarian.setPassword(eElement.getElementsByTagName("password").item(0).getTextContent());
                    librarianList.add(librarian);
                    System.out.println("Librarian id:" + eElement.getAttribute("id"));
                    System.out.println("Librarian Name:" + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Librarian Address:" + eElement.getElementsByTagName("address").item(0).getTextContent());
                    System.out.println("Librarian Email:" + eElement.getElementsByTagName("email").item(0).getTextContent());
                    System.out.println("Librarian phone:" + eElement.getElementsByTagName("phone").item(0).getTextContent());
                    System.out.println("Librarian Password:" + eElement.getElementsByTagName("password").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
