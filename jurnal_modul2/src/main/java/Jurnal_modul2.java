import java.util.Scanner;

public class Jurnal_modul2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input basis
        System.out.print("Masukkan basis (2 untuk biner, 16 untuk heksadesimal): ");
        int basis = scanner.nextInt();
        
        // Memastikan input sisa line tidak terganggu
        scanner.nextLine(); 

        // Input string bilangan
        System.out.print("Masukkan string bilangan: ");
        String bilangan = scanner.nextLine().toUpperCase();

        long hasilDesimal = 0;
        int pangkat = 0;

        // Proses konversi per karakter
        for (int i = bilangan.length() - 1; i >= 0; i--) {
            char karakter = bilangan.charAt(i);
            int nilaiKarakter;

            // Mengonversi karakter ke nilai integer
            if (basis == 16) {
                if (karakter >= '0' && karakter <= '9') {
                    nilaiKarakter = karakter - '0'; // '0'-'9' menjadi 0-9
                } else if (karakter >= 'A' && karakter <= 'F') {
                    nilaiKarakter = 10 + (karakter - 'A'); // 'A'-'F' menjadi 10-15
                } else if (karakter >= 'a' && karakter <= 'f') {
                    nilaiKarakter = 10 + (karakter - 'a'); // support huruf kecil juga
                } else {
                    System.out.println("Error: Digit hex tidak valid!");
                    return;
                }
            } else if (basis == 2) {
                if (karakter == '0' || karakter == '1') {
                    nilaiKarakter = karakter - '0';
                } else {
                    System.out.println("Input biner tidak valid!");
                    return;
                }
            } else {
                System.out.println("Basis yang dimasukkan tidak valid.");
                return;
            }

            // Menghitung nilai desimal
            hasilDesimal += nilaiKarakter * Math.pow(basis, pangkat);
            pangkat++;
        }

        // Output hasil
        System.out.println("Hasil konversi: " + hasilDesimal);

        scanner.close();
    }
}