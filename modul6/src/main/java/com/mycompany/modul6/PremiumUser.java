/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modul6;

/**
 *
 * @author ASUS
 */
public class PremiumUser extends User {
    private int repeatCount;

    public PremiumUser(String username, int repeatCount) {
        super(username);
        this.repeatCount = repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    @Override
    public void playPlaylist() {
        if (playlist == null || playlist.length == 0) {
            System.out.println("play list kosong");
            return;
        }

        System.out.println("Semua lagu diputar tanpa iklan");
        System.out.println();

        int totalSeconds = 0;

        for (Song song : playlist) {
            System.out.println(song.playMusic(repeatCount));
            System.out.println();
            totalSeconds += song.getDurationSeconds() * repeatCount;
        }

       
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        
        System.out.printf("Jumlah durasi lagu yang telah dimainkan: %d:%02d:%02d%n", 
                         hours, minutes, seconds);
    }
}
