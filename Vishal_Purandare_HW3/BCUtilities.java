import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class BCUtilities {
	
	private InputBean inBean;
	
	public static BCUtilities getInstance(InputBean inBean) {
		return new BCUtilities(inBean);
	}
	private BCUtilities(InputBean inBean) {
		this.inBean = inBean;
	}
	public Hashtable<String, Predicate> parseInput() {
		Hashtable<String, Predicate> predicateHashTable = new Hashtable<String, Predicate>();
		HashSet<String> predicates = new HashSet<String>();
		//deriving predicates from the KB: stored in the set predicates
		//loop over all the sentences
		for (String s : this.inBean.getAtomicSentences()) {
			//If it is a clause
			if (s.contains("=>")) {
				String sntcArr[] = s.split("=>");
				String premise = sntcArr[0];
				String conclusion = sntcArr[1];
				String predicate[] = premise.split("&");
				for (String p : predicate) {
					String pred = p.split("\\(")[0];
					predicates.add(pred);
				}
				predicates.add(conclusion.split("\\(")[0]);
			} 
			//If it is the fact
			else {
				String predicate[] = s.split("&");
				for (String p : predicate) {
					String pred = p.split("\\(")[0];
					predicates.add(pred);
				}
			}
		}
		//up till now created the set of unique predicates
		//create the predicate hash table- KB
		for (String p : predicates) {
			List<Sentence> sentForPredicate;
			sentForPredicate = new ArrayList<Sentence>();
			Predicate predicateObj = Predicate.getInstance();
			//loop over all the sentences given check if the sentence contains predicate... then accordingly create the object of Sentences
			for (String s : this.inBean.getAtomicSentences()) {
				if (s.contains(p+"(")) { 
					Sentence stnc = Sentence.getInstance();
					stnc.setSentenceStr(s);
					//If the sentence is clause then check accordingly for predicate
					if (s.contains("=>")) {
						String sntcArr[] = s.split("=>");
						String premise = sntcArr[0];
						String conclusion = sntcArr[1];
						//predicate may be present in both premise and conclusion so below if's handle that
						if (premise.contains(p+"(")) {
							stnc.setPremise(premise);
							stnc.setInPremise(true);
							stnc.setConclusion(conclusion);
						}
						if (conclusion.contains(p+"(")) {
							stnc.setConclusion(conclusion);
							stnc.setInConclusion(true);
							stnc.setPremise(premise);
						}
					}
					//Else part as sentence does not contain =>, so it is the fact
					//If the predicate is the fact it is part of the conclusion itself and we just have to check if it can directly be unified
					else {
						stnc.setAFact(true);
						stnc.setConclusion(s);
						stnc.setPremise(s); //For fact sentences set both conclusion and premise to same
						stnc.setInConclusion(true);
					}
					//Add all the sentences to the list
					sentForPredicate.add(stnc);
				}
			}
			//set the sentences list to the predicateObj
			predicateObj.setSentence(sentForPredicate);
			predicateHashTable.put(p, predicateObj);
		}
		//Predicate Hash Table would be created successfully after this step.
		return predicateHashTable;
	}

	public Sentence performUnification(String queryToProve,
			Sentence sentence, String predicate) {
		boolean ifUnified = false;
		boolean ifBinary = false;
		String sentenceStr = sentence.getConclusion();
		int indexStart = sentenceStr.indexOf(predicate+"(");
		// search for the next index of the ) (closing bracket) starting from
		// the indexStart
		int indexNext = sentenceStr.indexOf(")", indexStart) + 1;
		String predicateFromSentence = sentenceStr.substring(indexStart,
				indexNext);

		// separate arguments from the predicate sentence above
		int i1 = predicateFromSentence.indexOf("(");
		int i2 = predicateFromSentence.indexOf(")");
		String arguments = predicateFromSentence.substring(i1 + 1, i2);
		String argument1 = "";
		String argument2 = "";
		if (arguments.contains(",")) {
			ifBinary = true;
			argument1 = arguments.split(",")[0].trim();
			argument2 = arguments.split(",")[1].trim();
		} else {
			argument1 = arguments;
		}

		// check for the argument of the query
		i1 = queryToProve.indexOf("(");
		i2 = queryToProve.indexOf(")");

		String qArguments = queryToProve.substring(i1 + 1, i2);
		String qArgument1 = "";
		String qArgument2 = "";
		if (qArguments.contains(",")) {
			ifBinary = true;
			qArgument1 = qArguments.split(",")[0].trim();
			qArgument2 = qArguments.split(",")[1].trim();
		} else {
			qArgument1 = qArguments.trim();
		}
		
		String unifiedString = "";
		String unifiedWith = "";
		boolean ifUnifiedInQuery = false;
		
		if (ifBinary) {
			if (qArgument1.equals(argument1) && qArgument2.equals(argument2)) {
				ifUnified = true;
			} else if (qArgument1.equals(argument1)) {
				//argument2.equals("x")
				if(Character.isLowerCase(argument2.charAt(0))) {
					// unify qArgument2 with x
					ifUnified = true;
					unifiedString = argument2;
					unifiedWith = qArgument2;
				}
				//recently added for both ways unification
				else if(Character.isLowerCase(qArgument2.charAt(0))) {
					// unify qArgument2 with x
					ifUnified = true;
					unifiedString = qArgument2;
					unifiedWith = argument2;
					ifUnifiedInQuery = true;
				}
			} else if (qArgument2.equals(argument2)) {
				//argument1.equals("x");
				if (Character.isLowerCase(argument1.charAt(0))) {
					ifUnified = true;
					unifiedString = argument1;
					unifiedWith = qArgument1;
				}
				else if(Character.isLowerCase(qArgument1.charAt(0))) {
					// unify qArgument2 with x
					ifUnified = true;
					unifiedString = qArgument1;
					unifiedWith = argument1;
					ifUnifiedInQuery = true;
				}
			} 
			else {
				ifUnified = false;
			}
		} 
		else {
			// Unary Predicate satisfied
			if (qArgument1.equals(argument1)) {
				ifUnified = true;
			} 
			//Character.isLowerCase(argument1.charAt(0))
			//argument1.equals("x");
			else if (Character.isLowerCase(argument1.charAt(0))) {
				ifUnified = true;
				unifiedString = argument1;
				unifiedWith = qArgument1;
			} 
			//recently added to handle the unification both ways
			else if (Character.isLowerCase(qArgument1.charAt(0))) {
				ifUnified = true;
				unifiedString = qArgument1;
				unifiedWith = argument1;
				ifUnifiedInQuery = true;
			}
			
			else {
				ifUnified = false;
			}
		}
		
		sentence.setIfUnified(ifUnified);
		sentence.setUnifiedString(unifiedString);
		sentence.setUnifiedWith(unifiedWith);
		sentence.setIfUnifiedInQuery(ifUnifiedInQuery);
		
		return sentence;
	}
	
	public ReturnBean proveTheQuery(String queryToProve, Hashtable<String, Predicate> predicatesHashTable, String parentSentenceStr) {
		// code goes down here
		boolean proved = false;
		String queryPredicate = queryToProve.split("\\(")[0]; //extracting the predicate from the query to prove
		
		int id1 = queryToProve.indexOf("(");
		int id2 = queryToProve.indexOf(")");
		String qArguments = queryToProve.substring(id1 + 1, id2);
		String qArgument1 = "";
		String qArgument2 = "";
		
		boolean isVariableAt1 = false;
		boolean isVariableAt2 = false;
		
		if (qArguments.contains(",")) {
			qArgument1 = qArguments.split(",")[0].trim();
			qArgument2 = qArguments.split(",")[1].trim();

			if(Character.isLowerCase(qArgument1.charAt(0))) {
				isVariableAt1 = true;
			}
			if(Character.isLowerCase(qArgument2.charAt(0))) {
				isVariableAt2 = true;
			}
		} 
		else {
			qArgument1 = qArguments;
			if(Character.isLowerCase(qArgument1.charAt(0))) {
				isVariableAt1 = true;
			}
		}
			
		ReturnBean ret = new ReturnBean();
		
		Set<String> keys = predicatesHashTable.keySet();
		for (String key : keys) {
			if (key.equals(queryPredicate)) { //Only if the predicate from map key matches the queryPredicate
				Predicate prObj = predicatesHashTable.get(key);
				List<Sentence> s = prObj.getSentence(); //list of the sentences from the predicate object
				
				for (Sentence sentence : s) {
					// For the query to be true it either has to be part of below 2 cases: (1) In fact (2) In the conclusion of the atomic sentence
					// (1) check if the query is present as a FACT in the KB
					if (sentence.isAFact()) {
						//If the sentence is the fact... It can directly be unified as it can not contain variable
						// check for the argument for the query
						sentence = performUnification(queryToProve, sentence, key);
						boolean canBeUnified = sentence.isIfUnified();
						if(canBeUnified) {
							//when the sentence can be unified with the query... replace all the variables from query to the sentence
							//As this is the fact prove all the premises if it contains &
							//String[] premises = sentence.getPremise().split("&");
							ret.setUnifiedString(sentence.getUnifiedString());
							ret.setUnifiedWith(sentence.getUnifiedWith());
							ret.setIfProved(canBeUnified);
							boolean ifOk = true;
							if(!ret.getUnifiedWith().equals("")) {
								ifOk = checkTheSentence(sentence, predicatesHashTable, parentSentenceStr);
							}
							if(!ifOk) {
								continue;
							}
							return ret;
						}
					}
					
					// If not in the case (1) then check for the query to be in conclusion of some sentence
					// (2) check if the query is present as the premise in the KB
					else if (sentence.isInConclusion()) {
						//check if the conclusion can be unified...if it is then prove premises
						//check if the query can be unified with the conclusion we found, then only we can move ahead with proof and unification of the premise
						
						sentence = performUnification(queryToProve, sentence, key);
						boolean canBeUnified = sentence.isIfUnified();
						
						if(canBeUnified) {
							//when the sentence can be unified with the query... replace all the variables from query to the sentence
							boolean isProved = false;
							//String[] premises = sentence.getPremise().split("&");
							String unifiedSentence = sentence.getPremise();
							if(!sentence.isIfUnifiedInQuery()) {
								unifiedSentence = performSubstitution(sentence, queryToProve);
								ret.setUnifiedString(sentence.getUnifiedString());
								ret.setUnifiedWith(sentence.getUnifiedWith());
							}
							String[] premises = unifiedSentence.split("&");
							String strUnified ="";
							String strUnifiedWith="";
							for (String premise : premises) {
								if(ret != null) {
									if(ret.isIfProved()) {
										if(strUnified.equals("") && strUnifiedWith.equals("")){
											strUnified = ret.getUnifiedString();
											strUnifiedWith = ret.getUnifiedWith();
										}
										if(strUnified != null && strUnifiedWith != null) {
											if(!strUnified.equals("") && !strUnifiedWith.equals("")) {
												//premise = premise.replaceAll(ret.getUnifiedString(), ret.getUnifiedWith());
												int i1 = premise.indexOf("(");
												int i2 = premise.indexOf(")");
												String arguments = premise.substring(i1 + 1, i2);
												String argument1 = "";
												String argument2 = "";
												if (arguments.contains(",")) {
													argument1 = arguments.split(",")[0].trim();
													argument2 = arguments.split(",")[1].trim();
												} 
												else {
													argument1 = arguments;
												}
												if(Character.isLowerCase(argument1.charAt(0))) {
													premise = premise.replaceAll(argument1, strUnifiedWith);
												}
												if(!argument2.equals("")) {
													if(Character.isLowerCase(argument2.charAt(0))) {
														premise = premise.replaceAll(argument2, strUnifiedWith);
													}
												}
											}
										}
									}
								}
								ret = proveTheQuery(premise, predicatesHashTable, sentence.getSentenceStr());
								isProved = ret.isIfProved();
								if(!isProved) {
									proved = isProved;
									ret.setIfProved(proved);
									break;
								}
							}
							proved = isProved;
							if(proved){
								if(queryToProve.equals(this.inBean.getQuerySentence())) {
									break;
								}
								else {
									String conclusion = sentence.getConclusion();
									int i1 = conclusion.indexOf("(");
									int i2 = conclusion.indexOf(")");
									String arguments = conclusion.substring(i1 + 1, i2);
									String argument1 = "";
									String argument2 = "";
									boolean ifSetUnified = false;
									if (arguments.contains(",")) {
										argument1 = arguments.split(",")[0].trim();
										argument2 = arguments.split(",")[1].trim();
									} 
									else {
										argument1 = arguments;
									}
									if(Character.isLowerCase(argument1.charAt(0))) {
										conclusion = conclusion.replaceAll(argument1, strUnifiedWith);
										ret.setUnifiedString(argument1);
										ifSetUnified = true;
									}
									if(!argument2.equals("")) {
										if(Character.isLowerCase(argument2.charAt(0))) {
											conclusion = conclusion.replaceAll(argument2, strUnifiedWith);
											ret.setUnifiedString(argument2);
											ifSetUnified = true;
										}
									}
									if(!ifSetUnified) {
										if(isVariableAt1) {
											ret.setUnifiedWith(argument1);
										}
										if(isVariableAt2) {
											ret.setUnifiedWith(argument2);
										}
									}
									break;
								}
							}
						}
					}
				}
			}
		}
		return ret;
	}
	
	//To check if substitution is valid 
	private boolean checkTheSentence(Sentence sentence, Hashtable<String, Predicate> predicatesHashTable, String parentSentenceStr) {
		String unifiedWith = sentence.getUnifiedWith();
		String originalQueryToProve = this.inBean.getQuerySentence();
		String queryPredicate = originalQueryToProve.split("\\(")[0]; //extracting the predicate from the query to prove
		ReturnBean ret= new ReturnBean();
		Set<String> keys = predicatesHashTable.keySet();
		for (String key : keys) {
			if (key.equals(queryPredicate)) { //Only if the predicate from map key matches the queryPredicate
				Predicate prObj = predicatesHashTable.get(key);
				List<Sentence> s = prObj.getSentence(); //list of the sentences from the predicate object
				for (Sentence sent : s) {
					// For the query to be true it either has to be part of below 2 cases: (1) In fact (2) In the conclusion of the atomic sentence
					// (1) check if the query is present as a FACT in the KB
					if(sent.isInConclusion()) {
						String sentenceString = sent.getSentenceStr();
						if(sentenceString.equals(parentSentenceStr)) {
							if(sentenceString.contains("=>")) {
								String premise = sentenceString.split("=>")[0];
								String[] indPremises = premise.split("&");
								for (String p : indPremises) {
									int i1 = p.indexOf("(");
									int i2 = p.indexOf(")");
									String arguments = p.substring(i1 + 1, i2);
									String argument1 = "";
									String argument2 = "";
									
									if (arguments.contains(",")) {
										argument1 = arguments.split(",")[0].trim();
										argument2 = arguments.split(",")[1].trim();
									} 
									else {
										argument1 = arguments;
									}
									if(argument1.equals("x")) {
										p = p.replaceAll(argument1, unifiedWith);
									}
									if(argument2.equals("x")) {
										p = p.replaceAll(argument2, unifiedWith);
									}
									if(!p.equals(sentence.getSentenceStr())) {
										 ret = proveTheQuery(p, predicatesHashTable, p);
										 if(!ret.isIfProved()) {
											 return false;
										 }
									}
								}
								return true;
							}
						}
						else {
							if(parentSentenceStr.contains("=>")){
								String parentPremises =  parentSentenceStr.split("=>")[0];
								if(parentPremises.contains("&")) {
									String[] indPremises = parentPremises.split("&");
									for (String p : indPremises) {
										int i1 = p.indexOf("(");
										int i2 = p.indexOf(")");
										String arguments = p.substring(i1 + 1, i2);
										String argument1 = "";
										String argument2 = "";
										
										if (arguments.contains(",")) {
											argument1 = arguments.split(",")[0].trim();
											argument2 = arguments.split(",")[1].trim();
										} 
										else {
											argument1 = arguments;
										}
										if(argument1.equals("x")) {
											p = p.replaceAll(argument1, unifiedWith);
										}
										if(argument2.equals("x")) {
											p = p.replaceAll(argument2, unifiedWith);
										}
										if(!p.equals(sentence.getSentenceStr())) {
											 ret = proveTheQuery(p, predicatesHashTable, p);
											 if(!ret.isIfProved()) {
												 return false;
											 }
										}
									}
								}
								String parentConclusion =  parentSentenceStr.split("=>")[1];
								boolean ifPlacedInParentConclusion = false;
								
								int i1 = parentConclusion.indexOf("(");
								int i2 = parentConclusion.indexOf(")");
								String arguments = parentConclusion.substring(i1 + 1, i2);
								String argument1 = "";
								String argument2 = "";
								
								if (arguments.contains(",")) {
									argument1 = arguments.split(",")[0].trim();
									argument2 = arguments.split(",")[1].trim();
								} 
								else {
									argument1 = arguments;
								}
								if(argument1.equals("x")) {
									parentConclusion = parentConclusion.replaceAll(argument1, unifiedWith);
									ifPlacedInParentConclusion = true;
								}
								if(argument2.equals("x")) {
									parentConclusion = parentConclusion.replaceAll(argument2, unifiedWith);
									ifPlacedInParentConclusion = true;
								}
								if(ifPlacedInParentConclusion) {
									if(sentenceString.contains("=>")) {
										String premise = sentenceString.split("=>")[0];
										String[] indPremises2 = premise.split("&");
										for (String p : indPremises2) {
											int id1 = p.indexOf("(");
											int id2 = p.indexOf(")");
											String args = p.substring(id1 + 1, id2);
											String arg1 = "";
											String arg2 = "";
											
											if (args.contains(",")) {
												arg1 = args.split(",")[0].trim();
												arg2 = args.split(",")[1].trim();
											} 
											else {
												arg1 = args;
											}
											if(arg1.equals("x")) {
												p = p.replaceAll(arg1, unifiedWith);
											}
											if(arg2.equals("x")) {
												p = p.replaceAll(arg2, unifiedWith);
											}
											if(!p.equals(parentConclusion)) {
												 ret = proveTheQuery(p, predicatesHashTable, p);
												 if(!ret.isIfProved()) {
													 return false;
												 }
											}
										}
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	private String performSubstitution(Sentence sentence, String queryToProve) {
		String premiseStr = sentence.getPremise();
		String unifiedStr = sentence.getUnifiedString();
		String unifiedWith = sentence.getUnifiedWith();
		String premiseStrNew = "";
		if(!unifiedStr.equals("") && !unifiedWith.equals("")) {
			//premiseStr = premiseStr.replaceAll(unifiedStr, unifiedWith);
			//replace only variables
			String[] premises = premiseStr.split("&");
			for (String premise : premises) {
				int i1 = premise.indexOf("(");
				int i2 = premise.indexOf(")");
				String arguments = premise.substring(i1 + 1, i2);
				String argument1 = "";
				String argument2 = "";
				if (arguments.contains(",")) {
					argument1 = arguments.split(",")[0].trim();
					argument2 = arguments.split(",")[1].trim();
				} 
				else {
					argument1 = arguments;
				}
				if(Character.isLowerCase(argument1.charAt(0))) {
					premise = premise.replaceAll(argument1, unifiedWith);
				}
				if(!argument2.equals("")) {
					if(Character.isLowerCase(argument2.charAt(0))) {
						premise = premise.replaceAll(argument2, unifiedWith);
					}
				}			
				if(premiseStrNew.equals("")) {
					premiseStrNew = premise;
				}
				else {
					premiseStrNew = premiseStrNew + "&" + premise;
				}
			}
		}
		if(premiseStrNew.equals("")) {
			return premiseStr;
		}
		else {
			return premiseStrNew;
		}
	}
}
