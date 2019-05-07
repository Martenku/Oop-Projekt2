package oop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Projekt2 extends Application {
    private Desktop desktop = Desktop.getDesktop();
    @Override
    public void start(Stage peaLava) throws Exception {
        Group juur = new Group();
        Canvas lõuend = new Canvas(800, 600);
        FileChooser fileManus = new FileChooser();

        //Zanri box
        Label zanr = new Label("zanr:");
        TextField zanr_otsimine = new TextField();
        HBox hb = new HBox();
        hb.getChildren().addAll(zanr, zanr_otsimine);
        //Zanri box
        //Faili nupp

        Button Fail_nupp = new Button("Lisa fail...");
        Fail_nupp.setLayoutY(500);
        Fail_nupp.setLayoutX(10);
        //Faili nupp

        //filmide loendi areen
        TextArea text = new TextArea();
        text.setPrefColumnCount(22);
        text.setPrefRowCount(33);
        text.setWrapText(true);
        text.setLayoutY(20);
        text.setLayoutX(500);
        //filmide loendi areen


        //
        String texts = "";
        Zanrid uusZ = new Zanrid();
        uusZ.pealkirjad();
        List<String> list = uusZ.getPealkirjade_list();
        int i = 0;
       for (i=1;i<=list.size()-1;i++) {
           texts = texts + "" + list.get(i) + "\n";
        }//for
        System.out.println(zanr_otsimine.getText());
        text.setText(texts);
        Label filmide_summa = new Label("Filme kokku " + i);
        filmide_summa.setLayoutX(500);
        filmide_summa.setLayoutY(570);
        //täidame areeni filmidega

        //Zanri kuular
        EventHandler<ActionEvent> sisestatud_zanr = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(zanr_otsimine.getText());
            }//handle
        };//sisestatud_zanr
        zanr_otsimine.setOnAction(sisestatud_zanr);


        //Faili vastuvõtja
        Fail_nupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileManus.showOpenDialog(peaLava);
                if (file !=null){
                    Fail_nupp.setText(file.getName());
                    openFile(file);
                }//if
            }//handle
        });//setOnAction
        //Faili vastuvõtja

        juur.getChildren().addAll(lõuend,hb, Fail_nupp, text, filmide_summa);
        Scene stseen = new Scene(juur);
        peaLava.setScene(stseen);
        peaLava.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
    private void openFile(File file){
        try {
            desktop.open(file);
        }catch (IOException e){
            Logger.getLogger(Projekt2.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

