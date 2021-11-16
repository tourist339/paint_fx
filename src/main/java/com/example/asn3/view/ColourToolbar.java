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
    List<ColorButton> colorButtonList;
    private DrawingModel drawingModel;
    private ColorButton selectedButton;

    public ColourToolbar() {

        generateColorData();


        this.setPrefWidth(100f);
        this.setMaxWidth(100f);
        this.setMinWidth(100f);

        for (ColorButton btn:colorButtonList) {
            getChildren().add(btn);
        }

    }
    private void generateColorData(){
        colorButtonList=new ArrayList<>();
        colorButtonList.add(new ColorButton("Aqua", Color.AQUA));
        colorButtonList.add(new ColorButton("Violet", Color.VIOLET));
        colorButtonList.add(new ColorButton("Green", Color.GREEN));
        colorButtonList.add(new ColorButton("Gold", Color.GOLD));
        colorButtonList.add(new ColorButton("Orange", Color.ORANGE));
        colorButtonList.add(new ColorButton("Coral", Color.CORAL));
        colorButtonList.add(new ColorButton("Fuchsia", Color.FUCHSIA));
        colorButtonList.add(new ColorButton("Peru", Color.PERU));
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
