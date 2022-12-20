package root.controller.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.controller.controller.MainController;
import root.database.model.User;
import root.database.model.UserGroup;
import root.database.service.UserGroupService;
import root.database.service.UserService;

public class UserControlDialogController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ComboBox<UserGroup> userGroupComboBox;

    private User user;
    private MainController mainController;

    private UserService userService;
    private UserGroupService userGroupService;

    @FXML
    public void initialize() {
        userService = new UserService();
        userGroupService = new UserGroupService();
        for (var group : userGroupService.findAll()) {
            userGroupComboBox.getItems().add(group);
        }
    }

    private void close() {
        Stage thisStage = (Stage) usernameTextField.getScene().getWindow();
        thisStage.close();
    }

    public void setUser(User user) {
        this.user = user;
        usernameTextField.setText(user.getUsername());
        passwordTextField.setText(user.getPassword());
        userGroupComboBox.getSelectionModel().select(user.getUserGroup());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private boolean validateInput() {
        return usernameTextField.getText().length() != 0 && passwordTextField.getText().length() != 0;
    }

    @FXML
    public void save() {
        if (validateInput()) {
            if (user == null) {
                user = new User();
            }
            user.setUsername(usernameTextField.getText());
            user.setPassword(passwordTextField.getText());
            user.setUserGroup(userGroupComboBox.getSelectionModel().getSelectedItem());
            userService.update(user);
            mainController.refresh();
            close();
        }
    }

}
