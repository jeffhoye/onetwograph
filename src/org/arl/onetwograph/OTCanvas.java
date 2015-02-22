package org.arl.onetwograph;

import org.arl.onetwograph.dnd.ClipRegistry;
import org.arl.onetwograph.dnd.HasNode;
import org.arl.onetwograph.layout.StraightLine;
import org.arl.onetwograph.pallette.ThingFactory;
import org.arl.onetwograph.thing.Thing;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Listeners etc for a canvas.
 * 
 * @author jeffhoye
 *
 */
public class OTCanvas {
  ClipRegistry<Thing> registry;

//  double dragStartX, dragStartY; // where in the canvas they clicked
//  double dragObjStartX, dragObjStartY; // where on the Node they clicked
  HasNode dragging;
  
  Pane pane;
  
  public OTCanvas(Pane pane, ClipRegistry<Thing> registry) {
    this.registry = registry;
    this.pane = pane;
    
    pane.setOnDragOver(new EventHandler<DragEvent>(){
      public void handle(DragEvent event) {
        // event.getGestureSource()
        if (event.getDragboard().hasContent(ThingFactory.format)) {
          event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
          event.consume();          
        }
      }
    });
    
    pane.setOnDragEntered(new EventHandler<DragEvent>() {
      public void handle(DragEvent event) {
        System.out.println("OnDragEntered");
        if (event.getDragboard().hasContent(ThingFactory.format)) {
          event.consume();
        }
      }
    });
    
    pane.setOnDragDropped(new EventHandler<DragEvent>() {
      public void handle(DragEvent event) {
        System.out.println("OnDragDropped");
        Thing newObj = addThing(registry.getInstance(event.getDragboard()));
        
        newObj.setLocation(event.getX(), event.getY());
        pane.getChildren().add(newObj.getNode());
      }
    });
        
    pane.setOnDragDetected(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        System.out.println("OnDragDetected");
      }
    });
    
    pane.setOnMousePressed(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
//        System.out.println("cOnMousePressed: "+event.getX()+","+event.getY());
//        Circle a = new Circle(event.getX(),event.getY(),8.0);        
//        pane.getChildren().add(a);
//        Circle b = new Circle(event.getX(),event.getY(),8.0);
//        pane.getChildren().add(b);
//        new StraightLine(a, b, registry.getImage("Relation_sees"), pane);
//        dragging = new HasNode(b);
      }
    });

    pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        dragging = null;
      }
    });
    
    pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
//          System.out.println("OnMouseDragged");
          if (dragging != null) {
//            System.out.println("dragging: "+event.getX()+","+event.getY());
            dragging.setLocation(event.getX(), event.getY());
          }
        }
      });
      
    pane.setOnMouseDragReleased(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        System.out.println("OnMouseDragReleased");
      }
    });
  }

  public Thing addThing(Thing thing) {
    thing.setCanvas(this);
    return thing;
  }
  
  public void startDrag(MouseEvent event, Thing thing) {
    System.out.println("startDrag:"+thing);  
//    dragStartX = event.getX();
//    dragStartY = event.getY();
    dragging = thing;
  }

  public void stopDrag(MouseEvent event, Thing thing) {
    dragging = null;
  }

}
