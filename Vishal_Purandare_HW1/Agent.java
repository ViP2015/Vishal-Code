import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Agent {
	OutputBean outBean = null;
	static InputBean inBean = null;
	
	public static void main(String[] args) {
		File inputFile = null;
		BufferedReader brRd = null;
		FileWriter fw = null;
		BufferedWriter brWr = null;
		OutputBean outBean = null;
		
		try {
			inputFile = new File("input.txt");
			brRd = new BufferedReader(new FileReader(inputFile));
			fw = new FileWriter("output.txt");
			brWr = new BufferedWriter(fw);
			
			inBean = readInputFile(brRd);
			
		/* Input on console 
		 * 
		 * System.out.println("Task Number: " + inBean.getTaskNo());
			System.out.println("Source: " + inBean.getSource());
			System.out.println("Destination: " + inBean.getDestination());
			System.out.println("Number of Nodes: " + inBean.getNodesNo());
			System.out.println("Nodes: ");
			for (String node : inBean.getNodes()) {
				System.out.println(node);
			}
			// Output should be Expansion, Output, PathCost
			// If no solution then output NoPathAvailable, output file path fixed to output.txt
			System.out.println("Graph: ");
			Integer[][] arr = inBean.getGraphArr();
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.print("\n");
			}
		*/
			
			if(inBean.getTaskNo() == 1) {
				//System.out.println("Calling BFS...");
				BFSDFSUCS bfs = new BFSDFSUCS(inBean);
				outBean = bfs.runBFS();
			}
			
			else if(inBean.getTaskNo() == 2) {
				//System.out.println("Calling DFS...");
				BFSDFSUCS dfs = new BFSDFSUCS(inBean);
				outBean = dfs.runDFS();
			}
			
			else if(inBean.getTaskNo() == 3) {
				//System.out.println("Calling UCS...");
				BFSDFSUCS ucs = new BFSDFSUCS(inBean);
				outBean = ucs.runUCS();
			}
			//Write output to the file output.txt
			writeOutputFile(brWr, outBean);
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found at the location...");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception Occured...");
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Check the input file for validity...");
			e.printStackTrace();
		}
		finally {
			try {
				brWr.flush();
				brWr.close();
				brRd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static InputBean readInputFile(BufferedReader brRd) throws FileNotFoundException, IOException, Exception {
		String line = "";
		InputBean inBean = new InputBean();
		int lineCounter = 0;
		List<String> nodes = new ArrayList<String>();
		int rowCount = 0;
		Integer[][] arrGraph = null;
		
		while((line = brRd.readLine()) != null) {
			lineCounter++;
			if(lineCounter == 1) {
				inBean.setTaskNo(Integer.parseInt(line));
			}
			else if(lineCounter == 2) {
				inBean.setSource(line.trim());
			}
			else if(lineCounter == 3) {
				inBean.setDestination(line.trim());
			}
			else if(lineCounter == 4) {
				inBean.setNodesNo(Integer.parseInt(line));
			}
			else if(lineCounter > 4 && lineCounter <= (4 + inBean.getNodesNo())) {
				nodes.add(line.trim());
			}
			else if(lineCounter > (4 + inBean.getNodesNo())){
				if(rowCount == 0) {
					arrGraph = new Integer[inBean.getNodesNo()][inBean.getNodesNo()];
				}
				String str[] = line.split(" ");
				for (int i = 0; i < str.length; i++) {
					arrGraph[rowCount][i] = Integer.parseInt(str[i]);
				} 
				rowCount++;
			}
		}
		inBean.setNodes(nodes);
		inBean.setGraphArr(arrGraph);
		return inBean;
	}

	private static void writeOutputFile(BufferedWriter brWr, OutputBean outBean) throws IOException, Exception {
		//code to be written to write to the file output.txt
		if(outBean != null) {
			if(outBean.isNoPathAvailable()) {
				brWr.write(outBean.getExpansion());
				brWr.newLine();
				brWr.write("NoPathAvailable");
				//System.out.println("NoPathAvailable");
			}
			else {
				brWr.write(outBean.getExpansion());
				brWr.newLine();
				brWr.write(outBean.getOutput());
				brWr.newLine();
				brWr.write(String.valueOf(outBean.getPathCost()));
				//System.out.println(outBean.getExpansion());
				//System.out.println(outBean.getOutput());
				//System.out.println(outBean.getPathCost());
				}
			}
		else {
				System.out.println("null outbean object...Check file for validity");
		}
	}
}
