package org.arl.onetwograph;

import org.arl.onetwograph.dnd.ClipRegistry;
import org.arl.onetwograph.dnd.HasNode;
import org.arl.onetwograph.dnd.HasPane;
import org.arl.onetwograph.dnd.OTFactory;
import org.arl.onetwograph.layout.StraightLine;
import org.arl.onetwograph.pallette.ThingFactory;
import org.arl.onetwograph.thing.Noun;
import org.arl.onetwograph.thing.Relation;
import org.arl.onetwograph.thing.Thing;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
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
import javafx.scene.shape.Shape;

/**
 * Listeners etc for a canvas.
 * 
 * @author jeffhoye
 *
 */
public class OTCanvas {
  public ClipRegistry<Thing> registry;

//  double dragStartX, dragStartY; // where in the canvas they clicked
//  double dragObjStartX, dragObjStartY; // where on the Node they clicked
  public HasNode dragging;
  public Relation draggingRelation;
  
  public HasNode nullDragable;
  
  public Pane pane;
  
  public OTCanvas(Pane pane, ClipRegistry<Thing> registry) {
    this.registry = registry;
    this.pane = pane;
    
    final Shape mouseHandle = new Circle(-100,-100,3.0);
    mouseHandle.setVisible(false);
    pane.getChildren().add(mouseHandle);
    nullDragable = new HasNode() {
      
      @Override
      public void setLocation(double x, double y) {
//        System.out.println("nullDraggable.setLocation("+x+","+y+")");
        Bounds b = mouseHandle.getBoundsInLocal();
        double w = b.getWidth();
        double h = b.getHeight();
        mouseHandle.relocate(x-w/2.0, y-h/2.0);
      }
      
      @Override
      public Node getNode() {
        return mouseHandle;
      }
    };
    
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
      }
    });
        
    pane.setOnDragDetected(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        System.out.println("OnDragDetected");
        pane.startFullDrag();
      }
    });
    
    pane.setOnMousePressed(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        OTFactory<Thing> selectedFactory = registry.getSelected();
        if (selectedFactory == null) {
          return;
        }

        switch(selectedFactory.getType()) {
        case OTFactory.ACTOR:
        case OTFactory.PROP:
          Thing t = (Thing)selectedFactory.getInstance();
          t.setLocation(event.getX(), event.getY());
          addThing(t);
          startDrag(event,t,null);
          break;
        }
      }
    });

    pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        System.out.println("pane.OnMouseReleased");
        stopDrag(event,null);
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
        System.out.println("pane.OnMouseDragReleased:"+event.getPickResult().getIntersectedNode());
      }
    });
  }

  public Thing addThing(Thing thing) {
    thing.setCanvas(this);
    return thing;
  }
  
  public void startDrag(MouseEvent event, HasNode thing, Relation r) {
    System.out.println("startDrag:"+thing);  
//    dragStartX = event.getX();
//    dragStartY = event.getY();
    dragging = thing;
    draggingRelation = r;
  }

  public void stopDrag(MouseEvent event, Thing thing) {
    if (dragging == nullDragable) {
      nullDragable.getNode().setVisible(false);
    }
    dragging = null;
    if (draggingRelation != null) {
      draggingRelation.remove();
      draggingRelation = null;
    }
  }

  public Noun dragTarget = null;
  public void addDragTarget(Noun noun) {
    if (dragTarget == noun) return;
    if (dragTarget != null) {
      dragTarget.setSelected(false);
    }
    noun.setSelected(true);
    dragTarget = noun;
  }

  public void removeDragTarget(Noun noun) {
    if (dragTarget != noun) return;
    noun.setSelected(false);
    dragTarget = null;
  }

  public void catchDrag(Noun noun) {
    System.out.println("catchDrag:"+noun);
    if (dragTarget != null) {
      dragTarget.setSelected(false);      
    }
    if (draggingRelation != null) {
      draggingRelation.setEnd(noun);
      registry.deselectAll();
    }
    draggingRelation = null;
    noun.setSelected(false);
  }
}
