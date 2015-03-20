import java.util.List;

public class InputBean {
	
	private String querySentence;
	private int noOfKBSentences;
	private List<String> atomicSentences;
	
	public String getQuerySentence() {
		return querySentence;
	}
	public void setQuerySentence(String querySentence) {
		this.querySentence = querySentence;
	}
	public int getNoOfKBSentences() {
		return noOfKBSentences;
	}
	public void setNoOfKBSentences(int noOfKBSentences) {
		this.noOfKBSentences = noOfKBSentences;
	}
	public List<String> getAtomicSentences() {
		return atomicSentences;
	}
	public void setAtomicSentences(List<String> atomicSentences) {
		this.atomicSentences = atomicSentences;
	}

}
