import java.util.ArrayList;

public class Board {
	private int[][] boardVals = new int[9][9];
	private Square[][] board = new Square[3][3];
	public boolean constant = false;
	public boolean squared = false;
	
	@SuppressWarnings("unchecked")
	public Square getSquare(int r, int c) {
		return board[r][c];
	}
	
	public void putSquare(int r, int c, Square s) {
		board[r][c] = s;
		return;
	}
	
	public int get(int r, int c) {
		return boardVals[r][c];
	}
	
	public void put(int r, int c, int num) {
		boardVals[r][c]=num;
		return;
	}
	
	public void squareOff() {
		if (squared)return;
		int currentRow = 0;
		for (int b = 0;b<3;b++) { //for every row
			int start = 0; //also column num
			int end = 3;
			for (int a = 0;a<3;a++) { //for every square in one row;
				Square s = new Square();
				
				for (;start<end;start++) {
					for (int r = currentRow;r<currentRow+3;r++) {
						s.put(r%3, start%3, boardVals[r][start]);
					}
				}
				
				start = end;
				end+=3;
				board[b][a] = s;
			}
		currentRow+=3;
		}
		squared = true;
	}
	
	public void generateAllPermutations() {
		for (int r = 0;r<3;r++) {
			for (int c = 0;c<3;c++) {
				board[r][c].generatePermutations();
			}
		}
	}
	
	
	public String toString() {
		String boardString = "";
		if (!squared) {
			for (int r = 0;r<9;r++) {
				for (int c = 0;c<9;c++) {
					boardString+=Integer.toString(boardVals[r][c]);
				}
				boardString+="\n";
			}
		}else {
			for (int r = 0;r<3;r++) {
				for(int ir = 0;ir<3;ir++) {
					for (int c = 0;c<3;c++) {
						int[] layer = board[r][c].squareLayer(ir);
						for (int i:layer) {
							boardString+=i+" ";
						}
						
					}
					boardString+="\n";
				}
			}
			
			
			
			
		}
		return boardString;
	}
	
	public boolean validBoard(boolean ignoreZeroes) {
		if (!squared) {
			squareOff();
		}
		for (int r = 0;r<3;r++) {
			for (int ir = 0;ir<3;ir++) {
				int[] numbers = new int[10];
				for (int c = 0;c<3;c++) {
					int[] vals = board[r][c].squareLayer(ir);
					for (int number:vals) {
						if (number == 0) {
							if (!ignoreZeroes) {
								return false;
							}else {
								continue;
							}
						}
						if (numbers[number]==1) {
							return false;
						}else {
							numbers[number] = 1;
						}
					}
				}
			}
		}
		
		for (int c = 0;c<3;c++) {
			for (int ic = 0;ic<3;ic++) {
				int[] numbers = new int[10];
				for (int r = 0;r<3;r++) {
					for (int ir = 0;ir<3;ir++) {
						int val = board[r][c].squareLayer(ir)[ic];
						//System.out.println(val);
						if (val == 0) {
							if (!ignoreZeroes) {
								return false;
							}else {
								continue;
							}
						}
						if (numbers[val]==1) {
							return false;
						}else {
							numbers[val]=1;
						}
						
					}
				}
			}
			
			
			
		}
		return true;
	}
	//lol never used
	private boolean currentlyValid() {
		if (!squared) {
			squareOff();
		}
		for (int r = 0;r<3;r++) {
			for (int ir = 0;ir<3;ir++) {
				int[] numbers = new int[10];
				for (int c = 0;c<3;c++) {
					int[] vals = board[r][c].squareLayer(ir);
					for (int number:vals) {
						if (number == 0) {
							continue;
						}
						if (numbers[number]==1) {
							return false;
						}else {
							numbers[number] = 1;
						}
					}
				}
			}
		}
		return true;
	}
}
