package org.arl.onetwograph.pallette;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import org.arl.onetwograph.thing.Noun;

public abstract class NounFactory<T extends Noun> extends ThingFactory<T> {

  public NounFactory(String type, Image icon) {
    super(type, icon);
  }

  public NounFactory(String type, String iconName) {
    super(type, iconName);
  }
  
  @Override
  protected Pane generateNode() {
    final Pane node = (Pane)super.generateNode();
    
    node.setOnDragDetected(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        System.out.println("Dragging "+text);
        Dragboard db = node.startDragAndDrop(TransferMode.ANY);
        
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        if (text != null) {
          content.putString(text);          
        }
        content.putImage(icon);
        content.put(format,getClipRegistryKey());
        db.setContent(content);
        
        event.consume();
      }
      
    });

    return node;
  }

}
