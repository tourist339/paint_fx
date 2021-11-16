package com.example.asn3;
/**
 * Name: Kartik Kapoor
 * NSID: KAK110
 * St. Number. 11269913
 */

import com.example.asn3.Enum.XShapeType;
import com.example.asn3.controller.DrawingController;
import com.example.asn3.controller.MiniDrawingController;
import com.example.asn3.model.DrawingModel;
import com.example.asn3.model.InteractionModel;
import com.example.asn3.view.MainUI;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class DrawingApp extends Application {

    MainUI mainUI;
    DrawingController drawingController;
    DrawingModel drawingModel;
    InteractionModel interactionModel;
    MiniDrawingController miniDrawingController;
    public final double DOCUMENT_WIDTH=2000,DOCUMENT_HEIGHT=2000;


    @Override
    public void start(Stage stage) throws IOException {
        mainUI=new MainUI(DOCUMENT_WIDTH,DOCUMENT_HEIGHT);
        drawingController=new DrawingController();
        drawingModel=new DrawingModel();
        interactionModel=new InteractionModel(DOCUMENT_WIDTH,DOCUMENT_HEIGHT);
        miniDrawingController=new MiniDrawingController();

        interactionModel.setDrawingModel(drawingModel);
        drawingController.setModel(drawingModel);
        drawingController.setInteractionModel(interactionModel);
        miniDrawingController.setModel(drawingModel);
        miniDrawingController.setInteractionModel(interactionModel);
        mainUI.setDrawingController(drawingController);
        mainUI.setDrawingModel(drawingModel);
        mainUI.setInteractionModel(interactionModel);
        mainUI.setMiniDrawingController(miniDrawingController);

        drawingModel.addSubscriber(mainUI);
        interactionModel.addSubscribers(mainUI);
        drawingModel.setSelectedColor(Color.ORANGE);
        drawingModel.setSelectedShape(XShapeType.RECT);


        Scene scene = new Scene(mainUI, 700, 500);


        stage.setTitle("381 A3 ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}