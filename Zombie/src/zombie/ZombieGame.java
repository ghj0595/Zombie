package zombie;

import java.util.Random;
import java.util.Scanner;

public class ZombieGame {
	private final int RECOVERY = 1;
	private final int END = 0;
	private Scanner scanner = new Scanner(System.in);
	private Random random = new Random();
	
	private static ZombieGame instance = new ZombieGame();
	
	public static ZombieGame getInstance() {
		return instance;
	}
	
	private Hero hero;
	private Zombie zombie;
	private Boss boss;
	private boolean fight;
	private boolean isRun = true;
	
	public void run() {
		setGame();
		while(true){		
			play();
		}
	}
	private void setGame() {
		String name = input("Hero 이름 입력");
		hero = new Hero(name, 150, 10, 1);
		
		zombie = new Zombie("Zombie", 50, 10, 5);
		boss = new Boss("BIG BOSS", 500, 50, 10);
	}
	
	private void play() {
		hero.position ++;
		
		if(hero.position == zombie.position) {
			System.out.println("좀비와 마주쳤습니다!");			
			while(!fight) {
				fightZombie();
				if(hero.hp == END) {
					isRun = false;
					System.out.println("GAME OVER 영웅이 죽었습니다...");
				}
			}
		}
	}
	
	private void fightZombie() {
		hero.attack(zombie);
		
		if(zombie.death()) {
			fight = true;
			return;
		}
		printUnitHp(zombie);
		
		zombie.attack(hero);
		printUnitHp(hero);
		
		int number = random.nextInt(2);
		if(number == RECOVERY) {
			hero.healing();
		}
	}
	
	private void printUnitHp(Unit unit) {
		System.out.printf("%s HP : [%d/%d]\n", unit.name, unit.hp, unit.MAX_HP);
	}
	
	private String input(String message) {
		System.out.println(message + " : ");
		String name = scanner.nextLine();
		return name;
	}
	
	


}
