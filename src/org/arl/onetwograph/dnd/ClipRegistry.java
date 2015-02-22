package org.arl.onetwograph.dnd;

import java.util.HashMap;

import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;

public class ClipRegistry<T> {
  protected DataFormat format;
  protected HashMap<String, OTFactory<T>> table;
  
  public ClipRegistry(DataFormat format) {
    this.format = format;
    table = new HashMap<String, OTFactory<T>>();
  }
  
  public T getInstance(Dragboard db) {
    if (db.hasContent(format)) {
      return table.get((String)db.getContent(format)).getInstance();
    }
    return null;
  }
  
  public void register(String key, OTFactory<T> t) {
    table.put(key,t);
  }  
}
