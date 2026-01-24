/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.modul6;

public class Modul6 {
    public static void modul6 (String[] args) {
        
        User user = new User("asikinmang123");
        
        PremiumUser premiumUser = new PremiumUser("aduhpusing27", 2);

        System.out.println("=== Pengguna User ===");
        System.out.printf("User: username = \"%s\"%n", user.getUsername());

        
        Song[] list1 = {
            new Song("Andrea Bocelli", "Time To Say Goodbye", 434),
            new Song("Kana-Boon", "Silhouette", 265),
            new Song("NIKI", "High School in Jakarta", 241),
            new Song("Lucy Rose", "Shiver", 218),
            new Song("Oasis", "Wonderwall", 280),
            new Song("Ally Kerr", "The Sore Feet Song", 189)
        };

     
        user.setPlaylist(list1);
        user.playPlaylist();

        System.out.println();
        System.out.println("=== Pengguna Premium ===");
        System.out.printf("PremiumUser: username = \"%s\", repeatCount = %d%n", 
                         premiumUser.getUsername(), premiumUser.getRepeatCount());

        
        Song[] list2 = {
            new Song("Led Zeppelin", "How Many More Times", 509),
            new Song("Hans Zimmer", "Interstellar Main Theme", 785)
        };

        
        premiumUser.setPlaylist(list2);
        premiumUser.playPlaylist();
    }
}
