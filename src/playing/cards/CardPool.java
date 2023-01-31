package playing.cards;

public class CardPool {
	private int[][] field;
	private static int row1 = 10;
	private static int column1 = 10;
	private static int row2 = 10;
	private static int column2 = 10;

	public CardPool() { //多次元配列に初期値を生成するコンストラクタ
		boolean numCheck = false;
		while (numCheck == false) {
			System.out.println("マス目の数を選んでください 例:2を入力すると→4枚カードが生成されます");
			System.out.println("2,4,6,8から選んでください");
			System.out.println("入力 ↓");
			String inputCheck = new java.util.Scanner(System.in).nextLine();
			numCheck = this.numCheck(inputCheck);
			if (numCheck) {
				int n = Integer.parseInt(inputCheck);
				field = new int[n][n];
				for (int x = 0; x < this.field.length; x++) {
					int z = 1;
					for (int y = 0; y < this.field[x].length; y++) {
						this.field[x][y] = z;
						++z;
					}
				}
			} else {
				System.out.println("入力が不正です");
			}
		}
	}

	public int[][] getField() {
		return field;
	}

	public void setField(int[][] field) {
		this.field = field;
	}

	public int getRow1() {
		return row1;
	}

	public void setRow1(int row1) {
		CardPool.row1 = row1;
	}

	public int getColumn1() {
		return column1;
	}

	public void setColumn1(int column1) {
		CardPool.column1 = column1;
	}

	public int getRow2() {
		return row2;
	}

	public void setRow2(int row2) {
		CardPool.row2 = row2;
	}

	public int getColumn2() {
		return column2;
	}

	public void setColumn2(int column2) {
		CardPool.column2 = column2;
	}

	public void display() { // カードの状況を見せる
		for (int i = 0; i < field.length; i++) {
			if (i == 0) {
				System.out.print("    " + i);
			} else {
				System.out.print("  " + i);
			}
		}
		System.out.print("←列番号");
		System.out.println();
		for (int i = 0; i < field.length; i++) {
			if (i == 0) {
				System.out.print("_____");
			} else {
				System.out.print("___");
			}
		}
		System.out.println("");
		for (int j = 0; j < field.length; j++) {
			System.out.print(" " + j + "|");
			for (int k = 0; k < field.length; k++) {
				if (this.getRow1() == j && this.getColumn1() == k) {
					System.out.print(this.field[j][k] + "  ");
				} else if (this.getRow2() == j && this.getColumn2() == k) {
					System.out.print(this.field[j][k] + "  ");
				} else if (this.field[j][k] >= 1 ) {
					System.out.print("■ ");
				} else {
					System.out.print("□ ");
				}
			}
			System.out.println("");
		}
		System.out.println("↑行番号");
	}

	public void shuffleCheck() {// 入れ替わったかチェック
		for (int[] check : this.field) {
			for (int value : check) {
				System.out.print(value);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public void random() { // 配列の中身をシャッフル
		for (int i = 0; i < 50; i++) {
			int r1 = new java.util.Random().nextInt(this.field.length);
			int c1 = new java.util.Random().nextInt(this.field[0].length);//入れ替え元の添え字をランダム生成
			int r2 = new java.util.Random().nextInt(this.field.length);
			int c2 = new java.util.Random().nextInt(this.field[0].length);//入れ替え先の添え字をランダム生成
			int esc = this.field[r1][c1]; //入れ替え元の値を一旦エスケープ
			this.field[r1][c1] = this.field[r2][c2];//代入
			this.field[r2][c2] = esc;//入れ替え完了
		}
	}

	public boolean numCheck(String input) {// マス目のチェック
		return input.matches("[2,２,4,４,6,６,8,８]");
	}

}
