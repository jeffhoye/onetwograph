package org.arl.onetwograph.dnd;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HasNode {
  protected String text;
  protected Image icon; 
  
  protected Pane node;
  protected Label label;
  protected ImageView imageView;
  
  public HasNode(String type, Image icon) {
    this.text = type;
    this.icon = icon;
  }

  public HasNode(String type, String iconName) {
    this.text = type;
    this.icon = new Image("file:"+iconName,true);
  }

  public HasNode(Pane n) {
    this.node = n;
  }
  
  public double getWidth() {
    return 70.0;
  }

  public double getHeight() {
    return 50.0;
  }

  public double getIconWidth() {
    return 50.0;
  }
  
  public double getIconHeight() {
    return 50.0;
  }
  
  public Pane getBaseNode() {
    VBox ret = new VBox();
    ret.setAlignment(Pos.CENTER);
    return ret;
  }
  
  protected Pane generateNode() {
    imageView = new ImageView(this.icon);
    imageView.setPreserveRatio(true);
    imageView.setFitWidth(getIconWidth());
    imageView.setFitHeight(getIconHeight());
    
    label = new Label(this.text);
    
    Pane node = getBaseNode();
    node.getChildren().add(imageView);
    if (this.text != null) {
      node.getChildren().add(label);
    }
//    node.setPrefWidth(getWidth());
    node.setPrefHeight(getHeight());
    
    return node;
  }
  
  public Pane getNode() {
    if (node == null) {
      node = generateNode();
    }
    return node;
  }
  
  public void setText(String text) {
    this.text = text;
    label.setText(text);
  }
  
  public void setLocation(double x, double y) {
    Node node = getNode();
    Bounds b = node.getBoundsInLocal();
    double w = b.getWidth();
    double h = b.getHeight();
    node.relocate(x-w/2.0, y-h/2.0);
  }
  
  public Image getIcon() {
    return icon;
  }

}
