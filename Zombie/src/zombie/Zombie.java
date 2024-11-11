package zombie;

public class Zombie extends Unit {

	protected final int BLOODSUCKING;

	public Zombie(String name, int hp, int power, int position) {
		super(name, hp, power, position);
		this.BLOODSUCKING = power / 5;
	}

	@Override
	public void attack(Unit unit) {
		unit.hp -= this.power;
		this.hp += this.BLOODSUCKING;
		System.out.printf("%s의 흡혈공격! \n%s HP : [%d/%d]\n", this.name, this.name, this.hp, this.MAX_HP);
	}

}
