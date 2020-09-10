package stars;

// 추상클래스와 유사하지만 
// 추상 메서드만 존재 가능
public interface Behavior {

	public abstract void move();

	void repair();

	void attack(Behavior unit);

}
