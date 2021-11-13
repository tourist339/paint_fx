package com.example.asn3.model;

import com.example.asn3.Enum.DrawingNotificationType;
import com.example.asn3.Enum.XShapeType;
import com.example.asn3.Interface.IDrawingModelListener;
import com.example.asn3.view.MainUI;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DrawingModel {

    private Color selectedColor;
    private XShapeType selectedShape;
    private ArrayList<XShape> allShapes;

    private ArrayList<IDrawingModelListener> subscribers;

    public DrawingModel(){
        subscribers=new ArrayList<>();
        allShapes=new ArrayList<>();
        selectedColor=null;
        selectedShape=null;
    }
    public Color getSelectedColor() {
        return selectedColor;
    }

    public XShapeType getSelectedShape() {
        return selectedShape;
    }

    public void addNewShape(XShape shape){
        allShapes.add(shape);
    }

    public ArrayList<XShape> getAllShapes() {
        return allShapes;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = (this.selectedColor==selectedColor)?null:selectedColor;
        notifySubscribers(DrawingNotificationType.COLOR_CHANGE);

    }

    private void notifySubscribers(DrawingNotificationType notificationType){
        subscribers.forEach(subscriber->subscriber.onDrawingModelChange(notificationType));
    }

    public void setSelectedShape(XShapeType selectedShape) {
        this.selectedShape = (this.selectedShape==selectedShape)?null:selectedShape;
        notifySubscribers(DrawingNotificationType.SHAPE_CHANGE);
    }

    public void addSubscriber(IDrawingModelListener subscriber) {
        subscribers.add(subscriber);
    }

    public void bringShapeToTop(XShape shape){
        if(shape!=null) {
            allShapes.remove(shape);
            allShapes.add( shape);
        }
    }

    public XShape contains(double hitX,double hitY){

        for (int i = allShapes.size()-1; i >=0 ; i--) {
            if(allShapes.get(i).contains(hitX,hitY))return allShapes.get(i);
        }
        return null;
    }

    /**
     * Remove the given shape from allShapes arraylist
     * @param shape the shape to remove
     */
    public void removeShape(XShape shape){
        allShapes.remove(shape);
        notifySubscribers(DrawingNotificationType.SHAPE_DELETED);
    }
}
