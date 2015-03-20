import java.util.Comparator;

public class Node implements Comparator<Node>{
	
	public String nodeName;
	public int cost;
	
	public Node() {
		
	}
	
	public Node(String nodeName, int i) {
		this.nodeName = nodeName;
		this.cost = i;
	}
	
	public int compare(Node node1, Node node2) {
		if(node1.cost > node2.cost){
			return 1;
		}
		if(node1.cost < node2.cost){
			return -1;
		}
		if(node1.cost == node2.cost) {
			return (node1.nodeName).compareTo(node2.nodeName);
		}
		return 0;
	}
}
