/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modul6;

/**
 *
 * @author ASUS
 */
public class User {
    protected String username;
    protected Song[] playlist;

    public User(String username) {
        this.username = (username == null || username.isEmpty()) ? "anonymous" : username;
        this.playlist = null;
    }

    public void setPlaylist(Song[] arrSong) {
        if (arrSong == null || arrSong.length == 0) {
            System.out.println("play list kosong");
            this.playlist = null;
        } else {
            this.playlist = arrSong;
        }
    }

    public Song[] getPlaylist() {
        return playlist;
    }

    public void playPlaylist() {
        if (playlist == null || playlist.length == 0) {
            System.out.println("play list kosong");
            return;
        }

        for (Song song : playlist) {
            System.out.println("Lagu iklan selingan karena akun gratisan");
            System.out.println(song.playMusic());
            System.out.println();
        }
    }

    // Getter
    public String getUsername() { return username; }
}
