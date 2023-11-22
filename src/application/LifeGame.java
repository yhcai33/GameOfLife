package application;

public class LifeGame extends Thread {
	private int weight;
	private int height;
	private boolean[][] cells;

	LifeGame() {
		this.height = 200;
		this.weight = 200;
		cells = new boolean[height][weight];
	}

	LifeGame(int h, int w) {
		this.height = h;
		this.weight = w;
		cells = new boolean[height][weight];
	}

	public void run(boolean isStoped) {
		while (!isStoped) {
			this.update();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void update() {
		boolean next[][] = new boolean[this.height][this.weight];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < weight; j++) {
				next[i][j] = isLive(i, j);
			}
		}
		cells = next;
	}

	boolean isLive(int ith, int jth) {
		int count = 0;
		for (int i = ith - 1; i <= ith + 1; i++) {
			for (int j = jth - 1; j <= jth + 1; j++) {
				if (i < 0 || i > height - 1 || j < 0 || j > weight - 1 || (i == ith && j == jth))
					continue;
				if (cells[i][j]) {
					count++;
				}
			}
		}
		if (cells[ith][jth]) {
			if (count > 3 || count < 2) {
				return false;
			}
			return true;
		} else {
			if (count == 3) {
				return true;
			}
		}
		return false;
	}
	
	public void randomSeed() {
		for(int i=0;i<height;i++) {
			for(int j=0;j<weight;j++) {
				if(Math.random()>0.5) {
					this.cells[i][j]=!this.cells[i][j];
				}
			}
		}
	}
	public String toString() {		
		StringBuilder sb = new StringBuilder();
		for (boolean[] a : this.getCells()) {
			for (boolean b : a) {
				sb.append(b ? "1" : "0");
			}
			sb.append("\n");
		}
		return sb.toString();		
	}
	public String blocks() {		
		StringBuilder sb = new StringBuilder();
		for (boolean[] a : this.getCells()) {
			for (boolean b : a) {
				sb.append(b ? "□" : "■");
			}
			sb.append("\n");
		}
		return sb.toString();		
	}
	

	public void setCell(int i, int j, boolean b) {
		cells[i][j] = b;
	}

	public boolean[][] getCells() {
		return cells;
	}

	public boolean getCell(int i, int j) {
		return cells[i][j];

	}

}
