import java.util.HashMap;

class Pelanggan{
    String noPelanggan, nama, PIN;
    double saldo;
    boolean isBlocked;
    int loginAttempts;

    public Pelanggan(String noPelanggan, String nama, String PIN, double saldo) {
        this.noPelanggan = noPelanggan;
        this.nama = nama;
        this.PIN = PIN;
        this.saldo = saldo;
        this.isBlocked = false;
        this.loginAttempts = 0;
    }
    
    public boolean autentifikasi(String inputPIN) {
        if (isBlocked){
            System.out.println("Akun Anda telah diblokir!");
            return false;
        }
        if (this.PIN.equals(inputPIN)) {
            loginAttempts = 0;
            return true;
        } else {
            loginAttempts++;
            if (loginAttempts >= 3) {
                isBlocked = true;
                System.out.println("Akun Anda telah diblokir!");
            }
        return false;
        }
    }
    
    public boolean ceksaldo(double jumlah){
        return (saldo - jumlah) >= 10000;
    }

    public void tambahsaldo(double jumlah){
        saldo += jumlah;
    }

    public void kurangisaldo(double jumlah){
        saldo -= jumlah;
    }
}

class Transaksi {
    static HashMap<String, Pelanggan> database = new HashMap<>();

    public static void inisialisasi() {
        database.put("3800000023", new Pelanggan("3800000023", "Fadel", "2232", 5500000));
        database.put("5600000079", new Pelanggan("5600000079", "Style", "5453", 3500000));
        database.put("7400000028", new Pelanggan("7400000028", "Fang", "9075", 2600000));
    }

    public static double hitungcashback(String nomor, double total){
        String jenis = nomor.substring(0, 2);
        if (total > 1000000){
            return switch (jenis){
                case "38" -> 0.05;
                case "56" -> 0.07;
                case "74" -> 0.10;
                default -> 0;
            };
        }else{
            return switch (jenis){
                case "56" -> 0.02;
                case "7" -> 0.05;
                default -> 0;
            };
        }
    }
}
