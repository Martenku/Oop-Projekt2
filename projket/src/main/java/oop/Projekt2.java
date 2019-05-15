/**Projekt2
 * Aine: Oop
 * Authors: Marten Kuusmann ja Kaisa Liina Keps
 * 2019:05:16
 *
 */




package oop;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Projekt2 extends Application {

    private String nimi ="";
    private String loosiVõitja ="";
    private Desktop desktop = Desktop.getDesktop();


    @Override
    public void start(Stage peaLava) throws Exception {

        //alus
        Group juur = new Group();
        Scene stseen = new Scene(juur);
        final Canvas lõuend = new Canvas(300, 500);
        FileChooser fileManus = new FileChooser();
        //




        //Zanri Inputbox
        final TextField zanr_otsimine = new TextField();
        zanr_otsimine.setPromptText("Sisesta Zanr");
        zanr_otsimine.setFocusTraversable(false);
        HBox hb = new HBox();
        Label VeaSõnum = new Label();
        VeaSõnum.setTextFill(Color.RED);
        hb.setMargin(zanr_otsimine, new Insets(5,5,5,10));
        hb.setMargin(VeaSõnum, new Insets(10, 20, 20, 10));
        hb.getChildren().addAll(zanr_otsimine, VeaSõnum);
        //Zanri inputbox



        //failisõnum
        Label failisõnum = new Label();
        failisõnum.setTextFill(Color.RED);
        failisõnum.setLayoutX(10);
        failisõnum.setTranslateY(530);
        //



        //Faili nupp
        Button failiNupp = new Button("Lisa fail...");
        failiNupp.setLayoutY(500);
        failiNupp.setLayoutX(10);
        //Faili nupp

        //filmide loendi areen
        TextArea text = new TextArea();
        text.setPrefColumnCount(22);
        text.setPrefRowCount(27);
        text.setWrapText(true);
        text.setLayoutY(40);
        text.setLayoutX(10);
        //filmide loendi areen





        //Zanri kuular
        EventHandler<ActionEvent> sisestatud_zanr = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (zanr_otsimine.getText().matches("^[a-zA-Zä-üÄ-Ü]*$")) {
                        SobivadFilmid Sb = new SobivadFilmid(zanr_otsimine.getText());
                        Sb.sobivFilm();
                        List<String> midagi = Sb.getSoovitud();
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

                    }//else

                } catch (Exception e) {
                    VeaSõnum.setText(e.getMessage());
                }//catch
            }//handle
        };//sisestatud_zanr
        zanr_otsimine.setOnAction(sisestatud_zanr);
        //zanri kuular


        //Faili vastuvõtja
        failiNupp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                File file = fileManus.showOpenDialog(peaLava);
                try {
                    if (file.length() != 0) {
                        String tee = file.getAbsolutePath();
                        if (!file.getName().toLowerCase().endsWith(".txt")) throw new SisendiErind("Vigane fail");
                        failiNupp.setText(file.getName());
                        List<String> osalejad = loeFaili(tee);
                        String võitja = Loos(osalejad);
                        loosiVõitja(võitja);
                        failisõnum.setText("võitja lisati faili: loosiVõitja.txt");
                    }//if
                    else {
                        throw new SisendiErind("tühi fail");
                    }
                }catch (Exception e){
                    failisõnum.setText(e.getMessage());
                }//catch
            }//handle
        });//setOnAction
        //Faili vastuvõtja

        //lava sätted
        peaLava.getIcons().add(new Image("https://www.ut.ee/sites/all/themes/ut_main/img/logo-facebook-turvaalaga-uus.png"));
        peaLava.setTitle("");
        juur.getChildren().addAll(lõuend,hb, failiNupp, text, failisõnum);
        peaLava.setMaxHeight(600);
        peaLava.setMaxWidth(300);
        peaLava.setMinHeight(600);
        peaLava.setMinWidth(300);
        peaLava.setScene(stseen);
        peaLava.show();
    }//

    //Loosime failis olevatest inimestest välja ühe suvalise isiku
    public String Loos(List<String> osalejateList){
        int indeks = (int)(Math.random()*osalejateList.size());
        loosiVõitja = osalejateList.get(indeks);
        return loosiVõitja;
    }//

    // Loosi võitja nimi kirjutatakse faili loosiVõitja.txt
    public void loosiVõitja(String võitja) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("loosiVõitja.txt"), "UTF-8"));
        bw.write(võitja);
        bw.close();
    }//

    //Loeme sisse kasutaja antud faili
    public List<String> loeFaili(String nimi) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nimi), "UTF-8"))) {
            String rida = br.readLine();
            ArrayList<String> osalejad = new ArrayList<>();
            while (rida != null) {
                osalejad.add(rida);
                rida = br.readLine();
            } return osalejad;
        }
    }//

    //Fili avamine
    private void openFile(File file){
        try {
            desktop.open(file);
        }catch (IOException e){
            Logger.getLogger(Projekt2.class.getName()).log(Level.SEVERE, null, e);
        }
    }//

    public static void main(String[] args) {
        launch(args);
    }

}


