package konyvtar.lists;

import konyvtar.book.Book;
import konyvtar.person.Librarian;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UserList implements java.io.Serializable {

    public ArrayList<User> userList;

    public List<User> returnList(){
        return userList;
    }

    public UserList() {
        User[] user = new User[20];
        this.userList = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            user[i] = new User();
            user[i].id = i;
            user[i].name = randomGenerateString2();
            user[i].address = randomGenerateString2();
            user[i].email = randomGenerateString2();
            user[i].phone = randomGenerateNumber();
            userList.add(user[i]);
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

    public void addUserToList(User user) {
        user.setId(userList.size());
        this.userList.add(user);
    }

    public void deleteUserFromList(int id) {
        for (int i = 0; i < userList.size(); ++i) {
            if (userList.get(i).getId() == id) {
                userList.remove(i);
                break;
            }
        }
    }

    public void displayTheList() {
        int nrOfUsers = 0;
        for (User user : userList) {
            System.out.println(user);
            nrOfUsers++;
        }
        System.out.println("number of users:" + nrOfUsers);
    }

    public void changeDataofUser(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Add name:");
                user.setName(scan.nextLine());
                System.out.println("Add address:");
                user.setAddress(scan.nextLine());
                System.out.println("Add email:");
                user.setEmail(scan.nextLine());
                System.out.println("Add phone:");
                user.setPhone(scan.nextInt());
            }
        }
    }

    public String getUserNameFromList(int id) {
        String name = null;
        for (User user : userList) {
            if (user.getId() == id)
                name = user.getName();
        }
        System.out.println("The searched Users name: " + name);
        return name;
    }

    public String getUserAddressFromList(int id) {
        String address = null;
        for (User user : userList) {
            if (user.getId() == id)
                address = user.getAddress();
        }
        System.out.println("The searched Users address: " + address);
        return address;
    }

    public String getUserEmailFromList(int id) {
        String email = null;
        for (User user : userList) {
            if (user.getId() == id)
                email = user.getEmail();
        }
        System.out.println("The searched Users email: " + email);
        return email;
    }

    public int getUserPhoneFromList(int id) {
        int phone = 0;
        for (User user : userList) {
            if (user.getId() == id) {
                phone = user.getPhone();
            }
        }
        System.out.println("The searched phone number:" + phone);
        return phone;
    }

    public void writeIntoFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\userlist.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(userList);
            out.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in: \n userlist.txt ");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void readFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\userlist.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userList = (ArrayList<User>) in.readObject();
            int counter = 0;
            for (User user : userList) {
                counter++;
                System.out.println("\n" + counter + ".User:");
                System.out.println("ID:" + user.getId());
                System.out.println("Name:" + user.getName());
                System.out.println("Address:" + user.getAddress());
                System.out.println("Email:" + user.getEmail());
                System.out.println("Phone:" + user.getPhone());
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

            Element root = doc.createElement("UserList");
            doc.appendChild(root);

            for (User user : userList) {

                Element users = doc.createElement("Users");
                root.appendChild(users);

                Attr attr =  doc.createAttribute("id");
                attr.setValue(String.valueOf(user.getId()));
                users.setAttributeNode(attr);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(String.valueOf(user
                        .getName())));
                users.appendChild(name);

                Element address = doc.createElement("address");
                address.appendChild(doc.createTextNode(String.valueOf(user
                        .getAddress())));
                users.appendChild(address);

                Element email = doc.createElement("email");
                email.appendChild(doc.createTextNode(String.valueOf(user
                        .getEmail())));
                users.appendChild(email);

                Element phone = doc.createElement("phone");
                phone.appendChild(doc.createTextNode(String.valueOf(user
                        .getPhone())));
                users.appendChild(phone);


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
                FileWriter fos = new FileWriter("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\felhasznalolista.xml");
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
            File fXmlFile = new File("D:\\Egyetem2016-2021\\IV_ev\\tavkozles_szoftver\\src\\konyvtar\\felhasznalolista.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Users");

            System.out.println("----------------------------");
            userList.clear();
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                            User user = new User();
                            user.setId(Integer.parseInt(eElement.getAttribute("id")));
                            user.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                            user.setAddress(eElement.getElementsByTagName("address").item(0).getTextContent());
                            user.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
                            user.setPhone(Integer.parseInt(eElement.getElementsByTagName("phone").item(0).getTextContent()));
                            userList.add(user);
                    System.out.println("User id:" + eElement.getAttribute("id"));
                    System.out.println("User Name:" + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("User Address:" + eElement.getElementsByTagName("address").item(0).getTextContent());
                    System.out.println("User Email:" + eElement.getElementsByTagName("email").item(0).getTextContent());
                    System.out.println("User phone:" + eElement.getElementsByTagName("phone").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
