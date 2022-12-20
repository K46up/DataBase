package root.database.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_type_id", nullable = false)
    private SportType sportType;

    @OneToMany(mappedBy = "group", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Student> students;

    @OneToMany(mappedBy = "group", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Schedule> scheduleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    @Override
    public String toString() {
        return name + "               " + sportType;
    }
}
