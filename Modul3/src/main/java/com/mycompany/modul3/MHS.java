/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

package com.mycompany.modul3;

/**
 *
 * @author ASUS
 */

class MataKuliah {
    private String kode;
    private String nama;
    private int sks;

    public MataKuliah(String kode, String nama, int sks) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
    }


    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getSks() {
        return sks;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }
}


class AmbilMK {
    private MataKuliah matakuliah;
    private String nilai;
    private String tahunAjaran;

    public AmbilMK(MataKuliah matakuliah, String nilai, String tahunAjaran) {
        this.matakuliah = matakuliah;
        this.nilai = nilai;
        this.tahunAjaran = tahunAjaran;
    }


    public MataKuliah getMatakuliah() {
        return matakuliah;
    }

    public String getNilai() {
        return nilai;
    }

    public String getTahunAjaran() {
        return tahunAjaran;
    }

    public void setMatakuliah(MataKuliah matakuliah) {
        this.matakuliah = matakuliah;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public void setTahunAjaran(String tahunAjaran) {
        this.tahunAjaran = tahunAjaran;
    }
}


class Mahasiswa {
    private String nim;
    private String nama;
    private String kelas;
    private String prodi;
    private List<AmbilMK> ambilMK; 

    public Mahasiswa(String nim, String nama, int noKelas) {
        this.nim = nim;
        this.nama = nama;
        this.ambilMK = new ArrayList<>();
        
        
        this.setNim(nim); 
        this.setKelas(noKelas); 
    }

    
    public void setNim(String nim) { 
        this.nim = nim;
        if (nim != null && nim.length() >= 5) {
            String kodeProdi = nim.substring(0, 5);
            switch (kodeProdi) {
                case "10301":
                    this.prodi = "IF"; // NIM 10301 = "IF"
                    break;
                case "10302":
                    this.prodi = "SE"; // NIM 10302 = "SE"
                    break;
                case "10303":
                    this.prodi = "IT"; // NIM 10303 = "IT"
                    break;
                case "10305":
                    this.prodi = "DS"; // NIM 10305 = "DS"
                    break;
                default:
                    this.prodi = "Unknown";
                    break;
            }
        }
    }

    
    public void setKelas(int noKelas) { 
        // Ambil 2 digit kode angkatan dari NIM (contoh: 103012330004 -> 23)
        // Jika kode angkatan tidak tersedia, kita gunakan asumsi 47 (Kode kampus)
        String kodeAngkatan = "47"; // Default menggunakan 47
        if (nim != null && nim.length() >= 7) {
             kodeAngkatan = nim.substring(5, 7); // Mengambil 23 dari 10301*23*...
        }
        
        String noKelasStr = String.format("%02d", noKelas); // Format 1 -> 01, 12 -> 12
        this.kelas = this.prodi + "-" + kodeAngkatan + "-" + noKelasStr;
    }
    
    // Method untuk menambah MataKuliah ke dalam list ambilMK
    public void tambahMK(AmbilMK mk) { 
        this.ambilMK.add(mk);
    }

    // Method untuk menghitung IPK berdasarkan Tahun Ajaran
    public double hitungIPK(String tahunAjaran) { 
        double totalBobotXSkS = 0.0;
        int totalSkS = 0;

        for (AmbilMK mk : ambilMK) {
            if (mk.getTahunAjaran().equals(tahunAjaran)) {
                double bobot = konversiNilai(mk.getNilai());
                int sks = mk.getMatakuliah().getSks();
                
                totalBobotXSkS += (bobot * sks);
                totalSkS += sks;
            }
        }

        if (totalSkS == 0) {
            return 0.0; 
        }
        
        
        return totalBobotXSkS / totalSkS;
    }

    private double konversiNilai(String nilai) {
        switch (nilai.toUpperCase()) {
            case "A": return 4.0;
            case "AB": return 3.5;
            case "B": return 3.0;
            case "BC": return 2.5;
            case "C": return 2.0;
            case "D": return 1.0;
            case "E": return 0.0;
            default: return 0.0; 
        }
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getKelas() {
        return kelas;
    }

    public String getProdi() {
        return prodi;
    }
    
  
    public void setNama(String nama) { 
        this.nama = nama;
    }
}


public class MHS {
    public static void MHS(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        MataKuliah pbo = new MataKuliah("CII3B4", "Pemrograman Berorientasi Objek", 4);
        MataKuliah kalkulus = new MataKuliah("CII2A3", "Kalkulus", 3);
        MataKuliah dka = new MataKuliah("CII1F4", "Dasar Kecerdasan Artifisial", 4);
        MataKuliah kwu = new MataKuliah("UKI2A2", "Kewirausahaan", 2);

        Mahasiswa mhs1 = new Mahasiswa("103012330004", "Historia", 1);
        Mahasiswa mhs2 = new Mahasiswa("103012310024", "Sasha", 12);
        Mahasiswa mhs3 = new Mahasiswa("103012320012", "Falco", 5);

      
        mhs1.tambahMK(new AmbilMK(pbo, "A", "21/22"));
        mhs1.tambahMK(new AmbilMK(kalkulus, "A", "09/10"));
        mhs1.tambahMK(new AmbilMK(dka, "A", "09/10"));
        mhs1.tambahMK(new AmbilMK(kwu, "A", "21/22"));
        mhs2.tambahMK(new AmbilMK(pbo, "B", "21/22"));
        mhs2.tambahMK(new AmbilMK(kalkulus, "A", "19/20"));
        mhs2.tambahMK(new AmbilMK(dka, "BC", "19/20"));
        mhs2.tambahMK(new AmbilMK(kwu, "AB", "21/22"));     
        mhs3.tambahMK(new AmbilMK(pbo, "AB", "22/23"));
        mhs3.tambahMK(new AmbilMK(kalkulus, "D", "21/22"));
        mhs3.tambahMK(new AmbilMK(dka, "B", "21/22"));
        mhs3.tambahMK(new AmbilMK(kwu, "C", "22/23"));

        
        Mahasiswa[] daftarMahasiswa = {mhs1, mhs2, mhs3};

       
        String inputTahunAjaran;
        
        do {
            System.out.print("Masukkan Tahun Ajaran (ketik q untuk exit program): ");
            inputTahunAjaran = scanner.nextLine().trim();
            
            if (inputTahunAjaran.equalsIgnoreCase("q")) {
                break;
            }

            boolean adaData = false;
            
           
            for (Mahasiswa mhs : daftarMahasiswa) {
                double ipk = mhs.hitungIPK(inputTahunAjaran);
                
                if (ipk > 0.0) {
                   
                    System.out.printf("IPK %s (%s): %.2f%n", mhs.getNama(), mhs.getKelas(), ipk);
                    adaData = true;
                }
            }

            if (!adaData) {
                System.out.println("Tidak ada histori nilai pada Tahun Ajaran ini");
            }
            
            System.out.println();
            
        } while (true); 

        System.out.println("Semoga Selalu diberi kemudahan^^");
        scanner.close();
    }
}

