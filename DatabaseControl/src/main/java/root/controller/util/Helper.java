package root.controller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import root.controller.controller.AlertController;
import root.controller.controller.MainController;
import root.controller.controller.dialog.*;
import root.database.model.*;

import java.io.IOException;

public class Helper {

    public static void showForm(String fxmlPath, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
    }

    public static void showMainForm(String fxmlPath, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Панель управления");
        stage.setResizable(false);

        MainController controller = fxmlLoader.getController();
        controller.setUser(user);
        controller.roleCheck();
        stage.show();
    }

    public static void showChartForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource("/fxml/chart.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Гистограмма");
        stage.setResizable(false);
        stage.show();
    }

    public static void showAlertForm(String alertMessage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource("/fxml/alert.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("Уведомление");
        stage.setResizable(false);
        AlertController controller = fxmlLoader.getController();
        controller.setMessage(alertMessage);
        stage.show();
        stage.requestFocus();
    }

    public static void showUserControlDialog(MainController mainController, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource("/fxml/dialog/userControlDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("Редактор - Пользователи");
        stage.setResizable(false);
        UserControlDialogController controller = fxmlLoader.getController();
        controller.setMainController(mainController);
        if (user != null) {
            controller.setUser(user);
        }
        stage.show();
        stage.requestFocus();
    }

    public static void showUserGroupControlDialog(MainController mainController, UserGroup userGroup) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource("/fxml/dialog/userGroupControlDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("Редактор - Группы пользователей");
        stage.setResizable(false);
        UserGroupDialogController controller = fxmlLoader.getController();
        controller.setMainController(mainController);
        if (userGroup != null) {
            controller.setUserGroup(userGroup);
        }
        stage.show();
        stage.requestFocus();
    }

    public static void showEmployeeControlDialog(MainController mainController, Employee employee) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource("/fxml/dialog/employeeControlDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("Редактор - Работники");
        stage.setResizable(false);
        EmployeeControlDialogController controller = fxmlLoader.getController();
        controller.setMainController(mainController);
        if (employee != null) {
            controller.setEmployee(employee);
        }
        stage.show();
        stage.requestFocus();
    }

    public static void showSportTypeControlDialog(MainController mainController, SportType sportType) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource("/fxml/dialog/sportTypeControlDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("Редактор - Виды спорта");
        stage.setResizable(false);
        SportTypeControlDialogController controller = fxmlLoader.getController();
        controller.setMainController(mainController);
        if (sportType != null) {
            controller.setSportType(sportType);
        }
        stage.show();
        stage.requestFocus();
    }

    public static void showGroupControlDialog(MainController mainController, Group group) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource("/fxml/dialog/groupControlDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("Редактор - Группы");
        stage.setResizable(false);
        GroupControlDialogController controller = fxmlLoader.getController();
        controller.setMainController(mainController);
        if (group != null) {
            controller.setGroup(group);
        }
        stage.show();
        stage.requestFocus();
    }

    public static void showStudentControlDialog(MainController mainController, Student student) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource("/fxml/dialog/studentControlDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("Редактор - Студенты");
        stage.setResizable(false);
        StudentControlDialogController controller = fxmlLoader.getController();
        controller.setMainController(mainController);
        if (student != null) {
            controller.setStudent(student);
        }
        stage.show();
        stage.requestFocus();
    }

    public static void showScheduleControlDialog(MainController mainController, Schedule schedule) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Helper.class.getResource("/fxml/dialog/scheduleControlDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("Редактор - Расписание");
        stage.setResizable(false);
        ScheduleControlDialogController controller = fxmlLoader.getController();
        controller.setMainController(mainController);
        if (schedule != null) {
            controller.setSchedule(schedule);
        }
        stage.show();
        stage.requestFocus();
    }

}
