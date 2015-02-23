package org.arl.onetwograph.pallette;

import org.arl.onetwograph.thing.Actor;
import org.arl.onetwograph.thing.Relation;

public class RelationFactory extends ThingFactory<Relation> {

  public RelationFactory(String type, String iconName) {
    super(type,iconName);
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
