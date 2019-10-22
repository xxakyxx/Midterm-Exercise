package pkgGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import pkgEnum.ePuzzleViolation;

public class LatinSquare {

	/**
	 * LatinSquare - two dimensional array that's the core of the puzzle
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 */
	private int[][] LatinSquare;

	/**
	 * bIgnoreZero - should I ignore zero when doing checks...?
	 * @version 1.0
	 * @since Midterm #1
	 */
	private boolean bIgnoreZero;

	/**
	 * PV - ArrayList to collect PuzzleViolations
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 */
	private ArrayList<PuzzleViolation> PV = new ArrayList<PuzzleViolation>();
	
	/**
	 * No-arg constructor, make it public, don't do anything in the constructor
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 */
	public LatinSquare() {

	}

	/**
	 * Pass in a given puzzle, set the LatinSquare
	 * 
	 * @param puzzle Pass in given LatinSquare puzzle (probably for testing)
	 * @version 1.0
	 * @since Midterm #1
	 */

	public LatinSquare(int[][] puzzle) {
		this.LatinSquare = puzzle;
	}

	/**
	 * return LatinSquare instance attribute
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @return - returns the instance of the LatinSquare
	 */
	public int[][] getLatinSquare() {
		return LatinSquare;
	}

	/**
	 * set LatinSquare instance attribute
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param latinSquare - pass in an instance of a possible LatinSquare
	 */

	public void setLatinSquare(int[][] latinSquare) {
		LatinSquare = latinSquare;
	}

	/**
	 * Should the processing ignore zeros (hasDuplicates, etc)
	 * @version 1.0
	 * @since Midterm #1
	 * @return
	 */
	public boolean isbIgnoreZero() {
		return bIgnoreZero;
	}

	/**
	 * Should the processing ignore zeros (hasDuplicates, etc)
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param bIgnoreZero
	 */
	protected void setbIgnoreZero(boolean bIgnoreZero) {
		this.bIgnoreZero = bIgnoreZero;
	}

	/**
	 * getColumn - return a one-dimension array from a given column in the
	 * LatinSquare <br>
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 *
	 *
	 * In this example, getColumn(1) would return [1],[2],[4],[1]
	 *
	 * @version 1.0
	 * @since Midterm #1
	 * @param iCol The column you want returned from the two-dimensional array
	 * @return one dimensional array of values for the given column
	 */
	public int[] getColumn(int iCol) {
		int n = this.LatinSquare.length;
		int[] res = new int[n];
		for(int i = 0; i < n; i++) {
			res[i] = this.LatinSquare[i][iCol];
		}
		return res;
		// FIXME: Return a given column from LatinSquare
	}

	/**
	 * getRow - return a one-dimension array from a given row in the LatinSquare<br>
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * In this example, getRow(1) would return [1],[2],[3],[4]
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param iRow given row of the two dimensional array to return
	 * @return one dimensional array of the given row
	 */
	public int[] getRow(int iRow) {
		// FIXME: Return a given row from LatinSquare
		int n = this.LatinSquare[0].length;
		int[] res = new int[n];
		for(int i = 0;i < n; i++) {
			res[i] = this.LatinSquare[iRow][i];
		}
		return res;
	}

	/**
	 * Pass in a one-dimension array, pass back true if there is a duplicate value
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param arr - one dimensional array to check for duplicates
	 * @return - returns 'true' if any of the elements are duplicate
	 */
	public boolean hasDuplicates(int[] arr) {
		Set<Integer> visited = new HashSet<>();
		for(int i : arr) {
			if(!visited.add(i))
				return true;
		}
		return false;
		// TODO: Return 'true' if any element has a duplicate.  
		//		 If bIgnnoreZero is true, don't consider zeros when checking for duplicat
	}

