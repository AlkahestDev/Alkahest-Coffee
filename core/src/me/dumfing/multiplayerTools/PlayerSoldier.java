package me.dumfing.multiplayerTools;

/**
 * Created by dumpl on 4/28/2017.
 */
public class PlayerSoldier extends MultiplayerTools.PlayerInfo{
    // a more detailed version of the players that will be sent at the start but won't be sent around as much later
        private int health, maxHealth;
        private float vX, vY;
        public PlayerSoldier(float x, float y, int team){
            super(x,y,team,null);
            this.vX = 0;
            this.vY = 0;
            this.health = 100;
            this.maxHealth = 100;
        }
        public PlayerSoldier(float x, float y, int team, String name){
            super(x, y, team, name);
            this.vX = 0;
            this.vY = 0;
            this.health = 100;
            this.maxHealth = 100;
        }
        public void setMaxHealth(int maxHealth){
            this.maxHealth = maxHealth;
        }
        public MultiplayerTools.PlayerInfo getPlayerInfo(){
            return new MultiplayerTools.PlayerInfo(this); // a stripped down version of this for what other people see
        }
        public int getHealth(){
            return super.getHealth();
        }
        public int getMaxHealth(){
            return this.maxHealth;
        }
}
