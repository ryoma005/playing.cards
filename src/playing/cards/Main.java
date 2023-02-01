package playing.cards;


public class Main {
	public static void main(String[] args) {
		System.out.println("二人で遊べる数当てゲームみたいなものです");
		User u1 = new User(1);
		User u2 = new User(2);

		while(!User.isExitConditions()) {
			u1.choice();
			if(User.isExitConditions()) {
				break;
			}else {
				u2.choice();
			}
		}
		u1.winCheck(u1, u2);
	}
}
