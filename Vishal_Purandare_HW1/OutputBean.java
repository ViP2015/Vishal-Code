public class OutputBean {
	
	private String expansion;
	private String output;
	private int pathCost;
	private boolean noPathAvailable;
	
	public String getExpansion() {
		return expansion;
	}
	public void setExpansion(String expansion) {
		this.expansion = expansion;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public int getPathCost() {
		return pathCost;
	}
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	public boolean isNoPathAvailable() {
		return noPathAvailable;
	}
	public void setNoPathAvailable(boolean noPathAvailable) {
		this.noPathAvailable = noPathAvailable;
	}
	
}
