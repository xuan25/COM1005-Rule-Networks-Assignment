
/**
 * RunRuleNet.java
 * create a rule network & make deductions with it
 * @author Phil Green  Boxuan Shan
 * This Version 5/5/2019
 */

import sheffield.*;
import java.util.*;
import pmatch.*;


public class RunRuleNet{
 public static void main(String[] arg) {
   // create object for output
   EasyWriter scr = new EasyWriter();

   //make some rules

   //nephew
   ArrayList<String> nAnties = new ArrayList<String>();
   nAnties.add("?s is an aunt-or-uncle of ?c");
   nAnties.add("?c is male");
   RuleNode nRule = new RuleNode ("nephew rule", nAnties, "?c is a nephew of ?s");
   ArrayList<RuleNode> nSuccs = new ArrayList<RuleNode>();
   nRule.setSuccessors(nSuccs);

   //cousin
   ArrayList<String> cAntes = new ArrayList<String>();
   cAntes.add("?s is an aunt-or-uncle of ?c1");
   cAntes.add("?s is a parent of ?c2");
   RuleNode cRule = new RuleNode ("cousin rule", cAntes, "?c1 is a cousin of ?c2");
   ArrayList<RuleNode> cSuccs = new ArrayList<RuleNode>();
   cRule.setSuccessors(cSuccs);

   //aunt
   ArrayList<String> aAntes = new ArrayList<String>();
   aAntes.add("?s is an aunt-or-uncle of ?c");
   aAntes.add("?s is female");
   RuleNode aRule = new RuleNode ("aunt rule", aAntes, "?s is an aunt of ?c");
   ArrayList<RuleNode> aSuccs = new ArrayList<RuleNode>();
   aRule.setSuccessors(aSuccs);

   //uncle
   ArrayList<String> uAntes = new ArrayList<String>();
   uAntes.add("?s is an aunt-or-uncle of ?c");
   uAntes.add("?s is male");
   RuleNode uRule = new RuleNode ("uncle rule", uAntes, "?s is an uncle of ?c");
   ArrayList<RuleNode> uSuccs = new ArrayList<RuleNode>();
   uRule.setSuccessors(uSuccs);

   //aunt or uncle
   ArrayList<String> auAntes = new ArrayList<String>();
   auAntes.add("?p is a parent of ?c");
   auAntes.add("?s is a sibling of ?p");
   RuleNode auRule = new RuleNode ("aunt-or-uncle rule", auAntes, "?s is an aunt-or-uncle of ?c");
   ArrayList<RuleNode> auSuccs = new ArrayList<RuleNode>();
   auSuccs.add(uRule);
   auSuccs.add(aRule);
   auSuccs.add(cRule);
   auSuccs.add(nRule);
   auRule.setSuccessors(auSuccs);

   //grandmother
   ArrayList<String> gmAntes = new ArrayList<String>();
   gmAntes.add("?gf is a grandparent of ?c");
   gmAntes.add("?gf is female");
   RuleNode gmRule = new RuleNode ("grandmother rule", gmAntes, "?gf is the grandmother of ?c");
   ArrayList<RuleNode> gmSuccs = new ArrayList<RuleNode>();
   gmRule.setSuccessors(gmSuccs);

   //grandfather
   ArrayList<String> gfAntes = new ArrayList<String>();
   gfAntes.add("?gf is a grandparent of ?c");
   gfAntes.add("?gf is male");
   RuleNode gfRule = new RuleNode ("grandfather rule", gfAntes, "?gf is the grandfather of ?c");
   ArrayList<RuleNode> gfSuccs = new ArrayList<RuleNode>();
   gfRule.setSuccessors(gfSuccs);

   //grandparent
   ArrayList<String> gpAntes = new ArrayList<String>();
   gpAntes.add("?gf is a parent of ?p");
   gpAntes.add("?p is a parent of ?c");
   RuleNode gpRule = new RuleNode ("grandparent rule", gpAntes, "?gf is a grandparent of ?c");
   ArrayList<RuleNode> gpSuccs = new ArrayList<RuleNode>();
   gpSuccs.add(gfRule);
   gpSuccs.add(gmRule);
   gpRule.setSuccessors(gpSuccs);

   //sister
   ArrayList<String> sisAntes = new ArrayList<String>();
   sisAntes.add("?c1 is a sibling of ?c2");
   sisAntes.add("c1 is female");
   sisAntes.add("c2 is female");
   RuleNode sisRule = new RuleNode ("sister rule", sisAntes, "c1 is a sister of c2");
   ArrayList<RuleNode> sisSuccs = new ArrayList<RuleNode>();
   sisRule.setSuccessors(sisSuccs);

   //brother
   ArrayList<String> broAntes = new ArrayList<String>();
   broAntes.add("?c1 is a sibling of ?c2");
   broAntes.add("c1 is male");
   broAntes.add("c2 is male");
   RuleNode broRule = new RuleNode ("brother rule", broAntes, "c1 is a brother of c2");
   ArrayList<RuleNode> broSuccs = new ArrayList<RuleNode>();
   broRule.setSuccessors(broSuccs);

   //sibling
   ArrayList<String> sibAntes = new ArrayList<String>();
   sibAntes.add("?p is a parent of ?c1");
   sibAntes.add("?p is a parent of ?c2");
   RuleNode sibRule = new RuleNode ("sibling rule", sibAntes, "?c1 is a sibling of ?c2");
   ArrayList<RuleNode> sibSuccs = new ArrayList<RuleNode>();
   sibSuccs.add(broRule);
   sibSuccs.add(sisRule);
   sibSuccs.add(auRule);
   sibRule.setSuccessors(sibSuccs);

   //mother is female
   ArrayList<String> mfAntes = new ArrayList<String>();
   mfAntes.add("?m is the mother of ?c");
   RuleNode mfRule = new RuleNode ("mother-is-female rule", mfAntes, "?m is female");
   ArrayList<RuleNode> mfSuccs = new ArrayList<RuleNode>();
   mfSuccs.add(sisRule);
   mfSuccs.add(gmRule);
   mfSuccs.add(aRule);
   mfRule.setSuccessors(mfSuccs);

   //father is male
   ArrayList<String> fmAntes = new ArrayList<String>();
   fmAntes.add("?f is the father of ?c");
   RuleNode fmRule = new RuleNode ("father-is-male rule", fmAntes, "?f is male");
   ArrayList<RuleNode> fmSuccs = new ArrayList<RuleNode>();
   fmSuccs.add(broRule);
   fmSuccs.add(gfRule);
   fmSuccs.add(uRule);
   fmSuccs.add(nRule);
   fmRule.setSuccessors(fmSuccs);

   //mother is parent
   ArrayList<String> mpAntes = new ArrayList<String>();
   mpAntes.add("?m is the mother of ?c");
   RuleNode mpRule = new RuleNode ("mother-is-parent rule", mpAntes, "?m is a parent of ?c");
   ArrayList<RuleNode> mpSuccs = new ArrayList<RuleNode>();
   mpSuccs.add(sibRule);
   mpSuccs.add(gpRule);
   mpSuccs.add(auRule);
   mpSuccs.add(cRule);
   mpRule.setSuccessors(mpSuccs);

   //father is parent
   ArrayList<String> fpAntes = new ArrayList<String>();
   fpAntes.add("?f is the father of ?c");
   RuleNode fpRule = new RuleNode ("father-is-parent rule", fpAntes, "?f is a parent of ?c");
   ArrayList<RuleNode> fpSuccs = new ArrayList<RuleNode>();
   fpSuccs.add(sibRule);
   fpSuccs.add(gpRule);
   fpSuccs.add(auRule);
   fpSuccs.add(cRule);
   fpRule.setSuccessors(fpSuccs);


   // the set of rulenodes

   ArrayList<RuleNode> rset = new ArrayList<RuleNode>();
   rset.add(fpRule);
   rset.add(mpRule);
   rset.add(fmRule);
   rset.add(mfRule);
   rset.add(sibRule);
   rset.add(broRule);
   rset.add(sisRule);
   rset.add(gpRule);
   rset.add(gfRule);
   rset.add(gmRule);
   rset.add(auRule);
   rset.add(uRule);
   rset.add(aRule);
   rset.add(cRule);
   rset.add(nRule);


   // make the rule net
   RuleNet rs = new RuleNet(rset);
   //initialise it - set up initial tokens
   rs.initialise();

   //add facts
   long startTime = System.currentTimeMillis();

   rs.addFact("Jill is the mother of David");
   rs.addFact("Jill is the mother of Shula");
   rs.addFact("David is the father of Pip");
   rs.addFact("Shula is the mother of Daniel");

   long stopTime = System.currentTimeMillis();

   scr.println("compute time (ms) " + (stopTime-startTime));
  }
}