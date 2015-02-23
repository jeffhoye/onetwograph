package org.arl.onetwograph.dnd;

import javafx.scene.image.Image;

public interface OTFactory<T> {
  public static final int ACTOR = 1;
  public static final int PROP = 2;
  public static final int ATTRIBUTE = 3;
  public static final int RELATION = 4;
  
  public T getInstance();
  public String getClipRegistryKey();
  public Image getIcon();
  public void setSelected(boolean b);
  public int getType();
}
