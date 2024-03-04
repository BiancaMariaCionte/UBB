package Repository;

import Domain.IAction;
import Domain.Identifiable;
import Domain.ValidationException;

public class ActionAdd<ID, T extends Identifiable<ID>> implements IAction<ID, T> {
    private GenericRepository<T, ID> repo;
    private T addedElem;

    public ActionAdd(GenericRepository<T, ID> repo, T addedElem) {
        this.repo = repo;
        this.addedElem = addedElem;
    }

    @Override
    public void executeUndo() throws ValidationException {
        repo.deleteEntity(addedElem.getId());
    }

    @Override
    public void executeRedo() throws ValidationException {
        repo.addEntity(addedElem);
    }
}