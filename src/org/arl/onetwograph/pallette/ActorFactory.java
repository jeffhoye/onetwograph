package org.arl.onetwograph.pallette;

import org.arl.onetwograph.thing.Actor;

public class ActorFactory extends NounFactory<Actor> {

  public ActorFactory(String type, String iconName) {
    super(type,iconName);
  }

  @Override
  public Actor getInstance() {
    return new Actor(text,icon);
  }

  @Override
  public String getItemType() {
    return "Actor";
  }

  @Override
  public int getType() {
    return ACTOR;
  }

  
}
