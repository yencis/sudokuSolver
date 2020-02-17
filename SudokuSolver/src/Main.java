import java.util.*;
public class Main {
	static Board board;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		board = new Board();
		//System.out.println(board);
		for (int r = 0;r<9;r++) {
			for (int c = 0;c<9;c++) {
				int x = sc.nextInt(); //0 for empty spots
				board.put(r, c, x);
			}
		}
		//System.out.println(board.validBoard());
		board.squareOff();
		board.generateAllPermutations();
		//System.out.println(board.validBoard(true));
		//System.out.println(board);
		//Square x = board.getSquare(0, 0);
		//x.generatePermutations();
		//System.out.println(x.numberOfPermutations);
		//System.out.println(x.permutations);
		Board curBoard = new Board();
		curBoard.squareOff();
		search(curBoard,0,0);
		//System.out.println(curBoard);
		
	}
	
	
	
	public static void search(Board curBoard, int r, int c) { //r and c for squares
		//System.out.println(curBoard);
		if (r==3) {
			if (curBoard.validBoard(false)) {
				System.out.println("Found");
				System.out.println(curBoard);
			}
			return;
		}else {
			
			
			
			int newC = c+1;
			int newR = r;
			if (newC==3) {
				newC = 0;
				newR = r+1;
			}
			
			
			//System.out.println("Hello");
			Square original = board.getSquare(r, c);
			//System.out.println(original+" "+r+" "+c);
			ArrayList<Square> permutations = original.permutations;
			//System.out.println(permutations.size());
			//System.out.println(permutations+" "+r+" "+c);
			for (int i = 0;i<permutations.size();i++) {
				curBoard.putSquare(r, c, permutations.get(i));
				//System.out.println(curBoard);
				if (curBoard.validBoard(true)) {
				search(curBoard,newR,newC);
				}
				curBoard.putSquare(r, c, new Square());
			}
		}
		
		
		
	}
	
	

}
