package lab3;

public class EgoThreadLauncher {

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			new EgoThread("Thread " + i).start();
		}
	}

}
