package spookyfy.project;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class NewMusic extends Music {
	private final File folder = new File("./music");
	private final ArrayList<Music> music = new ArrayList<>();
	private final Scanner input = new Scanner(System.in);

    public NewMusic(String title,String path) {
        super(title,path);
    }

    public void play(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        listMusic(folder);
        Music m = music.get(i-1);
        String s = m.getTitle();
        String d=tipe(s);
        d=m.getPath();
        File file = new File(d);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        System.out.println("R = Reset - Q = Quit - + = add playlist");
        String response="";
        do {
            response = input.next();
            response = response.toUpperCase();
            switch(response) {
                case("R") -> clip.setMicrosecondPosition(0);
                case("Q") -> clip.stop();
                case("+") -> PremiumCustomer.play.add(m);
            }
        }while(!response.equalsIgnoreCase("q"));
    }

    void listMusic(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        listMusic(file); // Panggil rekursif jika itu adalah folder
                    } else {
                        String n = file.getName();
                        String p = file.getPath();
                        String k=getFileExtension(n);
                        if(k.equalsIgnoreCase("wav")) {
                            music.add(new NewMusic(n,p));
                        }
                    }
                }
            }
        }
    }

        @Override
    public void play() {
        // TODO Auto-generated method stub
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

    public void playPlaylist(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Music m = PremiumCustomer.play.get(i-1);
        String s = m.getTitle();
        String d=tipe(s);
        d=m.getPath();
        File file = new File(d);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        System.out.println("R = Reset - Q = Quit - D = Remove From Playlist");
        String response="";
        do {
            response = input.next();
            response = response.toUpperCase();
                switch(response) {
                    case("R") -> clip.setMicrosecondPosition(0);
                    case("Q") -> clip.stop();
                    case("D") -> PremiumCustomer.play.remove(i-1);
            }
        }while(!response.equalsIgnoreCase("q"));
    }
}
