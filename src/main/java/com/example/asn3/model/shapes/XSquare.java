package com.example.asn3.model.shapes;

import com.example.asn3.Enum.XShapeType;
import com.example.asn3.model.XShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class XSquare extends XRectangle {

    public XSquare(double startX, double startY, Color color) {
        super(startX,startY,color, XShapeType.SQUARE);
    }


    @Override
    public double getWidth() {
        return Math.min(super.getWidth(),super.getHeight());
    }
    @Override
    public double getHeight() {
        return Math.min(super.getWidth(),super.getHeight());
    }


}
