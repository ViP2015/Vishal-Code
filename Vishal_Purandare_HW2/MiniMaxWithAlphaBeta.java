import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Mini Max with Alpha Beta implementation of the Othello (Reversi)
 * @author Vishal Purandare
 * 
 */
public class MiniMaxWithAlphaBeta {
	private InputBean inBean;
	NavigableSet<MoveState> stateTreeSet;
	private int depth;
	private UtilitiesOthello utilityObj;
	private Queue<MoveState> stateQueue;
	private BufferedWriter brWr;
	private boolean passMax = false;
	private boolean passMin = false;
	
	public MiniMaxWithAlphaBeta(InputBean inBean) {
		this.inBean = inBean;
		this.stateTreeSet = new TreeSet<MoveState>(new MoveState());
		this.depth = 0;
		this.utilityObj = UtilitiesOthello.getInstance(true);
		this.stateQueue = new LinkedList<MoveState>();
	}

	public void runRunAlphaBeta(){
		try {
			this.brWr = this.utilityObj.getOutputWriter("output_temp.txt");
			int alpha = -Integer.MAX_VALUE;
			int beta = Integer.MAX_VALUE;
			char[][] finalMove = this.inBean.getGraphArr();
			trace("Node,Depth,Value,Alpha,Beta");
			int finalMax = 0;		
			boolean isValidBoard = this.utilityObj.checkInitialConfig(this.inBean.getGraphArr(), this.inBean.getMyPlayerSymbol(), this.inBean.getOppoPlayerSymbol());
			if(!isValidBoard) {
				finalMax = this.utilityObj.calculateScore(this.inBean.getGraphArr(), this.inBean);
				finalMove = this.inBean.getGraphArr();
				trace("root," + depth + "," +finalMax + "," +alpha+ "," +beta);
			}
			else {
				finalMax = maxValue(this.inBean.getGraphArr(), this.inBean.getCuttingOffDepth(), "root", this.inBean.getMyPlayerSymbol(), this.inBean.getOppoPlayerSymbol(), depth, alpha, beta);
				while (!stateQueue.isEmpty()) {
					MoveState ms = stateQueue.remove();
					if(ms.getScore() == finalMax) {
						finalMove = ms.getChangedPositionArray();
						break;
					}
				}
			}
			this.brWr.close();
			//CHANGE HERE--------------------------------------------------------------
			this.brWr = this.utilityObj.getOutputWriter("output.txt");
			//this.brWr = this.utilityObj.getOutputWriter(this.inBean.getCuttingOffDepth()+".txt");
			for (int i = 0; i < finalMove.length; i++) {
				String row = "";
				for (int j = 0; j < finalMove.length; j++) {
					row = row + finalMove[i][j];
				}
				trace(row);
			}
			this.utilityObj.copyFromTempOutput(this.brWr);
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

	private int maxValue(char[][] positionArray, int max_depth,
			String endPos, char myPlayerSymbol, char oppoPlayerSymbol, int depth, int alpha, int beta) throws Exception {
		if(depth == max_depth) {
			int score = this.utilityObj.calculateScore(positionArray, this.inBean);
			trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
			return score;
		}
		else {
			 int score = - Integer.MAX_VALUE;
			 boolean isValidBoard = this.utilityObj.checkInitialConfig(positionArray, this.inBean.getMyPlayerSymbol(), this.inBean.getOppoPlayerSymbol());
			 if(!isValidBoard) {
				score = this.utilityObj.calculateScore(positionArray, inBean);
				trace(endPos + "," + depth + "," + score + "," +alpha+ "," +beta);
				return score;
			 }
			 trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
			 stateTreeSet = new TreeSet<MoveState>(new MoveState());
			 for(int i = 0; i < positionArray.length; i++) {
					for (int j = 0; j < positionArray.length; j++) {
						if(positionArray[i][j] == myPlayerSymbol) {
							stateTreeSet = this.utilityObj.findValidPlacings(i, j, positionArray, stateTreeSet, oppoPlayerSymbol, myPlayerSymbol);
						}
					}
				}
				List<MoveState> moveStateList = new ArrayList<MoveState>();
				if(stateTreeSet.isEmpty()) {
					passMax = true;
					if(passMin) {
						score = this.utilityObj.calculateScore(positionArray, this.inBean);
						trace(endPos +  "," + (depth+1) + "," +score+ "," + alpha + "," + beta );
						if(alpha < score) {
							if(score >= beta) {
								trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
								return beta;
							}
							alpha = score;
	 					}
						trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
						return score;
					}
					int value  = minValue(positionArray, max_depth, "pass", oppoPlayerSymbol, myPlayerSymbol, depth+1, alpha, beta);
					if(value > score) {
						score = value;
					}
					if(alpha < score) {
						if(value >= beta) {
							trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
							return beta;
						}
						alpha = score;
 					}
					trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
					return score;
				}
				else {
					passMax = false;
					MoveState moveState;
					while (!stateTreeSet.isEmpty()) {
						boolean flag = true;
						moveState = stateTreeSet.pollFirst();
						if(!moveStateList.isEmpty()) {
							for (MoveState mvSt : moveStateList) {
								if(mvSt.getEndPosition().equals(moveState.getEndPosition())) {
									flag = false;
								}
							}
						}
						if(flag) {
							moveStateList.add(moveState);
						}
					}
					
					for (MoveState mState : moveStateList) {
						char[][] positionArrayTemp = mState.getChangedPositionArray() ;
						int value = minValue(positionArrayTemp, max_depth, mState.getEndPosition(), oppoPlayerSymbol, myPlayerSymbol, (depth+1), alpha, beta);
						//alpha = Math.max(alpha, value);
						if(value > score) {
							score = value;
						}
						if(alpha < score) {
							if(value >= beta) {
								trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
								return beta;
							}
							alpha = score;
 						}
						if(endPos.equals("root")) {
							mState.setScore(score);
							stateQueue.add(mState);
						}
						trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
					}
					if(endPos.equals("pass")) {
						return score;
					}
					else {
						return alpha;
					}
				}
		}
	}
	
	private int minValue(char[][] positionArray, int max_depth,
			String endPos, char myPlayerSymbol, char oppoPlayerSymbol, int depth, int alpha, int beta) throws Exception {
		if(depth == max_depth) {
			int score = this.utilityObj.calculateScore(positionArray, this.inBean);
			trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
			return score;
		 }
		else {
			 int score =  Integer.MAX_VALUE;
			 boolean isValidBoard = this.utilityObj.checkInitialConfig(positionArray, this.inBean.getMyPlayerSymbol(), this.inBean.getOppoPlayerSymbol());
			 if(!isValidBoard) {
				score = this.utilityObj.calculateScore(positionArray, this.inBean);
				trace(endPos+ "," + depth + "," + score + "," +alpha+ "," +beta);
				return score;
			 }
			 trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
			 stateTreeSet = new TreeSet<MoveState>(new MoveState());
			 for (int i = 0; i < positionArray.length; i++) {
					for (int j = 0; j < positionArray.length; j++) {
						if(positionArray[i][j] == myPlayerSymbol) {
							stateTreeSet = this.utilityObj.findValidPlacings(i, j, positionArray, stateTreeSet, oppoPlayerSymbol, myPlayerSymbol);
						}
					}
				}
				List<MoveState> moveStateList = new ArrayList<MoveState>();
				if(stateTreeSet.isEmpty()) {
					passMin = true;
					if(passMax){
						score = this.utilityObj.calculateScore(positionArray, this.inBean);
						trace(endPos +  "," + (depth+1) + "," +score+ "," + alpha + "," + beta );
						if(beta > score) {
							if(score <= alpha) {
								trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
								return alpha;
							}
							beta = score;
	 					}
						trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
						return score;
					}
					int value = maxValue(positionArray, max_depth, "pass", oppoPlayerSymbol, myPlayerSymbol, depth+1, alpha, beta);
					if(value < score) {
						score = value;
					}
					if(beta > score) {
						if(value <= alpha) {
							trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
							return alpha;
						}
						beta = score;
 					}
					trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
					return score;
				}
				else {
					passMin = false;
					MoveState moveState;
					while (!stateTreeSet.isEmpty()) {
						boolean flag = true;
						moveState = stateTreeSet.pollFirst();
						if(!moveStateList.isEmpty()) {
							for (MoveState mvSt : moveStateList) {
								if(mvSt.getEndPosition().equals(moveState.getEndPosition())) {
									flag = false;
								}
							}
						}
						if(flag) {
							moveStateList.add(moveState);
						}
					}
					
					for (MoveState mState : moveStateList) {
						char[][] positionArrayTemp = mState.getChangedPositionArray() ;
						int value = maxValue(positionArrayTemp, max_depth, mState.getEndPosition(), oppoPlayerSymbol, myPlayerSymbol, (depth+1), alpha, beta);
						//beta = Math.min(beta, value);
						if(value < score) {
							score = value;
						}
						if(beta > score) {
							if(value <= alpha) {
								trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
								return alpha;
							}
							beta = score;
 						}
						trace(endPos +  "," + depth + "," +score+ "," + alpha + "," + beta );
						/*if(beta <= alpha) {
							return alpha;
						}*/
					}
					if(endPos.equals("pass")) {
						return score;
					}
					else {
						return beta;
					}
				}
		}
	}
	
	private void trace(String str) throws IOException {
		str = str.replace("-2147483647", "-Infinity");
		str = str.replace("2147483647", "Infinity");
		this.brWr.write(str);
		this.brWr.newLine();
	}
}
