package com.example.asn3.Interface;

import com.example.asn3.Enum.DrawingNotificationType;

public interface IDrawingModelListener {
    void onDrawingModelChange(DrawingNotificationType drawingNotificationType);

}