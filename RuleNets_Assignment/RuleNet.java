/**
 * RuleNet.java
 * A rule net for forward chaining
 * @author Phil Green  Boxuan Shan
 * This Version 5/5/2019
 */

 import java.util.*;
 import java.io.*;
 import sheffield.*;

 public class RuleNet {

 	private ArrayList<RuleNode> rulenodes;        // the nodes in the network
 	private ArrayList<String> factsToPropagate; //facts not yet propagated
 	private ArrayList<ArrayList<RuleNode>> targetNodeLists; //each element is list of nodes which corresponding fact should be propagated to
 	EasyWriter scr = new EasyWriter();

 	/**
 	 * constructor is given the RuleNodes in the network
 	 */
 	public RuleNet (ArrayList<RuleNode> a){  //constructor is given the RuleNodes
 		rulenodes = a;
 	}

 	/**
 	 * accessor for the RuleNodes
 	 */
    public ArrayList<RuleNode> getRules() {return rulenodes;}

    /**
	 * Initialise all the RuleNodes
	 * to contain a single token: all antecedents, empty context
	 */
    public void initialise() { //initialise the rules - set initial tokens
        for (RuleNode r: rulenodes){
    	    r.initialise(); //set initial tokens in each node
            factsToPropagate=new ArrayList<String>(); //set toPropagate & targetNodes empty
            targetNodeLists = new ArrayList<ArrayList<RuleNode>>();
        }
    }

    /**
     * add a fact and make all the deductions
     * by calling self.propagate
     */
    public void addFact(String f){ // add a fact & make all deductions
        factsToPropagate.add(f);
        targetNodeLists.add(rulenodes); //propagate to all nodes
        propagate();
    }

    /**
     * drive the propagation of facts
     * take next fact from factsToPropagate
     * take the list of nodes to propagate it to from targetNodeLists
     * do this using the node's propagate method
     * if this results in deductions, then for each deduction
     *    print it out
     *    add it to factsToPropagate
     *    add the rulenode's successors list to targetNodeLists
     * continue until no more facts to propagate
     */
    public void propagate(){ //drive the propagation of facts
        // TO BE WRITTEN
        while(!factsToPropagate.isEmpty()){

            String fact = factsToPropagate.get(0);
            factsToPropagate.remove(0);

            ArrayList<RuleNode> ruleNodes = targetNodeLists.get(0);
            targetNodeLists.remove(0);

            for(RuleNode ruleNode : ruleNodes){
                if(ruleNode.propagate(fact)){
                    ArrayList<String> deductions = ruleNode.getDeductions();
                    for(String d : deductions){
                        scr.println("deduction: " + d);
                        factsToPropagate.add(d);
                        targetNodeLists.add(ruleNode.getSuccessors());
                    }
                }
            }
        }

    }
}



