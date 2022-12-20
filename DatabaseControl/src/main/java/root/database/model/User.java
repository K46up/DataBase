package root.database.model;


import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_group_id", nullable = false)
    private UserGroup userGroup;

    public User() {
    }

    public User(String username, String password, UserGroup userGroup) {
        this.username = username;
        this.password = password;
        this.userGroup = userGroup;
    }

    public boolean auth(String username, String password) {
        return Objects.equals(this.username, username) && Objects.equals(this.password, password);
    }

    public boolean isAdmin() {
        return getUserGroup().getName().equals(UserGroup.GROUPS[0]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public String toString() {
        return username;
    }


}
