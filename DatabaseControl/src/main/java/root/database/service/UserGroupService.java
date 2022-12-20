package root.database.service;

import root.database.dao.DaoManager;
import root.database.model.UserGroup;

import java.util.List;

public class UserGroupService {

    private DaoManager<UserGroup> daoManager;

    public UserGroupService() {
        daoManager = new DaoManager<>(UserGroup.class);
    }

    public void initGroups() {
        for (var group : UserGroup.GROUPS) {
            if(findByName(group) == null){
                save(new UserGroup(group));
            }
        }
    }

    public UserGroup findByName(String name) {
        for (var group : findAll()) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    public UserGroup findById(int id) {
        return daoManager.findById(id);
    }

    public void save(UserGroup userGroup) {
        daoManager.save(userGroup);
    }

    public void update(UserGroup userGroup) {
        daoManager.update(userGroup);
    }

    public void delete(UserGroup userGroup) {
        daoManager.delete(userGroup);
    }

    public List<UserGroup> findAll() {
        return daoManager.findAll();
    }

}
