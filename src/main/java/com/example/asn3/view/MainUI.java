package com.example.asn3.view;

import com.example.asn3.Enum.DrawingNotificationType;
import com.example.asn3.Interface.IDrawingModelListener;
import com.example.asn3.Interface.IInteractionModelListener;
import com.example.asn3.controller.DrawingController;
import com.example.asn3.controller.MiniDrawingController;
import com.example.asn3.model.DrawingModel;
import com.example.asn3.model.InteractionModel;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainUI extends HBox implements IDrawingModelListener, IInteractionModelListener {
    private ColourToolbar colourToolbar;
    private DrawingView drawingView;
    private MiniDrawingView miniDrawingView;
    private ShapeToolbar shapeToolbar;
    private StackPane canvasPane;

    public MainUI(double documentWidth,double documentHeight) {
        colourToolbar=new ColourToolbar();
        drawingView=new DrawingView(500,500,documentWidth,documentHeight, Color.LIGHTGRAY);
        miniDrawingView=new MiniDrawingView(100,100,documentWidth,documentHeight ,Color.GRAY);




        canvasPane=new StackPane(drawingView,miniDrawingView);
        canvasPane.setMinWidth(100);
        canvasPane.setMinHeight(100);
        shapeToolbar=new ShapeToolbar();
        canvasPane.widthProperty().addListener(e->handleResize());
        canvasPane.heightProperty().addListener(e->handleResize());

        HBox.setHgrow(canvasPane,Priority.ALWAYS);
        HBox.setHgrow(colourToolbar,Priority.ALWAYS);
        HBox.setHgrow(shapeToolbar,Priority.ALWAYS);

        getChildren().addAll(shapeToolbar,canvasPane,colourToolbar);
        StackPane.setAlignment(miniDrawingView,Pos.TOP_LEFT);
    }

    public void setDrawingController(DrawingController controller){
        shapeToolbar.setDrawingController(controller);
        colourToolbar.setDrawingController(controller);
        drawingView.setDrawingController(controller);
    }

    public void setDrawingModel(DrawingModel drawingModel) {
        colourToolbar.setDrawingModel(drawingModel);
        drawingView.setDrawingModel(drawingModel);
        miniDrawingView.setDrawingModel(drawingModel);
        shapeToolbar.setDrawingModel(drawingModel);

    }

    @Override
    public void onDrawingModelChange(DrawingNotificationType drawingNotificationType) {
        switch (drawingNotificationType) {
            case COLOR_CHANGE -> {
                colourToolbar.onColorChange();
                shapeToolbar.onShapeChange();
            }
            case SHAPE_CHANGE -> {
                shapeToolbar.onShapeChange();
            }
            case SHAPE_DELETED -> {
                drawingView.onCanvasUpdate();
                miniDrawingView.onCanvasUpdate();
            }
        }
    }

    @Override
    public void onInteractionModelChange() {

        drawingView.onCanvasUpdate();
        miniDrawingView.onCanvasUpdate();
    }


    public void setInteractionModel(InteractionModel interactionModel) {
        interactionModel.setViewDimensions(500,500);
        drawingView.setInteractionModel(interactionModel);
        miniDrawingView.setInteractionModel(interactionModel);
    }

    public void setMiniDrawingController(DrawingController miniDrawingController) {
        miniDrawingView.setDrawingController(miniDrawingController);
    }

    public void handleResize() {
        drawingView.updateWidth(canvasPane.getWidth());
        drawingView.updateHeight(canvasPane.getHeight());
        miniDrawingView.onCanvasUpdate();
    }
}
