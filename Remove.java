package game;

public class Remove extends CreatureAction{
   
    private String _name;

    public Remove(String name, Creature owner)
    {
        super(owner);
        _name = name;
        System.out.println("Remove:Remove" + _name);
    }

    public String getname()
    {
        return _name;
    }
}
