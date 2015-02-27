package org.arl.onetwograph.dnd;

import java.util.Collection;
import java.util.HashMap;

import org.arl.onetwograph.pallette.ThingFactory;

import javafx.scene.image.Image;
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
    System.out.println("ClipRegistry:"+key);
    table.put(key,t);
  }  
  
  public Image getImage(String key) {
    return table.get(key).getIcon();
  }
  
  public Collection<OTFactory<T>> values() {
    return table.values();
  }

  
  OTFactory<T> selected = null;
  public void setSelected(OTFactory<T> f) {
    for (OTFactory<T> t : table.values()) {
      t.setSelected(t == f);
    }
    selected = f;
  }
  
  public OTFactory<T> getSelected() {
    return selected;
  }

  public void deselectAll() {
    selected = null;
    for (OTFactory<T> t : table.values()) {
      t.setSelected(false);
    }
  }
  
}
