/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prak9;


public abstract class BangunDatar {
    private String nama;

    public BangunDatar() {
        this.nama = "Unnamed";
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public abstract double hitungLuas();
    public abstract double hitungKeliling();
}
