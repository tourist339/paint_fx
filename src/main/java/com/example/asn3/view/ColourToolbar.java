package com.example.asn3.view;

import com.example.asn3.Enum.DrawingNotificationType;
import com.example.asn3.Interface.IDrawingModelListener;
import com.example.asn3.controller.DrawingController;
import com.example.asn3.model.DrawingModel;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColourToolbar extends VBox {
    HashMap<String, Color> colorButtonsData;
    List<ColorButton> colorButtonList;
    private DrawingModel drawingModel;
    private ColorButton selectedButton;

    public ColourToolbar() {

        generateColorData();
        colorButtonList=new ArrayList<>();
        double buttonHeight=this.getPrefHeight();
        System.out.println(buttonHeight);
        System.out.println(this.getPrefWidth());

        this.setPrefWidth(100f);
        this.setMaxWidth(100f);
        this.setMinWidth(100f);

        for (String key:colorButtonsData.keySet()) {
            ColorButton colorButton=new ColorButton(key,colorButtonsData.get(key));
            colorButtonList.add(colorButton);
            getChildren().add(colorButton);
        }

    }
    private void generateColorData(){
        colorButtonsData=new HashMap<>();
        colorButtonsData.put("Aqua", Color.AQUA);
        colorButtonsData.put("Violet", Color.VIOLET);
        colorButtonsData.put("Green", Color.GREEN);
        colorButtonsData.put("Gold", Color.GOLD);
        colorButtonsData.put("Orange", Color.ORANGE);
        colorButtonsData.put("Fuchsia", Color.FUCHSIA);
        colorButtonsData.put("Peru", Color.PERU);
    }

    public void setDrawingController(DrawingController controller){
        colorButtonList.forEach(btn->btn.setController(controller));
    }

    public void onColorChange() {

            Color selectedColor=drawingModel.getSelectedColor();
            if(selectedButton!=null)selectedButton.setUnselected();
            if(selectedColor!=null){

                for (int i = 0; i < colorButtonList.size(); i++) {

                    if(colorButtonList.get(i).getColor().equals(selectedColor)){
                        selectedButton=colorButtonList.get(i);
                        selectedButton.setSelected();
                        break;
                    }
                }
        }
    }

    public void setDrawingModel(DrawingModel drawingModel){
        this.drawingModel=drawingModel;
    }
}
