package game;

public class Hallucinate extends ItemAction{
   private Creature owner;

    Hallucinate(Creature _owner){
        super(_owner);
        owner = _owner;
        System.out.println("Hallucinate");

    }

    public Creature getOwner() {
        return owner;
    }
}
