package spookyfy.project;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class OldSchoolMusic extends Music {
    private final File folder = new File("./music/old");
    private final ArrayList<Music> music = new ArrayList<>();
    private final Scanner input = new Scanner(System.in);

    public OldSchoolMusic(String title,String path) {
        super(title,path);
        
    }

    public void play(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        listMusic(folder);
        Music m = music.get(i-1);
        String s = m.getTitle();
        String d=tipe(s);
        d="./music/old/"+s+"/"+s;
        File file = new File(d);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        System.out.println("R = Reset - Q = Quit - + = Add to Playlist");
        String response="";
        do {
            response = input.next();
            response = response.toUpperCase();
            switch(response) {
                case("R") -> clip.setMicrosecondPosition(0);
                case("Q") -> clip.stop();
                case("+") -> FreeCustomer.play.add(m);
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
                        String p = file.getName();
                        String n =file.getPath();
                        String k=getFileExtension(p);
                        if(k.equalsIgnoreCase("wav")) {
                            music.add(new OldSchoolMusic(p,n));
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
        Music m = FreeCustomer.play.get(i-1);
        String s = m.getTitle();
        String d=tipe(s);
        d="./music/old/"+s+"/"+s;
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
                    case("D") -> FreeCustomer.play.remove(i-1);
            }
        }while(!response.equalsIgnoreCase("q"));
    }
}
