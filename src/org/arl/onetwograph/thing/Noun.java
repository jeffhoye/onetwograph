package org.arl.onetwograph.thing;

import org.arl.onetwograph.dnd.OTFactory;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Noun extends Thing {

  public Noun(String type, Image icon) {
    super(type,icon);
  }

  @Override
  protected Pane generateNode() {
    Pane node = super.generateNode();
    node.setOnMousePressed(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        OTFactory<Thing> selectedFactory = canvas.registry.getSelected();
        
        System.out.println("Noun pressed; selected factory:"+selectedFactory);
        
        if (selectedFactory == null) {
          canvas.startDrag(event, Noun.this, null);          
          return;
        }
        
        switch(selectedFactory.getType()) {
        case OTFactory.RELATION:
          
          Relation r = (Relation)selectedFactory.getInstance();
          r.setFrom(Noun.this);
          canvas.nullDragable.setLocation(event.getX(), event.getY());
          canvas.nullDragable.getNode().setVisible(true);
          canvas.addThing(r);
          canvas.startDrag(event,canvas.nullDragable,r);
          break;
        }
      }
    });
    
    node.setOnMouseReleased(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        System.out.println(Noun.this+" .OnMouseReleased:"+event.getPickResult().getIntersectedNode());
        canvas.stopDrag(event, Noun.this);
      }
    });
    
    node.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
      public void handle(MouseDragEvent event) {
        if (canvas.dragging == canvas.nullDragable) {
          canvas.addDragTarget(Noun.this);
        }
      }
    });

    node.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
      public void handle(MouseDragEvent event) {
        if (canvas.dragging == canvas.nullDragable) {
          canvas.removeDragTarget(Noun.this);
        }
      }
    });

    node.setOnDragDetected(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        System.out.println("onDragDetected:"+Noun.this);  
        node.startFullDrag();
      }
    });


    node.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
      public void handle(MouseDragEvent event) {
        System.out.println("onMouseDragReleased:"+Noun.this);  
        canvas.catchDrag(Noun.this);
      }      
    });
    
    
//    node.setOnDragDropped(new EventHandler<Event>() {
//    });
    
//    node.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
//
//      @Override
//      public void handle(MouseDragEvent event) {
//        System.out.println("onMouseDragOver:"+Noun.this);  
//      }
//    });
//    
//    node.setOnMouseEntered(new EventHandler<MouseEvent>() {
//      public void handle(MouseEvent event) {
//        
//        System.out.println("onMouseEntered:"+Noun.this);        
//      }
//    });
//
    return node;
  }

}
