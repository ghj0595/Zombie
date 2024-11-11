package zombie;

import java.util.Random;

public class Boss extends Zombie {
	private final int HIDDEN = 3;
	
	private Random random = new Random();	

	public Boss(String name, int hp, int power, int position) {
		super(name, hp, power, position);
	}
	
	@Override
	public void attack(Unit unit) {
		super.attack(unit);
		int skil = random.nextInt(5);
		
		if(skil == HIDDEN) {
			unit.hp -= unit.hp / 2;
			System.out.printf("BOSS의 스킬이 적중했습니다! %s[%d/%d]\n", unit.name, unit.hp, unit.MAX_HP);
		}
	}

}
