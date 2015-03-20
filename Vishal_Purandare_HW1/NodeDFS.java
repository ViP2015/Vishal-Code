import java.util.Comparator;

public class NodeDFS implements Comparator<NodeDFS>{
	
	public String nodeName;
	public int cost;
	
	public NodeDFS() {
		
	}
	
	public NodeDFS(String nodeName, int i) {
		this.nodeName = nodeName;
		this.cost = i;
	}
	
	public int compare(NodeDFS node1, NodeDFS node2) {
		if(node1.cost < node2.cost){
			return 1;
		}
		if(node1.cost > node2.cost){
			return -1;
		}
		if(node1.cost == node2.cost) {
			return (node1.nodeName).compareTo(node2.nodeName);
		}
		return 0;
	}
}
