import java.util.*;
public class Square implements Cloneable{
	int[][] square = new int[3][3];
	int[] numbers = new int[10];
	ArrayList<Square> permutations = new ArrayList<Square>();
	int numberOfPermutations = 0;
	
	
	
	public int[] squareLayer(int r) {
		return square[r];
	}
	
	public int get(int r, int c) {
		return square[r][c];
	}
	
	public void put(int r, int c, int data) {
		square[r][c] = data;
		numbers[data] = 1;
		return;
	}
	
	public void copy(Square s) {
		for (int r = 0;r<3;r++) {
			for (int c = 0;c<3;c++) {
				square[r][c]=s.get(r, c);
			}
		}
		return;
	}
	
	
	public String toString() {
		String squareString = "";
		for (int r = 0;r<3;r++) {
			for (int c = 0;c<3;c++) {
				squareString+=Integer.toString(square[r][c]);
			}
			squareString+="\n";
		}
		return squareString;
	}
	
	public ArrayList<Square> generatePermutations() {
		ArrayList<Integer> requiredNums = new ArrayList<Integer>();
		for (int i = 1;i<10;i++) {
			if (numbers[i]==0) {
				requiredNums.add(i);
			}
		}
	//	System.out.println(requiredNums);
		generate(requiredNums,0,0, new Square());
		//System.out.println(permutations.size());
		numberOfPermutations = permutations.size();
		return permutations;
		
	}
	
	private void generate(ArrayList<Integer> requiredNums, int r, int c, Square curSquare) {
		//System.out.println(curSquare);
		Collections.sort(requiredNums);
		if (r==3) {
			Square s = new Square();
			s.copy(curSquare);
			permutations.add(s);
			return;
		}else {
			int newC = c+1;
			int newR = r;
			
			if (newC==3) {
				newC = 0;
				newR = r+1;
			}
			
			if (square[r][c]!=0) {
				curSquare.put(r, c, square[r][c]);
				generate(requiredNums,newR,newC,curSquare);
			}else {
				int size = requiredNums.size();
				for (int i = 0;i<size;i++) {
					int num = requiredNums.get(i);
					requiredNums.remove(i);
					curSquare.put(r, c, num);
					generate(requiredNums,newR,newC,curSquare);
					requiredNums.add(i,num);
				}
			}
			
			
		}
	}
}
