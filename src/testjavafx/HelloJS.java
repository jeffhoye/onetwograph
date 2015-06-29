package testjavafx;

import netscape.javascript.JSObject;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.stage.Stage;

public class HelloJS extends Application {

  WebEngine eng;
  
  public void runCE() {
//    System.out.println(eng.executeScript("alert('bar');"));
//    eng.executeScript("cenode.add_sentence(\"there is a teacher named 'Mrs Smith'\");");
//    eng.executeScript("var data = cenode.ask_question(\"who is Mrs Smith?\");");
//    eng.executeScript("alert(data[0]);");

    eng.executeScript("cenode.add_sentence(\"there is a teacher named 'Mr C' that teaches the class 'Nothing'\");");

//    JSObject r = (JSObject)eng.executeScript("cenode.ask_question(\"who is Mrs Smith?\");");
//    JSObject r = (JSObject)eng.executeScript("cenode.ask_question(\"where is Mrs Smith?\");");
//    JSObject r = (JSObject)eng.executeScript("cenode.ask_question(\"where is Mrs Smith?\");");
    JSObject r = (JSObject)eng.executeScript("cenode.ask_question(\"who is Mr C?\");");
    System.out.println(r.getMember("data"));
  }
  
  @Override
  public void start(Stage stage) {
      // create scene
//      stage.setTitle("Web View");
      
      eng = new WebEngine();
      eng.setOnAlert(new EventHandler<WebEvent<String>>() {
        
        @Override
        public void handle(WebEvent<String> msg) {
          System.out.println(msg.getData());
        }
      });
      
      eng.getLoadWorker().stateProperty().addListener(
          new ChangeListener<State>() {
            public void changed(ObservableValue ov, State oldState, State newState) {
              if (newState == State.SUCCEEDED) {
                runCE();
              }
            }
          });

//        eng.loadContent("<script>alert('foo');</script>");
//      eng.load(getClass().getResource("/resource/html/testalert.html").toString());
//        eng.executeScript("alert('bar');");
      eng.load(getClass().getResource("/resource/html/loadce.html").toString());
      
  }

  public static void main(String[] args) {
    launch(args);
  }

}
