package konyvtar.person;

public class Librarian extends Person {

    public String password;

    public Librarian(int id, String name, String address, String email, int phone, String password) {
        super(id, name, address, email, phone);
        this.password = password;
    }

    public Librarian() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
