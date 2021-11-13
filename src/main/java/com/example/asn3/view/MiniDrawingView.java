package com.example.asn3.view;

import com.example.asn3.controller.DrawingController;
import com.example.asn3.model.InteractionModel;
import com.example.asn3.model.XShape;
import javafx.scene.paint.Color;

public class MiniDrawingView extends DrawingView{


    public MiniDrawingView(double canvasWidth, double canvasHeight, double documentWidth, double documentHeight, Color backgroundColor) {
        super(canvasWidth, canvasHeight, documentWidth, documentHeight, backgroundColor);
    }

    @Override
    public void initalDraw() {
        super.initalDraw();
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(1f);

    }

    @Override
    public void setInteractionModel(InteractionModel interactionModel) {
        super.setInteractionModel(interactionModel);
        gc.strokeRect(iModel.getViewPortX()*width/documentWidth,iModel.getViewPortY()*height/documentHeight,iModel.getViewWidth()*width/documentWidth,iModel.getViewHeight()*height/documentHeight);

    }

    @Override
    public double getDrawWidth() {
        return width;
    }

    @Override
    public double getDrawHeight() {
        return height;
    }

    @Override
    public boolean isCanvas() {
        return false;
    }

    @Override
    public void onCanvasUpdate() {

        super.onCanvasUpdate();
        if(iModel!=null){
            gc.strokeRect(iModel.getViewPortX()*width/documentWidth,iModel.getViewPortY()*height/documentHeight,iModel.getViewWidth()*width/documentWidth,iModel.getViewHeight()*height/documentHeight);
        }
    }

    @Override
    public void setDrawingController(DrawingController controller) {
        this.setOnMousePressed(e->controller.handlePressed(e.getX()/getDrawWidth(),e.getY()/getDrawHeight(),e));
        this.setOnMouseDragged(e->controller.handleDragged(e.getX()/getDrawWidth(),e.getY()/getDrawHeight(),e));
        this.setOnMouseReleased(e->controller.handleReleased(e.getX()/getDrawWidth(),e.getY()/getDrawHeight(),e));

    }
}
