package root.controller.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.controller.controller.MainController;
import root.database.model.Employee;
import root.database.model.Group;
import root.database.model.Schedule;
import root.database.model.SportType;
import root.database.service.EmployeeService;
import root.database.service.GroupService;
import root.database.service.ScheduleService;
import root.database.service.SportTypeService;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.regex.Pattern;

public class ScheduleControlDialogController {

    @FXML
    private ComboBox<Group> groupComboBox;
    @FXML
    private ComboBox<Employee> employeeComboBox;
    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField timeTextField;
    private Schedule schedule;
    private MainController mainController;

    private ScheduleService scheduleService;
    private EmployeeService employeeService;
    private GroupService groupService;

    @FXML
    public void initialize() {
        scheduleService = new ScheduleService();
        groupService = new GroupService();
        employeeService = new EmployeeService();

        timeTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!timeTextField.isFocused()) {
                if (!timeTextField.getText().matches("^(\\d\\d:\\d\\d)")) {
                    timeTextField.clear();
                }
            }
        });

        for (var group : groupService.findAll()) {
            groupComboBox.getItems().add(group);
        }

        for (var employee : employeeService.findAll()) {
            employeeComboBox.getItems().add(employee);
        }

    }

    private void close() {
        Stage thisStage = (Stage) groupComboBox.getScene().getWindow();
        thisStage.close();
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        datePicker.setValue(schedule.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        groupComboBox.getSelectionModel().select(schedule.getGroup());
        employeeComboBox.getSelectionModel().select(schedule.getEmployee());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private boolean validateInput() {
        return true;
    }

    @FXML
    public void save() throws ParseException {
        if (validateInput()) {
            if (schedule == null) {
                schedule = new Schedule();
            }
            schedule.setDate((Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
            schedule.setGroup(groupComboBox.getSelectionModel().getSelectedItem());
            schedule.setEmployee(employeeComboBox.getSelectionModel().getSelectedItem());
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            schedule.setTime(new Time(formatter.parse(timeTextField.getText()).getTime()));
            scheduleService.update(schedule);
            mainController.refresh();
            close();
        }
    }

}
