package org.arl.onetwograph.pallette;

import org.arl.onetwograph.thing.Actor;

public class ActorFactory extends ThingFactory<Actor> {

  public ActorFactory(String type, String iconName) {
    super(type,iconName);
  }

  @Override
  public Actor getInstance() {
    return new Actor(type,icon);
  }

  @Override
  public String getItemType() {
    return "Actor";
  }

}
