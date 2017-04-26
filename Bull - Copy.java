package application;

public class Bull {
	Coordinate pos;
	Coordinate lastSeen;
	boolean idea = false;
	
	public Bull(){
		pos = new Coordinate(1, 1, 'B');
		lastSeen = new Coordinate(0, 0, '/');
	}
	
	public void takeTurn(Runner runner, MazeGUIPane maze){
		if(pos.getRow() == runner.getRow() && pos.getCol() == runner.getCol()){
			System.out.println("Game Over, DO LATER");
			maze.gameOver = true;
		}
		setLastSeen(runner, maze.map);
		String direction;
		if(idea){
			direction = chooseDirection();
		} else{
			direction = randomDirection(maze.map);
		}
		
		
		System.out.println(direction);
		
		if(direction == "RIGHT"){
			maze.map.getMap()[pos.getRow()][pos.getCol()].setType(' ');
			maze.map.getMap()[pos.getRow()][pos.getCol() + 1].setType('B');
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().clear();
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().add("space");
			maze.labels[pos.getRow()][pos.getCol() + 1].getStyleClass().clear();
			maze.labels[pos.getRow()][pos.getCol() + 1].getStyleClass().add("bull");
			pos.setCol(pos.getCol() + 1);
		} else if(direction == "LEFT"){
			maze.map.getMap()[pos.getRow()][pos.getCol()].setType(' ');
			maze.map.getMap()[pos.getRow()][pos.getCol() - 1].setType('B');
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().clear();
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().add("space");
			maze.labels[pos.getRow()][pos.getCol() - 1].getStyleClass().clear();
			maze.labels[pos.getRow()][pos.getCol() - 1].getStyleClass().add("bull");
			pos.setCol(pos.getCol() - 1);
		} else if(direction == "UP"){
			maze.map.getMap()[pos.getRow()][pos.getCol()].setType(' ');
			maze.map.getMap()[pos.getRow() - 1][pos.getCol()].setType('B');
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().clear();
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().add("space");
			maze.labels[pos.getRow() - 1][pos.getCol()].getStyleClass().clear();
			maze.labels[pos.getRow() - 1][pos.getCol()].getStyleClass().add("bull");
			pos.setRow(pos.getRow() - 1);
		} else if(direction == "DOWN"){//DOWN
			maze.map.getMap()[pos.getRow()][pos.getCol()].setType(' ');
			maze.map.getMap()[pos.getRow() + 1][pos.getCol()].setType('B');
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().clear();
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().add("space");
			maze.labels[pos.getRow() + 1][pos.getCol()].getStyleClass().clear();
			maze.labels[pos.getRow() + 1][pos.getCol()].getStyleClass().add("bull");
			System.out.println(pos.getRow() + 1 + "&&" + pos.getCol());
			pos.setRow(pos.getRow() + 1);
			
			/*maze.map.getMap()[pos.getRow()][pos.getCol()].setType(' ');
			maze.map.getMap()[pos.getRow()][pos.getCol() + 1].setType('B');
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().clear();
			maze.labels[pos.getRow()][pos.getCol()].getStyleClass().add("space");
			maze.labels[pos.getRow()][pos.getCol() + 1].getStyleClass().clear();
			maze.labels[pos.getRow()][pos.getCol() + 1].getStyleClass().add("bull");*/
			System.out.println("got there");
		} else{
			
		}
	}
	
	private String randomDirection(StreetMap map){
		if(map.getMap()[pos.getRow() + 1][pos.getCol()].getType() != 'W' && map.getMap()[pos.getRow() + 1][pos.getCol()].getType() != 'E'){
			return "DOWN";
		} else if(map.getMap()[pos.getRow()][pos.getCol() + 1].getType() != 'W'){
			return "RIGHT";
		} else if(map.getMap()[pos.getRow()][pos.getCol() - 1].getType() != 'W'){
			return "LEFT";
		} else if(map.getMap()[pos.getRow() - 1][pos.getCol()].getType() != 'W' && map.getMap()[pos.getRow() - 1][pos.getCol()].getType() != 'S'){
			return "UP";
		} else{
			return "";
		}
	
	}
	
	private String chooseDirection(){
		System.out.println("Hi");
		if(lastSeen.getRow() == pos.getRow()){
			if(lastSeen.getCol() < pos.getCol()){
				return "RIGHT";
			} else{
				return "LEFT";
			}
		} else{
			if(lastSeen.getRow() > pos.getRow()){
				return "DOWN";
			} else{
				return "UP";
			}
		} 
	}
	
	private void setLastSeen(Runner runner, StreetMap map){
		if(runner.getRow() == pos.getRow()){
			if(runner.getRow() > pos.getRow()){
				for(int i = pos.getRow(); i <= runner.getRow() - pos.getRow(); i++){
					if(map.getMap()[i][pos.getCol()].getType() == 'W'){
						return;
					}
				}
				idea = true;
				lastSeen.setRow(runner.getRow());
				lastSeen.setCol(runner.getCol());
			}else{
				for(int i = runner.getRow(); i <= pos.getRow() - runner.getRow(); i++){
					if(map.getMap()[i][pos.getCol()].getType() == 'W'){
						return;
					}
				}
				idea = true;
				lastSeen.setRow(runner.getRow());
				lastSeen.setCol(runner.getCol());
			}
		} else if(runner.getCol() == pos.getCol()){
			if(runner.getRow() > pos.getRow()){
				for(int i = pos.getCol(); i <= runner.getCol() - pos.getCol(); i++){
					if(map.getMap()[pos.getRow()][i].getType() == 'W'){
						return;
					}
				}
				idea = true;
				lastSeen.setRow(runner.getRow());
				lastSeen.setCol(runner.getCol());
			} else{
				for(int i = runner.getCol(); i <= pos.getCol() - runner.getCol(); i++){
					if(map.getMap()[pos.getRow()][i].getType() == 'W'){
						return;
					}
				}
				idea = true;
				lastSeen.setRow(runner.getRow());
				lastSeen.setCol(runner.getCol());				
			}
		} else{
			idea = false;
		}
	}

}
