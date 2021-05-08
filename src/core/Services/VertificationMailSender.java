package core.Services;

import java.util.Random;

public class VertificationMailSender implements MailSender{

	@Override
	public String send(String mail) {
		Random rand = new Random();
		String code = String.valueOf(rand.nextInt(1000));
		System.out.println("Your verification code : " + code);
		return code;
	}

}
