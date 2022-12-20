package root.controller.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.controller.util.Helper;
import root.database.dao.DaoManager;
import root.database.model.User;
import root.database.service.UserService;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private UserService userService;

    @FXML
    public void initialize() {
        userService = new UserService();
    }

    private void close() {
        Stage thisStage = (Stage) passwordField.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    private void showRegistrationForm() throws IOException {
        Helper.showForm("/fxml/registration.fxml", "Регистрация");
    }


    private void showMainForm(User user) throws IOException {
        Helper.showMainForm("/fxml/main.fxml", user);
        close();
    }

    private boolean validateInput() {
        return usernameTextField.getText().length() != 0 && passwordField.getText().length() != 0;
    }

    @FXML
    public void login() throws IOException {
        if (validateInput()) {
            for (User user : userService.findAll()) {
                if (user.auth(usernameTextField.getText(), passwordField.getText())) {
                    showMainForm(user);
                    close();
                    return;
                }
            }
            Helper.showAlertForm("Пользователь не найден");
        } else {
            Helper.showAlertForm("Ошибка ввода");
        }

    }

}
