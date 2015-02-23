package org.arl.onetwograph.layout;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class StraightLine extends Connection {
  Line line = null;
  
  public StraightLine(Node a, Node b, Image img, Pane canvas) {
    super(a, b, img, canvas);
    line = new Line();
    canvas.getChildren().add(line);
    line.toBack();
  }

  @Override
  public void update() {
//    System.out.println("StraightLine.update");
    if (a != null && b != null) {
      Bounds aB = a.getBoundsInParent(); //b.getLayoutBounds();
      double x1 = aB.getMinX()+aB.getWidth()/2.0;
      double y1 = aB.getMinY()+aB.getHeight()/2.0;
      line.setStartX(x1);
      line.setStartY(y1);

      Bounds bB = b.getBoundsInParent(); //b.getLayoutBounds();
      double x2 = bB.getMinX()+bB.getWidth()/2.0;
      double y2 = bB.getMinY()+bB.getHeight()/2.0;
      line.setEndX(x2);
      line.setEndY(y2);
      
      icon.setLayoutX((x1+x2)/2.0);
      icon.setLayoutY((y1+y2)/2.0);
      
      icon.setVisible(true);
      line.setVisible(true);
    } else {
      icon.setVisible(false);
      line.setVisible(false);
    }
  }
  
  @Override
  public void remove() {
    super.remove();
    canvas.getChildren().remove(line);
  }

}
