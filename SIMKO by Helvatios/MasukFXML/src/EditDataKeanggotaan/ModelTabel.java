/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EditDataKeanggotaan;

/**
 *
 * @author Encapsulation - 21523244
 */
public class ModelTabel {
// Variable & Modifier-21523244
    String nama,jabatan,nohp,alamat;
    
    public ModelTabel(String nama, String jabatan, String nohp, String alamat) {
        
        this.nama = nama;
        this.jabatan = jabatan;
        this.nohp = nohp;
        this.alamat = alamat;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
}
