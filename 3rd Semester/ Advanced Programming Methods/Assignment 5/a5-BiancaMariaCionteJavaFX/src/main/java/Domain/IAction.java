package Domain;

public interface IAction<ID,T> {
    void executeUndo() throws ValidationException;

    void executeRedo() throws ValidationException;
}
