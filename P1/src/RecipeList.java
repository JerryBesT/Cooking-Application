/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          GroceryMatch
// FILE:             RecipeList.java
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

public class RecipeList implements ListADT<Recipe> 
{

	private ArrayList<Recipe> allRecipe = new ArrayList<Recipe>();
	
	@Override
	public Iterator<Recipe> iterator() {
		return allRecipe.iterator();
	}

	@Override
	public void add(Recipe item) {
		allRecipe.add(item);
	}

	@Override
	public void add(int pos, Recipe item) {
		allRecipe.add(pos , item);
	}

	@Override
	public boolean contains(Recipe item) {
		return allRecipe.contains(item);
	}

	@Override
	public int size() {
		return allRecipe.size();
	}

	@Override
	public boolean isEmpty() {
		return allRecipe.isEmpty();
	}

	@Override
	public Recipe get(int pos) {
		return allRecipe.get(pos);
	}

	@Override
	public Recipe remove(int pos) {
		return allRecipe.remove(pos);
	}
	
	public String toString() {
		return allRecipe.toString();
	}
    
    // You may use an ArrayList<Recipe> as your internal data structure
    
}
