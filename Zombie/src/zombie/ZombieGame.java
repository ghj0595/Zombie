package zombie;

import java.util.Random;
import java.util.Scanner;

public class ZombieGame {
	private final int END = 0;
	private final int SIZE = 5;
	private final int STRING = 1;
	private final int NUMBER = 2;

	private Scanner scanner = new Scanner(System.in);
	private Random random = new Random();

	private static ZombieGame instance = new ZombieGame();

	public static ZombieGame getInstance() {
		return instance;
	}

	private Zombie zombie;
	private Hero hero;
	private Boss boss;
	private int size = 10;
	private double warnig;
	private boolean fight;
	private boolean isRun = true;
	private int zombieSetCount;
	private int bossHideCount;

	public void run() {
		setGame();
		while (isRun) {
			play();
		}
	}

	private void setGame() {
		String name = (String) input(STRING, "Hero 이름 입력");
		hero = new Hero(name, 200, 20, 1);
		warnig = hero.MAX_HP * 0.7;

		zombie = new Zombie("Zombie", 100, 10, SIZE);
		boss = new Boss("BIG BOSS", 500, 30, 20);
		bossHideCount++;
		System.out.println("GAME START!!");
	}

	private void play() {
		printMap();
		slow(300);

		hero.position++;
		size++;

		if (hero.position == zombie.position) {
			System.out.println("좀비와 마주쳤습니다!");
			encounterZombie(zombie);
		} else if (hero.position == boss.position) {
			System.out.println("보스 등장!!!");
			encounterZombie(boss);
		}
	}

	private void encounterZombie(Unit unit) {
		fight = true;
		while (fight) {
			recoveryHeroHp();
			fightZombie(unit);
			if (heroDeath()) {
				break;
			}
		}
	}

	private boolean heroDeath() {
		if (hero.hp <= END) {
			hero.hp = END;
			isRun = false;
			System.out.println("'GAME OVER' 영웅이 죽었습니다...");
			return true;
		}
		return false;
	}

	private void fightZombie(Unit unit) {
		hero.attack(unit);

		if (unit.death()) {
			if (unit instanceof Boss) {
				isRun = false;
				fight = false;
				System.out.println("GAME CLEAR!!");
				return;
			}

			hero.getPotion();
			fight = false;
			zombieSetCount++;
			if (zombieSetCount < 3) {
				setZombie();
			}
			return;
		}
		printUnitHp(unit);

		if (unit instanceof Boss) {
			if (unit.hp < unit.MAX_HP / 2 && bossHideCount > 0) {
				hideBoss();
			}
		}

		int luckyNumber = random.nextInt(2);
		if (hero.dodge(luckyNumber)) {
			return;
		}

		unit.attack(hero);
		printUnitHp(hero);

		slow(700);
	}

	private void setZombie() {
		zombie = new Zombie("Zombie", 100, 10, SIZE * (zombieSetCount + 1));
	}

	private void hideBoss() {
		System.out.println("BOSS가 땅 속으로 숨었습니다!");
		System.out.println("회복하지 못하게 BOSS를 찾으세요!");

		printHideBoss();

		int select = (int) input(NUMBER, "번호 선택") - 1;

		if (select < 0 || select >= SIZE) {
			System.out.println("번호를 다시 확인해주세요.");
			return;
		}

		boss.hide(select);
		bossHideCount--;
	}

	private void recoveryHeroHp() {
		if (hero.hp < warnig) {
			hero.healing();
		}
	}

	private void printHideBoss() {
		for (int i = 0; i < SIZE; i++) {
			System.out.print(" ■");
		}
		System.out.println();

		for (int i = 0; i < SIZE; i++) {
			System.out.print(" " + (i + 1));
		}
		System.out.println();
	}

	private void printUnitHp(Unit unit) {
		System.out.printf("%s HP : [%d/%d]\n", unit.name, unit.hp, unit.MAX_HP);
	}

	private void printMap() {
		for (int i = hero.position; i < size; i++) {
			if (i == hero.position) {
				System.out.print("_옷");
			} else if (i == zombie.position) {
				System.out.print("_OTL");
			} else if (i == boss.position) {
				System.out.print("_◀TL");
			} else {
				System.out.print("_");
			}
		}
		System.out.println();
	}

	private Object input(int type, String message) {
		System.out.println(message + " : ");
		String input = "";

		switch (type) {
		case STRING:
			while (input.equals(""))
				input = scanner.nextLine();
			return input;
		case NUMBER:
			int number = 0;
			try {
				input = scanner.nextLine();
				number = Integer.parseInt(input);
				return number;
			} catch (Exception e) {
				System.err.println("숫자를 입력해주세요.");
			}
		default:
			return null;
		}
	}

	private void slow(int speed) {
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
		}
	}

}
