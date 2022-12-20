package root.database.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sport_type")
public class SportType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "sportType", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Employee> employees;

    @OneToMany(mappedBy = "sportType", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Group> groups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return name;
    }
}
