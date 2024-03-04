package Repository;

import Domain.IAction;
import Domain.Identifiable;
import Domain.ValidationException;

public class ActionUpdate<ID, T extends Identifiable<ID>>implements IAction<ID, T> {
    private GenericRepository<T, ID> repo;
    private T oldElem;
    private T newElem;

    public ActionUpdate(GenericRepository<T, ID> repo, T oldElem, T newElem) {
        this.repo = repo;
        this.oldElem = oldElem;
        this.newElem = newElem;
    }

    @Override
    public void executeUndo() throws ValidationException {
        repo.updateEntity(oldElem.getId(), oldElem);
    }

    @Override
    public void executeRedo() throws ValidationException {
        repo.updateEntity(newElem.getId(), newElem);
    }
}