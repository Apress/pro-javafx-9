package com.projavafx.charts;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ChartApp15 extends Application {

    @Override
    public void start(Stage primaryStage) {
  NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(30);
        xAxis.setAutoRanging(false);
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(20170);
        xAxis.setUpperBound(20261);
        xAxis.setTickUnit(10);
        xAxis.setTickLabelFormatter(new StringConverter<Number>() {

            @Override
            public String toString(Number n) {
                return String.valueOf(n.intValue() / 10);
            }

            @Override
            public Number fromString(String s) {
                return Integer.valueOf(s) * 10;
            }
        });
        BubbleChart bubbleChart = new BubbleChart(xAxis, yAxis);
        bubbleChart.setData(getChartData());
        bubbleChart.setTitle("Speculations");
        primaryStage.setTitle("BubbleChart example");

        StackPane root = new StackPane();
        root.getChildren().add(bubbleChart);
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }
    private ObservableList<XYChart.Series<Integer, Double>> getChartData() {
        double javaValue = 15.57;
        double cValue = 6.97;
        double cppValue = 4.55;
        ObservableList<XYChart.Series<Integer, Double>> answer = FXCollections.observableArrayList();
        Series<Integer, Double> java = new Series<>();
        Series<Integer, Double> c = new Series<>();
        Series<Integer, Double> cpp = new Series<>();
        java.setName("java");
        c.setName("C");
        cpp.setName("C++");
        for (int i = 20170; i < 20270; i =  i+10) {
            double diff = Math.random();
            java.getData().add(new XYChart.Data(i, javaValue, 2*diff));
            javaValue = Math.max(javaValue + 2*diff - 1,0);
            diff = Math.random();
            c.getData().add(new XYChart.Data(i, cValue,2* diff));
            cValue = Math.max(cValue + 2*diff - 1,0);
            diff = Math.random();
            cpp.getData().add(new XYChart.Data(i, cppValue, 2*diff));
            cppValue = Math.max(cppValue + 2*diff - 1,0);
        }
        answer.addAll(java, c, cpp);
        return answer;
    }

}

