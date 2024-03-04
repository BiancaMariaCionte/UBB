package Repository;

import Domain.IAction;
import Domain.Identifiable;
import Domain.ValidationException;

public class ActionRemove<ID, T extends Identifiable<ID>> implements IAction<ID, T> {
    private GenericRepository<T, ID> repo;
    private T deletedElem;

    public ActionRemove(GenericRepository<T, ID> repo, T deletedElem) {
        this.repo = repo;
        this.deletedElem = deletedElem;
    }

    @Override
    public void executeUndo() throws ValidationException {
        repo.addEntity(deletedElem);
    }

    @Override
    public void executeRedo() throws ValidationException {
        repo.deleteEntity(deletedElem.getId());
    }
}