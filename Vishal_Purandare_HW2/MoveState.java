import java.util.Comparator;

/**
 * This is POJO class implementation to store the state of the Othello(Reversi) game state.
 * 
 * @author Vishal Purandare
 *
 */
public class MoveState implements Comparator<MoveState> {
	//POJO will be used to return the valid moves array and max eval and move type for the same.
	public char[][] updatedPositionArray;
	public int maxEvalValue;
	public String moveType; 
	public int startIndexRow;
	public int startIndexCol;
	public int endIndexRow;
	public int endIndexCol;
	public String startPosition;
	public String endPosition;
	public char[][] changedPositionArray;
	public int score;
	
	public MoveState() {
		
	}
	
	public MoveState(char[][] updatedPositionArray, int maxEvalValue, String moveType, int startIndexRow, int startIndexCol,
			int endIndexRow, int endIndexCol, String startPosition, String endPosition) {
		this.updatedPositionArray = updatedPositionArray;
		this.maxEvalValue = maxEvalValue;
		this.moveType = moveType;
		this.startIndexRow = startIndexRow;
		this.startIndexCol = startIndexCol;
		this.endIndexRow = endIndexRow;
		this.endIndexCol = endIndexCol;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}

	public char[][] getUpdatedPositionArray() {
		return updatedPositionArray;
	}
	public void setUpdatedPositionArray(char[][] updatedPositionArray) {
		this.updatedPositionArray = updatedPositionArray;
	}
	public int getMaxEvalValue() {
		return maxEvalValue;
	}
	public void setMaxEvalValue(int maxEvalValue) {
		this.maxEvalValue = maxEvalValue;
	}
	public String getMoveType() {
		return moveType;
	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	public int getStartIndexRow() {
		return startIndexRow;
	}
	public void setStartIndexRow(int startIndexRow) {
		this.startIndexRow = startIndexRow;
	}
	public int getStartIndexCol() {
		return startIndexCol;
	}
	public void setStartIndexCol(int startIndexCol) {
		this.startIndexCol = startIndexCol;
	}
	public int getEndIndexRow() {
		return endIndexRow;
	}
	public void setEndIndexRow(int endIndexRow) {
		this.endIndexRow = endIndexRow;
	}
	public int getEndIndexCol() {
		return endIndexCol;
	}
	public void setEndIndexCol(int endIndexCol) {
		this.endIndexCol = endIndexCol;
	}

	public String getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}

	public String getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(String endPosition) {
		this.endPosition = endPosition;
	}

	public char[][] getChangedPositionArray() {
		return changedPositionArray;
	}

	public void setChangedPositionArray(char[][] changedPositionArray) {
		this.changedPositionArray = changedPositionArray;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int compare(MoveState o1, MoveState o2) {
		if(o1.maxEvalValue > o2.maxEvalValue) {
			return -1;
		}
		if(o1.maxEvalValue < o2.maxEvalValue) {
			return 1;
		}
		if(o1.maxEvalValue == o2.maxEvalValue) {
			if(o1.endIndexRow < o2.endIndexRow) {
				return -1;
			}
			else if (o1.endIndexRow == o2.endIndexRow) {
				if(o1.endIndexCol < o2.endIndexCol) {
					return -1;
				}
				else {
					return 1;
				}
			}
			else {
				return 1;
			}
		}
		return 0;
	}
}
