package root.database.model;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String fullName;
    @Column
    private String gender;
    @Column
    private String phone;
    @Column
    private Date birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_type_id", nullable = false)
    private SportType sportType;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Student> students;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Schedule> scheduleList;
    public SportType getSportType() {
        return sportType;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    private String getDateString(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        return String.format("%d-%d-%d",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public String toString() {
        return fullName + "               " + getDateString() + "               " + phone + "               " + sportType;
    }
}
