package org.arl.onetwograph.thing;

import java.util.List;

import javafx.scene.image.Image;

public class Actor extends Noun {
  String name;
  List<Attribute> attributes;

  public Actor(String type, Image icon) {
    super(type,icon);
  }
}
