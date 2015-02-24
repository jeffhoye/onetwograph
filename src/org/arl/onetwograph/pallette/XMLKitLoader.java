package org.arl.onetwograph.pallette;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLKitLoader extends DefaultHandler {
  PaletteSet paletteSet;
  String curPaletteName = null;
  
  public XMLKitLoader(PaletteSet paletteSet) {
    this.paletteSet = paletteSet;
  }
  
  
  public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
    if (tagName.equalsIgnoreCase("palette")) {
      curPaletteName = attributes.getValue("name");
//      System.out.println("curPalette:"+curPaletteName);
      attributes.getValue("name");
      return;
    }
    
    if (tagName.equalsIgnoreCase("actor")) {
      paletteSet.addFactory(curPaletteName, new ActorFactory(attributes.getValue("name"),attributes.getValue("img")));
    }
    
    if (tagName.equalsIgnoreCase("relation")) {
      paletteSet.addFactory(curPaletteName, new RelationFactory(attributes.getValue("name"),attributes.getValue("img")));
    }
  }
  
  
  
  public void load(InputStream kit_file) throws Exception {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();
   
    saxParser.parse(kit_file, this);
  }
}
