package me.dumfing.multiplayerTools;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import java.util.HashMap;
/**
 * Objects that will be sent between the client and server as well as useful variables like ports<br>
 * All classes that the server will send will be prefixed with <b>Server</b> and all classes that the client will send will be prefixed with <b>Client</b>
 */
public class MultiplayerTools {
    public static final int UDPPORT = 19815;
    public static final int TCPPORT = 19816;
    public static void register(EndPoint endpoint){
        Kryo serializer = endpoint.getKryo();
        serializer.register(ClientInfoRequest.class);
        serializer.register(ClientConnectionRequest.class);
        serializer.register(ClientPickedTeam.class);
        serializer.register(ClientSentChatMessage.class);
        serializer.register(ServerPlayerInfo.class);
        serializer.register(ServerSummary.class);
        serializer.register(ServerResponse.class);
        serializer.register(ServerResponse.ResponseCode.class);
        serializer.register(ServerDetailedSummary.class);
        serializer.register(ServerGameCountdown.class);
        serializer.register(ServerSentChatMessage.class);
    }

    /**
     * Sent by the server to tell all clients that it's received a message
     */
    public static class ServerSentChatMessage{
        String message;
        public ServerSentChatMessage() {
        }

        /**
         * Sends the message to all clients, uses a hashmap of all players and a connection so that it'll automatically generate a message with the user's name and the message
         * without needing you to insert it yourself
         * @param message The message to be sent
         * @param sender The connection of the person who sent it
         * @param players All the connections and their respective playersoldiers
         */
        public ServerSentChatMessage(String message, Connection sender, HashMap<Connection, PlayerSoldier> players) {
            this.message = String.format("%s: %s",players.get(sender).getName(),message);
        }

        /**
         * Similar to ServerSentChatMessage but with an Object so you don't have to convert it yourself
         * @param messageIn Supposed to be a ClientSentChatMessage object, will throw an error otherwise
         * @param sender The connection for the person who's sending the message
         * @param players The hashmap of all players connected
         */
        public ServerSentChatMessage(Object messageIn, Connection sender, HashMap<Connection, PlayerSoldier> players){
            if(messageIn instanceof  ClientSentChatMessage){
                this.message = String.format("%s[BLACK]: %s",players.get(sender).getName(),((ClientSentChatMessage) messageIn).getMessage());
            }
            else{
                throw new ClassCastException("Object must be an instance of a ClientSentChatMessage");
            }
        }
    }
    /**
     * Sent by the client to tell the server that they've sent a message
     */
    public static class ClientSentChatMessage{
        String message;
        public ClientSentChatMessage(){

        }
        public ClientSentChatMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
    public static class ClientPickedTeam{
        //Red is 0 Blue is 1
        int picked; //to prevent creating too many unnecessary enums, I'm using an int here
        public ClientPickedTeam(){

        }
        public ClientPickedTeam(int picked){
            this.picked = picked;
        }

        public int getPicked() {
            return picked;
        }
    }
    /**
     * used in the lobby when the server is counting down to the actual game
     */
    public static class ServerGameCountdown{
        int seconds;
        public ServerGameCountdown(){

        }

        public ServerGameCountdown(int seconds) {
            this.seconds = seconds;
        }
        public int getSeconds(){
            return this.seconds;
        }
    }
    /**
     * TODO: determine what info should be sent in the detailed server summary
     */
    public static class ServerDetailedSummary {
        int rTeam, bTeam, rMax,bMax;
        public ServerDetailedSummary(){

        }
        public ServerDetailedSummary(int redTeam, int blueTeam,int maxPeople){
            this.rTeam = redTeam;
            this.bTeam = blueTeam;
            this.bMax = maxPeople/2;
            this.rMax = maxPeople - bMax;
        }
    }
    /**
     * The response from the server for if the client has connected as a player or if they aren't allowed on
     */
    public static class ServerResponse{
        public enum ResponseCode{
            CLIENTCONNECTED,
            SERVERFULL
        }
        ResponseCode response;
        public ServerResponse(){}
        public ServerResponse(ResponseCode response){
            this.response = response;
        }
    }
    /**
     * Sent by the client to tell the server they want basic info about the server
     */
    public static class ClientInfoRequest {
        public ClientInfoRequest(){

        }
    }

    /**
     * Contains the ping, amount of people on, max people on, and server's name
     */
    public static class ServerSummary{
        public int num, max, ping;
        public String serverName;
        public ServerSummary(){

        }
        public ServerSummary(int numPlayers, int maxPlayers, int ping, String serverName){
            this.num = numPlayers;
            this.max = maxPlayers;
            this.ping = ping;
            this.serverName = serverName;
        }
        public String toString(){
            return String.format("%20s %d/%d %d",serverName.substring(0,Math.min(20,serverName.length())),num,max,ping);
        }
    }

    public static class ServerPlayerInfo {
        private Rectangle playerArea;
        private int team, health;
        private String name;
        public ServerPlayerInfo(Rectangle area, int team, String name){
            this.playerArea = area;
            this.team = team;
            this.name = name;
            this.health = 100;
        }
        public ServerPlayerInfo(Rectangle area, int team, String name, int health){ // a simple version of a player that can be sent back and forth
            this.playerArea = area;
            this.team = team;
            this.name = name;
            this.health = health;
        }
        public ServerPlayerInfo(PlayerSoldier sIn){
            this.playerArea = sIn.getRect();
            this.team = sIn.getTeam();
            this.name = sIn.getName();
            this.health = sIn.getHealth();
        }
        public Vector2 getPos(){
            return this.playerArea.getPosition(new Vector2());
        }
        public float getX(){
            return this.playerArea.x;
        }
        public float getY(){
            return this.playerArea.y;
        }
        public Rectangle getRect(){
            return this.playerArea;
        }
        public int getTeam(){
            return this.team;
        }
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
        public void setTeam(int team) {
            this.team = team;
        }

        public int getHealth() {
            return health;
        }
    }
    /**
     * Sent by the client when they wish to connect to the server to play
     */
    public static class ClientConnectionRequest {
        public String playerName;
        public ClientConnectionRequest(){
        }
        public ClientConnectionRequest(String playerName){
            this.playerName = playerName;
        }
    }
    public static class RequestWorld{
        public RequestWorld(){
        }
    }
    public static class WorldInfo{
        HashMap<Connection, PlayerSoldier> world;
        public WorldInfo(HashMap<Connection, PlayerSoldier> world){
            this.world = world;
        }
    }
}
