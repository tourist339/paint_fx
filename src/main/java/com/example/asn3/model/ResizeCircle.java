package com.example.asn3.model;

import com.example.asn3.model.shapes.XCircle;
import javafx.scene.paint.Color;

public class ResizeCircle extends XCircle{


    public ResizeCircle(double startX, double startY, Color color) {
        super(startX, startY, color);
    }

    public void setAllPoints(double startX,double startY,double endX,double endY){
        this.startX=startX;
        this.startY=startY;
        this.endX=endX;
        this.endY=endY;
        this.width=endX-startX;
        this.height=endY-startY;

    }


}
