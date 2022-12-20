package root.controller.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import root.controller.util.TableName;
import root.database.service.*;

import java.util.HashMap;
import java.util.Map;

public class ChartController {

    @FXML
    private BarChart<String,Integer> barChart;
    private SportTypeService sportTypeService;
    private GroupService groupService;

    @FXML
    public void initialize() {

        sportTypeService = new SportTypeService();
        groupService = new GroupService();


        showDiagramData();
    }

    public void showDiagramData() {
        Map<String, Integer> sportTypeGroupsCount = new HashMap<>();
        var sportTypes = sportTypeService.findAll();
        for (var sportType : sportTypes) {
            sportTypeGroupsCount.put(sportType.getName(), 0);
        }

        var groups = groupService.findAll();
        for (var group : groups) {
            sportTypeGroupsCount.put(group.getSportType().getName(), sportTypeGroupsCount.get(group.getSportType().getName()) + 1);

        }

        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        String title = "Виды спорта";
        series1.setName(title);
        for (Map.Entry<String, Integer> entry : sportTypeGroupsCount.entrySet()) {
            String tmpString = entry.getKey();
            Integer tmpValue = entry.getValue();
            XYChart.Data<String, Integer> d = new XYChart.Data<>(tmpString, tmpValue);
            series1.getData().add(d);

        }

        barChart.setTitle(title);
        barChart.getData().addAll(series1);
    }

}
