package com.zenred.cosmos;

import com.zenred.util.TwoDMatrix;

public class BldmatOps {

	/**
	 * 
	 */
	public static TwoDMatrix bldMatrix(TwoDMatrix input_matrix, double test,
			TwoDMatrix object_matrix) {
		int mat_size = input_matrix.getSize();
		TwoDMatrix _ret_matrix = new TwoDMatrix(mat_size);
		for (int _idex = 0; _idex < mat_size; _idex++) {
			double _input_matrix = input_matrix.getEntry(_idex).doubleValue();
			if (_input_matrix <= test
					|| (Math.abs(_input_matrix - (-2.0))) < TwoDMatrix.TOLERANCE) {
				_ret_matrix.setEntry(_idex, object_matrix);
			}
		}
		return _ret_matrix;
	}

	/**
	 * 
	 */
	public static TwoDMatrix bldMatrix1(TwoDMatrix input_matrix, double test,
			TwoDMatrix object_matrix) {
		int mat_size = input_matrix.getSize();
		TwoDMatrix _ret_matrix = new TwoDMatrix(mat_size);
		for (int _idex = 0; _idex < mat_size; _idex++) {
			double _input_matrix = input_matrix.getEntry(_idex).doubleValue();
			if (_input_matrix >= test
					|| (Math.abs(_input_matrix - (-2.0))) < TwoDMatrix.TOLERANCE) {
				_ret_matrix.setEntry(_idex, object_matrix);
			}
		}
		return _ret_matrix;
	}

	/**
	 * 
	 */
	public static TwoDMatrix intersectMatrix(TwoDMatrix input_matrix,
			TwoDMatrix object_matrix) {
		int mat_size = input_matrix.getSize();
		TwoDMatrix _ret_matrix = new TwoDMatrix(mat_size);
		for (int _idex = 0; _idex < mat_size; _idex++) {
			for (int _idex2 = 0; _idex2 < mat_size; _idex2++) {

				if (input_matrix.equalOperation(_idex, _idex2, object_matrix)
						&& input_matrix.isElementNotZero(_idex)
						&& object_matrix.isElementNotZero(_idex)) {
					_ret_matrix.setEntry(_idex, input_matrix);
				}
			}
		}
		return _ret_matrix;
	}

	public static TwoDMatrix normMatrix(TwoDMatrix input_matrix) {
		TwoDMatrix _targ_matrix = new TwoDMatrix();
		for (int _idex = 0; _idex < _targ_matrix.getSize(); _idex++) {
			_targ_matrix.setEntry(_idex, new Double(0.0));
		}

		for (int _idex = 0, _idex2 = 0; _idex < input_matrix.getSize(); _idex++) {
			if (input_matrix.getEntry(_idex) != 0.0 || _idex == 0) {
				_targ_matrix.setEntry(_idex2++, input_matrix.getEntry(_idex));
			}
		}
		return _targ_matrix;
	}

}
