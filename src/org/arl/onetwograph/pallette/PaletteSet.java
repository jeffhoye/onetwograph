package org.arl.onetwograph.pallette;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.arl.onetwograph.dnd.ClipRegistry;
import org.arl.onetwograph.thing.*;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


/**
 * Collection of pallettes.
 * 
 * @author jeffhoye
 *
 */
public class PaletteSet extends TabPane {
  LinkedHashMap<String, Palette> palettes;
  ClipRegistry<Thing> clipRegistry;
  
  
  public PaletteSet(ClipRegistry<Thing> clipRegistry) {
    this.clipRegistry = clipRegistry;
    setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    
    palettes = new LinkedHashMap<String, Palette>();
    
//    palettes.put("Actor", new Palette<Actor>("Actor", clipRegistry));
//    palettes.put("Attribute", new Palette<Attribute>("Attribute", clipRegistry));
//    palettes.put("Prop", new Palette<Prop>("Prop", clipRegistry));
//    palettes.put("Relation", new Palette<Relation>("Relation", clipRegistry));
//    
//    for (Map.Entry<String, Palette> p : palettes.entrySet()) {
//      Tab t = new Tab(p.getKey());
//      t.setContent(p.getValue());
//      getTabs().add(t); // p.getValue()
//    }
  }
  
  
  
  public void addFactory(String paletteName, ThingFactory pi) {
    Palette p;
    if (palettes.containsKey(paletteName)) {
      p = palettes.get(paletteName);
    } else {
      p = new Palette(paletteName, clipRegistry);
      palettes.put(paletteName, p);
      Tab t = new Tab(paletteName);
      t.setContent(p);
      getTabs().add(t); // p.getValue()
    } 
    p.addFactory(pi);
  }
}
