package org.arl.onetwograph.dnd;

import javafx.scene.image.Image;

public interface OTFactory<T> {
  public T getInstance();
  public String getClipRegistryKey();
  public Image getIcon();
}
