package com.example.asn3.view;

import com.example.asn3.Enum.XShapeType;
import com.example.asn3.controller.DrawingController;
import com.example.asn3.model.XShape;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class ShapeButton extends ToolbarButton {
    private XShapeType shapeType;
    private String shapeName;
    private Shape icon;

    public ShapeButton(String s, Node icon,XShapeType shapeType) {
        super(s, icon);
        this.shapeName=s;
        this.shapeType=shapeType;

    }

    public XShapeType getShapeType() {
        return shapeType;
    }

    @Override
    public void setUnselected() {
        super.setUnselected();
        this.getIcon().setFill(Color.BLACK);
        this.getIcon().setStroke(Color.BLACK);

    }

    public void setIcon(Shape node, String label){
        VBox vBox=new VBox();
        Label text=new Label(label);
        this.icon=node;
        vBox.getChildren().addAll(node,text);
        vBox.setAlignment(Pos.CENTER);
        this.setGraphic(vBox);
    }

    public Shape getIcon() {
        return icon;
    }

    @Override
    public void setController(DrawingController controller) {
        setOnAction(e->controller.onShapeSelected(getShapeType()));
    }
}
