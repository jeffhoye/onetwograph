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
    this.a = a;
    this.b = b;
    this.icon = new ImageView(icon);
    this.canvas = canvas;
    
    this.canvas.getChildren().add(this.icon);
    
    a.layoutXProperty().addListener(this);
    b.layoutYProperty().addListener(this);
    a.layoutXProperty().addListener(this);
    b.layoutYProperty().addListener(this);
  }

  @Override
  public void changed(ObservableValue<? extends Number> observable,
      Number oldValue, Number newValue) {
    update();
  }

  public abstract void update();  
}
