import java.util.List;

public class InputBean {
	int taskNo;
	String source;
	String destination;
	int nodesNo;
	List<String> nodes;
	Integer[][] graphArr;
	
	public int getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(int taskNo) {
		this.taskNo = taskNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getNodesNo() {
		return nodesNo;
	}
	public void setNodesNo(int nodesNo) {
		this.nodesNo = nodesNo;
	}
	public List<String> getNodes() {
		return nodes;
	}
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
	public Integer[][] getGraphArr() {
		return graphArr;
	}
	public void setGraphArr(Integer[][] graphArr) {
		this.graphArr = graphArr;
	}
}
