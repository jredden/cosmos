package com.zenred.util;

/**
  * This class is a generic Factory that
  * employs Java reflection to create objects.
  */
public class ObjectFactory {

	/**
	  * Create an instance of the class with the specified name.
	  * (Note that for convenience, exceptions are caught within
	  * the method with errors printed to standard out).
	  */
    public static Object newInstance(String className) {

        Class a_class= null;

        // Get the class
        try {
			a_class= Class.forName(className);
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("Error in newInstance method " +
			  "of ObjectFactory - can't find class named: " +
			  className);
			System.out.println(cnfe);
		}

		Object newObject = null;
		if (a_class!= null) {
			// Create the instance
            try {
				newObject = a_class.newInstance();
			}
			catch (InstantiationException ie) {
			    System.out.println("Error in newInstance method " +
			      "of ObjectFactory - can't instantiate class named: " +
			      className);
				System.out.println(ie);
			}
			catch (IllegalAccessException iae) {
				System.out.println("Error in newInstance method " +
				  "of ObjectFactory - can't access class named: " +
			      className);
				System.out.println(iae);
			}
		}
        return newObject;
	}
}
