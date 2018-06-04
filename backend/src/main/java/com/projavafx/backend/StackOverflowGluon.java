package com.projavafx.backend;

import com.gluonhq.connect.ConnectState;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author johan
 */
public class StackOverflowGluon extends Application {

    static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY");

    @Override
    public void start(Stage primaryStage) throws IOException {
        TableView<Question> tableView = new TableView<>();
        tableView.setItems(getObservableList());
        TableColumn<Question, String> dateColumn = new TableColumn<>("Date");
        TableColumn<Question, String> ownerColumn = new TableColumn<>("Owner");
        TableColumn<Question, String> questionColumn = new TableColumn<>("Question");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("timestampString"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));

        questionColumn.setPrefWidth(350);
        tableView.getColumns().addAll(dateColumn, ownerColumn, questionColumn);
        StackPane root = new StackPane();
        root.getChildren().add(tableView);

        Scene scene = new Scene(root, 500, 300);

        primaryStage.setTitle("StackOverflow Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    ObservableList<Question> getObservableList() throws IOException {
        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://api.stackexchange.com")
                .path("/2.2/search").queryParam("order", "desc")
                .queryParam("sort", "activity")
                .queryParam("tagged", "javafx")
                .queryParam("site", "stackoverflow");
        GluonObservableList<Question> answer = DataProvider.retrieveList(restClient.createListDataReader(Question.class));
        
        return answer;
    }

    private String getTimeStampString(long ts) {
        return sdf.format(new Date(ts));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
