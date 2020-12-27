package game;
import java.util.Stack;

public class Player extends Creature {

    private int room_id;
    private int pos_X;
    private int pos_Y;
    private Player player;
    private int startX;
    private int startY;
    private int hp;
    private int hpmoves;
    //private ArrayList<Integer> PosX = new ArrayList<Integer>();
    //private ArrayList<Integer> PosY = new ArrayList<Integer>();
    private Item wear_armor;
    private Item wield_sword;
    private CreatureAction dropPack;
    private Stack<Item> item = new Stack<Item>();
    private Stack<String> item_string = new Stack<String>();

    public void setPlayer(Player _player)
    {
      player = _player;
      System.out.println("Player:" + player);
    }

    public void setHp(int HP)
    {
      hp = HP;
    }

    public void setHpMove(int hpMoves)
    {
        hpmoves = hpMoves;
    }

    public int getHpMoves() {
        return hpmoves;
    }

    public int getHp()
    {
      return hp;
    }

    public Player getPlayer()
    {
      return player;
    }

    public Player( )
    {
      System.out.println("Player");
    }

    public int getRoomID(){
      return room_id;
    }

    public void setWeapon(Item sword)
    {
      item.push(sword);
      item_string.push(((Sword)sword).getName());
      System.out.println("Player:setWeapon");
    }
    
    public void setArmor(Item armor)
    {
      item.push(armor);
      item_string.push(((Armor)armor).getName());
      System.out.println("Player:setArmor");
    }

    public void setScroll(Item scroll)
    {
      item.push(scroll);
      item_string.push(((Scroll)scroll).getName());
      System.out.println("Player:setScroll");
    }

    public Stack<Item> getItem() {
      System.out.println("Player:getItem");

      return item;
    }

    public Stack<String> getStrItem(){
      System.out.println("Player:getStrItem");

      return item_string;
    }
    public void setID(int room, int serial)
    {
       room_id = room;
       System.out.println("Player:setID"+room + "\n" +serial);
    }

    public void wearArmor(Item armour){
      wear_armor = armour;
      System.out.println("Player:wearArmour");
    }

    public Item getWornArmor(){
      System.out.println("Player:getWornArmor");
      return wear_armor;
    }

    public void wieldSword(Item sword){
      System.out.println("Player:wieldSword");
      wield_sword = sword;
    }

    public Item getWieldSword(){
      System.out.println("Player:getWieldSword");
      return wield_sword;
    }

    public void setDropPack(CreatureAction dp){
      System.out.println("Player:setDropPack");
      dropPack = dp;
    }

    public CreatureAction getDropPack(){
      System.out.println("Player:getDropPack");
      return dropPack;
    }    

    public int getX()
    {
      pos_X = getPosX().get(0);
      return pos_X;
    }
    
    public int getY()
    {
      pos_Y = getPosY().get(0);
      return pos_Y;
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
      startX = getPosX().get(0) + x;
    } 

    public void setstartingY(int y)
    {
      startY = getPosY().get(0) + y;
    }
}
