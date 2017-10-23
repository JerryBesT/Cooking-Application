/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          GroceryMatch
// FILE:             GroceryList.java
//
// TEAM:    individual
// Author:  Zhenyu Zou
// email: zzou24@wisc.edu
// netID: 907 593 6980
// Lecture number: 001
//
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: Identify persons by name, relationship to you, and email. 
// Describe in detail the the ideas and help they provided. 
// 
// Online sources: Piazza Discussions
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class GroceryList implements ListADT<Ingredient>  {

	private ArrayList<Ingredient> allIngredient = new ArrayList<Ingredient>();
	
	@Override
	public Iterator<Ingredient> iterator() {
		
		return allIngredient.iterator();
	}

	@Override
	public void add(Ingredient item) {
		allIngredient.add(item);
	}

	@Override
	public void add(int pos, Ingredient item) {
		allIngredient.add(pos , item);
	}

	@Override
	public boolean contains(Ingredient item) {

		return allIngredient.contains(item);
	}

	@Override
	public int size() {
		return allIngredient.size();
	}

	@Override
	public boolean isEmpty() {
		return allIngredient.isEmpty();
	}

	@Override
	public Ingredient get(int pos) {
		return allIngredient.get(pos);
	}

	@Override
	public Ingredient remove(int pos) {
		return allIngredient.remove(pos);
	}
	
	public String toString(){
		return allIngredient.toString();
	}

	
    // you may use an ArrayList<Ingredient> as your internal data structure
    
    
}
