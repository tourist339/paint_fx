package com.example.asn3.view;

import com.example.asn3.controller.DrawingController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public abstract class ToolbarButton extends Button {
    public ToolbarButton(String s, Node node) {
        super(s, node);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(Double.MAX_VALUE);
    }

    public void setSelected(){
        this.setStyle("-fx-border-width: 2px");
        this.setStyle("-fx-border-color: black");

    }
    public void setUnselected(){
        this.setStyle("-fx-border-width: 0px");
    }

    public abstract void setController(DrawingController controller);

}
