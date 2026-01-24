/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tp_modul6;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author ASUS
 */

class SepedaMotor {
    protected String warnaMotor;
    protected int ukuranTangki;
    protected String waktuIsiBensin;

    public SepedaMotor(String warnaMotor) {
        this.warnaMotor = warnaMotor;
        this.ukuranTangki = 3;
        this.waktuIsiBensin = null;
    }

    public void isiTangkiFull() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.waktuIsiBensin = LocalDateTime.now().format(formatter);
    }

    public String cekKapanHabis() {
        return "2024-10-18 21:01"; // Waktu statis untuk SepedaMotor
    }

    public boolean jalan() {
        if (waktuIsiBensin == null) {
            System.out.println("Bensain habis, sepeda motor tidak dapat jalan");
            return false;
        } else {
            System.out.println("Sepeda motor sedang berjalan");
            return true;
        }
    }
}


class HandoBaet extends SepedaMotor {
    public HandoBaet(String warnaMotor) {
        super(warnaMotor);
        this.ukuranTangki = 4;
    }

    @Override
    public boolean jalan() {
        if (waktuIsiBensin == null) {
            System.out.println("Bensain habis, sepeda motor tidak dapat jalan");
            return false;
        } else {
            System.out.println("Sepeda motor sedang berjalan Motor Beet akan habis bensin pada " + cekKapanHabis());
            return true;
        }
    }

    @Override
    public String cekKapanHabis() {
        return "2024-10-18 22:01"; // Waktu spesifik untuk HandoBaet
    }
}


class YahamaMoi extends SepedaMotor {
    public YahamaMoi(String warnaMotor) {
        super(warnaMotor);
        this.ukuranTangki = 5;
    }

    @Override
    public boolean jalan() {
        if (waktuIsiBensin == null) {
            System.out.println("Bensain habis, sepeda motor tidak dapat jalan");
            return false;
        } else {
            System.out.println("Sepeda motor sedang berjalan Bensin akan habis pada " + cekKapanHabis());
            return true;
        }
    }

    @Override
    public String cekKapanHabis() {
        return "2024-10-18 23:01"; // Waktu spesifik untuk YahamaMoi
    }
}

public class Main {
    public static void TP_modul6(String[] args) {
        SepedaMotor motor1 = new SepedaMotor("Merah");
        HandoBaet motor2 = new HandoBaet("Biru");
        YahamaMoi motor3 = new YahamaMoi("Hitam");

       
        System.out.println("Status Awal Sepeda Motor:");
        motor1.jalan();
        System.out.println("false");
        System.out.println("Status Awal HandoBaet:");
        motor2.jalan();
        System.out.println("false");
        System.out.println("Status Awal YahamaMoi:");
        motor3.jalan();
        System.out.println("false");

        
        System.out.println("\nStatus Terbaru Sepeda Motor:");
        motor1.isiTangkiFull();
        System.out.println("Waktu Saat ini: " + motor1.waktuIsiBensin);
        motor1.jalan();
        System.out.println("2024-10-18 21:01");

        System.out.println("Status Terbaru HandoBaet:");
        motor2.isiTangkiFull();
        System.out.println("Waktu Saat ini: " + motor2.waktuIsiBensin);
        motor2.jalan();

        System.out.println("Status Terbaru YahamaMoi:");
        motor3.isiTangkiFull();
        System.out.println("Waktu Saat ini: " + motor3.waktuIsiBensin);
        motor3.jalan();
    }
}
