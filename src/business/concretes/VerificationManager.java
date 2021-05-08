package business.concretes;

import java.util.Scanner;

import business.abstracts.VerificationService;
import core.Services.MailSender;

public class VerificationManager implements VerificationService {
	
	private MailSender mailSender;
    public VerificationManager(MailSender mailSender){
        this.mailSender = mailSender;
    }
    @Override
    public boolean verificate(String mail) {
        String code = "";
        try{
            Thread.sleep(1000);
            code = mailSender.send(mail);
        }catch (Exception e){
            System.out.println("ERROR!!!");
        }
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the verification code: ");
        String enteredCode = scan.nextLine();
        if(!code.equals(enteredCode)){
            return false;
        }
        return true;
    }
}
