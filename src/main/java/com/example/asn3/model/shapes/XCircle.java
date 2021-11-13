package com.example.asn3.model.shapes;

import com.example.asn3.Enum.XShapeType;
import com.example.asn3.model.XShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class XCircle extends XShape {

    public XCircle(double startX, double startY, Color color) {
        super(startX,startY,color, XShapeType.CIRCLE);
    }

    @Override
    public double getWidth() {
        return Math.min(super.getWidth(),super.getHeight());
    }
    @Override
    public double getHeight() {
        return Math.min(super.getWidth(),super.getHeight());
    }
    @Override
    public void drawShape(GraphicsContext gc,double drawX,double drawY,double drawWidth,double drawHeight,boolean isSelected) {
        gc.fillOval(drawX,drawY,drawWidth,drawHeight);
        gc.strokeOval(drawX, drawY, drawWidth, drawHeight);

    }

    @Override
    public boolean contains(double hitX, double hitY) {
        double radius=getWidth()/2;
        double centreX=getStartingPointX()+getWidth()/2;
        double centreY=getStartingPointY()+getHeight()/2;
        double distanceHitFromCenter=XShape.getDistanceBetweenTwoPoints(centreX,centreY,hitX,hitY);
        return distanceHitFromCenter<=(radius+0.0001f);
    }
}
