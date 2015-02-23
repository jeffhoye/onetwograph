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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.arl.onetwograph.dnd.HasPane;
import org.arl.onetwograph.dnd.OTFactory;
import org.arl.onetwograph.thing.Thing;

/**
 * 
 * @author jeffhoye
 *
 * @param <T>
 */
public abstract class ThingFactory<T extends Thing> extends HasPane implements OTFactory<T> {
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
  
  public Pane getBaseNode() {
    HBox ret = new HBox();
    ret.setAlignment(Pos.CENTER_LEFT);
//    ret.setStyle("-fx-border-radius: 5;");
//    ret.setStyle("-fx-border-color: black;");
//    ret.setStyle("-fx-border-style: solid;");
    ret.setMinWidth(200.0);
    return ret;
  }
  

  
  @Override
  protected Pane generateNode() {
    final Pane node = (Pane)super.generateNode();
//    node.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));  
    
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
    
    node.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (selected) {
          palette.setSelected(null);          
        } else {
          palette.setSelected(ThingFactory.this);          
        }
      }
    });
    
    return node;
  }

  public abstract String getItemType();
  
}
