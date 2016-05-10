package lab3;

public class EgoThread extends Thread {
	private String name;
	
	public EgoThread(String name){
        this.name = name;
    }
	
	@Override
	public void run() {
		for(int i = 0; i < 5; i++){
			System.out.println(name);
			try {
				sleep((long) Math.random());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
