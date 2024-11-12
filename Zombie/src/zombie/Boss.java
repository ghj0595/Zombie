package zombie;

import java.util.Random;

public class Boss extends Zombie {
	private final int HIDDEN = 3;
	private final int SIZE = 5;

	private Random random = new Random();

	public Boss(String name, int hp, int power, int position) {
		super(name, hp, power, position);
	}

	@Override
	public void attack(Unit unit) {
		super.attack(unit);
		int skil = random.nextInt(5);

		if (skil == HIDDEN) {
			unit.hp -= unit.hp / 3;
			System.out.println("BOSS의 히든 스킬 발동!");
		}
	}

	public void hide(int number) {
		int location = random.nextInt(SIZE);

		if (number == location) {
			System.out.println("BOSS를 찾았다! 급소타격!!");
			this.hp -= this.hp / 2;
			System.out.println("공격이 효과적이였다!");
			System.out.printf("%s HP : [%d/%d]\n", this.name, this.hp, this.MAX_HP);
		} else {
			this.hp += 200;
			System.out.println("BOSS를 찾지 못했습니다...");
			System.out.println("BOSS가 HP를 회복합니다.");
			System.out.printf("%s HP : [%d/%d]\n", this.name, this.hp, this.MAX_HP);
		}
	}
}
