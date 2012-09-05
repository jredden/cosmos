package com.zenred.cosmos.dao.base;

import java.io.Serializable;

/**
 * Base Interface for all dao objects.  This class uses java generics for
 * subclasses to implement typesafe CRUD functions.
 * 
 * @author J redden taken from wls project and Ji Kim
 *
 * @param <T> Domain object class
 * @param <PK> Primary key
 */
public interface GenericDao <T, PK extends Serializable> {
    /** 
     * Persist the newInstance object into database 
     */
    PK create(T newInstance);

    /** 
     * Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(PK id);

    /** 
     * Save changes made to a persistent object.
     */
    void update(T transientObject);

    /** 
     * Remove an object from persistent storage in the database 
     */
    void delete(T persistentObject);

}
