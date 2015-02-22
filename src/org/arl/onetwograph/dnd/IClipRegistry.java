package org.arl.onetwograph.dnd;

import javafx.scene.input.DataFormat;

public interface IClipRegistry<T> {
  public DataFormat getFormat();
  public T getInstance();
}
