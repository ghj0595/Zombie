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
			System.out.println("BOSS의 히든 스킬 발동!");
		}
	}

}
