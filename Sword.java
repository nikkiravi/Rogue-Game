package game;

public class Sword extends Item{
  private String _name;
  private int room_id;

    public Sword(String name)
    {
        _name = name;
        System.out.println("Sword:Sword" + _name);
    }

    public int getRoomID(){
        return room_id;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }


    public void setID(int room, int serial)
    {
        room_id = room;
        System.out.println("Sword:setID" + room + "\n" + serial);
    }
}
