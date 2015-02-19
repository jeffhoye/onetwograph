package org.arl.onetwograph.thing;

import javafx.scene.image.Image;

public class Relation extends Thing {
  Noun nounFrom;
  Noun nounTo;
  
  public Relation(String type, Image icon) {
    super(type, icon);
  }
  
  
}
