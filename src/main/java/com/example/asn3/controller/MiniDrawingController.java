package com.example.asn3.controller;

import javafx.scene.input.MouseEvent;

public class MiniDrawingController extends DrawingController{

    private boolean isViewportHit=false;
    private double lastVPX,lastVPY;
    private boolean checkViewportHit(double normX,double normY){
       return( normX >= iModel.getViewPortX() / iModel.getDocumentWidth() && normX <= (iModel.getViewPortX() + iModel.getViewWidth()) / iModel.getDocumentWidth() &&
                normY >= iModel.getViewPortY() / iModel.getDocumentHeight() && normY <= (iModel.getViewPortY() + iModel.getViewHeight()) / iModel.getDocumentHeight());
    }
    @Override
    public void handlePressed(double normX, double normY, MouseEvent e) {


        if (checkViewportHit(normX,normY)) {
            normX*=iModel.getDocumentWidth();
            normY*=iModel.getDocumentHeight();
            System.out.println("Hit");
            isViewportHit=true;
            lastVPX=normX;
            lastVPY=normY;
        } else {
            isViewportHit=false;
            super.handlePressed(normX, normY, e);
        }

    }

    @Override
    public void handleDragged(double normX, double normY, MouseEvent e) {
        System.out.println(isViewportHit);
        if(isViewportHit){
            normX*=iModel.getDocumentWidth();
            normY*=iModel.getDocumentHeight();
            iModel.increaseViewPort(normX-lastVPX,normY-lastVPY);
            lastVPX=normX;
            lastVPY=normY;
        }else {
            super.handleDragged(normX, normY, e);
        }
    }

    @Override
    public void handleReleased(double normX, double normY, MouseEvent e) {
        if(isViewportHit)isViewportHit=false;
        else
        super.handleReleased(normX, normY, e);
    }
}
