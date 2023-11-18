/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package masukData;

/**
 *
 * @author Abstraksi (Intrface)-21523207
 */
public interface MasukInterface {
    
    public void setRegistrasi(String EmailReg,String PasswordUtamaReg, 
            String PasswordPengurusReg,String NamaOr,String AlamatOr);
    public void setDataMasuk (String EmailReg,String PasswordPengurusReg);
    public String getEmailReg();
    public String getPasswordUtamaReg();
    public String getPasswordPengurusReg();
    public String getNamaOr();
    public String getAlamatOr();
    public void setEmailReg(String EmailReg);
    public void setPasswordUtamaReg(String PasswordUtamaReg);
    public void setPasswordPengurusReg(String PasswordPengurusReg);
    public void setNamaOr(String NamaOr);
    public void setAlamatOr(String AlamatOr);
    
}
