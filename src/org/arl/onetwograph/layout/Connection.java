package org.arl.onetwograph.layout;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Connection implements ChangeListener<Number>{
  protected Node a;
  protected Node b;
  protected ImageView icon;
  protected Pane canvas;
  
  public Connection(Node a, Node b, Image icon, Pane canvas) {
    setA(a);
    setB(b);
    this.icon = new ImageView(icon);
    this.canvas = canvas;
//    this.canvas.getChildren().add(this.icon); // done with call to getNode();
  }

  public void setA(Node a) {
    if (a == this.a) return;
    if (this.a != null) {
      this.a.layoutXProperty().removeListener(this);
      this.a.layoutYProperty().removeListener(this);
    }
    this.a = a;
    if (a != null) {
      a.layoutXProperty().addListener(this);
      a.layoutYProperty().addListener(this);      
    } 
  }
  
  public void setB(Node b) {
    if (b == this.b) return;
    if (this.b != null) {
      this.b.layoutXProperty().removeListener(this);
      this.b.layoutYProperty().removeListener(this);
    }
    this.b = b;
    if (b != null) {
      b.layoutXProperty().addListener(this);
      b.layoutYProperty().addListener(this);      
    } 
  }
  
  @Override
  public void changed(ObservableValue<? extends Number> observable,
      Number oldValue, Number newValue) {
    update();
  }

  public abstract void update();

  public void remove() {
    setA(null);
    setB(null);
    this.canvas.getChildren().remove(this.icon);
  }  
  
  public Node getNode() {
    return icon;
  }
}
