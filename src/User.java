import java.io.Serializable;

public class User implements Serializable {

    private String Lastname;
    private String Firstname;
    private String username;
    private String password;
    private String role;

    public User(String lastname, String firstname, String username, String password, String role) {
        Lastname = lastname;
        Firstname = firstname;
        this.username = username;
        this.password = password;
        if (role!="1" && role!="0") {
            System.out.println("This role does not exists. You will be entered as user!");
            this.role = "0";
        } else {
            this.role = role;
        }
    }

    public User() {

    }

    public String getLastname() {
        return Lastname;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return
//                "User{" +
                Lastname  + " " + Firstname;
//                "+, username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", role=" + (role==1? "'admin'" : "'user'") +
//                "}";
    }
}
