package com.example.asn3.model;

import com.example.asn3.Interface.IInteractionModelListener;
import com.example.asn3.view.DrawingView;

import java.util.ArrayList;

public class InteractionModel {
    private XShape selectedShape;
    private ArrayList<IInteractionModelListener> subscribers;
    private DrawingModel model;
    private double viewPortX,viewPortY;
    private double documentWidth,documentHeight,viewWidth,viewHeight;

    public void setDrawingModel(DrawingModel model){
        this.model=model;
    }

    public InteractionModel(double documentWidth,double documentHeight) {
        selectedShape=null;
        subscribers=new ArrayList<>();
        viewPortX=0;
        viewPortY=0;
        this.documentWidth=documentWidth;
        this.documentHeight=documentHeight;
    }

    public void setViewDimensions(double width,double height){
        this.viewWidth=width;
        this.viewHeight=height;
    }
    public void increaseViewPort(double dX,double dY){


        if(viewPortX+dX+viewWidth>documentWidth){
            viewPortX=documentWidth-viewWidth;
        }else if(viewPortX+dX<0){
            viewPortX=0;
        }else{
            viewPortX+=dX;
        }
        if(viewPortY+dY+viewHeight>documentHeight){
            viewPortY=documentHeight-viewHeight;
        }else if(viewPortY+dY<0){
            viewPortY=0;
        }else{
            viewPortY+=dY;
        }
        notifySubscribers();
    }

    public double getViewPortX() {
        return viewPortX;
    }

    public double getViewPortY() {
        return viewPortY;
    }

    public double getViewHeight() {
        return viewHeight;
    }

    public double getViewWidth() {
        return viewWidth;
    }

    public double getDocumentHeight() {
        return documentHeight;
    }

    public double getDocumentWidth() {
        return documentWidth;
    }

    public void addSubscribers(IInteractionModelListener sub){
        subscribers.add(sub);
    }

    public void setSelectedShape(XShape shape){
        this.selectedShape=shape;
        model.bringShapeToTop(shape);
        notifySubscribers();
    }

    public XShape getSelectedShape() {
        return selectedShape;
    }

    /**
     * Update the start points of the selected shape
     * @param dX the margin by which to change the startX of the shape
     * @param dY the margin by which to change the startY of the shape
     */
    public void updateStartPoints(double dX,double dY){
        selectedShape.updateStartPoints(dX,dY);
        notifySubscribers();
    }

    public void updateEndPoints(double normX, double normY) {
        selectedShape.updateEndPoints(normX,normY);
        notifySubscribers();
    }

    private void notifySubscribers() {
        subscribers.forEach(sub->sub.onInteractionModelChange());
    }


}
