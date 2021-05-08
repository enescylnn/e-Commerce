package business.concretes;

import business.abstracts.AuthService;
import business.abstracts.UserService;
import business.abstracts.VerificationService;
import core.Services.LoginWithGmailService;
import entities.concretes.User;

public class AuthManager implements AuthService {

	private UserService userService;
    private VerificationService verificationService;
    private LoginWithGmailService loginWithGmailService;

    public AuthManager(UserService userService, VerificationService verificationService, LoginWithGmailService loginWithGmailService){
        this.userService = userService;
        this.verificationService = verificationService;
        this.loginWithGmailService = loginWithGmailService;
    }
    @Override
    public boolean login(User user) {
        User userToCheck = this.userService.getByMail(user.getMail());
        if(userToCheck == null){
            System.out.println("Registered user not found");
            return false;
        }
        if(!user.getPassword().equals(userToCheck.getPassword())){
            System.out.println("Email or password is incorrect");
            return false;
        }
        System.out.println("Login successful...");
        return true;
    }

    @Override
    public boolean register(User user) {
    	
        if(!(this.userService.checkMailCorrect(user.getMail()) &&
                this.userService.getByMail(user.getMail()) == null &&
                user.getFirstName().length() >= 2 && user.getLastName().length() >= 2 &&
                user.getPassword().length() >= 6)){
            System.out.println("Login failed...");
            return false;
        }
        System.out.println("Mail sending...");
        if(!verificationService.verificate(user.getMail())){
            System.out.println("Verification code is incorrect!");
            return false;
        }
        System.out.println("Your account has been approved and you registered.");
        this.userService.add(user);
        return true;

    }

    @Override
    public boolean loginWithGoogle(User user) {
        if(!loginWithGmailService.login(user.getMail(),user.getPassword())){
            System.out.println("Google did not confirm your information");
            return false;
        }
        if(this.userService.getByMail(user.getMail()) == null){
            this.userService.add(user);
        }
        System.out.println("Login successful...");
        return true;
    }
}
