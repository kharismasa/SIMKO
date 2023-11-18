/* 

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package LaporanKeuanganController;

import TransaksiData.Data;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Buka XML,Perulangan- 21523207
 */
public class LaporanController implements Initializable {

    ObservableList<Data> bismillah = observableArrayList();

    @FXML
    private Label label;
    @FXML
    private TextField tfFilter;
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> tcNama;
    @FXML
    private TableColumn<Data, Integer>  tcNominal;
    @FXML
    private TableColumn<Data, String>  tcTanggal;
    @FXML
    private TableColumn<Data, String>  tcKet;
    @FXML
    private TableColumn<Data, String>  tcJenis;
    @FXML
    private LineChart <String, Number> lc;
    @FXML
    private DatePicker dp;
    
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
//=============================================================================    

    @FXML
    private void select(MouseEvent event) {
     table.getSelectionModel().getSelectedItem();
     System.out.println(table.getSelectionModel().getSelectedItem().getN());
     System.out.println(table.getSelectionModel().getSelectedItem().getNom());
     System.out.println(table.getSelectionModel().getSelectedItem().getT());
     System.out.println(table.getSelectionModel().getSelectedItem().getK());
     System.out.println(table.getSelectionModel().getSelectedItem().getJ());
     System.out.println();
//=============================================================================
    }
    
        //LineChrt- 21523244 ========================================================================
    @FXML
    private void ChartAction(ActionEvent event) {
        dp.setValue(LocalDate.of(dp.getValue().getYear(), dp.getValue().getMonthValue(), 1));
        int bulanMasukan = dp.getValue().getMonthValue();
        int tahunMasukan = dp.getValue().getYear();

        lc.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<String,Number>();
        
        Comparator <Data> comparatorAsc = (prod1,prod2)-> prod1.getT().compareTo(prod2.getT());
        FXCollections.sort(bismillah, comparatorAsc);
        
        for (int j = 0; j<bismillah.size();j++){
            
            LocalDate d = LocalDate.parse(bismillah.get(j).getT());
            int bulanData = d.getMonthValue();
            int tahunData = d.getYear();
            if(bismillah.get(j).getNom()>0 && bulanMasukan == bulanData && 
                tahunMasukan == tahunData){
                String t = String.valueOf(d.getDayOfMonth());
                int nom = bismillah.get(j).getNom();
                series.getData().add(new XYChart.Data<String, Number>(t,nom));
            }
            if (bismillah.get(j).getNom()<0 && bulanMasukan == bulanData &&
                    tahunMasukan == tahunData){
                String T = String.valueOf(d.getDayOfMonth());
                int Nom = bismillah.get(j).getNom();
                series2.getData().add(new XYChart.Data<String, Number>(T,-Nom));
            }
        
        }
            
    series.setName("Pendapatan");   
    series2.setName("Pengeluaran");

    lc.getData().addAll(series,series2);
    
    }

  // Buka XML,Perulangan,TabelView-21523244
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OpenXML();
//=============================================================================
//Perulangan - 21523244
        int total = 0;
        for (int i =0;i<bismillah.size();i++){
           total = total + bismillah.get(i).getNom();
        }
        label.setText("Rp. "+total);
//TabelView - 21523244 =========================================================
        tcNama.setCellValueFactory(new PropertyValueFactory<Data, String> ("N"));
        tcNominal.setCellValueFactory(new PropertyValueFactory<Data, Integer> ("Nom"));
        tcTanggal.setCellValueFactory(new PropertyValueFactory<Data, String> ("T"));
        tcKet.setCellValueFactory(new PropertyValueFactory<Data, String> ("K"));
        tcJenis.setCellValueFactory(new PropertyValueFactory<Data, String> ("J"));
        
        table.setItems(bismillah);
        
        FilteredList<Data> filteredData = new FilteredList<>(bismillah, b-> true);
        tfFilter.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(data -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (data.getN().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (data.getK().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (data.getT().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (data.getJ().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (String.valueOf(data.getNom()).indexOf(lowerCaseFilter) != -1)
                    return true;
                    else
                        return false;
            });      
        });
        
        SortedList<Data> sortedData = new SortedList<> (filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
            
//    lc.getData();
    
    }  
    
}