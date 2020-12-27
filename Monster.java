package game;

public class Monster extends Creature{

   private int room_id;
   private int startX;
   private int startY;
   private int maxhit;

   public Monster( )
   {
      System.out.println("Monster");
   }

   // @Override
   public int getRoomID(){
      return room_id;
   }

   public void setName(String name)
   {
      System.out.println("Monster:setName"+name);

   }
    
   public void setID(int room, int serial)
   {
      room_id = room;
      // System.out.println("Monster:setID"+room + "\n" +serial);

   }

   public void setMaxHit(int _maxHit) {
      maxhit = _maxHit;
   }

   @Override
   public int getMaxHit() {
      return maxhit;
   }

   public int getstartingX()
   {
     return startX;
   } 

   public int getstartingY()
   {
     return startY;
   }

   public void setstartingX(int x)
   {
     startX = x;
   } 

   public void setstartingY(int y)
   {
     startY = y;
   }
}
