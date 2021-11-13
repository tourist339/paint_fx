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
        shapeButtons.get(0).setIcon(new Rectangle(30,30),"Rect");

        shapeButtons.add(new ShapeButton("Square",null, XShapeType.SQUARE));
        shapeButtons.add(new ShapeButton("Circle",null, XShapeType.CIRCLE));
        shapeButtons.add(new ShapeButton("Oval",null, XShapeType.OVAL));
        shapeButtons.add(new ShapeButton("Line",null, XShapeType.LINE));

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
            }
        }
    }

    public void setDrawingModel(DrawingModel drawingModel){
        this.drawingModel=drawingModel;
    }

    public void onColorChange() {

    }
}
