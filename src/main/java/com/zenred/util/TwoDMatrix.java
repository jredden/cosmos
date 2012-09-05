package com.zenred.util;

public class TwoDMatrix {
	public static final double TOLERANCE = 0.0000000000000001;

	private int size_of_matrix;

	private Double[] matrix;

	/**
	 * ctor - no arg
	 */
	public TwoDMatrix() {
		this(16);
	}

	/**
	 * ctor - with matrix size
	 */
	public TwoDMatrix(int size_of_matrix) {
		this.size_of_matrix = size_of_matrix;
		matrix = new Double[size_of_matrix];
		zeroMatrix(matrix);
	}

	public TwoDMatrix(double[] ray) {
		this.size_of_matrix = ray.length;
		matrix = new Double[size_of_matrix];
		for (int _idex = 0; _idex < matrix.length; _idex++) {
			matrix[_idex] = new Double(ray[_idex]);
		}
	}

	/**
	 * zero out matrix for input size
	 */
	public static void zeroMatrix(Double[] matrix) {
		for (int _idex = 0; _idex < matrix.length; _idex++) {
			matrix[_idex] = new Double(0.0);
		}
	}

	/**
	 * 
	 */
	public static void selfMatrix(Double[] matrix) {
		for (int _idex = 0; _idex < matrix.length; _idex++) {
			matrix[_idex] = new Double(_idex);
		}
	}

	/**
	 * 
	 */
	public static void selfMatrix(TwoDMatrix targ_matrix)

	{
		for (int _idex = 0; _idex < targ_matrix.getSize(); _idex++) {
			targ_matrix.setEntry(_idex, new Double(_idex));
		}
	}

	/**
	 * 
	 */
	public int getSize() {
		return size_of_matrix;
	}

	/**
	 * 
	 */
	public Double getEntry(int position) {
		return matrix[position];
	}

	/**
	 * 
	 */
	public void setEntry(int position, TwoDMatrix copyfrom) {
		this.matrix[position] = copyfrom.getEntry(position);
	}

	/**
	 * 
	 */
	public void setEntry(int position, Double copyfrom) {
		this.matrix[position] = copyfrom;
	}

	/**
	 * 
	 */
	public boolean lessThanEqualOperation(int postion, TwoDMatrix operand) {
		boolean _ret = false;
		double _operand1 = this.getEntry(postion).doubleValue();
		double _operand2 = operand.getEntry(postion).doubleValue();
		if (_operand1 < _operand2
				|| Math.abs(_operand1 - _operand2) < TOLERANCE) {
			_ret = true;
		}
		return _ret;
	}

	/**
	 * 
	 */
	public boolean equalOperation(int postion, TwoDMatrix operand) {
		boolean _ret = false;
		double _operand1 = this.getEntry(postion).doubleValue();
		double _operand2 = operand.getEntry(postion).doubleValue();
		if (Math.abs(_operand1 - _operand2) < TOLERANCE) {
			_ret = true;
		}
		return _ret;
	}
	/**
	 * 
	 */
	public boolean equalOperation(int postion, int position2, TwoDMatrix operand) {
		boolean _ret = false;
		double _operand1 = this.getEntry(postion).doubleValue();
		double _operand2 = operand.getEntry(position2).doubleValue();
		if (Math.abs(_operand1 - _operand2) < TOLERANCE) {
			_ret = true;
		}
		return _ret;
	}

	/**
	 * 
	 */
	public boolean isElementZero(int position) {
		return matrix[position].doubleValue() == 0.0 ? true : false;
	}

	/**
	 * 
	 */
	public boolean isElementNotZero(int position) {
		return !isElementZero(position);
	}

	/**
	 * index of first element that is zero
	 */
	public int firstZero() {
		int _ret = -1;
		for (int _idex = 0; _idex < matrix.length; _idex++) {
			if (isElementZero(_idex)) {
				_ret = _idex;
				break;
			}
		}
		return _ret;
	}
	public int firstZeroSkip() {
		int _ret = -1;
		for (int _idex = 1; _idex < matrix.length; _idex++) {
			if (isElementZero(_idex)) {
				_ret = _idex;
				break;
			}
		}
		return _ret;
	}
	/**
	 * index of first element that is zero
	 */
	public int lastZero() {
		int _ret = -1;
		for (int _idex = matrix.length - 1; _idex >= 0; --_idex) {
			if (isElementZero(_idex)) {
				_ret = _idex;
				break;
			}
		}
		return _ret;
	}
}
