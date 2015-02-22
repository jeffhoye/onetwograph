package org.arl.onetwograph.pallette;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.arl.onetwograph.dnd.HasNode;
import org.arl.onetwograph.dnd.OTFactory;
import org.arl.onetwograph.thing.Thing;

/**
 * 
 * @author jeffhoye
 *
 * @param <T>
 */
public abstract class ThingFactory<T extends Thing> extends HasNode implements OTFactory<T> {
  public static final DataFormat format = new DataFormat("OTThingFactory"); // for the clipboad

  protected Palette palette;
  
  public ThingFactory(String type, String iconName) {
    super(type,iconName);
  }

  protected void setPalette(Palette p) {
    this.palette = p;
  }
  
  public String getClipRegistryKey() {
    return this.palette.type+"_"+this.text;
  }
  
  protected Node generateNode() {
    final VBox node = (VBox)super.generateNode();
    node.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));  
    
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

  public abstract String getItemType();
  
}
