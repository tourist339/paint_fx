package com.example.asn3.model.shapes;

import com.example.asn3.Enum.XShapeType;
import com.example.asn3.model.XShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class XRectangle extends XShape {

    public XRectangle(double startX, double startY, Color color){
        super(startX,startY,color, XShapeType.RECT);
    }

    public XRectangle(double startX, double startY, Color color,XShapeType childShapeType){
        super(startX,startY,color, childShapeType);

    }

    @Override
    public void drawShape(GraphicsContext gc,double drawX,double drawY,double drawWidth,double drawHeight,boolean isSelected) {



        gc.fillRect(drawX,drawY,drawWidth,drawHeight);
        if(!isSelected){
            gc.strokeRect(drawX, drawY, drawWidth, drawHeight);
        }
    }

    @Override
    public boolean contains(double hitX, double hitY) {
        return(hitX>=getStartingPointX()&& hitX<=getStartingPointX()+getWidth()&&hitY>=getStartingPointY()&&hitY<=getStartingPointY()+getHeight());
    }
}
