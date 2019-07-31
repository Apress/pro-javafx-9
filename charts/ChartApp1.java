package com.projavafx.charts;

import java.util.LinkedList;
import java.util.UUID;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Listing 8-1
public class ChartApp1 extends Application {

    public static void main(String[] args) {
System.setProperty("prism.verbose","true");
System.setProperty("javafx.verbose","true");
System.setProperty("javafx.platform","ios");
System.setProperty("glass.platform","ios");
System.out.println("loader = "+ChartApp1.class.getClassLoader());
System.out.println("applicationloader = "+Application.class.getClassLoader());
System.out.println("objectloader = "+Object.class.getClassLoader());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
ObservableList<String> ll = FXCollections.observableArrayList();
for (int i = 0 ; i < 100; i++) {
ll.add(UUID.randomUUID().toString().substring(0,20));
}
ListView<String> lv = new ListView<>(ll);
        PieChart pieChart = new PieChart();
        pieChart.setData(getChartData());
        primaryStage.setTitle("PieChart");

        StackPane root = new StackPane();
        root.getChildren().addAll( pieChart);
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    private ObservableList<Data> getChartData() {
        ObservableList<Data> answer = FXCollections.observableArrayList();
        answer.addAll(new PieChart.Data("java", 15.57),
                new PieChart.Data("C", 6.97),
                new PieChart.Data("C++", 4.55),
                new PieChart.Data("C#", 3.58),
                new PieChart.Data("Python", 3.45),
                new PieChart.Data("PHP", 3.38),
                new PieChart.Data("Visual Basic .NET", 3.25));
        return answer;
    }
}
