package com.projavafx.charts;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Listing 8-4
public class ChartApp3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        PieChart pieChart = new PieChart();
        pieChart.setData(getChartData());
        pieChart.setTitle("Tiobe index");

        primaryStage.setTitle("PieChart");

        StackPane root = new StackPane();
        root.getChildren().add(pieChart);
        Scene scene = new Scene(root, 400, 250);
        scene.getStylesheets().add("/chartappstyle.css");
        primaryStage.setScene(scene);
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
