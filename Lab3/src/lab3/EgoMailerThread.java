package lab3;

public class EgoMailerThread extends Thread{
	private Mailbox mailbox;
	private String name;
	
	public EgoMailerThread(Mailbox mailbox, String name){
		this.mailbox = mailbox;
		this.name = name;
	}
	
	@Override
	public void run(){
		for(int i = 0; i < 5; i++){
			mailbox.postMessage(name);
			try {
				sleep((long) Math.random());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
