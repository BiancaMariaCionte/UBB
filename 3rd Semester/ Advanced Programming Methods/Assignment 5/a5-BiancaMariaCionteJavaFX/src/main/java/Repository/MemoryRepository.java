package Repository;

import Domain.Identifiable;
import Domain.ValidationException;

import java.util.LinkedHashMap;
import java.util.Map;

public class MemoryRepository<ID,T extends Identifiable<ID>> implements GenericRepository<T, ID>
{
    protected Map<ID, T> data = new LinkedHashMap<ID,T>();



    @Override
    public void addEntity(T item) throws ValidationException {
        if(data.containsKey(item.getId())){
            throw new ValidationException("The element already exists.");
        }
            data.put(item.getId(),item);
    }

//    @Override
//    public void deleteEntity(ID id) throws ValidationException {
//        if(!data.containsKey(id)){
//            throw new ValidationException("The element doesn't exist");
//        }else{
//            data.remove(id);}
//    }

    @Override
    public void deleteEntity(ID id) throws ValidationException {
        if (!data.containsKey(id)) {
            throw new ValidationException("The element doesn't exist");
        } else {
            data.remove(id);
            //writeToFile();
        }
    }




    @Override
    public T findById(ID id) throws ValidationException {
        if(!data.containsKey(id)){
            throw new ValidationException("The id doesn't exits");}
        return data.get(id);
    }

    @Override
    public Iterable<T> getAll() {
        return data.values();
    }

    @Override
    public void updateEntity(ID id, T updatedEntity) throws ValidationException {
        if(!data.containsKey(id)){
            throw new ValidationException("The element doesn't exits");}
        else{
            data.put(id, updatedEntity);}
    }

    //    @Override
//    public void clear() {
//        if(data.isEmpty()){
//            throw new RuntimeException("already empty");
//        }
//        else{
//            data.clear();}
//    }

}
