/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
//Multi Scene - 21523147
package HalamanPengurus;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import masukData.Masuk;
import masukData.MasukInterface;
import masukfxml.OpenScene;

/**
 * FXML Controller class
 *
 * @author Multi Scene - 21523147
 */
public class HalamanPengurusController implements Initializable {
    @FXML
    private Button bLogout;
    @FXML
    private BorderPane BP;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;

     MasukInterface a = new Masuk();
     XStream xstream = new XStream (new StaxDriver());
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
    
    @FXML
    private void EditData(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("EditDataDoc");
        BP.setCenter(halaman);   
    }
    
    @FXML
    private void TransaksiAction(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("TabelLaporan");
        BP.setCenter(halaman);   
    }
  
    @FXML
    private void BerandaAction(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("Beranda");
        BP.setCenter(halaman);   
    }
    @FXML
    private void LaporanAction(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("KasDoc_1");
        BP.setCenter(halaman);   
    }
    
     @FXML
    private void CloseAction(ActionEvent event) {
        Stage primaryStage = (Stage) bLogout.getScene().getWindow();
        primaryStage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OpenXML2();
        l1.setText(a.getNamaOr());
        l2.setText(a.getEmailReg());
        l3.setText(a.getAlamatOr());
        
    }    
    
}
