/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          GroceryMatch
// FILE:             Loaders.java
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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class provides the file reader methods for reading ingredient data files and recipe data files 
 * for the GroceryMatch program.  Do not change method signatures.  Do implement the missing method bodies.
 */
public class Loaders {
    
    // DO NOT CHANGE THESE CLASS CONSTANTS
    public static final String GROCERY_FILE_IO_ERROR_MSG = "IOError when loading grocery lists";
    public static final String RECIPE_FILE_IO_ERROR_MSG = "IOError when loading recipes";
    public static final String OUTPUT_FILENAME = "remaining.txt" ;
    
    /**
     * 1. Load groceries from file, each line of the file indicates an ingredient and its quantity.
     * 2. Each ingredient is in the format of "name : quantity", the number of spaces between name, colon and quantity can be any.
     *    And there may be leading and trailing spaces in a line.
     * 3. Name of ingredient may have duplicate, this means there may be multiple lines with the same ingredient name.
     *    If names are duplicated, their quantities should be summed up.
     * 4. If a line does not match the above mentioned format, ignore the line and continue reading the next line of ingredients.
     * 5. If an IOException happens, print GROCERY_FILE_IO_ERROR_MSG, and throw the exception.
     * 
     * @param filename The name of the file that contains the list of ingredients for the grocery.
     * @return A grocery list that includes all of the ingredients that were were properly read from the file.
     * @throws IOException if the filename does not exist, the error msg is displayed and the exception is thrown to calling method
     */
    public static GroceryList loadGroceriesFromFile(String filename) throws IOException {
    	
    	GroceryList allitems = new GroceryList();
   
    	try{
    	File ingredients = new File(filename);
    		if(!ingredients.exists())
    			throw new IOException();
    		// Read the file and print in proper form
    		Scanner input = new Scanner(ingredients);
    		while(input.hasNextLine())
    		{
    			String currLine = input.nextLine();
    			String[] x = currLine.split(":");
    			if(!currLine.contains(":"))
    				continue;
    			else if(currLine.isEmpty())
    				continue;
    			else if(x.length > 2)
    				continue;
    			else
    			{
    				String temp = "";
    				// file source error handling
    				for(int i=0;i < x[1].length();i++)
    				{
    					if(Character.isDigit(x[1].charAt(i)) || x[1].charAt(i) == 46)
    						temp = temp + x[1].charAt(i);
    				}
    				double y = Double.parseDouble(temp);
    				allitems.add(new Ingredient(x[0].trim() , y));
    			}
    		}

    		// Search for duplicates and sums the quantity up for the same item.
    		for (int i = 0 ; i < allitems.size(); i ++) {
    			Ingredient a = allitems.get(i);
    			Iterator<Ingredient> SearchforDup = allitems.iterator();
    			while (SearchforDup.hasNext()) {
    				Ingredient b = SearchforDup.next();
    				// compare names
    				if (!b.equals(a) && b.getName().toLowerCase().equals(a.getName().toLowerCase())) {
    					SearchforDup.remove();
    					a.setQuantity(a.getQuantity() + b.getQuantity() );
    				}
    			}
    		}
    		
    		input.close();
    		return allitems;
    		}catch(IOException e){
        		System.out.println(GROCERY_FILE_IO_ERROR_MSG);
        		return allitems;
        	}
    	
    }
    
    /**
     * 1. Load recipes from file, each line of the file indicates a recipe.
     * 2. Each recipe is in the format "name -> ingredient1-name: ingredient1-quantity, ingredient2-name: ingredient2-quantity"
     * 3. The number of ingredients in a recipe can be any.
     * 4. The number of spaces between name and quantity can be any, and there may be leading and trailing spaces.
     * 5. For simplicity, names of recipes will not have duplication, names of ingredients in a recipe will not have duplication, the format of the recipe is guaranteed to be correct.
     * 6. Names of ingredients might not be in GroceryList, this means you need to buy this ingredient if you want to use this recipe.
     * 7. If an IOException happens, print RECIPE_FILE_IO_ERROR_MSG, and throw the exception.
     * 
     * @param filename The name of a file containing recipe information.
     * @return A recipe list containing the recipes read from the named file.
     * @throws IOException if the filename does not exist, the error msg is displayed and the exception is thrown to calling method
     */
    public static RecipeList loadRecipesFromFile( String filename ) throws IOException {

    	RecipeList allRecipe = new RecipeList();
    	
    	try{
        	File RecipesText = new File(filename);
        		if(!RecipesText.exists())
        			throw new IOException();
        		
        		// read text from the file and store in a proper format
        		Scanner input = new Scanner(RecipesText);
        		while(input.hasNextLine())
        		{   
	    			String l = input.nextLine();
	
	    			if(!l.equals("")) 
	    			{
	    				// split the line into parts
	    				String[] elements = l.split(" -> ");
	    				String recipe = elements[0];
	    				String[] ingredients = elements[1].split(", ");
	    		    	ArrayList<Ingredient> Allitems = new ArrayList<Ingredient>();
	
	    		    	// initialize a new ingredient
	    				for(int i = 0; i < ingredients.length; i++) {
	    					String name = ingredients[i].split(": ")[0];
	    					Double quantity = Double.parseDouble(ingredients[i].split(": ")[1]);
	    					Allitems.add(new Ingredient(name, quantity));
	    				}
	    				// add the new ingredient into recipe list
	    				allRecipe.add(new Recipe(recipe, Allitems));
        			}
        		}
        		
        		input.close();
        		return allRecipe;
        	}catch(IOException e)
    			{
            		System.out.println(RECIPE_FILE_IO_ERROR_MSG);
            		return allRecipe;
            	}
    	
    	
    	
    }

    /** 
     * Write the GroceryList items to the specified file.
     *
     * Each ingredient is written to the file in the order that the ingredient is found in the GrocerList
     * the format for each line is:
     *
     * ingredient_name: amount
     *
     * @param grocery list of ingredients
     * @param name of the file to write them to.
     */
    public static void write(GroceryList groceries, String filename) 
    {
    	try{
    		FileWriter write = new FileWriter(filename);
    		PrintWriter out = new PrintWriter(write);
    		// write it in the file line by line in proper format
    		for(int i=0;i < groceries.size();i++)
    		{
    			out.println("ingredient_" + groceries.get(i).getName() + ": " + groceries.get(i).getQuantity());
    		}
    		out.close();
    	}catch(IOException e)
    	{
    		System.out.println();
    	}
    	
    }
    

}
