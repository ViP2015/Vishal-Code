
public class InputBean {
	//Greedy = 1, MiniMax = 2, Alpha-beta = 3, Competition =4
	private int taskNo;
	private char myPlayerSymbol;
	private char oppoPlayerSymbol;
	private int cuttingOffDepth;
	private char[][] graphArr;
	
	public int getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(int taskNo) {
		this.taskNo = taskNo;
	}
	public int getCuttingOffDepth() {
		return cuttingOffDepth;
	}
	public void setCuttingOffDepth(int cuttingOffDepth) {
		this.cuttingOffDepth = cuttingOffDepth;
	}
	public char[][] getGraphArr() {
		return graphArr;
	}
	public void setGraphArr(char[][] graphArr) {
		this.graphArr = graphArr;
	}
	public char getMyPlayerSymbol() {
		return myPlayerSymbol;
	}
	public void setMyPlayerSymbol(char myPlayerSymbol) {
		this.myPlayerSymbol = myPlayerSymbol;
	}
	public char getOppoPlayerSymbol() {
		return oppoPlayerSymbol;
	}
	public void setOppoPlayerSymbol(char oppoPlayerSymbol) {
		this.oppoPlayerSymbol = oppoPlayerSymbol;
	}
	
}
