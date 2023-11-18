/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Beranda;

import TransaksiData.Data;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import masukData.Masuk;
import masukData.MasukInterface;

/**
 * FXML Controller class
 *
 * @author Buka XML, perulangan,Percabangan,LineChart - 21523244
 */
public class BerandaController implements Initializable {

    ObservableList<Data> bismillah = observableArrayList();
    MasukInterface a = new Masuk();

    @FXML
    private Label lnama;
    @FXML
    private Label label;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private LineChart <String, Number> lc;
  
    
// Buka XML - 21523244 ======================================================
    XStream xstream = new XStream (new StaxDriver());
    void OpenXML () {
    
        FileInputStream cobi = null;
        try {
            cobi = new FileInputStream("CobaDataLaporan.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya ="";
            while ((isi = cobi.read()) != -1) {
                charnya= (char) isi;
                stringnya = stringnya + charnya;
            }    
            bismillah = (ObservableList) xstream.fromXML(stringnya);	  
            }
        catch (Exception e){
            System.err.println("test: "+e.getMessage());
        }
        finally{
            if(cobi != null){
                try{
                    cobi.close();
                }
                catch (IOException e) {
                        e.printStackTrace();
                }
            }      
        } 
        System.out.println(bismillah);
    }
    void OpenXML2 () {
    
        FileInputStream cobi = null;
        try {
            cobi = new FileInputStream("sementara.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya ="";
            while ((isi = cobi.read()) != -1) {
                charnya= (char) isi;
                stringnya = stringnya + charnya;
            }    
            a = (MasukInterface) xstream.fromXML(stringnya);	  
            }
        catch (Exception e){
            System.err.println("test: "+e.getMessage());
        }
        finally{
            if(cobi != null){
                try{
                    cobi.close();
                }
                catch (IOException e) {
                        e.printStackTrace();
                }
            }      
        } 
        System.out.println(a);
    }
        
    //Buka XML, perulangan,Percabangan - 21523207
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OpenXML2();
        OpenXML();
//=============================================================================
//Perulangan & Percabangan - 21523207
        int total = 0;
        for (int i =0;i<bismillah.size();i++){
           total = total + bismillah.get(i).getNom();
        }
        label.setText("Rp. "+total);
        
        int totalPen = 0;
        for (int n =0;n<bismillah.size();n++){
            if(bismillah.get(n).getNom()>0){
                totalPen = totalPen + bismillah.get(n).getNom();
            }
        }
        label2.setText("Rp. "+totalPen);
        
        int totalPeng = 0;
        for (int m =0;m<bismillah.size();m++){
            if(bismillah.get(m).getNom()<0){
                totalPeng = totalPeng + bismillah.get(m).getNom();
            }
        }
        label3.setText("Rp. "+totalPeng);
        
        lnama.setText("Selamat Datang "+a.getNamaOr());

//    linechart - 21523244
        int Year = LocalDate.now().getYear();
        int Month = LocalDate.now().getMonthValue();
        LocalDate.of(Year, Month, 1);
        
        Comparator <Data> comparatorAsc = (prod1,prod2)-> prod1.getT().compareTo(prod2.getT());
        FXCollections.sort(bismillah, comparatorAsc);
//        
        lc.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<String,Number>();
    
        for (int j = 0; j<bismillah.size();j++){
            
            LocalDate d = LocalDate.parse(bismillah.get(j).getT());
            int bulanData = d.getMonthValue();
            int tahunData = d.getYear();
            if(bismillah.get(j).getNom()>0 && Month == bulanData && 
                Year == tahunData){
                String t = String.valueOf(d.getDayOfMonth());
                int nom = bismillah.get(j).getNom();
                series.getData().add(new XYChart.Data<String, Number>(t,nom));
            }
            else if (bismillah.get(j).getNom()<0 && Month == bulanData && 
                Year == tahunData){
                String T = String.valueOf(d.getDayOfMonth());
                int Nom = bismillah.get(j).getNom();
                series2.getData().add(new XYChart.Data<String, Number>(T,-Nom));
            }
        }
    series.setName("Pendapatan");   
    series2.setName("Pengeluaran");
    
    lc.getData().addAll(series,series2);
    

    }  
    
}