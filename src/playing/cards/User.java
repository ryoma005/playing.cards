package playing.cards;


public class User {
	private String name;
	private int count = 0;
	private static CardPool board = new CardPool();
	private static boolean exitConditions = false;

	public User(int i) {
		System.out.println(i + "人目のプレイヤーの名前を入力してください");
		String name = new java.util.Scanner(System.in).nextLine();
		if(name.isEmpty()) {
			this.setName("ゲスト" + i);
		}else {
			this.setName(name);
		}
		User.board.random();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static boolean isExitConditions() {
		return exitConditions;
	}

	public static void setExitConditions(boolean exitConditions) {
		User.exitConditions = exitConditions;
	}

	public void choice() {// ユーザーにカードを選んでもらう
		boolean duplicate = false;
		boolean end = false;
		while (end == false) {
			System.out.println(this.getName() + "さんのターンです\n");
			User.board.display();
			System.out.println("1枚目カードを添え字番号で選んでください :1回目行番号 2回目列番号");
			System.out.println(this.getName() + "さん、選びたいカードの行番号を指定してください");
			System.out.println(User.board.getField().length + "未満の自然数を入力してください\n");
			User.board.setRow1(this.choiceCheck());
			System.out.println(this.getName() + "さん、選びたいカードの列番号を指定してください");
			System.out.println(User.board.getField().length + "未満の自然数を入力してください\n");
			User.board.setColumn1(this.choiceCheck());
			end = this.endNumberCheck(User.board.getRow1(), User.board.getColumn1());
		}
		System.out.println(this.getName() + "さんの選んだ1枚目のカードは"
				+ User.board.getField()[User.board.getRow1()][User.board.getColumn1()]);
		while (duplicate == false || end == false) {
			User.board.display();
			System.out.println("2枚目カードを添え字番号で選んでください :1回目行番号 2回目列番号");
			System.out.println(this.getName() + "さん、選びたいカードの行番号を指定してください");
			System.out.println(User.board.getField().length + "未満の自然数を入力してください\n");
			User.board.setRow2(this.choiceCheck());
			System.out.println(this.getName() + "さん、選びたいカードの列番号を指定してください");
			System.out.println(User.board.getField().length + "未満の自然数を入力してください\n");
			User.board.setColumn2(this.choiceCheck());
			end = this.endNumberCheck(User.board.getRow2(), User.board.getColumn2());
			duplicate = this.duplicateCheck(User.board.getRow1(), User.board.getColumn1(), User.board.getRow2(),
					User.board.getColumn2());
		}
		System.out.println(this.getName() + "さんの2枚目に選んだカードは"
				+ User.board.getField()[User.board.getRow2()][User.board.getColumn2()]);
		User.board.display();
		this.pairCheck(User.board.getRow1(), User.board.getColumn1(), User.board.getRow2(), User.board.getColumn2());
	}

	public int choiceCheck() {// 入力してもらった値を数字か判断しつつ配列の要素数を超えていないかのチェック
		boolean correctInput = false;
		String inputCoordinate = null;
		int a = 0;
		while (!correctInput) {
			inputCoordinate = new java.util.Scanner(System.in).nextLine();
			correctInput = User.inputRegularCheck(inputCoordinate);
			if (!correctInput) {
				System.out.println(User.board.getField().length + "未満の自然数を入力してください\n");
			} else {
				a = Integer.parseInt(inputCoordinate);
				if (this.valueCheck(a) == false) {
					correctInput = false;
				} else {
					break;
				}
			}
		}
		return a;
	}

	public boolean valueCheck(int a) {// 入力された値が配列の要素数を超えていないかのチェック
		if (a < User.board.getField().length) {
			return true;
		} else {
			System.out.println(User.board.getField().length + "未満の自然数を入力してください\n");
			return false;
		}
	}

	public boolean duplicateCheck(int a, int b, int c, int d) {// 一枚目に選んだカードと同じカードを選んでいないかチェック
		if (a == c && b == d) {
			System.out.println("1枚目と同じカードです選びなおしてください\n");
			return false;
		} else {
			return true;
		}
	}

	public void pairCheck(int a, int b, int c, int d) {// 選んだ2枚のカードがペアになってるかチェック
		if (User.board.getField()[a][b] == User.board.getField()[c][d]) {
			System.out.println("成功！ペアになったので場から取り除き、もう一度選択できます\n");
			User.board.getField()[a][b] = 0;
			User.board.getField()[c][d] = 0;
			User.board.setRow1(10);
			User.board.setColumn1(10);
			User.board.setRow2(10);
			User.board.setColumn2(10);
			this.count++;
			System.out.println(this.getName() + "さんの得点は" + this.count);
			this.exitConditionsCheck();
		} else {
			System.out.println("失敗！ペアにならなかったので元に戻して相手のターンに移ります\n");
			User.board.setRow1(10);
			User.board.setColumn1(10);
			User.board.setRow2(10);
			User.board.setColumn2(10);
		}
	}

	public boolean endNumberCheck(int x, int y) {//終了済みのカードを選択していないかチェック
		if (User.board.getField()[x][y] != 0) {
			return true;
		} else {
			System.out.println("既にめくったカードです、もう一度選択してください\n");
			return false;
		}
	}

	public void exitConditionsCheck() {// 終了条件を満たしているかチェック
		int sum = 0;
		for (int[] check : User.board.getField()) {
			for (int value : check) {
				sum += value;
			}
		}
		if (sum == 0) {
			System.out.println("全てのカードがめくられたので終了\n");
			User.setExitConditions(true);
		} else {
			System.out.println("まだカードが残っているので続行もう一度引けます\n");
			this.choice();
		}
	}

	public void winCheck(User u1, User u2) {// 勝利判定
		if (u1.getCount() > u2.getCount()) {
			System.out.println(u1.getName() + "さんの勝ち");
		} else if (u1.getCount() == u2.getCount()) {
			System.out.println("引き分け");
		} else if (u1.getCount() < u2.getCount()) {
			System.out.println(u2.getName() + "さんの勝ち");
		} else {
			System.out.println("エラー");
		}
	}

	public static boolean inputRegularCheck(String c) {// 入力してもらった値が0～9の値か判定
		return c.matches("[0-9]");
	}
}
