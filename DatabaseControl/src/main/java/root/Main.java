package root;

import javafx.application.Application;
import javafx.stage.Stage;
import root.controller.util.Helper;
import root.database.model.User;
import root.database.model.UserGroup;
import root.database.service.UserGroupService;
import root.database.util.HibernateSessionFactoryUtil;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Helper.showForm("/fxml/login.fxml", "Авторизация");
    }

    public static void main(String[] args) {
        HibernateSessionFactoryUtil.getSessionFactory().openSession();
        new UserGroupService().initGroups();
        launch();
    }

}