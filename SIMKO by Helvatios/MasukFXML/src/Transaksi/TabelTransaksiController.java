/* TabelView,SimpanBuka XML, Exception Handling,OOP,percabangan, perulangan - 21523244

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package Transaksi;

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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import masukData.Masuk;
import masukData.MasukInterface;

/**
 * FXML Controller class
 *
 * @author TabelView,SimpanBuka XML, Exception Handling,OOP,percabangan, perulangan - 21523244
 */
public class TabelTransaksiController implements Initializable {

    ObservableList<Data> bismillah = observableArrayList();

    @FXML
    private Label label;
    @FXML
    private Label laDp;
    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfNominal;
    @FXML
    private TextField tfKet;
    @FXML
    private TextField tfFilter;
    @FXML
    private DatePicker dp;
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
// TableView - 21523244    
    @FXML
    private void PendapatanAction(ActionEvent event) {
// Exception Handling - 21523244
    try {
        String p = "Pendapatan";
        String Temp1 = tfNama.getText();
        int Temp2 = Integer.parseInt(tfNominal.getText());
        String Temp3 = tfKet.getText();
        String tanggal = dp.getValue().toString();
// percabangan     =============================================================   
        if (!Temp1.equals("")&&Temp2 != 0&&!Temp3.equals("")&&!tanggal.equals("")){
// OOP - 21523244            
            bismillah.add(new Data(Temp1, Temp2, tanggal, Temp3, p));
            System.out.println("Transaksi Berhasil Ditambahkan!");

// Simpan XML===================================================================
        String xml = xstream.toXML(bismillah);
        FileOutputStream coba = null;
        try {
            coba = new FileOutputStream("CobaDataLaporan.xml");
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
//Perulangan ================================================================== 
            int total = 0;
            for (int i =0;i<bismillah.size();i++){
                total = total + bismillah.get(i).getNom();
            }
            label.setText("Rp. "+total);
//=============================================================================            
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Data Masukan Tidak Valid!!!");
            Optional<ButtonType> result = alert.showAndWait();
            System.out.println("Data Masukan Tidak Valid!!!");
        }
//=============================================================================        
    }catch(Exception e){
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Data Masukan Tidak Valid!!!");
            Optional<ButtonType> result = alert.showAndWait();
        System.out.println("Data Masukan Tidak Valid!!!");
    }
//=============================================================================    
        tfNama.setText("");
        tfNominal.setText("");
        tfKet.setText("");
        dp.setValue(null);
        

    }
    
       @FXML
    private void PengeluaranAction(ActionEvent event) {
// Exception Handling - 21523244 ============================================== 
    try {
        String p = "Pengeluaran";
        String Temp1 = tfNama.getText();
        int Temp2 = Integer.parseInt(tfNominal.getText());
        String Temp3 = tfKet.getText();
        String tanggal = dp.getValue().toString();
// percabangan==================================================================        
        if (!Temp1.equals("")&&Temp2 != 0&&!Temp3.equals("")&&!tanggal.equals("")){
// OOP - 21523244            
            bismillah.add(new Data(Temp1, -Temp2, tanggal, Temp3, p));
            System.out.println("Transaksi Berhasil Ditambahkan!");

// Simpan XML ==================================================================
            String xml = xstream.toXML(bismillah);
            FileOutputStream coba = null;
            try {
                coba = new FileOutputStream("CobaDataLaporan.xml");
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
// Perulangan =================================================================
            int total = 0;
            for (int i =0;i<bismillah.size();i++){
                total = total + bismillah.get(i).getNom();
            }
            label.setText("Rp. "+total);
//=============================================================================   
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Data Masukan Tidak Valid!!!");
            Optional<ButtonType> result = alert.showAndWait();
            System.out.println("Data Masukan Tidak Valid!!!");
        }
//=============================================================================        
    }catch(Exception e){
        System.out.println("Data Masukan Tidak Valid!!!");
    }
//=============================================================================  
        tfNama.setText("");
        tfNominal.setText("");
        tfKet.setText("");
        dp.setValue(null);
        
    }

    @FXML
    private void HapusRow(ActionEvent event) {
         
         String Temp1 = table.getSelectionModel().getSelectedItem().getN();
         int Temp2 = table.getSelectionModel().getSelectedItem().getNom();
         String Temp3 = table.getSelectionModel().getSelectedItem().getK();
         String tanggal = table.getSelectionModel().getSelectedItem().getT();
         String Temp4 = table.getSelectionModel().getSelectedItem().getJ();
//=============================================================================
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hapus Data");
        alert.setHeaderText("Anda yakin ingin menghapus data ini?");
        alert.setContentText("Tekan Cancel jika tidak jadi");
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancel);
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            bismillah.remove(new Data(Temp1, Temp2, tanggal, Temp3, Temp4));
            table.getItems().remove(table.getSelectionModel().getSelectedItem());
        }else if(result.get()==ButtonType.CANCEL){
            System.out.println("Cancel");
        }else{
            System.out.println("Close");
        }
         

// Simpan XML =================================================================
            String xml = xstream.toXML(bismillah);
            FileOutputStream coba = null;
            try {
                coba = new FileOutputStream("CobaDataLaporan.xml");
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
//Perulangan===================================================================            
     int total = 0;
     for (int i =0;i<bismillah.size();i++){
         total = total + bismillah.get(i).getNom();
     }
     label.setText("Rp. "+total);
//=============================================================================
     System.out.println("Data Berhasil Dihapus!");
        
    }
    
    @FXML
    private void select(MouseEvent event) {
     table.getSelectionModel().getSelectedItem();
     System.out.println(table.getSelectionModel().getSelectedItem().getN());
     System.out.println(table.getSelectionModel().getSelectedItem().getNom());
     System.out.println(table.getSelectionModel().getSelectedItem().getT());
     System.out.println(table.getSelectionModel().getSelectedItem().getK());
     System.out.println(table.getSelectionModel().getSelectedItem().getJ());
     System.out.println();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OpenXML();
//=============================================================================
//Perulangan
        int total = 0;
        for (int i =0;i<bismillah.size();i++){
           total = total + bismillah.get(i).getNom();
        }
        label.setText("Rp. "+total);
//=============================================================================
        tcNama.setCellValueFactory(new PropertyValueFactory<Data, String> ("N"));
        tcNominal.setCellValueFactory(new PropertyValueFactory<Data, Integer> ("Nom"));
        tcTanggal.setCellValueFactory(new PropertyValueFactory<Data, String> ("T"));
        tcKet.setCellValueFactory(new PropertyValueFactory<Data, String> ("K"));
        tcJenis.setCellValueFactory(new PropertyValueFactory<Data, String> ("J"));
        
        table.setItems(bismillah);
        
       
    
    }  
    
}