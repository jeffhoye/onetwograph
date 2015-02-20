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
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.arl.onetwograph.thing.Thing;

/**
 * 
 * @author jeffhoye
 *
 * @param <T>
 */
public abstract class ThingFactory<T extends Thing> {
  protected String type;
  protected Image icon; 
  protected VBox node;
  
  public ThingFactory(String type, String iconName) {
    this.type = type;
    this.icon = new Image("file:"+iconName,true);
    
    node = new VBox();
    node.setAlignment(Pos.CENTER);
    node.getChildren().add(new ImageView(this.icon));
    node.getChildren().add(new Label(this.type));
    node.setPrefHeight(100);
    node.setPrefWidth(100);
    node.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    node.setOnDragDetected(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        System.out.println("Dragging "+type);
        Dragboard db = node.startDragAndDrop(TransferMode.ANY);
        
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(type);
        db.setContent(content);
        
        event.consume();
      }
      
    });

  }
  
  public abstract T getInstance();
  public abstract String getItemType();
  public Node getNode() {
    return node;
  };
}
