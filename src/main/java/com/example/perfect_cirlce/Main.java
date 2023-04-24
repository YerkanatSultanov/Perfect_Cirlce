package com.example.perfect_cirlce;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    static final double CENTER_POINT_X = 200;
    static double result = 0;
    static final double CENTER_POINT_Y = 200;
    static int countPercent = 0;

    static boolean isFirstClick = true;
    static double firstX = 0;
    static double sum = 0;

    static double firstY = 0;
    static double radius = 0;
    static double resultPercent = 0;
    static double closeDotRadius = 20.0;
    static boolean isDraggingAllowed = true;
    static Timeline timeline;
    static int seconds = 0;
    static int count = 0;
    static double prev = 0;
    static double curr = 0;
    static double beast = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public void startStopWatch(Text text) {
        KeyFrame kf = new KeyFrame(Duration.millis(1), e -> {
            seconds++;
            if (seconds == 7000) {
                isDraggingAllowed = false;
                clearStopWatch();
                text.setText("Too slow");
                text.setFill(Color.WHITE);
            }
        });

        timeline = new Timeline(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void clearStopWatch() {
        timeline.stop();
        seconds = 0;
    }


    @Override
    public void start(Stage stage) throws Exception {
        StackPane main = new StackPane();
        VBox vb = new VBox();
        vb.setMaxWidth(stage.getMaxWidth() - 100);
        vb.setMaxHeight(stage.getMaxHeight() - 100);


        Scene scene = new Scene(main, stage.getMaxWidth(), stage.getMaxHeight());
        Canvas canvas = new Canvas(500, 600);
//        canvas.setStyle("-fx-border-color: yellow; -fx-background-color: gray; -fx-border-width: 2px");
        main.setStyle("-fx-background-color: #000000");
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Text text = new Text("0.0%");
        Text beastText = new Text("BEST: 0.0%");
        vb.getChildren().add(text);
        vb.getChildren().add(beastText);
        main.getChildren().add(vb);
        vb.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(50, 50, 50, 50));
        text.setFill(Color.WHITE);
        beastText.setFill(Color.WHITE);
        Font font = Font.loadFont("file:src/fonts/webpixel-bitmap/webpixel_bitmap/webpixel bitmap_black.otf", 45);
        text.setFont(font);
        beastText.setFont(font);
        text.setStyle("-fx-font-size: 50px");
        vb.setPadding(new Insets(50, 0, 0, 0));
        beastText.setStyle("-fx-font-size: 30px");
//        text.setStyle("-fx-text-fill: white");

        vb.getChildren().add(canvas);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(CENTER_POINT_X, CENTER_POINT_Y, 10, 10);


        canvas.setOnMouseDragged((event) -> {
            if (isDraggingAllowed) {
                countPercent++;
//            text.setText(String.format("%.3f", getLengthBetweenDots(CENTER_POINT_X, CENTER_POINT_Y, event.getX(), event.getY())));

//                if (firstX == event.getX()) {
//                    countX++;
//                    System.out.println("countX"+ " " + countX);
//                }
//
//                if (firstY == event.getY()) {
//                    countY++;
//                    System.out.println("countY"+ " " + countY);
//                }
//                firstX = event.getX()+1;
//                firstY = event.getY()+1;
//                if (countX == 2 || countY == 2) {
//                    isDraggingAllowed = false;
//                }

                if (isFirstClick) {
                    firstX = event.getX();
                    firstY = event.getY();
                    startStopWatch(text);
                    radius = getLengthBetweenDots(CENTER_POINT_X, CENTER_POINT_Y, event.getX(), event.getY());


                    double x2 = firstX;
                    double y1 = firstY;
                    double y2 = CENTER_POINT_Y;
                    double x0 = CENTER_POINT_X;

//                    System.out.println("A : " + CENTER_POINT_X + " , " + CENTER_POINT_Y);
//                    System.out.println("B : " + firstX + " , " + firstY);
//                    System.out.println("C : " + firstX + " , " + CENTER_POINT_Y);


                    double k = ((x2 + CENTER_POINT_X) - x0) / (y2 - y1);
                    double b = firstY - (k * firstX);


//                    System.out.printf("y = %.1f * x + %.1f%n", k, b);
                    resultPercent = 100.0;
                    graphicsContext.clearRect(0, 0, 400, 500);
//                    graphicsContext.setFill(Color.WHITE);
                    graphicsContext.fillOval(CENTER_POINT_X, CENTER_POINT_Y, 10, 10);
                    graphicsContext.setFill(Color.GREEN);
//
//                    text.setText(String.format("%.3f", radius));
                    isFirstClick = false;

                }
                double pointOnline = ((event.getX() - CENTER_POINT_X) / (firstX - CENTER_POINT_X)) - ((event.getY() - CENTER_POINT_Y) / (firstY - CENTER_POINT_Y));

//                boolean isPointOnline = ;
//                System.out.println(pointOnline);
                prev = curr;
                curr = pointOnline;

                if (radius < 50.0) {
                    isDraggingAllowed = false;
                    clearStopWatch();
                    text.setFill(Color.WHITE);
                    text.setText("To close to dot");
                }
//
//                if (firstX == event.getX() && firstY == event.getY()){
//                    isDraggingAllowed = false;
//                    clearStopWatch();
//                    text.setText("Done");
//                }

                if (getLengthBetweenDots(CENTER_POINT_X, CENTER_POINT_Y, event.getX(), event.getY()) > radius) {
                    double temp = 0;
                    double l = getLengthBetweenDots(CENTER_POINT_X, CENTER_POINT_Y, event.getX(), event.getY()) - radius;
                    temp = (l * 100) / radius;
                    resultPercent = 100.0 - temp;

//                    if (resultPercent < 1) {
//                        text.setText("XX.X %");
//                        isDraggingAllowed = false;
//                        clearStopWatch();
//                    }
                if (resultPercent >= 1) {
                        sum += resultPercent;
                        result = sum / countPercent;
                        text.setText(String.format("%.1f %s", result, "%"));
                    }
                } else if (getLengthBetweenDots(CENTER_POINT_X, CENTER_POINT_Y, event.getX(), event.getY()) < radius) {
                    double temp = 0;
                    double l = getLengthBetweenDots(CENTER_POINT_X, CENTER_POINT_Y, event.getX(), event.getY());
                    temp = (l * 100) / radius;
                    resultPercent = temp;
//                    if (resultPercent < 1) {
//                        isDraggingAllowed = false;
//                        clearStopWatch();
//                        text.setText("XX.X %");
                    if (resultPercent >= 1) {
                        sum += resultPercent;
                        result = sum / countPercent;
                        text.setText(String.format("%.1f %s", result, "%"));
                    }
                }
                if (prev >= 0 ^ curr >= 0) {
                    count++;
//                    System.out.println(prev + " : " + curr);
//                    System.out.println(count + " " + pointOnline);
                }
                if (count == 2) {
                    isDraggingAllowed = false;
                    if (result > beast) {
                        beast = result;
                        beastText.setText("BEST: " + String.format("%.1f %s", beast, "%"));
                    }
                    clearStopWatch();
                }

                double len = getLengthBetweenDots(CENTER_POINT_X, CENTER_POINT_Y, event.getX(), event.getY());
                if ((len > radius + 5 && len < radius + 15) || (len < radius - 5 && len > radius - 15)) {
                    text.setFill(Color.YELLOW);
                    graphicsContext.setFill(Color.YELLOW);
                } else if ((len > radius + 15) || (len < radius - 15)) {
                    text.setFill(Color.RED);
                    graphicsContext.setFill(Color.RED);
                } else {
                    text.setFill(Color.GREEN);
                    graphicsContext.setFill(Color.GREEN);
                }
                graphicsContext.fillOval(event.getX(), event.getY(), 5, 5);

                if (len <= closeDotRadius) {
                    text.setFill(Color.WHITE);
                    text.setText("To close to dot");
                    isDraggingAllowed = false;
                    clearStopWatch();
                }

            }


        });

        canvas.setOnMouseReleased((event) -> {
            prev = 0;
            curr = 0;
            count = 0;
            radius = 0;
            sum = 0;
            countPercent = 0;
            isFirstClick = true;
            isDraggingAllowed = true;
            clearStopWatch();
        });


        stage.setScene(scene);
//        stage.setMaximized(true);
        stage.show();
    }

    double getLengthBetweenDots(double x1, double y1, double x2, double y2) {
        double diffX = x2 - x1;
        double diffY = y2 - y1;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }
}