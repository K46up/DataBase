package root.database.service;

import root.database.dao.DaoManager;
import root.database.model.SportType;

import java.util.List;

public class SportTypeService {
    private DaoManager<SportType> daoManager;

    public SportTypeService() {
        daoManager = new DaoManager<>(SportType.class);
    }

    public SportType findById(int id) {
        return daoManager.findById(id);
    }

    public void save(SportType sportType) {
        daoManager.save(sportType);
    }

    public void update(SportType sportType) {
        daoManager.update(sportType);
    }

    public void delete(SportType sportType) {
        daoManager.delete(sportType);
    }

    public List<SportType> findAll() {
        return daoManager.findAll();
    }

}
