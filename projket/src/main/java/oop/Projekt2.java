package oop;

import com.sun.source.tree.IfTree;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Projekt2 extends Application {
    private Desktop desktop = Desktop.getDesktop();
    @Override
    public void start(Stage peaLava) throws Exception {

        Group juur = new Group();
        final Canvas lõuend = new Canvas(300, 500);
        FileChooser fileManus = new FileChooser();

        //Zanri box
        final TextField zanr_otsimine = new TextField();
        zanr_otsimine.setPromptText("Sisesta Zanr");
        zanr_otsimine.setFocusTraversable(false);
        HBox hb = new HBox();
        Label VeaSõnum = new Label();
        VeaSõnum.setTextFill(Color.RED);
        hb.setMargin(zanr_otsimine, new Insets(5,5,5,10));
        hb.setMargin(VeaSõnum, new Insets(10, 20, 20, 10));
        hb.getChildren().addAll(zanr_otsimine, VeaSõnum);
        //Zanri box




        //Faili nupp

        Button Fail_nupp = new Button("Lisa fail...");
        Fail_nupp.setLayoutY(500);
        Fail_nupp.setLayoutX(10);
        //Faili nupp

        //filmide loendi areen
        TextArea text = new TextArea();
        text.setPrefColumnCount(22);
        text.setPrefRowCount(27);
        text.setWrapText(true);
        text.setLayoutY(40);
        text.setLayoutX(10);
        //filmide loendi areen

        //Errori sõnum



        //
 //       String texts = "";
 //       Zanrid uusZ = new Zanrid();
//        uusZ.pealkirjad();
//        List<String> list = uusZ.getPealkirjade_list();
//        int i = 0;
//       for (i=1;i<=list.size()-1;i++) {
//           texts = texts + "" + list.get(i) + "\n";
//        }//for
//        System.out.println(zanr_otsimine.getText());
//        text.setText(texts);
//        filmide_summa.setLayoutX(500);
//        filmide_summa.setLayoutY(570);
        //täidame areeni filmidega


        //Zanri kuular
        EventHandler<ActionEvent> sisestatud_zanr = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (zanr_otsimine.getText().matches("^[a-zA-Zä-üÄ-Ü]*$")) {
                        SobivadFilmid eesel = new SobivadFilmid(zanr_otsimine.getText());
                        eesel.sobivFilm();
                        List<String> midagi = eesel.getSoovitud();
                        String texts = "";
                        int i = 0;
                        for (i = 0; i <= midagi.size() - 1; i++) {
                            texts = texts + "" + midagi.get(i) + "\n";
                        }//for
                        text.setText(texts);
                        VeaSõnum.setText("");
                    }else {
                        text.setText("");
                        throw new SisendiErind("Vigane Sisend!");

                    }

                } catch (Exception e) {
                    VeaSõnum.setText(e.getMessage());

                }
            }//handle
        };//sisestatud_zanr
        zanr_otsimine.setOnAction(sisestatud_zanr);


        lõuend.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });


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
        peaLava.getIcons().add(new Image("https://www.ut.ee/sites/all/themes/ut_main/img/logo-facebook-turvaalaga-uus.png"));
        peaLava.setTitle("");
        juur.getChildren().addAll(lõuend,hb, Fail_nupp, text);
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

class ResizableCanvas extends Canvas {

    @Override
    public double minHeight(double width) {
        return 64;
    }

    @Override
    public double maxHeight(double width) {
        return 500;
    }

    @Override
    public double prefHeight(double width) {
        return minHeight(width);
    }

    @Override
    public double minWidth(double height) {
        return 0;
    }

    @Override
    public double maxWidth(double height) {
        return 300;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
    }
}
