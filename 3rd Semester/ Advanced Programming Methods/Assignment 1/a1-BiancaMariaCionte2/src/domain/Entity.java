package src.domain;

public class Entity {
    protected int id;
    Entity(int id)
    {
        this.id =id;
    }

    public void setId(int id)
    {
        this.id =id;
    }
    public int getId()
    {
        return this.id;
    }
}
