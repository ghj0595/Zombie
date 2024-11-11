package zombie;

abstract class Zombie extends Unit {

	protected final int BLOODSUCKING;

	public Zombie(String name, int hp, int power, int position) {
		super(name, hp, power, position);
		this.BLOODSUCKING = power / 10;
	}

	@Override
	public void attack(Unit unit) {
		unit.hp -= this.power;
		this.hp += this.BLOODSUCKING;
		System.out.printf("%s가 생명력을 회복했습니다.[%d/%d]", this.name, this.hp, this.MAX_HP);
	}

}
