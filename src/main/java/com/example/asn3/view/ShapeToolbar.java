package com.example.asn3.view;

import com.example.asn3.Enum.DrawingNotificationType;
import com.example.asn3.Enum.XShapeType;
import com.example.asn3.Interface.IDrawingModelListener;
import com.example.asn3.controller.DrawingController;
import com.example.asn3.model.DrawingModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ShapeToolbar extends VBox {

    ArrayList<ShapeButton> shapeButtons;
    private DrawingModel drawingModel;
    private ShapeButton selectedShapeButton;

    public ShapeToolbar() {
        init();
        this.setPrefWidth(100f);
        this.setMaxWidth(100f);
        this.setMinWidth(100f);

        shapeButtons.forEach(btn->{
            getChildren().add(btn);
        });
    }

    private void init() {
        shapeButtons=new ArrayList<>();

        shapeButtons.add(new ShapeButton("",null, XShapeType.RECT));
        shapeButtons.get(0).setIcon(new Rectangle(40,30),"Rect");

        shapeButtons.add(new ShapeButton("",null, XShapeType.SQUARE));
        shapeButtons.get(1).setIcon(new Rectangle(30,30),"Square");
        shapeButtons.add(new ShapeButton("",null, XShapeType.CIRCLE));
        shapeButtons.get(2).setIcon(new Circle(20),"Circle");

        shapeButtons.add(new ShapeButton("",null, XShapeType.OVAL));
        shapeButtons.get(3).setIcon(new Ellipse(25,15),"Oval");

        shapeButtons.add(new ShapeButton("",null, XShapeType.LINE));
        shapeButtons.get(4).setIcon(new Line(5,5,40,30),"Line");


    }

    public void setDrawingController(DrawingController controller){
        shapeButtons.forEach(btn->btn.setController(controller));
    }

    public void onShapeChange(){
        if(selectedShapeButton!=null)selectedShapeButton.setUnselected();
        XShapeType selectedShapeType=drawingModel.getSelectedShape();
        for (int i = 0; i < shapeButtons.size(); i++) {
            if(shapeButtons.get(i).getShapeType().equals(selectedShapeType)){
                selectedShapeButton=shapeButtons.get(i);
                selectedShapeButton.setSelected();
                if (drawingModel.getSelectedColor()!=null){
                    selectedShapeButton.getIcon().setFill(drawingModel.getSelectedColor());
                    selectedShapeButton.getIcon().setStroke(drawingModel.getSelectedColor());
                }
            }
        }
    }

    public void setDrawingModel(DrawingModel drawingModel){
        this.drawingModel=drawingModel;
    }

    public void onColorChange() {
        if (drawingModel.getSelectedColor()!=null){
            selectedShapeButton.getIcon().setFill(drawingModel.getSelectedColor());
        }
    }
}