	/**
	 * hasDuplicates - overloaded, checking the entire puzzle for a duplicate
	 * @version 1.0
	 * @since Midterm #1
	 * @return Return 'true' if any element in column or row is duplicate
	 */
	protected boolean hasDuplicates() {
		
		//	Check if anything in the row is duplicate
		for (int i = 0; i < LatinSquare.length; i++) {
			if (hasDuplicates(getRow(i)))
				AddPuzzleViolation(new PuzzleViolation(ePuzzleViolation.DupRow, i));
		}
		
		for(int i = 0; i < LatinSquare[0].length; i++) {
			if(hasDuplicates(getColumn(i)))
				AddPuzzleViolation(new PuzzleViolation(ePuzzleViolation.DupCol, i));
		}
		//	TODO: Check if anything in the column is duplicate
		//			If there's a duplicate in the column, call AddPuzzleViolation(pv)


		//	FIXME: Return if PV.size > 0 then return true
		return this.PV.size() > 0;
	}

	/**
	 * Remove any zeros in an array
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param arr
	 * @return
	 */
	private int[] RemoveZeros(int[] arr) {
		while (ArrayUtils.contains(arr, 0))
			arr = ArrayUtils.removeElement(arr, 0);
		return arr;
	}

	/**
	 * doesElementExist - pass in one-dimension array and a value, if value exists
	 * in array... then return true
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param arr    - one dimensional array to check
	 * @param iValue - value to check against one dimensional array
	 * @return - return 'true' if iValue exists in arr
	 */
	public boolean doesElementExist(int[] arr, int iValue) {
	
		// FIXME: Return 'true' if iValue is found in arr
		for(int i : arr)
			if(i == iValue)
				return true;
		return false; 

	}

	/**
	 * hasAllValues - return 'true' if every element from arr2 is in arr1
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param arr1 target array
	 * @param arr2 source array
	 * @return return 'true' if every element from source array is in target array
	 */
	public boolean hasAllValues(int[] arr1, int[] arr2) {
		if(arr1.length != arr2.length)
			return false;
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		int i = 0;
		// TODO: Return 'true' if every element from arr2 is in arr1
		while(i < arr1.length) {
			if(arr1[i] != arr2[i])
				return false;
			i++;
		}
		
		return true;

	}

	/**
	 * isLatinSquare - return 'true' if: Each row and column doesn't contain
	 * duplicates If each element in the first row is found in every other row If
	 * each element in the first coumn is found in every other column
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @return returns 'true' if the two-dimensional array is a LatinSquare
	 */
	public boolean isLatinSquare() {

		boolean isLatinSquare = true;

		for (int i = 0; i < LatinSquare.length; i++) {
			if (hasDuplicates(getRow(i)))
				return false;
		}

		for (int j = 0; j < LatinSquare.length; j++) {
			if (hasDuplicates(getColumn(j)))
				return false;
		}

		for (int i = 1; i < LatinSquare.length; i++) {

			if (!hasAllValues(getRow(0), getRow(i))) {
				return false;
			}
		}

		for (int j = 1; j < LatinSquare.length; j++) {

			if (!hasAllValues(getColumn(0), getColumn(j))) {
				return false;
			}
		}

		return isLatinSquare;
	}

	/**
	 * ContainsZero - return 'true' if any element in the LatinSquare is a zero
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @return - returns 'true' if any element in the LatinSquare is a zero
	 */
	public boolean ContainsZero() {
		//TODO: Return 'true' if any element in the puzzle contains a zero
		for(int i = 0; i < LatinSquare.length; i++) {
			if(this.doesElementExist(this.getRow(i), 0))
				return true;
		}
		return false;

	}

	/**
	 * getPV - Return the collection of PuzzleViolations
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @return
	 */
	protected ArrayList<PuzzleViolation> getPV() {
		return PV;
	}
	
	/**
	 * getPV - Get all puzzle violations by type
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param ePV - given puzzle violation type
	 */	
	protected ArrayList<PuzzleViolation> getPV(ePuzzleViolation ePV)
	{
		//FIXME: Return a list of the puzzle violations by type, null if none. 
		for(PuzzleViolation p : this.PV) {
			if(p.getePuzzleViolation() != (ePV))
				this.PV.remove(p);
		}
		
		return this.PV;
	}
	

	/**
	 * ClearPuzzleViolation - clear the collection of PuzzleViolations
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 */
	protected void ClearPuzzleViolation() {
		PV.clear();
	}

	/**
	 * AddPuzzleViolation - Add a PuzzleViolation to the collection
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param pv
	 */
	protected void AddPuzzleViolation(PuzzleViolation pv) {
		PV.add(pv);
	}
	

}
