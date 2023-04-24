package com.example.perfect_cirlce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("creating canvas");
        Canvas canvas = new Canvas(100.0f, 100.0f);
        GraphicsContext graphics_context = canvas.getGraphicsContext2D();
        Group group = new Group(canvas);
        Scene scene = new Scene(group, 200, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}