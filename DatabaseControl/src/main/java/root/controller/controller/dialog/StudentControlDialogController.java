package root.controller.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.controller.controller.MainController;
import root.database.model.Employee;
import root.database.model.Group;
import root.database.model.Student;
import root.database.service.EmployeeService;
import root.database.service.GroupService;
import root.database.service.StudentService;

import java.sql.Date;
import java.time.ZoneId;

public class StudentControlDialogController {


    @FXML
    private TextField fullNameTextField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextField phoneTextField;
    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private ComboBox<Group> groupComboBox;
    @FXML
    private ComboBox<Employee> employeeComboBox;

    private Student student;
    private MainController mainController;

    private StudentService studentService;
    private EmployeeService employeeService;
    private GroupService groupService;

    @FXML
    public void initialize() {
        studentService = new StudentService();
        employeeService = new EmployeeService();
        groupService = new GroupService();
        genderComboBox.getItems().add("Ж");
        genderComboBox.getItems().add("М");

        phoneTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!phoneTextField.isFocused()) {
                if (!phoneTextField.getText().matches("^(\\+7|7|8)?[\\s\\-]?\\(?[489][0-9]{2}\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}$")) {
                    phoneTextField.clear();
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
        Stage thisStage = (Stage) fullNameTextField.getScene().getWindow();
        thisStage.close();
    }

        public void setStudent(Student student) {
        this.student = student;
        fullNameTextField.setText(student.getFullName());
        genderComboBox.getSelectionModel().select(student.getGender());
        phoneTextField.setText(student.getPhone());
        birthDatePicker.setValue(student.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        groupComboBox.getSelectionModel().select(student.getGroup());
        employeeComboBox.getSelectionModel().select(student.getEmployee());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private boolean validateInput() {
        return fullNameTextField.getText().length() != 0 && phoneTextField.getText().length() != 0;
    }

    @FXML
    public void save() {
        if (validateInput()) {
            if (student == null) {
                student = new Student();
            }
            student.setFullName(fullNameTextField.getText());
            student.setGender(genderComboBox.getSelectionModel().getSelectedItem());
            student.setPhone(phoneTextField.getText());
            student.setBirthDate(Date.from(birthDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            student.setGroup(groupComboBox.getSelectionModel().getSelectedItem());
            student.setEmployee(employeeComboBox.getSelectionModel().getSelectedItem());
            studentService.update(student);
            mainController.refresh();
            close();
        }
    }

}
