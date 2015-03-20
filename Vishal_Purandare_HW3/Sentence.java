public class Sentence {

	public static Sentence getInstance() {
		return new Sentence();
	}

	private String premise;
	private String conclusion;
	private boolean isInPremise;
	private boolean isInConclusion;
	private boolean isAFact;
	private String sentenceStr;
	private String unifiedSentence;

	public String getPremise() {
		return premise;
	}

	public void setPremise(String premise) {
		this.premise = premise;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public boolean isInPremise() {
		return isInPremise;
	}

	public void setInPremise(boolean isInPremise) {
		this.isInPremise = isInPremise;
	}

	public boolean isInConclusion() {
		return isInConclusion;
	}

	public void setInConclusion(boolean isInConclusion) {
		this.isInConclusion = isInConclusion;
	}

	public boolean isAFact() {
		return isAFact;
	}

	public void setAFact(boolean isAFact) {
		this.isAFact = isAFact;
	}

	public String getSentenceStr() {
		return sentenceStr;
	}

	public void setSentenceStr(String sentenceStr) {
		this.sentenceStr = sentenceStr;
	}

	private boolean ifUnified;
	private String unifiedString;
	private String unifiedWith;
	private boolean ifUnifiedInQuery;

	public boolean isIfUnified() {
		return ifUnified;
	}

	public void setIfUnified(boolean ifUnified) {
		this.ifUnified = ifUnified;
	}

	public String getUnifiedString() {
		return unifiedString;
	}

	public void setUnifiedString(String unifiedString) {
		this.unifiedString = unifiedString;
	}

	public String getUnifiedWith() {
		return unifiedWith;
	}

	public void setUnifiedWith(String unifiedWith) {
		this.unifiedWith = unifiedWith;
	}

	public String getUnifiedSentence() {
		return unifiedSentence;
	}

	public void setUnifiedSentence(String unifiedSentence) {
		this.unifiedSentence = unifiedSentence;
	}

	public boolean isIfUnifiedInQuery() {
		return ifUnifiedInQuery;
	}

	public void setIfUnifiedInQuery(boolean ifUnifiedInQuery) {
		this.ifUnifiedInQuery = ifUnifiedInQuery;
	}
	
	
}
