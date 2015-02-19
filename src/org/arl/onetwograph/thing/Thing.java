package org.arl.onetwograph.thing;

import javafx.scene.image.Image;

public class Thing {
  String type;
  Image icon; 
  
  public Thing(String type, Image icon) {
    this.type = type;
    this.icon = icon;
  }
}
