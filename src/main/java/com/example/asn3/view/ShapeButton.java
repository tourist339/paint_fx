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

public class ShapeButton extends ToolbarButton {
    private XShapeType shapeType;
    private String shapeName;

    public ShapeButton(String s, Node icon,XShapeType shapeType) {
        super(s, icon);
        this.shapeName=s;
        this.shapeType=shapeType;

    }

    public XShapeType getShapeType() {
        return shapeType;
    }

    public void setIcon(Node node,String label){
        VBox vBox=new VBox();
        Label text=new Label(label);
        vBox.getChildren().addAll(node,text);
        vBox.setAlignment(Pos.CENTER);
        this.setGraphic(vBox);
    }
    @Override
    public void setController(DrawingController controller) {
        setOnAction(e->controller.onShapeSelected(getShapeType()));
    }
}
