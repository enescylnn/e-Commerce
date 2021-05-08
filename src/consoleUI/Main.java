package consoleUI;

import java.util.Scanner;

import business.abstracts.UserService;
import business.concretes.AuthManager;
import business.concretes.UserManager;
import business.concretes.VerificationManager;
import core.Services.LoginWithGmailAdapter;
import core.Services.VertificationMailSender;
import dataAccess.concretes.UserInMemoryDao;
import entities.concretes.User;

public class Main {
	static boolean loggedIn;
    static Scanner scan = new Scanner(System.in);
    static UserService userService = new UserManager(new UserInMemoryDao());
    static AuthManager authManager = new AuthManager(userService,new VerificationManager(new VertificationMailSender()),new LoginWithGmailAdapter());
	public static void main(String[] args) {
		
		String mainMessage = "\t******  Welcome To Wafture  ******\n\n"+
				"1- Login\n"+
				"2- Login with Google\n"+
				"3- Register\n"+
				"4- Log out\n"+
				"0- Show main message\n\n"+
				"\t\tRequirements\n\n"+
				"1- E-mail address must be in the correct format ==> test@test.com\n"+
				"2- Your password must be at least 6 characters long\n"+
				"3- Name and surname must be longer than 2 characters";
		
		String loggedMessage = "\t******  Welcome To Wafture  ******\n\n"+
		"1- Delete my account\n"+
		"2- Log out\n"+
		"0- Show the message\n"+
		"***********************************";
		
		/*User user = new User(1,"Enes","Ceylan","test@test.com","12345");
		authManager.register(user);
		authManager.login(user);*/
		
		System.out.println(mainMessage);
        int key;
        while (true){
            System.out.print("Please select action: ");
            key = scan.nextInt();
            switch (key){
                case 1:
                    login();
                    break;
                case 2:
                    loginWithGoogle();
                    break;
                case 3:
                    register();
                    break;
                case 4:
                    return;
                case 0:
                    System.out.println(mainMessage);
                    break;
                default:
                    System.out.println("Make the right choice.");
            }
            System.out.println(loggedMessage);
            loggedloop:
            while (loggedIn){
                System.out.print("Please select action: ");
                key = scan.nextInt();
                switch (key){
                    case 1:
                        deleteAccount();
                        break;
                    case 2:
                        System.out.println(mainMessage);
                        loggedIn = false;
                        break;
                    case 0:
                        System.out.println(loggedMessage);
                        break;
                    default:
                        System.out.println("Make the right choice.");
                }
            }
        }
    }

    public static void register(){
        scan.nextLine();
        String firstName,lastName,mail,password;
        System.out.print("Enter your name: ");
        firstName = scan.nextLine();
        System.out.print("Enter your surname: ");
        lastName = scan.nextLine();
        System.out.print("Enter your e-mail: ");
        mail = scan.nextLine();
        System.out.print("Enter your password: ");
        password = scan.nextLine();
        User user = new User(UserInMemoryDao.getLastId() + 1,firstName,lastName,mail,password);
        loggedIn = authManager.register(user);
    }

    public static void login(){
        scan.nextLine();
        String mail,password;
        System.out.print("Enter your e-mail: ");
        mail = scan.nextLine();
        System.out.print("Enter your password: ");
        password = scan.nextLine();
        User user = new User(mail,password);
        loggedIn = authManager.login(user);
    }

    public static void loginWithGoogle(){
        scan.nextLine();
        String mail,password;
        System.out.print("Enter your e-mail: ");
        mail = scan.nextLine();
        System.out.print("Enter your password: ");
        password = scan.nextLine();
        User user = new User(mail,password);
        loggedIn = authManager.loginWithGoogle(user);
    }

    public static void deleteAccount(){
        System.out.println("The system is not yet available!");
    }
}
