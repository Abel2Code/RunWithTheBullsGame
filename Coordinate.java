package application;

public class Coordinate {
	private int row;
	private int col;
	private char type; // ' ' empty space, 'W' wall, 'S' starting point, 'E' exit, 'R' for runner, 'B' for bull
	
	public Coordinate(int row, int col, char type){
		this.row = row;
		this.col = col;
		this.type = type;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int column) {
		this.col = column;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	public String toString(){
		//for debugging
		return "" + type;
	}
}
