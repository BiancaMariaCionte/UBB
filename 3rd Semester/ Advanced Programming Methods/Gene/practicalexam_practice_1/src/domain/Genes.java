package domain;

import java.util.Arrays;

public class Genes
{
    private String name;
    private String organism;
    private String function;
    private String associated;

    public Genes(String name, String organism, String function, String associated)
    {
        this.name = name;
        this.organism = organism;
        this.function = function;
        this.associated = associated;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOrganism()
    {
        return organism;
    }

    public void setOrganism(String organism)
    {
        this.organism = organism;
    }

    public String getFunction()
    {
        return function;
    }

    public void setFunction(String function)
    {
        this.function = function;
    }

    public String getAssociated()
    {
        return associated;
    }

    public void setAssociated(String associated)
    {
        this.associated = associated;
    }

    @Override
    public String toString()
    {
        return name + " | " +
                organism + " | " +
                function;
    }
}
