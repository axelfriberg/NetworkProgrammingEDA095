package lab3;

public class MailReader extends Thread{
	private Mailbox mailbox;
	
	public MailReader(Mailbox mailbox){
		this.mailbox = mailbox;
	}
	
	@Override
	public void run(){
		while(true){
			String message = mailbox.getMessage();	
			System.out.println(message);
			
			try {
				sleep((long) Math.random());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
