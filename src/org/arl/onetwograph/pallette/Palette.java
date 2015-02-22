package org.arl.onetwograph.pallette;

import java.util.ArrayList;
import java.util.List;

import org.arl.onetwograph.dnd.ClipRegistry;
import org.arl.onetwograph.dnd.OTFactory;
import org.arl.onetwograph.thing.Thing;

import javafx.geometry.Insets; 
import javafx.scene.input.DataFormat;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

/**
 * A collection of PalletteItems that can be dragged on to the Canvas
 * 
 * @author jeffhoye
 *
 */
public class Palette<T extends Thing> extends FlowPane {
  public String type;
  ClipRegistry<Thing> registry;  
  List<ThingFactory<T>> factories;
  
  
  public Palette(String type, ClipRegistry<Thing> registry) {
    this.type = type;
    this.registry = registry;
    factories = new ArrayList<ThingFactory<T>>();
    
    setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    setPadding(new Insets(5, 5, 5, 5));
    setVgap(4);
    setHgap(4);
    setPrefWrapLength(170); // preferred width allows for two columns
    setStyle("-fx-background-color: DAE6F3;");
  }
  
  public void addFactory(ThingFactory<T> pi) {
    factories.add(pi);
    getChildren().add(pi.getNode());
    pi.setPalette(this);
    registry.register(pi.getClipRegistryKey(), (OTFactory<Thing>)pi);
  }

}
