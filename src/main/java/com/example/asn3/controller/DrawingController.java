package com.example.asn3.controller;

import com.example.asn3.Enum.XShapeType;
import com.example.asn3.model.DrawingModel;
import com.example.asn3.model.InteractionModel;
import com.example.asn3.model.XShape;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawingController {

    protected DrawingModel model;
    protected InteractionModel iModel;
    double prevX,prevY,prevViewPortX,prevViewPortY;
    private enum State{READY,PREPARE_CREATE,SELECTED,CREATION_DRAGGING,START_RESIZE,RESIZING,SELECTED_DRAGGING};
    private State drawingState=State.READY;

    public void handleKeyPressedAtCanvas(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.BACK_SPACE || keyEvent.getCode()==KeyCode.DELETE){
            if(iModel.getSelectedShape()!=null){
                model.removeShape(iModel.getSelectedShape());
            }
        }
    }

    public void handleRightDragged(double x, double y) {

        iModel.increaseViewPort(prevViewPortX-x,prevViewPortY-y);
        prevViewPortX=x;
        prevViewPortY=y;
    }

    public void handleRightPressed(double x, double y) {
        prevViewPortX=x;
        prevViewPortY=y;
    }



    public void setInteractionModel(InteractionModel interactionModel) {

        this.iModel=interactionModel;
    }

    public void onColorSelected(Color color){

        model.setSelectedColor(color);
    }

    private boolean isInteractionValid(){
        return (model.getSelectedShape()!=null&&model.getSelectedColor()!=null);
    }

    public void onShapeSelected(XShapeType shapeType){

        model.setSelectedShape(shapeType);
    }
    public void setModel(DrawingModel drawingModel) {
        this.model=drawingModel;
    }

    public void handlePressed(double normX, double normY, MouseEvent e) {

            prevX = normX;
            prevY = normY;
            drawingState = State.READY;
            if (iModel.getSelectedShape() != null) {
                if (iModel.getSelectedShape().checkResizeHit(normX, normY)) {
                    drawingState = State.START_RESIZE;
                }
            }
            if (drawingState != State.START_RESIZE) {
                XShape existingShapeClicked = model.contains(normX, normY);

                if (existingShapeClicked != null) {
                    iModel.setSelectedShape(existingShapeClicked);
                    drawingState = State.SELECTED;
                } else {
                    if (!isInteractionValid()) return;
                    drawingState = State.PREPARE_CREATE;
                }

            }
    }





    public void handleDragged(double normX, double normY, MouseEvent e) {

            switch (drawingState) {

                case START_RESIZE -> {
                    iModel.updateEndPoints(normX - prevX, normY - prevY);
                }
                case SELECTED -> {

                    iModel.updateStartPoints(normX - prevX, normY - prevY);
                }
                case PREPARE_CREATE -> {
                    XShape newShape = XShape.initShape(model.getSelectedShape(), prevX, prevY, model.getSelectedColor());
                    model.addNewShape(newShape);
                    iModel.setSelectedShape(newShape);

                    drawingState = State.CREATION_DRAGGING;
                }
                case CREATION_DRAGGING -> {

                    iModel.updateEndPoints(normX - prevX, normY - prevY);

                }
            }

            prevX = normX;
            prevY = normY;
        }




    public void handleReleased(double normX, double normY, MouseEvent e) {
            switch (drawingState) {
                case CREATION_DRAGGING, PREPARE_CREATE, READY -> {
                    iModel.setSelectedShape(null);
                }
            }

    }
}
