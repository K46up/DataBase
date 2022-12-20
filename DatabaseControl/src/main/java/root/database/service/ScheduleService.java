package root.database.service;

import root.database.dao.DaoManager;
import root.database.model.Group;
import root.database.model.Schedule;

import java.util.List;

public class ScheduleService {
    private DaoManager<Schedule> daoManager;

    public ScheduleService() {
        daoManager = new DaoManager<>(Schedule.class);
    }

    public Schedule findById(int id) {
        return daoManager.findById(id);
    }

    public void save(Schedule schedule) {
        daoManager.save(schedule);
    }

    public void update(Schedule schedule) {
        daoManager.update(schedule);
    }

    public void delete(Schedule schedule) {
        daoManager.delete(schedule);
    }

    public List<Schedule> findAll() {
        return daoManager.findAll();
    }

}
