package spookyfy.project;

import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\033[31mWelcome to Spookyfy!\033[0m");
            
            // Ask user to enter customer information
            System.out.println("Enter customer code:");
            String customerCode = scanner.nextLine();
            System.out.println("Enter customer name:");
            String name = scanner.nextLine();
            System.out.println("Choose membership type (free or premium):");
            String membershipStatus = scanner.nextLine();
            
            // Create customer based on input
            Customer customer;
            if (membershipStatus.equalsIgnoreCase("free")) {
                customer = new FreeCustomer(customerCode, name);
            } else if (membershipStatus.equalsIgnoreCase("premium")) {
                customer = new PremiumCustomer(customerCode, name);
            } else {
                System.out.println("Invalid membership type. Defaulting to Free Customer.");
                customer = new FreeCustomer(customerCode, name);
            }
            boolean loop = true;
            
            while (loop) {
                // Display available music options
                // ANSI escape code untuk warna biru
                String blueColor = "\033[34m";
                // ANSI escape code untuk mengembalikan warna ke default
                String resetColor = "\033[0m";
                System.out.println(blueColor + "Available music options:" + resetColor);
                System.out.println(blueColor + "=============================" + resetColor);
                System.out.println(blueColor + "| No. |        Option       |" + resetColor);
                System.out.println(blueColor + "=============================" + resetColor);
                System.out.println(blueColor + "|  1  |   Daftar musik      |" + resetColor);
                System.out.println(blueColor + "|  2  |     Playlist        |" + resetColor);
                System.out.println(blueColor + "|  3  |   Ganti Status      |" + resetColor);
                System.out.println(blueColor + "|  4  |       Keluar        |" + resetColor);
                System.out.println(blueColor + "=============================" + resetColor);
                // Ask user to choose music
                System.out.println("Please choose music option:");
                int musicOption = scanner.nextInt();
                
                switch (musicOption) {
                    case (1) -> {
                        customer.ambilListLagu();
                        System.out.println("Pilih lagu yang ingin diputar (0 untuk kembali ke menu):");
                        int play = scanner.nextInt();
                        if (play == 0) {
                            continue;
                        }
                        customer.play(play);
                    }
                    case(2) -> {
                        customer.ambilplayList();
                        System.out.println("Pilih lagu yang ingin diputar (0 untuk kembali ke menu):");
                        int play = scanner.nextInt();
                        if (play == 0) {
                            continue;
                        }   customer.playPlaylist(play);
                    }
                    case(3) -> {
                        if(customer instanceof FreeCustomer) {
                            customer = new PremiumCustomer(customer.getCustomerCode(), customer.getName());
                            System.out.println("Keanggotaan anda diganti menjadi premium\n");
                        }else if(customer instanceof PremiumCustomer) {
                            customer = new FreeCustomer(customer.getCustomerCode(), customer.getName());
                            System.out.println("Keanggotaan anda diganti menjadi free\n");
                        }
                    }
                    case(4) -> loop=false;
                }
            }
        }
    }
}
