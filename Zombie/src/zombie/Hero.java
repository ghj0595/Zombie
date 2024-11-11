package zombie;

import java.util.Random;

public class Hero extends Unit {
	private final int EMPTY = 0;
	private final int LUCK = 3;
	private final int BASIC = 5;
	private final int RECOVERY = 10;

	private Random random = new Random();

	private int potion;

	public Hero(String name, int hp, int power, int position) {
		super(name, hp, power, position);
		this.potion = BASIC;
	}

	@Override
	public void attack(Unit unit) {
		int number = random.nextInt(5);

		if (number == LUCK) {
			unit.hp -= (this.power * 2);
			System.out.println("크리티컬!!!");
		} else {
			unit.hp -= this.power;
			System.out.printf("%s의 공격이 %s에게 적중했다!\n", this.name, unit.name);
		}
	}

	public void healing() {
		if (potion == EMPTY) {
			System.out.println("포션이 부족합니다.");
			return;
		}

		if (this.hp == MAX_HP) {
			System.out.println("HP가 가득 찼습니다.");
		} else if (this.hp < MAX_HP) {
			this.hp += RECOVERY;
			if (this.hp > MAX_HP) {
				this.hp = MAX_HP;
			}
			System.out.printf("%s가 포션을 사용해 HP를 회복했습니다. [%d/%d]\n", this.name, this.hp, this.MAX_HP);
			potion--;
		}
	}

}
