package com.example.asn3.model.shapes;

import com.example.asn3.Enum.XShapeType;
import com.example.asn3.model.XShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class XLine extends XShape {

    public XLine(double startX, double startY, Color color) {
        super(startX,startY,color, XShapeType.LINE);

    }

    @Override
    public void drawShape(GraphicsContext gc,double drawStartX,double drawStartY,double drawEndX,double drawEndY,boolean isSelected) {

        gc.strokeLine(drawStartX,drawStartY,drawEndX,drawEndY);
    }

    @Override
    public boolean contains(double hitX, double hitY) {
        //line eq. = y=mx+c
        // y-y1=m(x-x1)
        //y-mx-y1+mx1=0 // ax+by+c=0 // a=-m , b=1 , c = -y1+mx1

        if(!(hitX>=Math.min(startX,endX)&&hitX<=Math.max(startX,endX)&&hitY>=Math.min(startY,endY)&&hitY<=Math.max(startY,endY)))
            return false;
        double m=(startY-endY)/(startX-endX);
        double A=-m;
        double B=1;
        double C=-startY+m*startX;

        double distance=Math.abs(A*hitX+B*hitY+C)/Math.sqrt(A*A+B*B);

        return distance<=0.01f;

    }
}
