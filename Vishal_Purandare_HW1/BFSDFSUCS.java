import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class BFSDFSUCS {

	private LinkedHashSet<String> visitedVertexSet;
	private LinkedHashSet<String> closedVertexSet;
	private InputBean inBean;
	private PriorityQueue<Node> prioQueue;
	private PriorityQueue<NodeDFS> prioQueueDFS;
	private int[] distCost;
	private int[] parent;
	private OutputBean outObj;
	private List<String> path = null;
	private int pathCost;
	private	boolean notFound = true;
	private int[] depth;
	
	public BFSDFSUCS(InputBean inBean) {
		this.visitedVertexSet = new LinkedHashSet<String>();
		this.closedVertexSet = new LinkedHashSet<String>();
		this.outObj = new OutputBean();
		this.pathCost = 0;
		this.inBean = inBean;
		this.prioQueue = new PriorityQueue<Node>(this.inBean.getNodesNo(), new Node());
		this.prioQueueDFS = new PriorityQueue<NodeDFS>(this.inBean.getNodesNo(), new NodeDFS());
		this.distCost = new int[this.inBean.getNodesNo()];
		this.parent = new int[this.inBean.getNodesNo() + 1];
		depth = new int[this.inBean.getNodesNo()];
		for (int i = 0; i < this.inBean.getNodesNo(); i++) {
			distCost[i] = Integer.MAX_VALUE;
			depth[i] = Integer.MAX_VALUE;
		}
	}

	public OutputBean runBFS() {
		String node = this.inBean.getSource();
		String destinationNode = this.inBean.getDestination();
		List<String> nodeList = this.inBean.getNodes();
		Integer[][] graphArr = this.inBean.getGraphArr();
		
		int srcNodeIndex = this.inBean.getNodes().indexOf(node);
		boolean visitedOnce = false;
		depth[srcNodeIndex] = 0;
		prioQueue.add(new Node(node, depth[srcNodeIndex]));
		distCost[srcNodeIndex] = 0;
		
		while(!prioQueue.isEmpty()) {
			node = prioQueue.remove().nodeName;
			closedVertexSet.add(node);
			visitedVertexSet.add(node);
			if(destinationNode.equals(node)) {
				notFound =  false;
				return constructOutput();
			}
			
			int nodeIndex = this.inBean.getNodes().indexOf(node);
			for (int i = 0; i < graphArr[nodeIndex].length; i++) {
				if(graphArr[nodeIndex][i] != 0) {
					if(visitedVertexSet.contains(nodeList.get(i))) {
						if(depth[i] > depth[nodeIndex] + 1) {
							depth[i] = depth[nodeIndex] + 1;
							distCost[i] = graphArr[nodeIndex][i] + distCost[nodeIndex];
							parent[i] = nodeIndex;
							Node nodeObj = new Node(nodeList.get(i), depth[i]);
							prioQueue.remove(nodeObj);
							prioQueue.add(nodeObj);
							visitedVertexSet.add(nodeObj.nodeName);
							if(destinationNode.equals(nodeObj.nodeName) && !visitedOnce) {
								tracePath();
								visitedOnce = true;
							}
						}
					}
					else {
						depth[i] = depth[nodeIndex] + 1;
						distCost[i] = graphArr[nodeIndex][i] + distCost[nodeIndex];
						parent[i] = nodeIndex;
						Node nodeObj = new Node(nodeList.get(i), depth[i]);
						prioQueue.add(nodeObj);
						visitedVertexSet.add(nodeObj.nodeName);
						if(destinationNode.equals(nodeObj.nodeName) && !visitedOnce) {
							tracePath();
							visitedOnce = true;
						}
					}
					
				}
			}
		}
		return constructOutput();
	}

	public OutputBean runDFS() {
		String sourceNode = this.inBean.getSource();
		int srcNodeIndex = this.inBean.getNodes().indexOf(sourceNode);
		
		distCost[srcNodeIndex] = 0;
		depth[srcNodeIndex] = 0;
		//dfsStack = new Stack<Node>();

		NodeDFS node = new NodeDFS(sourceNode, distCost[srcNodeIndex]);
		prioQueueDFS.add(node);
		visitedVertexSet.add(sourceNode);

		while (!prioQueueDFS.isEmpty()) {
			NodeDFS node1 = prioQueueDFS.remove();
			closedVertexSet.add(node1.nodeName);
			dfs(this.inBean.getGraphArr(), node1.nodeName);
		}
		
		return constructOutput();
	}
	
	
	/* This is right way to do the DFS as per spec
	 * But as per Prof. Itti's latest remark changing to new implementation
	 * Try-2 
	 * 
	 */ 
	 public void dfs(Integer[][] graphArr, String nodeIn) {
		int nodeInIndex = this.inBean.getNodes().indexOf(nodeIn);
		final List<String> nodeList = this.inBean.getNodes();
		
		String destinationNode = this.inBean.getDestination();
		
		if(destinationNode.equals(nodeIn)) {
			tracePath();
			return;
		}
		
		List<String> tempNodeList = new ArrayList<String>(nodeList);
		Collections.sort(tempNodeList);
		
		for (String n : tempNodeList) {
			int originalIndex =  nodeList.indexOf(n);
				if(depth[originalIndex] > depth[nodeInIndex] + 1) {
					if(graphArr[nodeInIndex][originalIndex] !=0 && !visitedVertexSet.contains(n)) {
						distCost[originalIndex] = graphArr[nodeInIndex][originalIndex] + distCost[nodeInIndex];
						depth[originalIndex] = depth[nodeInIndex] + 1;
						parent[originalIndex] = nodeInIndex;
						NodeDFS node = new NodeDFS(nodeList.get(originalIndex), depth[originalIndex]); 
						prioQueueDFS.add(node);
						visitedVertexSet.add(node.nodeName);
					}
				}
			}
	}
	
	
	/* Normal Legit DFS with recursive call... but not as per pro. Itti's new remarks
	 * Try-1 Original
	 * 
	 * public void dfs(Integer[][] graphArr, String nodeIn) {
		int nodeInIndex = this.inBean.getNodes().indexOf(nodeIn);
		String sourceNode = this.inBean.getSource();
		int srcNodeIndex = this.inBean.getNodes().indexOf(sourceNode);
	
		//Node node = new Node(nodeIn, distCost[nodeInIndex]);
		//prioQueue.add(node);
		//visitedVertexSet.add(nodeIn);
		
		String destinationNode = this.inBean.getDestination();
		int destination = this.inBean.getNodes().indexOf(destinationNode);
		
		if(destinationNode.equals(nodeIn)) {
			int newNode = destination;
			path.add(destinationNode);
			do {
				path.add(this.inBean.getNodes().get(parent[newNode]));
				newNode = parent[newNode];
			} while(newNode > srcNodeIndex);
			Collections.reverse(path);
			pathCost = distCost[destination];
			notFound =  false;
		}
		
		for (int i = 0; i < graphArr[nodeInIndex].length; i++) {
			List<String> nodeList = inBean.getNodes();
			if(i != nodeInIndex) {
				if(graphArr[nodeInIndex][i] !=0 && !visitedVertexSet.contains(nodeList.get(i))) {
					distCost[i] = graphArr[nodeInIndex][i] + distCost[nodeInIndex];
					depth[i] = depth[nodeInIndex] + 1;
					parent[i] = nodeInIndex;
					Node node = new Node(nodeList.get(i), depth[i]); 
					prioQueue.add(node);
					visitedVertexSet.add(node.node);
					dfs(graphArr, this.inBean.getNodes().get(i));
				}
			}
		}
	}
	*/
	
	//Uniform Cost Search: actually visitedVertexSet and closedVertexSet doing the same thing here.
	public OutputBean runUCS() {
		String nextNode = null;
		String node = this.inBean.getSource();
		String destinationNode = this.inBean.getDestination();
		prioQueue.add(new Node(node, 0));
		int srcNodeIndex = this.inBean.getNodes().indexOf(node);
		distCost[srcNodeIndex] = 0;
		
		while(!prioQueue.isEmpty()) {
			nextNode = prioQueue.remove().nodeName;
			visitedVertexSet.add(nextNode);
			closedVertexSet.add(nextNode);
			if(nextNode.equals(destinationNode)){
				tracePath();
				return constructOutput();
			}
			addNextChildToQueue(nextNode);
		}
		return constructOutput();
	}

	private void addNextChildToQueue(String nextNode) {
		Integer[][] graphArr = this.inBean.getGraphArr();
		List<String> nodesList = inBean.getNodes();
		int nodeIndex = nodesList.indexOf(nextNode);
		for (int i = 0; i < graphArr.length; i++) {
			if(!visitedVertexSet.contains(nodesList.get(i)) && graphArr[nodeIndex][i] != 0) {
				int newCost = graphArr[nodeIndex][i] + distCost[nodeIndex];
				if(distCost[i] > newCost) {
					distCost[i] = newCost;
					parent[i] = nodeIndex;
				}
				Node node = new Node(nodesList.get(i), distCost[i]);
				if(prioQueue.contains(node)) {
					prioQueue.remove(node);
				}
				prioQueue.add(node);
			}
		}
	}
	//UCS End

	private void tracePath() {
		String destinationNode = this.inBean.getDestination();
		int destination = this.inBean.getNodes().indexOf(destinationNode);
		String srcNode = this.inBean.getSource();
		int srcNodeIndex = this.inBean.getNodes().indexOf(srcNode);
		int newNode = destination;
		
		path = new ArrayList<String>();
		path.add(destinationNode);
		
		do {
			path.add(this.inBean.getNodes().get(parent[newNode]));
			newNode = parent[newNode];
		} while(newNode > srcNodeIndex);
		Collections.reverse(path);
		pathCost = distCost[destination];
		notFound = false;
	}
	
	private OutputBean constructOutput() {
		String expansionQueue = "";
		//System.out.println(closedVertexSet);
		//LinkedList<String> visitedVertexList = new LinkedList<String>(visitedVertexSet);
		LinkedList<String> closedVertexList = new LinkedList<String>(closedVertexSet);
		Iterator<String> itr = closedVertexList.iterator();
		boolean visited = false;
		
		while(itr.hasNext()) {
		    String item = itr.next();
		    if(!visited) {
		    	 if(expansionQueue.isEmpty()) {
						expansionQueue = item;
					}
					else {
						expansionQueue = expansionQueue + "-" + item;
				}
		    }
		    if(inBean.getDestination().equals(item)) {
		    	visited = true;
		    }
		}
		
		if(!notFound) {
			String outPath = "";
			for (String s : path) {
				if(outPath.isEmpty()) {
					outPath = s;
				}
				else {
					outPath = outPath + "-" + s;
				}
			}
			outObj.setExpansion(expansionQueue);
			outObj.setOutput(outPath);
			outObj.setPathCost(pathCost);
		}
		else {
			outObj.setExpansion(expansionQueue);
			outObj.setNoPathAvailable(true);
		}
		return outObj;
	}
}