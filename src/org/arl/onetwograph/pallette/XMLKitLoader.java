package org.arl.onetwograph.pallette;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLKitLoader extends DefaultHandler {
  PaletteSet paletteSet;
  String curPalette = null;
  
  public XMLKitLoader(PaletteSet paletteSet) {
    this.paletteSet = paletteSet;
  }
  
  
  public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
    if (tagName.equalsIgnoreCase("palette")) {
      curPalette = attributes.getValue("name");
      System.out.println("curPalette:"+curPalette);
      return;
    }
    
    if (tagName.equalsIgnoreCase("actor")) {
      paletteSet.addFactory(new ActorFactory(attributes.getValue("name"),attributes.getValue("img")));
    }
  }
  
  
  
  public void load(File kit_file) throws Exception {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();
   
    saxParser.parse(kit_file, this);
  }
}
