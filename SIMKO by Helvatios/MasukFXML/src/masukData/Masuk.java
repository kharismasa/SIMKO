/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package masukData;

/**
 *
 * @author Solid (open closed -> Interface), Encapsulation - 21523244
 */
public class Masuk implements MasukInterface{
    
    // data yg diidikan saatRegistrasi
    String EmailReg;
    String PasswordUtamaReg;
    String PasswordPengurusReg;
    String NamaOr;
    String AlamatOr;
    
    @Override
    public void setRegistrasi(String EmailReg, String PasswordUtamaReg, 
            String PasswordPengurusReg, String NamaOr, String AlamatOr) {
       this.EmailReg = EmailReg;
        this.PasswordUtamaReg = PasswordUtamaReg;
        this.PasswordPengurusReg = PasswordPengurusReg;
        this.NamaOr = NamaOr;
        this.AlamatOr = AlamatOr;
    }
    
// Encapsulation - 21523244             
    @Override
    public void setDataMasuk (String EmailReg,String PasswordPengurusReg){
        this.EmailReg = EmailReg;
        this.PasswordPengurusReg = PasswordPengurusReg;
    }
    
    @Override
    public String getEmailReg() {
        return EmailReg;
    }

    @Override
    public String getPasswordUtamaReg() {
        return PasswordUtamaReg;
    }

    @Override
    public String getPasswordPengurusReg() {
        return PasswordPengurusReg;
    }

    @Override
    public String getNamaOr() {
        return NamaOr;
    }

    @Override
    public String getAlamatOr() {
        return AlamatOr;
    }

    @Override
    public void setEmailReg(String EmailReg) {
        this.EmailReg = EmailReg;
    }

    @Override
    public void setPasswordUtamaReg(String PasswordUtamaReg) {
        this.PasswordUtamaReg = PasswordUtamaReg;
    }

    @Override
    public void setPasswordPengurusReg(String PasswordPengurusReg) {
        this.PasswordPengurusReg = PasswordPengurusReg;
    }

    @Override
    public void setNamaOr(String NamaOr) {
        this.NamaOr = NamaOr;
    }

    @Override
    public void setAlamatOr(String AlamatOr) {
        this.AlamatOr = AlamatOr;
    }


    
}
