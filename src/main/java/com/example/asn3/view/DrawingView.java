package com.example.asn3.view;

import com.example.asn3.controller.DrawingController;
import com.example.asn3.model.DrawingModel;
import com.example.asn3.model.InteractionModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawingView extends Canvas {
    protected GraphicsContext gc;
    protected DrawingModel drawingModel;
    protected InteractionModel iModel;
    protected double width,height;
    protected Color backgroundColor;
    protected double documentWidth,documentHeight;

    public DrawingView(double canvasWidth,double canvasHeight,double documentWidth,double documentHeight,Color backgroundColor) {
        super(canvasWidth,canvasHeight);
        width=canvasWidth;
        height=canvasHeight;
        this.backgroundColor=backgroundColor;
        this.documentWidth=documentWidth;
        this.documentHeight=documentHeight;

        this.gc = this.getGraphicsContext2D();
        initalDraw();
    }

    public void updateWidth(double width) {
        super.setWidth(width);
        this.width = width;
        iModel.setViewDimensions(this.width,this.height);

        onCanvasUpdate();
    }

    public void updateHeight(double height){
        super.setHeight(height);
        this.height = height;
        iModel.setViewDimensions(this.width,this.height);

        onCanvasUpdate();

    }

    public void initalDraw() {
        gc.setStroke(Color.BLACK);
        gc.setFill(backgroundColor);
        gc.fillRect(0,0,width,height);
    }
        public void setDrawingController(DrawingController controller){
        this.addEventHandler(MouseEvent.MOUSE_PRESSED,e->this.requestFocus());
        this.setOnKeyPressed(controller::handleKeyPressedAtCanvas);
        this.setOnMousePressed(e -> {
            if(e.isPrimaryButtonDown()) {
                controller.handlePressed((iModel.getViewPortX() + e.getX()) / documentWidth, (iModel.getViewPortY() + e.getY()) / documentHeight, e);
            }else if(e.isSecondaryButtonDown()){
                controller.handleRightPressed(e.getX(),e.getY());
            }
        });
        this.setOnMouseDragged(e -> {
            if(e.isPrimaryButtonDown()) {
                controller.handleDragged((iModel.getViewPortX() + e.getX()) / documentWidth, (iModel.getViewPortY() + e.getY()) / documentHeight, e);
            } else if(e.isSecondaryButtonDown()){
                controller.handleRightDragged(e.getX(),e.getY());
            }});
        this.setOnMouseReleased(e -> controller.handleReleased((iModel.getViewPortX()+e.getX())/documentWidth,(iModel.getViewPortY()+e.getY())/documentHeight,e));
    }

    public double getDrawWidth(){
        return documentWidth;
    }
    public double getDrawHeight(){
        return documentHeight;
    }

    public boolean isCanvas(){
        return true;
    }

    public void onCanvasUpdate(){
        gc.clearRect(0,0,width,height);
        initalDraw();
        drawingModel.getAllShapes().forEach(shape ->{
            boolean isShapeSelected=(iModel.getSelectedShape()!=null&&iModel.getSelectedShape().equals(shape));
            shape.draw(gc,getDrawWidth(),getDrawHeight(),iModel.getViewPortX(),iModel.getViewPortY(),isShapeSelected,isCanvas());
        });
    }

    public void setDrawingModel(DrawingModel drawingModel) {
        this.drawingModel=drawingModel;
    }

    public void setInteractionModel(InteractionModel interactionModel) {
        this.iModel=interactionModel;
    }
}
