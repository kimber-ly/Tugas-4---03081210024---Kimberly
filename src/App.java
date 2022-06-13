import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Nasabah> nasabah = new ArrayList<Nasabah>();
        ArrayList<String> logmutasi = new ArrayList<String>();
        //app banking
        //tambah nasabah
        //setor uang
        //cetak mutasi
        //transfer uang
        //cetak nasabah
        //keluar
        Scanner input = new Scanner(System.in);
        init(nasabah);
        String yn = "y";
        do{
            menu();
            int pilihan = 0;
            pilihan = input.nextInt();
            if(pilihan == 1){ //tmbh data nasabah
                String NoRek;
                String NamaNsb;
                long Saldo = 0;

                System.out.print("Nama Nasabah\t:\t");
                NamaNsb = input.next();
                System.out.print("Nomor Rekening\t:\t");
                NoRek = input.next();
                System.out.print("Saldo Awal\t:\t");
                Saldo = input.nextLong();

                nasabah.add(new Nasabah(NoRek, NamaNsb, Saldo));

            }else if(pilihan == 2){ //setor uang
                String NoRek;
                long setoran = 0;

                System.out.print("Nomor Rekening\t:\t");
                NoRek = input.next();
                System.out.print("Nominal setoran (IDR)\t:\t");
                setoran = input.nextLong();
                //cek apakah nomor rekening terdaftar? jika ya tambahkan saldonya.
                // jika tidak maka tampilkan pesan nomor rekening tidak ditemukan.
                // if(nasabah.contains(new Nasabah(NoRek))){ //cek 
                //     System.out.println("Nomor rekening ditemukan.");
                // }else{
                //     System.out.println("Nomor rekening tidak ditemukan.");
                // }
                int i = 0;
                for (Nasabah nasabah2 : nasabah) {
                    if(nasabah2.getNoRek().equals(NoRek)){
                        // System.out.println("Nomor rekening ditemukan.");
                        // System.out.println(nasabah2);
                        Nasabah tmpnsb = nasabah2;
                        tmpnsb.setSaldo(tmpnsb.getSaldo() + setoran);
                        nasabah.set(i, tmpnsb);
                        System.out.println("Setoran telah berhasil.");
                        logmutasi.add("Setor uang " + setoran + " ke rekening " + tmpnsb.getNoRek() + " " + tmpnsb.getNamaNsb());
                    }
                    i++;
                }

            }else if(pilihan == 3){ //cetak mutasi
                for (String string : logmutasi) {
                    System.out.println(string);
                }
            }else if(pilihan == 4){ // transfer uang
                String NoRek2, NoRek3;
                long transfer;
                int i = 0;
                Nasabah temp, temp2;

                System.out.print("Masukkan nomor rekening Anda\t:\t");
                NoRek2 = input.next();

                for (Nasabah nasabah4 : nasabah) {
                    if(nasabah4.getNoRek().equals(NoRek2)){
                        System.out.println("Akun tersedia AN " + nasabah4.getNamaNsb());
                        temp = nasabah4;
                    System.out.print("Masukkan nomor rekening tujuan\t:\t");
                    NoRek3 = input.next();
                    for (Nasabah nasabah5 : nasabah) {
                        if(nasabah5.getNoRek().equals(NoRek3)){
                            System.out.println("Akun tujuan tersedia AN " + nasabah5.getNamaNsb());
                            temp2 = nasabah5;
                            System.out.print("Masukkan nominal transfer\t:\t");
                            transfer = input.nextLong();
                            for (Nasabah nasabah6 : nasabah) {
                                if(transfer > nasabah6.getSaldo()){
                                    System.out.println("Saldo tidak mencukupi");
                                }
                            }
                            temp.setSaldo(temp.getSaldo() - transfer);
                            nasabah.set(i, temp);

                            temp2.setSaldo(temp2.getSaldo() + transfer);
                            nasabah.set(i, temp2);

                            System.out.println("Transfer telah berhasil");
                            logmutasi.add("Transfer uang " + transfer + " ke rekening " + temp2.getNoRek() + " AN " + temp2.getNamaNsb());
                        }
                        
                        
                    }
                    }
                    
                    i++;
                }
            }
                // cek nomor rekening pertama terdaftar
                // cek nomor rekening kedua terdaftar jg? jika ya maka kita bisa memindahkan uang
                // dari rekening 1 ke rekening 2
                //simpan log transfer
            else if(pilihan == 5){ //cetak nasabah
                cetakNasabah(nasabah);
            }else if(pilihan == 6){ //keluar
                break;
            }else{
                continue;
            }
            System.out.print("Apakah Anda ingin kembali ke menu utama? (y/n): ");
            yn = input.next();
        }while(yn.equalsIgnoreCase("y"));
        input.close();
    }

        //scanner
        //nasabah.add inputan yg diterima dlm string
        // cetakNasabah(nasabah);
        // nsb3.setNamaNsb("Kimberly");
        // nasabah.set(2, nsb3);
        // cetakNasabah(nasabah);

        // hapusNasabah(nasabah, 1);
        // cetakNasabah(nasabah);

        // hapusNasabah(nasabah, nsb1);
        // cetakNasabah(nasabah);
    public static ArrayList<Nasabah> init(ArrayList<Nasabah> nasabah){
        Nasabah nsb1 = new Nasabah("0214578", "Jian", 500000);
        nasabah.add(nsb1);

        Nasabah nsb2 = new Nasabah("0214571", "Gilbert", 500000);
        nasabah.add(nsb2);

        Nasabah nsb3 = new Nasabah("0214572", "Kimberly", 500000);
        nasabah.add(nsb3);
        nasabah.add(new Nasabah("0214573", "Wilbert", 500000));
        return nasabah;
    }

    public static void menu(){
        System.out.println("Aplikasi Banking");
        System.out.println("1. Tambah Nasabah Baru");
        System.out.println("2. Setor Uang");
        System.out.println("3. Cetak Mutasi");
        System.out.println("4. Transfer Uang");
        System.out.println("5. Cetak Data Nasabah");
        System.out.println("6. Keluar");
        System.out.print("Masukkan pilihan: ");
    }

    public static void hapusNasabah(ArrayList<Nasabah> nasabah, int idx){
        nasabah.remove(idx);
    }
    public static void hapusNasabah(ArrayList<Nasabah> nasabah, Nasabah nsb){
        nasabah.remove(nsb);
    }

    public static void cetakNasabah(ArrayList<Nasabah> nasabah){
        System.out.println("No.Rekening\tNama\t\tSaldo\tNo.Kartu\tPIN\tTanggal Daftar");
        for (Nasabah nsbh : nasabah) {
            System.out.println(nsbh); 
        }
    }
}
