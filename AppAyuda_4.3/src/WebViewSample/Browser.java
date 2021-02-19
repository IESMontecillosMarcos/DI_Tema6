/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebViewSample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import netscape.javascript.JSObject;

/**
 *
 * @author Marcos Gonzalez Leon
 */
class Browser extends Region {
    
    private HBox toolBar;

    // Arrays con Nombres de Recursos.
    private static String[] imageFiles = new String[]{
        "imgs/product.png",
        "imgs/blog.png",
        "imgs/documentation.png",
        "imgs/partners.png",
        "imgs/help.png"
    };
    private static String[] captions = new String[]{
        "Products",
        "Blogs",
        "Documentation",
        "Partners ",
        "Help"
    };
    private static String[] urls = new String[]{
        " http://www.oracle.com/products/index.html",
        "http://blogs.oracle.com/",
        "http://docs.oracle.com/javase/index.html",
        "http://www.oracle.com/partners/index.html ",
        DI_T6A43.class.getResource("help.html").toExternalForm()
    };    
    
    // Opciones de la Barra de Herramientas(?)
    final ImageView selectedImage = new ImageView();
    final Hyperlink[] hpls = new Hyperlink[captions.length];
    final Image[] images = new Image[imageFiles.length];    

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    
    final Button showPrevDoc = new Button("Toggle Previous Docs");
    final WebView smallView = new WebView();
    
    final WebHistory history = webEngine.getHistory();

    
    private boolean needDocumentationButton = false;

    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        
        //Para tratar lo tres enlaces
        for (int i = 0; i < captions.length; i++) {
            
            Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
            
            Image image = images[i] = new Image(getClass().getResourceAsStream(imageFiles[i]));
            hpl.setGraphic(new ImageView (image));
            final String url = urls[i];
            final boolean addButton = (hpl.getText().equals("Documentacion"));

            //proccess event
            hpl.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    needDocumentationButton = addButton;
                    webEngine.load(url);
                }
            });
        }
        
        // habrá que definir la combo como propiedad de la clase Brower
        final ComboBox comboBox = new ComboBox();
        
        //En el constructor de la clase Browser damos formato al combobox y lo
        //incluimos en la toolbar
        comboBox.setPrefWidth(60);        
        
        //también el constructor de la clase Browser declaramos el manejador
        //del histórico
        final WebHistory history = webEngine.getHistory();
        
        // load the web page
        webEngine.load("http://www.ieslosmontecillos.es/wp/");
        
        // create the toolbar
        toolBar = new HBox();
        toolBar.setAlignment(Pos.CENTER);
        // Esto va a funcionar al no cargar el Style Sheet(?) '._.
        toolBar.getStyleClass().add("browser-toolbar");
        toolBar.getChildren().add(comboBox);
        toolBar.getChildren().addAll(hpls);
        toolBar.getChildren().add(createSpacer());

        //set action for the button
        showPrevDoc.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                webEngine.executeScript("toggleDisplay('PrevRel')");
            }
        });
        
        smallView.setPrefSize(120, 80);
        
        //handle popup windows
        webEngine.setCreatePopupHandler(
            new Callback<PopupFeatures, WebEngine>() {
                @Override public WebEngine call(PopupFeatures config) {
                    smallView.setFontScale(0.8);
                    if (!toolBar.getChildren().contains(smallView)) {
                        toolBar.getChildren().add(smallView);
                    }
                    return smallView.getEngine();
                }
            }
        );
        
        webEngine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<Worker.State>(){
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, 
                    Worker.State oldValue, Worker.State newValue) {
                    toolBar.getChildren().remove(showPrevDoc);
                    
                    if (newValue == Worker.State.SUCCEEDED){
                        // Lamamos a la interfaz JavaFX desde Script.
                        // Iimportacion Correcta?
                        JSObject win =
                        (JSObject) webEngine.executeScript("window");
                        win.setMember("app", new JavaApp());

                        if (needDocumentationButton){
                            toolBar.getChildren().add(showPrevDoc);
                        }
                    }
            }                
            });
        
        history.getEntries().addListener(new
            ListChangeListener<WebHistory.Entry>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends WebHistory.Entry> c) {
                c.next();
                
                for (WebHistory.Entry e : c.getRemoved()) {
                    comboBox.getItems().remove(e.getUrl());
                }
                
                for (WebHistory.Entry e : c.getAddedSubList()) {
                    comboBox.getItems().add(e.getUrl());
                }
            }
        });
        
        //Se define el comportamiento del combobox
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                int offset =
                comboBox.getSelectionModel().getSelectedIndex() - history.getCurrentIndex();
                history.go(offset);
            }
        });
        
        // load the web page
        webEngine.load("http://www.ieslosmontecillos.es/wp/");
        
        //add components
        getChildren().add(toolBar);
        getChildren().add(browser);             
    }
    
    // JavaScript interface object
    public class JavaApp {
        public void exit() {
            Platform.exit();
        }
    }
        
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
    
    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        double tbHeight = toolBar.prefHeight(w);
        layoutInArea(browser,0,0,w,h-tbHeight,0, HPos.CENTER, VPos.CENTER);
        layoutInArea(toolBar,0,h-
        tbHeight,w,tbHeight,0,HPos.CENTER,VPos.CENTER);

    }
    
    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }
    
    @Override
    protected double computePrefHeight(double width) {
        return 500;
    }
}
