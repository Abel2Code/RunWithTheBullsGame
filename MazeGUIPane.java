package application;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MazeGUIPane extends GridPane{
	Label[][] labels;
	int rowMax = 25;
	int colMax = 25;
	Runner runner;
	int bullTurns; // 3
	int numBulls; // 2
	int turnsBeforeRelease; // 3
	int turnsLeft;
	StreetMap map = new StreetMap(rowMax, colMax);
	boolean gameOver = false;
	Bull[] bulls;
	Label gameResult;

	public void startGame(Label mainLabel, int turnsBeforeRelease, int numBulls, int bullTurns){
		setUpLabels();
		gameResult = mainLabel;
		this.turnsBeforeRelease = turnsBeforeRelease;
		this.numBulls = numBulls;
		this.bullTurns = bullTurns;
		runner = new Runner(1, 1);
		turnsLeft = turnsBeforeRelease;
		gameOver = false;
		bulls = new Bull[numBulls];
		for(int i = 0; i < numBulls; i++){
			int tempRow = 1;
			int tempCol = 2 * i + 2;
			bulls[i] = new Bull(tempRow, tempCol);
			map.getMap()[tempRow][tempCol].setType('B');
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("bull");
		}
	}
	
	public void moveLeft(){
		int tempRow = runner.getRow();
		int tempCol = runner.getCol();
		if(map.getMap()[tempRow][tempCol - 1].getType() == 'W' || map.getMap()[tempRow][tempCol - 1].getType() == 'B'){
		
		} else{
			map.getMap()[tempRow][tempCol].setType(' ');
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("space");
			map.getMap()[tempRow][tempCol - 1].setType('R');
			labels[tempRow][tempCol - 1].getStyleClass().clear();
			labels[tempRow][tempCol - 1].getStyleClass().add("runner");
			runner.setRow(tempRow);
			runner.setCol(tempCol - 1);
			checkTurns();
		}
	}

	public void moveRight(){
		int tempRow = runner.getRow();
		int tempCol = runner.getCol();
		if(map.getMap()[tempRow][tempCol + 1].getType() == 'W' || map.getMap()[tempRow][tempCol + 1].getType() == 'B'){
		
		} else{
			map.getMap()[tempRow][tempCol].setType(' ');
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("space");
			map.getMap()[tempRow][tempCol + 1].setType('R');
			labels[tempRow][tempCol + 1].getStyleClass().clear();
			labels[tempRow][tempCol + 1].getStyleClass().add("runner");
			runner.setRow(tempRow);
			runner.setCol(tempCol + 1);
			checkTurns();
		}
	}
	
	public void moveUp(){
		int tempRow = runner.getRow();
		int tempCol = runner.getCol();
		if(map.getMap()[tempRow - 1][tempCol].getType() == 'W' || map.getMap()[tempRow - 1][tempCol].getType() == 'S' || map.getMap()[tempRow - 1][tempCol].getType() == 'B'){
		
		} else{
			map.getMap()[tempRow][tempCol].setType(' ');
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("space");
			map.getMap()[tempRow - 1][tempCol].setType('R');
			labels[tempRow - 1][tempCol].getStyleClass().clear();
			labels[tempRow - 1][tempCol].getStyleClass().add("runner");
			runner.setRow(tempRow - 1);
			runner.setCol(tempCol);
			checkTurns();
		}
	}
	
	public void moveDown(){
		int tempRow = runner.getRow();
		int tempCol = runner.getCol();
		if(map.getMap()[tempRow + 1][tempCol].getType() == 'W'){
			
		} else if(map.getMap()[tempRow + 1][tempCol].getType() == 'E'){
			gameOver = true;	
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("space");
			//YOU WIN
			gameResult.setText("You Win!");
			
		} else{
			map.getMap()[tempRow][tempCol].setType(' ');
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("space");
			map.getMap()[tempRow + 1][tempCol].setType('R');
			labels[tempRow + 1][tempCol].getStyleClass().clear();
			labels[tempRow + 1][tempCol].getStyleClass().add("runner");
			runner.setRow(tempRow + 1);
			runner.setCol(tempCol);
			checkTurns();
		}
	}

	private void setUpLabels() {
		labels = new Label[rowMax][colMax];

		for(int row = 0; row < rowMax; row++){
			for (int col = 0; col < colMax; col++) {
				Label l = new Label();
				setUpLabel(l, row, col);
				labels[row][col] = l;
				add(l, col, row);
			}
		}
		labels[0][1].setText("S");
		labels[0][1].setTextFill(Color.RED);
		labels[24][23].setText("E");
		labels[24][23].setTextFill(Color.RED);
	}
	
	private void checkTurns(){
		turnsLeft--;
		if(turnsLeft <= 0){
			for(int i = 0; i < bullTurns; i++){
				bullTurn();
				turnsLeft = 1;
			}
		}
	}

	private void bullTurn(){
//		System.out.println("Bulls go here!");
		for(int i = 0; i < numBulls; i++){
			if(!gameOver)
				bulls[i].takeTurn(runner, this);
		}
	}
	private void setUpLabel(final Label l, final int row, final int col) {
		l.setPrefWidth(50);
		l.setMaxHeight(25);
		l.setMinHeight(5);

		if(row == 0 && col == 1){
			l.getStyleClass().add("start");
			map.getMap()[0][1] = new Coordinate(0,1,'S');
		} else if(row == 24 && col == 23){
			l.getStyleClass().add("end");
			map.getMap()[24][23] = new Coordinate(24,23,'E');
		} else if(row == 0 || row == 24){
			l.getStyleClass().add("wall");
			map.getMap()[row][col] = new Coordinate(row,col,'W');
		} else if(col == 0 || col == 24){
			l.getStyleClass().add("wall");
			map.getMap()[row][col] = new Coordinate(row, col, 'W');
		} else if((col == 23 && row == 23)){
			//First available space should always be a space
			l.getStyleClass().add("space");
			map.getMap()[row][col] = new Coordinate(row, col, ' ');
		} else if(col == 1 && row == 1) {
			l.getStyleClass().add("runner");
			map.getMap()[row][col] = new Coordinate(row, col, 'R');
		}else{
			
			if((int)(Math.random() * (5 - 1) + 1) == 1){
				l.getStyleClass().add("wall");
				map.getMap()[row][col] = new Coordinate(row, col, 'W');
			} else{
				l.getStyleClass().add("space");
				map.getMap()[row][col] = new Coordinate(row, col, ' ');
			}
			l.setOnMouseClicked(new EventHandler<InputEvent>(){
				@Override
				public void handle(InputEvent arg0){
					if(l.getStyleClass().contains("wall")){
						l.getStyleClass().clear();
						l.getStyleClass().add("space");
						map.getMap()[row][col] = new Coordinate(row, col, ' ');
					} else if(l.getStyleClass().contains("space")){
						l.getStyleClass().clear();
						l.getStyleClass().add("wall");
						map.getMap()[row][col] = new Coordinate(row, col, 'W');
					} else{
						
					}
				}
			});

		}
	}
}
