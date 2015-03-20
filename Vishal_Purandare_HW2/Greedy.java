import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * @author Vishal Purandare
 * 
*/

public class Greedy {
	private InputBean inBean;
	NavigableSet<MoveState> stateTreeSet;
	private BufferedWriter brWr;
	private UtilitiesOthello utilityObj;
	
	public Greedy(InputBean inBean) {
		this.inBean = inBean;
		this.stateTreeSet = new TreeSet<MoveState>(new MoveState());
		this.utilityObj = UtilitiesOthello.getInstance(false);
	}

	public void runGreedy() {
		try {
			//Run greedy algorithm and decide on next step for the game
			char[][] newMovePositionArray = getAvailableMoves();
			this.brWr = this.utilityObj.getOutputWriter("output.txt");
			//this.brWr = this.utilityObj.getOutputWriter(this.inBean.getCuttingOffDepth()+".txt");
			if(newMovePositionArray != null) {
				for (int i = 0; i < newMovePositionArray.length; i++) {
					String row = "";
					for (int j = 0; j < newMovePositionArray.length; j++) {
						row = row + newMovePositionArray[i][j];
					}
					trace(row);
				}
			}
			else {
				newMovePositionArray = this.inBean.getGraphArr();
				for (int i = 0; i < newMovePositionArray.length; i++) {
					String row = "";
					for (int j = 0; j < newMovePositionArray.length; j++) {
						row = row + newMovePositionArray[i][j];
					}
					trace(row);
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		finally {
			try {
				this.brWr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private char[][] getAvailableMoves() {
		//return the availble moves 
		char[][] positionArray = this.inBean.getGraphArr();
		char[][] updatedPositionArray = new char[8][8];
		
		for (int i=0; i <positionArray.length; i++) {
	        for (int j = 0; j < positionArray.length; j++) {
				updatedPositionArray[i][j] = positionArray[i][j];
			}
	    }
		MoveState moveState = new MoveState();
		
		for (int i = 0; i < positionArray.length; i++) {
			for (int j = 0; j < positionArray.length; j++) {
				if(positionArray[i][j] == this.inBean.getMyPlayerSymbol()) {
					stateTreeSet = utilityObj.findValidPlacings(i, j, positionArray, stateTreeSet, this.inBean.getOppoPlayerSymbol(), this.inBean.getMyPlayerSymbol());
				}
			}
		}
		//Removes top most from the Queue which is according specification: check MoveState POJO in priority queue implementation
		//Arrays.sort(stateQueue.toArray());
		List<MoveState> moveStateList = new ArrayList<MoveState>();
		if(stateTreeSet.isEmpty()) {
			return null;
		}
		while (!stateTreeSet.isEmpty()) {
			moveState = stateTreeSet.pollFirst();
			moveStateList.add(moveState);
		}
		return moveStateList.get(0).getChangedPositionArray();
	}
	
	private void trace(String str) throws IOException {
		this.brWr.write(str);
		this.brWr.newLine();
	}
}
