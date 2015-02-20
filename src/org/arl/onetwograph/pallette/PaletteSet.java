package org.arl.onetwograph.pallette;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

  public PaletteSet() {
    setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    
    palettes = new LinkedHashMap<String, Palette>();
    
    palettes.put("Actor", new Palette<Actor>());
    palettes.put("Attribute", new Palette<Attribute>());
    palettes.put("Prop", new Palette<Prop>());
    palettes.put("Relation", new Palette<Relation>());
    
    for (Map.Entry<String, Palette> p : palettes.entrySet()) {
      Tab t = new Tab(p.getKey());
      t.setContent(p.getValue());
      getTabs().add(t); // p.getValue()
    }
  }
  
  
  
  public void addFactory(ThingFactory pi) {
    Palette p = palettes.get(pi.getItemType());
    p.addFactory(pi);
  }
}
