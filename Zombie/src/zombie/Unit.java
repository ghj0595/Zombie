package zombie;

abstract public class Unit implements Attackable {
	private final int END = 0;

	protected String name;
	protected int hp;
	protected int power;
	protected int position;

	public Unit(String name, int hp, int power, int position) {
		this.name = name;
		this.hp = hp;
		this.power = power;
		this.position = position;
	}

	protected void deth() {
		if (this.hp <= END) {
			this.hp = END;
			String message = String.format("[%s가 죽었습니다.]", this.name);
			System.out.println(message);
		}
	}

}
