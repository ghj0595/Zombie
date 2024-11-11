package zombie;

public class ZombieGame {
	private static ZombieGame instance = new ZombieGame();
	
	public static ZombieGame getInstance() {
		return instance;
	}
	
	public void run() {
		while(true){
			play();
		}
	}
	
	private void play() {
		
	}


}
