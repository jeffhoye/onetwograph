package org.arl.onetwograph;

import java.io.File;

import org.arl.onetwograph.dnd.ClipRegistry;
import org.arl.onetwograph.pallette.PaletteSet;
import org.arl.onetwograph.pallette.ThingFactory;
import org.arl.onetwograph.pallette.XMLKitLoader;
import org.arl.onetwograph.thing.Thing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OneTwoGraph extends Application {

  OTCanvas canvas;
  PaletteSet paletteSet;
  ClipRegistry<Thing> clipRegistry;
  
  public OneTwoGraph() {
    clipRegistry = new ClipRegistry<Thing>(ThingFactory.format);
  }
  
  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("ONeTwOGraph");

    
    Pane p = new Pane();
    p.setPrefWidth(300);
    p.setPrefHeight(250);
    canvas = new OTCanvas(p,clipRegistry);

    paletteSet = new PaletteSet(clipRegistry);
    
    BorderPane masterPane = new BorderPane();
    masterPane.setCenter(p);
    masterPane.setRight(paletteSet);
    
    stage.setScene(new Scene(masterPane));
    stage.show();
    
    new XMLKitLoader(paletteSet).load(new File("resource/standard_kit.xml"));;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
