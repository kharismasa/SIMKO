/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//Multi Scane - 21523244
package masukfxml;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Multi Scane - 21523244
 */
public class OpenScene {
    private Pane halaman;
    
    public Pane getPane(String fileName){
        try {
            URL fileHalaman = MasukFXML.class.getResource("/masukfxml/"+fileName+".fxml");
            if (fileHalaman == null){
                throw new java.io.FileNotFoundException("Halaman tidak ditemukan");
            }
            halaman = new FXMLLoader().load(fileHalaman);    
        }catch (Exception e){
            System.out.println("Tidak ditemukan halaman tersebut");
        }
        return halaman;
   }  

    private static class MasukFXML {

        public MasukFXML() {
        }
    }
}
