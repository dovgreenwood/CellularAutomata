package com.dovgreenwood.CellularAutomata;

import java.util.ArrayList;

public class Rule {

	private ArrayList<Integer> deathRules = new ArrayList<Integer>();
	
	public Rule() {
	}
	/*
	 * constructor
	 * @param deathRules	the states which will lead to death
	 * */
	public Rule(ArrayList<Integer> deathRules) {
		this.deathRules = deathRules;
	}
	
	/*
	 * willBe
	 * @param states	the number of live cells surrounding the cell
	 * @return boolean	whether the cell should be dead (false) or alive (true)
	 * */
	public boolean willBe(int states) {
		for(Integer rule : deathRules)
			if(rule.equals(states)) 
				return false; //if it is a death rule, return false (dead)
		return true;
	}
	
}
