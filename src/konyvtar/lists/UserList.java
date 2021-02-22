package konyvtar.lists;

import konyvtar.person.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserList implements java.io.Serializable {
    //public User(int id,
    // String name,
    // String address,
    // String email,
    // int phone)

    public ArrayList<User> userList;

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
}
