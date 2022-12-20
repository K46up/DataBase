package root.controller.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.controller.util.Helper;
import root.database.model.User;
import root.database.model.UserGroup;
import root.database.service.UserGroupService;
import root.database.service.UserService;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordAgainField;

    @FXML
    private ComboBox<UserGroup> userGroupComboBox;

    private UserService userService;
    private UserGroupService userGroupService;

    @FXML
    public void initialize() {
        userService = new UserService();
        userGroupService = new UserGroupService();
        for (var group : userGroupService.findAll()) {
            userGroupComboBox.getItems().add(group);
        }
        userGroupComboBox.getSelectionModel().select(1);
    }

    private void close() {
        Stage thisStage = (Stage) passwordField.getScene().getWindow();
        thisStage.close();
    }

    private boolean validateInput() {
        if (usernameTextField.getText().length() == 0) {
            return false;
        } else if (passwordField.getText().length() == 0) {
            return false;
        } else if (!passwordField.getText().equals(passwordAgainField.getText())) {
            return false;
        }
        return true;
    }

    @FXML
    public void registration() throws IOException {
        if (validateInput()) {
            if (userService.findByUsername(usernameTextField.getText()) == null) {
                User user = new User(usernameTextField.getText(), passwordField.getText(), userGroupComboBox.getSelectionModel().getSelectedItem());
                userService.save(user);
                close();
            } else {
                Helper.showAlertForm("Пользователь с таким логином уже зарегистрирован");
            }
        } else {
            Helper.showAlertForm("Ошибка ввода");
        }
    }
}
