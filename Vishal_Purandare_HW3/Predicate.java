import java.util.List;

public class Predicate {

	private List<Sentence> sentence;
	
	public static Predicate getInstance() {
		return new Predicate();
	}
	public List<Sentence> getSentence() {
		return sentence;
	}
	public void setSentence(List<Sentence> sentence) {
		this.sentence = sentence;
	}	
}
