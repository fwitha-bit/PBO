/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prak9;



public class Prak9 {
    public static void main(String[] args) {
        
        BangunDatar[] bangunDatar = new BangunDatar[3];

        Lingkaran lingkaran = new Lingkaran(2);
        lingkaran.setNama("Piter Perpan");  

        PersegiPanjang persegiPanjang = new PersegiPanjang(18, 27);
        persegiPanjang.setNama("Kawasaki Sesaki");  

        Segitiga segitiga = new Segitiga(30, 36);
        segitiga.setNama("Bulet");  
                
        bangunDatar[0] = lingkaran;
        bangunDatar[1] = persegiPanjang;
        bangunDatar[2] = segitiga;

        
        System.out.println("Masukkan jumlah bangun datar: 3");
        System.out.println("1. Lingkaran");
        System.out.println("2. Persegi Panjang");
        System.out.println("3. Segitiga Sama Kaki");
        System.out.println();

        
        for (int i = 0; i < bangunDatar.length; i++) {
            BangunDatar bd = bangunDatar[i];
            if (bd instanceof Lingkaran) {
                Lingkaran l = (Lingkaran) bd;
                System.out.println("Nama BangunDatar: " + l.getNama());
                System.out.printf("Jari-jari: %.0f%n", (double) l.getJariJari());  
                System.out.printf("Luas: %.1f%n", l.hitungLuas());
                System.out.printf("Keliling: %.1f%n", l.hitungKeliling());
            } else if (bd instanceof PersegiPanjang) {
                PersegiPanjang pp = (PersegiPanjang) bd;
                System.out.println("Nama BangunDatar: " + pp.getNama());
                System.out.printf("Panjang: %d%n", pp.getPanjang());
                System.out.printf("Lebar: %d%n", pp.getLebar());
                System.out.printf("Luas: %.1f%n", pp.hitungLuas());
                System.out.printf("Keliling: %.1f%n", pp.hitungKeliling());
            } else if (bd instanceof Segitiga) {
                Segitiga s = (Segitiga) bd;
                System.out.println("Nama BangunDatar: " + s.getNama());
                System.out.printf("Alas: %d%n", s.getAlas());
                System.out.printf("Tinggi: %d%n", s.getTinggi());
                System.out.printf("Luas: %.1f%n", s.hitungLuas());
                System.out.printf("Keliling: %.1f%n", s.hitungKeliling());
            }
            System.out.println();  
        }
    }
}
