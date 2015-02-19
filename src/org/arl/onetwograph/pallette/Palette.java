package org.arl.onetwograph.pallette;

import java.util.ArrayList;
import java.util.List;

import org.arl.onetwograph.thing.Thing;

import javafx.scene.layout.FlowPane;

/**
 * A collection of PalletteItems that can be dragged on to the Canvas
 * 
 * @author jeffhoye
 *
 */
public class Palette<T extends Thing> extends FlowPane {
  List<ThingFactory<T>> factories;
  
  public Palette() {
    factories = new ArrayList<ThingFactory<T>>();
  }
  
  public void addFactory(ThingFactory<T> pi) {
    factories.add(pi);
  }

}
