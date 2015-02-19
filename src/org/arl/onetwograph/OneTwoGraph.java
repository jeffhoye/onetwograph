package org.arl.onetwograph;

import java.io.File;

import org.arl.onetwograph.pallette.PaletteSet;
import org.arl.onetwograph.pallette.XMLKitLoader;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OneTwoGraph extends Application {

  OTCanvas canvas;
  PaletteSet paletteSet;
  
  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("ONeTwOGraph");

    
    Canvas c = new Canvas(300, 250);
    canvas = new OTCanvas(c);

    paletteSet = new PaletteSet();
    
    BorderPane masterPane = new BorderPane();
    masterPane.setCenter(c);
    masterPane.setRight(paletteSet);
    
    stage.setScene(new Scene(masterPane));
    stage.show();
    
    new XMLKitLoader(paletteSet).load(new File("resource/standard_kit.xml"));;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
