package root.controller.controller;

import com.sun.javafx.menu.MenuItemBase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;
import root.controller.util.Helper;
import root.controller.util.TableName;
import root.database.model.*;
import root.database.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainController {
    @FXML
    private ListView listView;
    @FXML
    private TabPane tabPane;

    private UserService userService;
    private UserGroupService userGroupService;
    private EmployeeService employeeService;
    private SportTypeService sportTypeService;
    private GroupService groupService;
    private StudentService studentService;
    private ScheduleService scheduleService;

    private String activeTableName;

    @FXML
    private TextField searchTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button showChartFormButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label LableText;
    private String filterText;



    private User currentUser;


    @FXML
    public void initialize() throws IOException {
        userService = new UserService();
        userGroupService = new UserGroupService();
        employeeService = new EmployeeService();
        sportTypeService = new SportTypeService();
        groupService = new GroupService();
        studentService = new StudentService();
        scheduleService = new ScheduleService();

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterText = searchTextField.getText();
            refresh();
        });

        tabPane.getTabs().add(new Tab(TableName.USER));
        tabPane.getTabs().add(new Tab(TableName.USER_GROUP));
        tabPane.getTabs().add(new Tab(TableName.EMPLOYEE));
        tabPane.getTabs().add(new Tab(TableName.SPORT_TYPE));
        tabPane.getTabs().add(new Tab(TableName.GROUP));
        tabPane.getTabs().add(new Tab(TableName.STUDENT));
        tabPane.getTabs().add(new Tab(TableName.SCHEDULE));
        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            refresh();
        });
        refresh();
    }

    @FXML
    public void showCharForm() throws IOException {
        Helper.showChartForm();
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public void roleCheck() {
        if (!currentUser.isAdmin()) {
            addButton.setDisable(true);
            editButton.setDisable(true);
            deleteButton.setDisable(true);
            showChartFormButton.setDisable(true);
        }
    }

    private void filter() {
        if (filterText != null && filterText.length() > 0) {
            var items = new ArrayList<>();
            for (var item : listView.getItems()) {
                if (item.toString().toLowerCase().contains(filterText.toLowerCase())) {
                    items.add(item);
                }
            }
            listView.getItems().clear();
            for (var item : items) {
                listView.getItems().add(item);
            }
        }
    }


    private void showUserData() {
        listView.getItems().clear();
        listView.getItems().addAll(userService.findAll());
        filter();
    }

    private void showUserGroupData() {
        listView.getItems().clear();
        listView.getItems().addAll(userGroupService.findAll());
        filter();
    }

    private void showEmployeeData() {
        listView.getItems().clear();
        listView.getItems().addAll(employeeService.findAll());
        filter();
    }

    private void showSportTypeData() {
        listView.getItems().clear();
        listView.getItems().addAll(sportTypeService.findAll());
        filter();
    }

    private void showGroupData() {
        listView.getItems().clear();
        listView.getItems().addAll(groupService.findAll());
        filter();
    }

    private void showStudentData() {
        listView.getItems().clear();
        listView.getItems().addAll(studentService.findAll());
        filter();
    }

    private void showScheduleData() {
        listView.getItems().clear();
        listView.getItems().addAll(scheduleService.findAll());
        filter();
    }

    public void refresh() {
        String selectTabName = tabPane.getSelectionModel().getSelectedItem().getText();
        activeTableName = selectTabName;
        if (selectTabName.equals(TableName.USER)) {
            showUserData();
        } else if (selectTabName.equals(TableName.USER_GROUP)) {
            showUserGroupData();
        } else if (selectTabName.equals(TableName.EMPLOYEE)) {
            showEmployeeData();
        } else if (selectTabName.equals(TableName.SPORT_TYPE)) {
            showSportTypeData();
        } else if (selectTabName.equals(TableName.GROUP)) {
            showGroupData();
        } else if (selectTabName.equals(TableName.STUDENT)) {
            showStudentData();
        } else if (selectTabName.equals(TableName.SCHEDULE)) {
            showScheduleData();
        }
    }

    @FXML
    public void editItem() throws IOException {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            if (activeTableName.equals(TableName.USER)) {
                Helper.showUserControlDialog(this, (User) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.USER_GROUP)) {
                Helper.showUserGroupControlDialog(this, (UserGroup) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.EMPLOYEE)) {
                Helper.showEmployeeControlDialog(this, (Employee) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.SPORT_TYPE)) {
                Helper.showSportTypeControlDialog(this, (SportType) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.GROUP)) {
                Helper.showGroupControlDialog(this, (Group) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.STUDENT)) {
                Helper.showStudentControlDialog(this, (Student) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.SCHEDULE)) {
                Helper.showScheduleControlDialog(this, (Schedule) listView.getSelectionModel().getSelectedItem());
            }
        }
    }


    @FXML
    public void addItem() throws IOException {
        if (activeTableName.equals(TableName.USER)) {
            Helper.showUserControlDialog(this, null);
        } else if (activeTableName.equals(TableName.USER_GROUP)) {
            Helper.showUserGroupControlDialog(this, null);
        } else if (activeTableName.equals(TableName.EMPLOYEE)) {
            Helper.showEmployeeControlDialog(this, null);
        } else if (activeTableName.equals(TableName.SPORT_TYPE)) {
            Helper.showSportTypeControlDialog(this, null);
        } else if (activeTableName.equals(TableName.GROUP)) {
            Helper.showGroupControlDialog(this, null);
        } else if (activeTableName.equals(TableName.STUDENT)) {
            Helper.showStudentControlDialog(this, null);
        } else if (activeTableName.equals(TableName.SCHEDULE)) {
            Helper.showScheduleControlDialog(this, null);
        }
    }

    @FXML
    public void deleteItem() {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            if (activeTableName.equals(TableName.USER)) {
                userService.delete((User) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.USER_GROUP)) {
                userGroupService.delete((UserGroup) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.EMPLOYEE)) {
                employeeService.delete((Employee) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.SPORT_TYPE)) {
                sportTypeService.delete((SportType) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.GROUP)) {
                groupService.delete((Group) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.STUDENT)) {
                studentService.delete((Student) listView.getSelectionModel().getSelectedItem());
            } else if (activeTableName.equals(TableName.SCHEDULE)) {
                scheduleService.delete((Schedule) listView.getSelectionModel().getSelectedItem());
            }
            refresh();
        }
    }

}

