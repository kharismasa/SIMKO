/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
//TableView, Buka XML-21523244
package DataKeanggotaanLihat;

import EditDataKeanggotaan.ModelTabel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author TableView, Buka XML,Percabangan-21523244
 */
public class DataKeanggotaanController implements Initializable {

    @FXML
    private TableView tabel;
    @FXML
    private TableColumn namaCol;
    @FXML
    private TableColumn jabatanCol;
    @FXML
    private TableColumn nohpCol;
    @FXML
    private TableColumn alamatCol;
    @FXML
    private TextField tfFilter;
    
    ObservableList oblist1 = observableArrayList();

    XStream xstream = new XStream (new StaxDriver());
// Program Fail XML Buka-21523244
    void OpenXML (){
        FileInputStream cobi = null;
        try {
            cobi = new FileInputStream("datakeanggotaan.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya ="";
            while ((isi = cobi.read()) != -1) {
                charnya= (char) isi;
                stringnya = stringnya + charnya;
            }    
            oblist1 = (ObservableList) xstream.fromXML(stringnya);	  
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
        System.out.println(oblist1);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OpenXML();
        
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        jabatanCol.setCellValueFactory(new PropertyValueFactory<>("jabatan"));
        nohpCol.setCellValueFactory(new PropertyValueFactory<>("nohp"));
        alamatCol.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        
        tabel.setItems(oblist1);
        
        FilteredList<ModelTabel> filteredData = new FilteredList<>(oblist1, b-> true);
        tfFilter.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(data -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (data.getNama().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (data.getNohp().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (data.getJabatan().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (data.getAlamat().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                        return false;
                }
            });      
        });
        
        SortedList<ModelTabel> sortedData = new SortedList<> (filteredData);
        sortedData.comparatorProperty().bind(tabel.comparatorProperty());
        tabel.setItems(sortedData);
        
    }    
    
}
