package org.arl.onetwograph.pallette;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import org.arl.onetwograph.thing.Actor;
import org.arl.onetwograph.thing.Relation;

public class RelationFactory extends ThingFactory<Relation> {

  public static final double w = 40.0;  // the circle w/h
  public static final double h = 40.0;
  public static final double iw = 70.0; // the icon w/ cupid's arrow
  
  public RelationFactory(String type, String iconName) {
    super(type,makeIcon(iconName));
  }
  
  public static Image makeIcon(String iconName) {
    // canvas
    double w2 = w/2.0;
    double h2 = h/2.0;
    Canvas c = new Canvas(w,h);
    GraphicsContext g = c.getGraphicsContext2D();

    // icon scaled in center
    Image baseIcon = new Image(iconName.getClass().getResourceAsStream(iconName));
    double bw = baseIcon.getWidth();
    double bh = baseIcon.getHeight();
    double bm = Math.max(bw, bh); // max
    double bs = (w-16.0)/bm; // scale w/ 20px inset
    double nw = bw*bs; // new width
    double nh = bh*bs; // new height
    g.drawImage(baseIcon, w2-nw/2.0, h2-nh/2.0, nw, nh);
    
    // circle
    g.setStroke(Color.BLACK);
    g.setLineWidth(3.0);
    g.strokeOval(5, 5, w-10, h-10);

    // export
    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(Color.TRANSPARENT);
    WritableImage ret = c.snapshot(sp, new WritableImage((int)w, (int)h));
    return ret;
  }

  /**
   * longer due to the cupid's arrow
   */
  @Override
  public double getIconWidth() {
    return iw;
  }
  
  /**
   * include cupid's arrow
   */
  @Override
  public Image getNodeIcon() {
    double iw2 = iw/2.0;
    double aw = 10; // arrow width
    double aw2 = aw/2.0;
    
    // canvas
    double w2 = w/2.0;
    double h2 = h/2.0;
    Canvas c = new Canvas(iw,h);
    GraphicsContext g = c.getGraphicsContext2D();

    // cupid's arrow
    double cw = iw2-w2+3; // width of each half of cupid's arrow
    g.setStroke(Color.BLACK);
    g.setLineWidth(3.0);
    g.strokeLine(0, h2, cw-aw2, h2);
    g.strokeLine(iw-cw-aw2, h2, iw-aw, h2); // for arrowhead
    g.setLineWidth(1.0);
    g.fillPolygon(new double[]{iw-1,iw-aw,iw-aw}, new double[]{h2,h2-5,h2+5}, 3); // arrowhead
    
    // icon in center
    g.drawImage(this.icon, iw2-aw2-w2, 0, w, h);
    
    // export
    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(Color.TRANSPARENT);
    WritableImage ret = c.snapshot(sp, new WritableImage((int)iw, (int)h));
    return ret;
  }
    
  @Override
  public Relation getInstance() {
    return new Relation(text,icon);
  }

  @Override
  public String getItemType() {
    return "Relation";
  }

  @Override
  public int getType() {
    return RELATION;
  }

}
