package org.arl.onetwograph.thing;

import org.arl.onetwograph.OTCanvas;
import org.arl.onetwograph.layout.Connection;
import org.arl.onetwograph.layout.StraightLine;

import javafx.scene.Node;
import javafx.scene.image.Image;

public class Relation extends Thing {
  Noun nounFrom;
  Noun nounTo;
  Connection connection;
  
  public Relation(String type, Image icon) {
    super(type, icon);
  }

  public void setFrom(Noun noun) {
    this.nounFrom = noun;
    if (connection != null) {
      if (noun == null) {
        connection.setA(null);
      } else {
        connection.setA(noun.getNode());              
      }
    }
  }
  
  public void setTo(Noun noun) {
    this.nounTo = noun;
    if (connection != null) {
      if (noun == null) {
        connection.setB(null);
      } else {
        connection.setB(noun.getNode());
      }
    }
  }
  
  public void setCanvas(OTCanvas canvas) {
    super.setCanvas(canvas);
    
    Node a = null;
    if (nounFrom != null) {
      a = nounFrom.getNode();
    }
    
    Node b = null;
    if (nounTo == null) {
      b = canvas.nullDragable.getNode();
    } else {
      b = nounTo.getNode();
    }
    
    connection = new StraightLine(a, b, icon, canvas.pane);
  }

  public void setEnd(Noun noun) {
    if (this.nounFrom == null) {
      setFrom(noun);
    }
    setTo(noun);
  }

  public void remove() {
    setFrom(null);
    setTo(null);
    connection.remove();
  }
}
