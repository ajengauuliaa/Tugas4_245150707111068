import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Transaksi.inisialisasi();

        while (true){
            System.out.println("\nMasukkan nomor pelanggan (atau ketik 'exit' untuk keluar): ");
            String nomor = scanner.next();
            if (nomor.equalsIgnoreCase("exit")) break;

            if (!Transaksi.database.containsKey(nomor)){
                System.out.println("Nomor pelanggan tidak ditemukan");
                continue;
            }

            Pelanggan pelanggan = Transaksi.database.get(nomor);

            while (true) {
                System.out.println("Masukkan PIN: ");
                String PIN = scanner.next();
                if (pelanggan.autentifikasi(PIN)) break;
                System.out.println("Autentifikasi gagal! Coba lagi.");
            }

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Cek Saldo");
                System.out.println("2. Top-up Saldo");
                System.out.println("3. Pembelian");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu: ");
                int pilihan = scanner.nextInt();

                switch(pilihan){
                    case 1 -> System.out.println("Saldo anda: Rp" + pelanggan.saldo);
                    case 2 -> {
                        System.out.print("Masukkan nominal top-up: ");
                        double jumlah = scanner.nextDouble();
                        pelanggan.tambahsaldo(jumlah);
                        System.out.println("Top-up berhasil! saldo anda: Rp" + pelanggan.saldo);
                    }
                    case 3 -> {
                        System.out.print("Masukkan nominal pembelian: ");
                        double total = scanner.nextDouble();
                        double cashback = Transaksi.hitungcashback(nomor, total);
                        double totaldibayar = total - cashback;
                        
                        if (pelanggan.ceksaldo(totaldibayar)){
                            pelanggan.kurangisaldo(totaldibayar);
                            System.out.println("Pembelian sukses! anda mendapatkan cashback Rp" + cashback);
                            System.out.println("Saldo sekarang: Rp" + pelanggan.saldo);
                        } else {
                            System.out.println("Transaksi gagal! Saldo anda tidak mencukupi!");
                        }
                    }
                    case 4 -> {
                        System.out.println("Logout berhasil!");
                        break;
                    }
                    default -> System.out.println("Pilihan tidak valid!");
                }

                if (pilihan == 4) break;
            }
        }
        System.out.println("Terima kasih tela menggunakan layanan kami!");
        scanner.close();
    }
}
