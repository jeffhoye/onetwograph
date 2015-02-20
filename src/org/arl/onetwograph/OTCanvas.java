package org.arl.onetwograph;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

/**
 * Listeners etc for a canvas.
 * 
 * @author jeffhoye
 *
 */
public class OTCanvas {

  public OTCanvas(Canvas canvas) {
    canvas.setOnDragOver(new EventHandler<DragEvent>(){

      @Override
      public void handle(DragEvent event) {
        // event.getGestureSource()
        // event.getDragboard()
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        
        event.consume();
      }
      
    });
    
    canvas.setOnDragEntered(new EventHandler<DragEvent>() {
      public void handle(DragEvent event) {
        
        
        event.consume();
      }
    });
  }

}
