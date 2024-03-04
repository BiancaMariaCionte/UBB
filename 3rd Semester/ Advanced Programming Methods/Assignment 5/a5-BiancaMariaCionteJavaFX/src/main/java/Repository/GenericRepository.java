package Repository;

import Domain.Identifiable;
import Domain.ValidationException;

public interface GenericRepository <T extends Identifiable<ID>, ID>{

     void addEntity(T item) throws ValidationException;
     void deleteEntity(ID id) throws ValidationException;

     T findById(ID id) throws ValidationException;

     Iterable<T> getAll();

     void updateEntity(ID id, T updatedEntity) throws ValidationException;

    //Arrays getById();

    //public default void add(T entity) throws ValidationException {}


}
