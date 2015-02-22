package org.arl.onetwograph.dnd;

public interface OTFactory<T> {
  public T getInstance();
  public String getClipRegistryKey();
}
