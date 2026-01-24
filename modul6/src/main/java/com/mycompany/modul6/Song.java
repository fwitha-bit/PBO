/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modul6;

/**
 *
 * @author ASUS
 */
public class Song {
    private String artist;
    private String title;
    private int durationSeconds;

    public Song(String artist, String title, int durationSeconds) {
        this.artist = artist;
        this.title = title;
        this.durationSeconds = durationSeconds;
    }

    public int getDurationMM() {
        return durationSeconds / 60;
    }

    public int getDurationDD() {
        return durationSeconds % 60;
    }

    public String playMusic() {
        return String.format("Lagu %s oleh %s diputar dengan durasi %d:%02d", 
                           title, artist, getDurationMM(), getDurationDD());
    }

    public String playMusic(int repeatCount) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= repeatCount; i++) {
            result.append(String.format("Pemutaran ke-%d\n", i));
            result.append(playMusic()).append("\n");
        }
        return result.toString().trim();
    }

    // Getters
    public String getArtist() { return artist; }
    public String getTitle() { return title; }
    public int getDurationSeconds() { return durationSeconds; }
}
