package com.example.asn3.view;

import com.example.asn3.controller.DrawingController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ColorButton extends ToolbarButton {
    private String colorName;
    private Color color;

    public ColorButton(String colorName, Color color) {
        super(colorName,null);
        this.colorName = colorName;
        this.color = color;

        //this.setBackground(new Background(new BackgroundFill(color,new CornerRadii(2f), Insets.EMPTY)));


    }

    public Color getColor() {
        return this.color;
    }

    public String getColorName() {
        return colorName;
    }


    @Override
    public void setController(DrawingController controller) {
        this.setOnAction(e->{
            controller.onColorSelected(getColor());
        });
    }
}
