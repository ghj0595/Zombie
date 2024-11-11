package zombie;

abstract public class Unit implements Attackable {
	private final int END = 0;
	protected final int MAX_HP;

	protected String name;
	protected int hp;
	protected int power;
	protected int position;

	public Unit(String name, int hp, int power, int position) {
		this.name = name;
		this.hp = hp;
		this.MAX_HP = hp;
		this.power = power;
		this.position = position;
	}

	protected boolean death() {
		if (this.hp <= END) {
			this.hp = END;
			String message = String.format("[%s를 죽였습니다!]", this.name);
			System.out.println(message);
			return true;
		}
		return false;
	}

}
