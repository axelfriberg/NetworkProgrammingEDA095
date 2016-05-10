package lab3;

public class Mailbox {
	private String message = null;
	
	public Mailbox(){
		
	}
	
	synchronized public void postMessage(String message){
		while(this.message != null){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.message = message;
		notifyAll();	
	}
	
	synchronized public String getMessage(){
		while(message == null){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		
		String temp = message;
		message = null;
		notifyAll();
		return temp;
	}
}
