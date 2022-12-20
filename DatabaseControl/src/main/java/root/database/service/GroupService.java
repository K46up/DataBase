package root.database.service;

import root.database.dao.DaoManager;
import root.database.model.Group;

import java.util.List;

public class GroupService {
    private DaoManager<Group> daoManager;

    public GroupService() {
        daoManager = new DaoManager<>(Group.class);
    }

    public Group findById(int id) {
        return daoManager.findById(id);
    }

    public void save(Group group) {
        daoManager.save(group);
    }

    public void update(Group group) {
        daoManager.update(group);
    }

    public void delete(Group group) {
        daoManager.delete(group);
    }

    public List<Group> findAll() {
        return daoManager.findAll();
    }

}
