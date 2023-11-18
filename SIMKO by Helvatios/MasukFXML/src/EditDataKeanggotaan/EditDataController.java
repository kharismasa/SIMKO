/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
//TableView-21523244
package EditDataKeanggotaan;

import TransaksiData.Data;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import masukData.Masuk;
import masukData.MasukInterface;

/**
 *
 * @author TableView-21523244
 */
public class EditDataController implements Initializable {

    ObservableList<ModelTabel> oblist1 = observableArrayList();
    
    XStream xstream = new XStream (new StaxDriver());
    
    @FXML
    private TableView <ModelTabel>tabel;
    @FXML
    private TableColumn <ModelTabel, String>namaCol;
    @FXML
    private TableColumn<ModelTabel, String> jabatanCol;
    @FXML
    private TableColumn<ModelTabel, String>nohpCol;
    @FXML
    private TableColumn<ModelTabel, String> alamatCol;
    @FXML
    private TextField tfNo;
    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfJabatan;
    @FXML
    private TextField tfNohp;
    @FXML
    private TextField tfAlamat;
    

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
    
    
    @FXML
    private void TambahAction(ActionEvent event) {
        String Temp1 = tfNama.getText();
        String Temp2 = tfJabatan.getText();
        String Temp3 = tfNohp.getText();
        String Temp4 = tfAlamat.getText();
// OOP - 21523244
        oblist1.add(new ModelTabel(Temp1,Temp2,Temp3,Temp4));
        tfNama.setText("");
        tfJabatan.setText("");
        tfNohp.setText("");
        tfAlamat.setText("");  
        tfNo.setText("");  
    }
    
        @FXML
    private void select(MouseEvent event) {
        tfNo.setText(String.valueOf(tabel.getSelectionModel().getSelectedIndex()));
        tfNama.setText(tabel.getSelectionModel().getSelectedItem().getNama());
        tfJabatan.setText(tabel.getSelectionModel().getSelectedItem().getJabatan());
        tfNohp.setText(tabel.getSelectionModel().getSelectedItem().getNohp());
        tfAlamat.setText(tabel.getSelectionModel().getSelectedItem().getAlamat());
    }
    
        @FXML
    private void EditAction(ActionEvent event) {
        String Temp1 = tfNama.getText();
        String Temp2 = tfJabatan.getText();
        String Temp3 = tfNohp.getText();
        String Temp4 = tfAlamat.getText();
        oblist1.set(tabel.getSelectionModel().getSelectedIndex(),new ModelTabel(Temp1,Temp2,Temp3,Temp4));
        tfNama.setText("");
        tfJabatan.setText("");
        tfNohp.setText("");
        tfAlamat.setText(""); 
        tfNo.setText(""); 
        
    }
    
     @FXML
    private void rowClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hapus Data");
        alert.setHeaderText("Anda yakin ingin menghapus data ini?");
        alert.setContentText("Tekan Cancel jika tidak jadi");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancel);
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            tabel.getItems().removeAll(tabel.getSelectionModel().getSelectedItem());
        }else if(result.get()==ButtonType.CANCEL){
            System.out.println("Cancel");
        }else{
            System.out.println("Close");
        }
         
         tfNama.setText("");
        tfJabatan.setText("");
        tfNohp.setText("");
        tfAlamat.setText(""); 
        tfNo.setText(""); 
    }
// 21523244- Program Fail XML Simpan    
    @FXML
    private void hbSimpan (ActionEvent event) {
        
        String xml = xstream.toXML(oblist1);
 
        FileOutputStream coba = null;
        try {
            coba = new FileOutputStream("datakeanggotaan.xml");
            byte[] bytes = xml.getBytes("UTF-8");
            coba.write(bytes);
        } 
        catch (Exception e) {
            System.err.println("Perhatian: " + e.getMessage());
          } 
        finally {
            if (coba != null) {
                try {
                    coba.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText("Data Tersimpan");
        Optional<ButtonType> result = alert.showAndWait();
    }
   // Buka XML,Tabel view-21523147
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OpenXML();
        
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        jabatanCol.setCellValueFactory(new PropertyValueFactory<>("jabatan"));
        nohpCol.setCellValueFactory(new PropertyValueFactory<>("nohp"));
        alamatCol.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        
        tabel.setItems(oblist1);
        
        
        
    }    
    
}
