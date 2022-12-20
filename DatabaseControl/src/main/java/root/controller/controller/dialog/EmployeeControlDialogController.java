package root.controller.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.controller.controller.MainController;
import root.database.model.Employee;
import root.database.model.SportType;
import root.database.service.EmployeeService;
import root.database.service.SportTypeService;

import java.sql.Date;
import java.time.ZoneId;

public class EmployeeControlDialogController {


    @FXML
    private TextField fullNameTextField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextField phoneTextField;
    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private ComboBox<SportType> sportTypeComboBox;

    private Employee employee;
    private MainController mainController;

    private EmployeeService employeeService;
    private SportTypeService sportTypeService;

    @FXML
    public void initialize() {
        employeeService = new EmployeeService();
        sportTypeService = new SportTypeService();
        genderComboBox.getItems().add("Ж");
        genderComboBox.getItems().add("М");
        birthDatePicker.toString();


        phoneTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!phoneTextField.isFocused()) {
                if (!phoneTextField.getText().matches("^(\\+7|7|8)?[\\s\\-]?\\(?[489][0-9]{2}\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}$")) {
                    phoneTextField.clear();
                }
            }
        });

        for (var sportType : sportTypeService.findAll()) {
            sportTypeComboBox.getItems().add(sportType);
        }

    }

    private void close() {
        Stage thisStage = (Stage) fullNameTextField.getScene().getWindow();
        thisStage.close();
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        fullNameTextField.setText(employee.getFullName());
        genderComboBox.getSelectionModel().select(employee.getGender());
        phoneTextField.setText(employee.getPhone());
        birthDatePicker.setValue(employee.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        sportTypeComboBox.getSelectionModel().select(employee.getSportType());
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
            if (employee == null) {
                employee = new Employee();
            }
            employee.setFullName(fullNameTextField.getText());
            employee.setGender(genderComboBox.getSelectionModel().getSelectedItem());
            employee.setPhone(phoneTextField.getText());
            employee.setBirthDate(Date.from(birthDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            employee.setSportType(sportTypeComboBox.getSelectionModel().getSelectedItem());
            employeeService.update(employee);
            mainController.refresh();
            close();
        }
    }

}
