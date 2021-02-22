package konyvtar.person;

public class User extends Person implements java.io.Serializable{

    public User(int id, String name, String address, String email, int phone) {
        super(id, name, address, email, phone);
    }
    public User(){
        super();
    }
}
