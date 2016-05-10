package lab3;

public class MailLauncher {

	public static void main(String[] args) {
		Mailbox mailbox = new Mailbox();
		
		new MailReader(mailbox).start();
		
		for(int i = 0; i < 10; i++){
			new EgoMailerThread(mailbox, "Thread " + i).start();
		}
		
	}
}
