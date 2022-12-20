package root.database.service;

import root.database.dao.DaoManager;
import root.database.model.Group;
import root.database.model.Student;

import java.util.List;

public class StudentService {
    private DaoManager<Student> daoManager;

    public StudentService() {
        daoManager = new DaoManager<>(Student.class);
    }

    public Student findById(int id) {
        return daoManager.findById(id);
    }

    public void save(Student student) {
        daoManager.save(student);
    }

    public void update(Student student) {
        daoManager.update(student);
    }

    public void delete(Student student) {
        daoManager.delete(student);
    }

    public List<Student> findAll() {
        return daoManager.findAll();
    }

}
