package spookyfy.project;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Customer {
    private String customerCode;
    private String name;
    private String membershipStatus;

    public Customer(String customerCode, String name, String membershipStatus) {
        this.customerCode = customerCode;
        this.name = name;
        this.membershipStatus = membershipStatus;
    }

    public void upgradeMembership(String newStatus) {
        this.membershipStatus = newStatus;
        System.out.println(name + "'s membership status upgraded to " + newStatus);
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(String membershipStatus) {
        this.membershipStatus = membershipStatus;
    }
    
    public void ambilListLagu() throws IOException {
        
    }

    public void play(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
    }

    public void ambilplayList() {
        
    }

    public void playPlaylist(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

    }
}
