/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prak10;

/**
 *
 * @author ASUS
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Prak10 {
    private String nim;
    private String nama;
    private LocalDate tanggalLahir;
    private double ipk;

   
    public void setNim(String nim) throws Exception {
        if (nim == null || nim.trim().isEmpty()) {
            throw new Exception("NIM tidak boleh kosong");
        }
        if (nim.length() != 12) {
            throw new Exception("NIM harus 12 digit");
        }
        if (!nim.matches("\\d{12}")) {
            throw new Exception("NIM harus semua angka");
        }
        this.nim = nim;
    }

    public void setNama(String nama) throws Exception {
        if (nama.trim().isEmpty()) {
            throw new Exception("Nama tidak boleh kosong");
        }
        if (nama.length() < 8) {
            throw new Exception("Nama minimal 8 karakter");
        }
        this.nama = nama;
    }

   
    public void setTanggalLahir(String tanggal) throws Exception {
        if (tanggal == null || !tanggal.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new Exception("Format tanggal harus YYYY-MM-dd");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(tanggal, formatter);
            this.tanggalLahir = LocalDate.parse(tanggal, formatter);
        } catch (DateTimeParseException e) { //parse itu memecah data string buat dialanisis bjir 
            throw new Exception("Tanggal lahir tidak valid");
        }
    }


    public void setIpk(double ipk) throws Exception {
        if (ipk < 0.00 || ipk > 4.00) {
            throw new Exception("IPK antara 0.00-4.00");
        }
        this.ipk = ipk;
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public LocalDate getTanggalLahir() { return tanggalLahir; }
    public double getIpk() { return ipk; }
}
