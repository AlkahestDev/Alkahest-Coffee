package me.dumfing.server;

import com.badlogic.gdx.math.GridPoint2;
import me.dumfing.multiplayerTools.ConcurrentGameWorld;
import me.dumfing.multiplayerTools.MultiplayerTools;
import me.dumfing.multiplayerTools.PlayerSoldier;
import me.dumfing.multiplayerTools.WorldMap;

import java.util.HashMap;

/**
 * A class that manages the entire gameplay portion of the server
 */
public class ServerGameInstance {
    int frameCount = 0;
    ConcurrentGameWorld world;
    public ServerGameInstance(HashMap<Integer, PlayerSoldier> players){
        world = new ConcurrentGameWorld(players);
    }
    public void update(MainServer sv){
        world.update();
        for(ServerEvent svEvent : sv.getEvents()){
            switch (svEvent.getEventType()){
                case PLAYERPICKEDTEAM:
                    PlayerSoldier plr = world.getPlayers().get(svEvent.getConnectionID());
                    GridPoint2 spawnPos = plr.getTeam()==MultiplayerTools.REDTEAM?world.getMap().getRedSpawn():world.getMap().getBluSpawn();
                    world.setPlayerPos(svEvent.getConnectionID(),spawnPos.x,spawnPos.y);
                    break;
            }
        }
        if(frameCount == 3){ // 2 gets an interesting 30hz
            frameCount = 0;
            //System.out.println("send players");
            sv.quickSendAll(new MultiplayerTools.ServerPlayerPositions(world.getPlayers()));
            sv.quickSendAll(new MultiplayerTools.ServerProjectilePositions(world.getProjectiles()));
            sv.quickSendAll(new MultiplayerTools.ServerFlagPositions(world.getFlags()));
        }

        frameCount++;
    }
    public void setWorldMap(WorldMap map){
        world.setWorld(map);
    }
}
