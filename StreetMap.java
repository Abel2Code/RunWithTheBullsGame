package application;

public class StreetMap {
	Coordinate[][] map;
	
	public StreetMap(int row, int col){
		map = new Coordinate[row][col];
	}
	
	public Coordinate[][] getMap(){
		return map;
	}
	
	public void printStreetMap(){
		 for(int i = 0; i < 25; i++)
		   {
		      for(int j = 0; j < 25; j++)
		      {
		         System.out.print(map[i][j] + " ");
		      }
		      System.out.println();
		   }
	}
}
