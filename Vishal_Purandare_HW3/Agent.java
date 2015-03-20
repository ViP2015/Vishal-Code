import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Agent {
	static InputBean inBean = null;
	//start point for the program
	public static void main(String[] args) {
		File inputFile = null;
		BufferedReader brRd = null;
		boolean isProved = false;
		ReturnBean ret;
		FileWriter fw = null;
		BufferedWriter brWr = null;
		try {	
		//for (int i = 1; i <=63; i++) {
			//inputFile = new File("input"+i+".txt");	
			inputFile = new File("input.txt");
			brRd = new BufferedReader(new FileReader(inputFile));
			
			fw = new FileWriter("output.txt");
			brWr = new BufferedWriter(fw);
			inBean = readInputFile(brRd);
			
			BCUtilities bcUtilObj = BCUtilities.getInstance(inBean);
			
			//Utilities: parse the input and create the HashTable for Predicate
			Hashtable<String, Predicate> predicatesHashTable = bcUtilObj.parseInput();
			
			String queryToProve = inBean.getQuerySentence();
			ret = bcUtilObj.proveTheQuery(queryToProve, predicatesHashTable, queryToProve);
			if(ret != null) {
				isProved = ret.isIfProved();
			}
			
			//System.out.println(i+"-" + isProved);
			
			//System.out.println("Is Proved: " + isProved);
			
			String output = "";
			
			if(isProved) {
				output = "TRUE";
			}
			else {
				output = "FALSE";
			}
			brWr.write(output);
		//}
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found at the location...Make sure a valid input.txt is present at the current directory location");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception Occured...");
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Check the input file for validity...");
			try {
				brWr.write("FALSE");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
		List<String> atomicSentences = new ArrayList<String>();
		while((line = brRd.readLine()) != null) {
			line = line.trim();
			lineCounter++;
			if(lineCounter == 1) {
				inBean.setQuerySentence(line);
			}
			else if(lineCounter == 2) {
				inBean.setNoOfKBSentences(Integer.parseInt(line));
			}
			else if(lineCounter > 2 && lineCounter <= (inBean.getNoOfKBSentences()+2)){
				atomicSentences.add(line);
			}
		}
		inBean.setAtomicSentences(atomicSentences);
		return inBean;
	}
}