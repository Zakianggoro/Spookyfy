package spookyfy.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PremiumCustomer extends Customer implements Mendengarkan {
    int count=1;
    public static ArrayList<Music> play = new ArrayList<>();

    public PremiumCustomer(String customerCode, String name) {
        super(customerCode, name, "premium");
    }
    

    @Override
    public void ambilListLagu() throws IOException {
        this.count=1;
        System.out.println("Daftar Lagu");

        // Cetak header tabel
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("| %-2s | %-30s | %-10s | %-12s | %-15s |\n", "No", "Judul", "Tahun", "Penyanyi", "Genre");
        System.out.println("-------------------------------------------------------------------------------------");

        File folder = new File("./music");
        list(folder);
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private void list(File folder) throws IOException {
        // Periksa apakah path adalah direktor
        if (folder.isDirectory()) {
            // Mendapatkan daftar file dalam folder
            File[] files = folder.listFiles();
            if (files != null) {
                // Loop melalui setiap file dalam folder
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Rekursi: jika file adalah folder, panggil method list() lagi
                        list(file);
                    } else {
                        String filename = file.getName();
                        String extension = getFileExtension(filename);
                        filename=tipe(filename);
                        if(extension.equalsIgnoreCase("txt")){
                            String tahun="";
                            String penyanyi="";
                            String judul ="";
                            String genre ="";
                            String text=file.getPath();
                            try (BufferedReader reader = new BufferedReader(new FileReader(text))) {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    String[] data = line.split(",");
                                    judul = data[0];
                                    tahun = data[1];
                                    penyanyi = data[2];
                                    genre = data[3];
                                }
                                // Cetak baris dalam bentuk tabel
                                System.out.printf("| %-2s | %-30s | %-10s | %-12s | %-15s |\n", count, judul, tahun, penyanyi, genre);
                                count++;
                            }
                        }
                    }
                }
            } else {
                System.out.println("Folder kosong atau tidak dapat diakses.");
            }
            
        } else {
            System.out.println("Path " + folder.getPath() + " bukan direktori.");
        }
    }

    private String tipe(String file) {
        int pos = file.lastIndexOf(".");
        String fileName="";
        if (pos > 0) {
            fileName = file.substring(0, pos);
        }
        return fileName;
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }

    @Override
    public void play(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        NewMusic oldschoolMusic = new NewMusic(null,null);
		oldschoolMusic.play(i);
    }

    @Override
    public void ambilplayList() {
        if (!play.isEmpty()) {
            // ANSI escape code untuk warna hijau
            String greenColor = "\033[32m";
            // ANSI escape code untuk mengembalikan warna ke default
            String resetColor = "\033[0m";

            System.out.println(greenColor + "Berikut playlist Anda:" + resetColor);
            // Cetak header tabel
            System.out.println(greenColor + "+-----+--------------------------------+" + resetColor);
            // Cetak baris header untuk tabel
            System.out.printf(greenColor + "| %-3s | %-30s |\n" + resetColor, "No", "Judul Lagu");
            // Cetak garis pembatas antara header dan data
            System.out.println(greenColor + "+-----+--------------------------------+" + resetColor);
            // Cetak setiap lagu dengan nomor urut dan judul
            for (int i = 0; i < play.size(); i++) {
                Music music = play.get(i);
                String title = music.getTitle();
                String formattedTitle = tipe(title); // Misalnya, Anda ingin memformat judul lagu
                // Cetak baris data dalam tabel
                System.out.printf(greenColor + "| %-3d | %-30s |\n" + resetColor, (i + 1), formattedTitle);
            }
            // Cetak garis penutup tabel
            System.out.println(greenColor + "+-----+--------------------------------+" + resetColor);
        } else {
            String resetColor = "\033[0m";
            String greenColor = "\033[32m";
            // Cetak pesan "Playlist Kosong" dalam warna hijau jika playlist kosong
            System.out.println(greenColor + "Playlist Kosong" + resetColor);
        }
    }

    @Override
    public void playPlaylist(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        NewMusic NewMusic = new NewMusic(null,null);
		NewMusic.playPlaylist(i);
    }
}
