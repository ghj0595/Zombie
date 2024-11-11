package zombie;

abstract public class Unit implements Attackable {
	protected int hp;
	protected int power;
	protected int position;
	
	public Unit(int hp, int power, int position) {
		this.hp = hp;
		this.power = power;
		this.position = position;
	}

}
