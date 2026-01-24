/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prak10;

/**
 *
 * @author ASUS
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Prak10 mhs = new Prak10();

        System.out.println("Validator Mahasiswa");
        System.out.println();

        
        while (true) {
            System.out.print("NIM : ");
            String nim = sc.nextLine();
            try {
                mhs.setNim(nim);
                System.out.println("NIM valid");
                System.out.println();
                break;
            } catch (Exception e) {
                System.out.println("NIM : " + e.getMessage());
            }
        }

       
        while (true) {
            System.out.print("Nama : ");
            String nama = sc.nextLine();
            try {
                mhs.setNama(nama);
                System.out.println("Nama valid");
                System.out.println();
                break;
            } catch (Exception e) {
                System.out.println("Nama : " + e.getMessage());
            }
        }

      
        while (true) {
            System.out.print("Tanggal Lahir : ");
            String tgl = sc.nextLine();
            try {
                mhs.setTanggalLahir(tgl);
                System.out.println("Tanggal lahir valid");
                System.out.println();
                break;
            } catch (Exception e) {
                System.out.println("Tanggal lahir : " + e.getMessage());
            }
        }

        
        while (true) {
            System.out.print("IPK : ");
            String inputIpk = sc.nextLine();
            try {
                double ipk = Double.parseDouble(inputIpk);
                mhs.setIpk(ipk);
                System.out.println("IPK valid");
                break;
            } catch (NumberFormatException e) {
                System.out.println("IPK harus angka");
            } catch (Exception e) {
                System.out.println("IPK : " + e.getMessage());
            }
        }

        sc.close();
    }
}
