


import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        BackgroundImage img = new BackgroundImage(new Image("https://i.pinimg.com/564x/00/c2/09/00c209749c39e04a4a199224a8a3ece5.jpg"),null, null, null, null);
//        Image img = new Image("https://i.pinimg.com/564x/00/c2/09/00c209749c39e04a4a199224a8a3ece5.jpg");
        Button btn = new Button("Enter...");
        btn.setOnAction(e-> new FormUsers());

        HBox pane = new HBox();
        pane.getChildren().add(btn);
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-image: url('https://bgr.com/wp-content/uploads/2022/03/AdobeStock_194080021.jpeg?quality=82&strip=all&w=500&h=500&crop=1');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 500 500;" +
                "-fx-background-position: center center;");
        //        pane.getChildren().add(new ImageView(img));

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setTitle("My project");
        stage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}