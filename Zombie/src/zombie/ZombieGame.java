package zombie;

import java.util.Random;
import java.util.Scanner;

public class ZombieGame {
	private final int END = 0;
	private Scanner scanner = new Scanner(System.in);
	private Random random = new Random();

	private static ZombieGame instance = new ZombieGame();

	public static ZombieGame getInstance() {
		return instance;
	}

	private Zombie zombie;
	private Hero hero;
	private Boss boss;
	private double warnig;
	private boolean fight;
	private boolean isRun = true;

	public void run() {
		setGame();
		while (isRun) {
			play();			
		}
	}

	private void setGame() {
		String name = input("Hero 이름 입력");
		hero = new Hero(name, 200, 10, 1);
		warnig = hero.MAX_HP * 0.7;

		zombie = new Zombie("Zombie", 50, 10, 5);
		boss = new Boss("BIG BOSS", 500, 30, 10);
	}

	private void play() {
		hero.position++;

		if (hero.position == zombie.position) {
			System.out.println("좀비와 마주쳤습니다!");
			encounterZombie(zombie);
		}

		if (hero.position == boss.position) {
			System.out.println("보스 등장!!!");
			encounterZombie(boss);
		}
	}

	private void encounterZombie(Unit unit) {
		fight = true;
		while (fight) {
			fightZombie(unit);
			if (heroDeath()) {
				break;
			}
			recoveryHeroHp();
		}
	}

	private boolean heroDeath() {
		if (hero.hp <= END) {
			hero.hp = END;
			isRun = false;
			System.out.println("GAME OVER 영웅이 죽었습니다...");
			return true;
		}
		return false;
	}

	private void fightZombie(Unit unit) {
		hero.attack(unit);

		if (unit.death()) {
			fight = false;
			return;
		}
		printUnitHp(unit);

		unit.attack(hero);
		printUnitHp(hero);
		
		slow();
	}

	private void recoveryHeroHp() {
		if (hero.hp < warnig) {
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
	
	private void slow() {
		try {
			Thread.sleep(600);
		} catch (Exception e) {		
		}
	}

}
