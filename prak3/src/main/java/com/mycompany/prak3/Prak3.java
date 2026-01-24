/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prak3;

/**
 *
 * @author ASUS
 */
class Mhs {

    private String Nama;
    private String NIM;
    
    public String getNama () {
        return this.Nama;
    }
    
    public void setNama() {
        this.Nama = Nama;
    }
    
    public String getNIM(){
        return this.NIM;
    }
    
    public void setNIM() {
        this.NIM = NIM;
    }
}

public class Prak3 {
    public static void main (String[] args) {
        Mhs mhs1 = new Mhs();
        
        mhs1.setNIM("12022300068");
        mhs1.setNama("Freti");
       
        
        
        System.out.println("Nama : " + mhs1.getNama() + " " + "NIM : " +mhs1.getNIM());
       
    }
}
