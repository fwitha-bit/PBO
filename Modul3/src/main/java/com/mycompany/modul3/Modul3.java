/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.modul3;

/**
 *
 * @author ASUS
 */
public class Modul3 {
    public static void main(String[] args) {
        
        Pro pr1 = new Pro();
        Pro pr2 = new Pro();

        
        pr1.setNama("Teknik Informatika");
        pr2.setNama("Data Science");

        
        MHS mhs1 = new MHS();
        MHS mhs2 = new MHS();

        
        mhs1.setNama("Bruce Wayne");
        mhs1.setPro(pr1);

        mhs2.setNama("Tony Stark");
        mhs2.setPro(pr2);

        
        mhs1.displayMahasiswa();
        System.out.println("----------------------");
        mhs2.displayMahasiswa();
    } 
}

