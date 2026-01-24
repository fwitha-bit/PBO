import java.util.Scanner;

public class Jurnal2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Masukkan basis : ");
        int basis = sc.nextInt();

        // Input string bilangan
        System.out.print("Masukkan bil: ");
        String bilangan = sc.next();

        int hasil = 0; // hasil konversi ke desimal
        int pangkat = 1; // posisi digit (basis^0, basis^1, ...)

        
        for (int i = bilangan.length() - 1; i >= 0; i--) {
            char ch = bilangan.charAt(i); // ambil 1 karakter
            int nilaiDigit;

            
            if (basis == 2) {
                if (ch == '0' || ch == '1') {
                    nilaiDigit = ch - '0'; // '0'->0, '1'->1
                } else {
                    System.out.println("Error: Digit biner tidak valid!");
                    return;
                }
            }
            // Jika basis heksadesimal
            else if (basis == 16) {
                if (ch >= '0' && ch <= '9') {
                    nilaiDigit = ch - '0'; // '0'-'9' menjadi 0-9
                } else if (ch >= 'A' && ch <= 'F') {
                    nilaiDigit = 10 + (ch - 'A'); // 'A'-'F' menjadi 10-15
                } else if (ch >= 'a' && ch <= 'f') {
                    nilaiDigit = 10 + (ch - 'a'); // support huruf kecil juga
                } else {
                    System.out.println("Error: Digit hex tidak valid!");
                    return;
                }
            } else {
                System.out.println("Basis tidak valid! Harus 2 atau 16.");
                return;
            }

            // Tambahkan ke hasil: digit * pangkat
            hasil += nilaiDigit * pangkat;

            // Update pangkat sesuai basis (basis^posisi)
            pangkat *= basis;
        }

        System.out.println("Hasil konversi: " + hasil);
    }
}
