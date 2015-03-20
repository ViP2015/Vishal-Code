import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Agent {
	static InputBean inBean = null;
	
	//start point for the program
	public static void main(String[] args) {
		File inputFile = null;
		BufferedReader brRd = null;
		FileWriter fw = null;
		BufferedWriter brWr = null;
		try {
			inputFile = new File("input.txt");
			brRd = new BufferedReader(new FileReader(inputFile));
			fw = new FileWriter("output.txt");
			brWr = new BufferedWriter(fw);
			inBean = readInputFile(brRd);
			//Input on console
			if(inBean.getTaskNo() == 1) {
				Greedy grdyObj = new Greedy(inBean);
				grdyObj.runGreedy();
			}
			if(inBean.getTaskNo() == 2) {
				MiniMax mmObj = new MiniMax(inBean);
				mmObj.runMiniMax();
			}
			if(inBean.getTaskNo() == 3) {
				MiniMaxWithAlphaBeta abObj = new MiniMaxWithAlphaBeta(inBean);
				abObj.runRunAlphaBeta();
			}
			if(inBean.getTaskNo() == 4) {
				inBean.setCuttingOffDepth(10);
				AlphaBetaCompetition abObj = new AlphaBetaCompetition (inBean);
				abObj.runRunAlphaBeta();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found at the location...");
			e.printStackTrace();
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
	
	/**
	 * @param brRd
	 * @return InputBean
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 * @author Vishal Purandare
	 */
	private static InputBean readInputFile(BufferedReader brRd) throws FileNotFoundException, IOException, Exception {
		String line = "";
		InputBean inBean = new InputBean();
		int lineCounter = 0;
		int rowCount = 0;
		char[][] arrGraph = null;
		while((line = brRd.readLine()) != null) {
			line = line.trim();
			lineCounter++;
			if(lineCounter == 1) {
				inBean.setTaskNo(Integer.parseInt(line));
			}
			else if(lineCounter == 2) {
				inBean.setMyPlayerSymbol(line.toCharArray()[0]);
				if(line.toCharArray()[0] == 'X') {
					inBean.setOppoPlayerSymbol('O');
				}
				else {
					inBean.setOppoPlayerSymbol('X');
				}
			}
			else if(lineCounter == 3) {
				inBean.setCuttingOffDepth(Integer.parseInt(line));
			}
			
			else if(lineCounter > 3){
				if(rowCount == 0) {
					arrGraph = new char[8][8];
			}
			char[] cArray = line.toCharArray();
			for (int i = 0; i < cArray.length; i++) {
				arrGraph[rowCount][i] = cArray[i];
			}
			rowCount++;
			}
		}
		inBean.setGraphArr(arrGraph);
		return inBean;
	}	
}