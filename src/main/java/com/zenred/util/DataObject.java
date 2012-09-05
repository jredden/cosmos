package com.zenred.util;

/**
  * This is a container class for data that
  * is passed between strategy instances.
  */
public class DataObject {

    private Object data;

    public DataObject(Object data) {
		this.data = data;
	}

	public String getStringData() {
		return data.toString();
	}

	public Object getDocumentData() {
		return data;
	}

}

