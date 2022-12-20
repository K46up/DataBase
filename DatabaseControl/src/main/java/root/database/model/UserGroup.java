package root.database.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_group")
public class UserGroup {

    public static final String[] GROUPS = {"ADMIN", "TEACHER"};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "userGroup", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<User> users;

    public UserGroup() {
    }

    public UserGroup(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return name;
    }
}
