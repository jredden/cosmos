package com.zenred.bizrule;

/**
 * forces domain integrity of sequential series.
 */

public class SequentialIntegrity {
	private String target;
	private String delimeter;
	private char c_delimeter;
	private static final String DEFAULT = "_";
	private int count;
	private boolean diditchange;

	/**
	 * construction
	 */
	public SequentialIntegrity() {
		this(DEFAULT);
	}

	public SequentialIntegrity(String delimeter) {
		this.delimeter = delimeter;
		c_delimeter = delimeter.charAt(0);
		count = 0;
	}

	/**
	 * number at end of string forced to be <I>count</I>;
	 */
	public String nextInSequence(String value) {
		diditchange = false;
		String _end = delimeter + count;
		++count;
		if (value.endsWith(_end)) {
			return value;
		}
		diditchange = true;
		int _length = value.length();
		int _pos = value.lastIndexOf(c_delimeter);
		return value.substring(0, _pos) + _end;
	}

	/**
	 * exposes state of last value in count.
	 */
	public boolean wasLastCountAltered() {
		return diditchange;
	}

	public static void main(String[] argv) {
		SequentialIntegrity sequentialintegrity = new SequentialIntegrity();
		System.out.println(sequentialintegrity.nextInSequence("thestr_0"));
		System.out.println(sequentialintegrity.nextInSequence("thestr_2"));
		System.out.println(sequentialintegrity.nextInSequence("thestr_4"));

	}

}
