package org.arl.onetwograph.pallette;

import javafx.scene.image.Image;

import org.arl.onetwograph.thing.Thing;

/**
 * 
 * @author jeffhoye
 *
 * @param <T>
 */
public abstract class ThingFactory<T extends Thing> {
  protected String type;
  protected Image icon; 
  
  public ThingFactory(String type, String iconName) {
    this.type = type;
    this.icon = null; //new Image(iconName);
  }
  
  public abstract T getInstance();
  public abstract String getItemType();
}
