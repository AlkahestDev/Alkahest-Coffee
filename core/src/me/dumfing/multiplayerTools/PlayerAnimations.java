package me.dumfing.multiplayerTools;
//FILENAME
//Aaron Li  5/23/2017
//EXPLAIN

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PlayerAnimations {
    private static final TextureAtlas playerSprites = new TextureAtlas(Gdx.files.internal("SpriteSheets/SoldierSprites.atlas"));
    private static final float WALKINGFRAMETIME = 0.07f;
    private static final float IDLEFRAMETIME = 0.3f;
    //file names are in format adct
    //a is the animation (walk, jump, fall, etc.)
    //d is the direction (left, right)
    //c is the colour (red, blue)
    //t is the type (player, nonplayer)
    private static final Animation<TextureRegion> redNonPlayerWalkLeft = new Animation<TextureRegion>(WALKINGFRAMETIME,playerSprites.findRegions("walk/wlrnk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> redNonPlayerWalkRight = new Animation<TextureRegion>(WALKINGFRAMETIME,playerSprites.findRegions("walk/wrrnk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> bluNonPlayerWalkLeft = new Animation<TextureRegion>(WALKINGFRAMETIME,playerSprites.findRegions("walk/wlbnk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> bluNonPlayerWalkRight = new Animation<TextureRegion>(WALKINGFRAMETIME,playerSprites.findRegions("walk/wrbnk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> redPlayerWalkRight = new Animation<TextureRegion>(WALKINGFRAMETIME,playerSprites.findRegions("walk/wrrpk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> redPlayerWalkLeft = new Animation<TextureRegion>(WALKINGFRAMETIME, playerSprites.findRegions("walk/wlrpk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> bluPlayerWalkRight = new Animation<TextureRegion>(WALKINGFRAMETIME,playerSprites.findRegions("walk/wrbpk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> bluPlayerWalkLeft = new Animation<TextureRegion>(WALKINGFRAMETIME,playerSprites.findRegions("walk/wlbpk"), Animation.PlayMode.LOOP);

    private static final Animation<TextureRegion> redNonPlayerIdleLeft = new Animation<TextureRegion>(IDLEFRAMETIME,playerSprites.findRegions("idle/ilrnk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> redNonPlayerIdleRight = new Animation<TextureRegion>(IDLEFRAMETIME,playerSprites.findRegions("idle/irrnk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> bluNonPlayerIdleLeft = new Animation<TextureRegion>(IDLEFRAMETIME,playerSprites.findRegions("idle/ilbnk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> bluNonPlayerIdleRight = new Animation<TextureRegion>(IDLEFRAMETIME,playerSprites.findRegions("idle/irbnk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> redPlayerIdleLeft = new Animation<TextureRegion>(IDLEFRAMETIME,playerSprites.findRegions("idle/ilrpk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> redPlayerIdleRight = new Animation<TextureRegion>(IDLEFRAMETIME,playerSprites.findRegions("idle/irrpk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> bluPlayerIdleLeft = new Animation<TextureRegion>(IDLEFRAMETIME,playerSprites.findRegions("idle/ilbpk"), Animation.PlayMode.LOOP);
    private static final Animation<TextureRegion> bluPlayerIdleRight = new Animation<TextureRegion>(IDLEFRAMETIME,playerSprites.findRegions("idle/irbpk"), Animation.PlayMode.LOOP);
    //animations will be accessed with redPlayer[direction][animationID]
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int WALK = 2;
    public static final int FALL = 4;
    public static final int JUMP = 8;
    public static final int ATTACK = 16;
    public static final int IDLE = 32;
    public static final int DIRECTION = 1;
    public static final int ISWALKING = 2;
    public static final int ISFALLING = 4;
    public static final int ISJUMPING = 8;
    public static final int ISATTACK = 16;
    public static final int ISIDLE = 32;
    public static final Animation[][] redPlayer = {{redPlayerWalkLeft,null,null,null,redPlayerIdleLeft},{redPlayerWalkRight,null,null,null,redPlayerIdleRight}};
    public static final Animation[][] redNonPlayer = {{redNonPlayerWalkLeft,null,null,null,redNonPlayerIdleLeft},{redNonPlayerWalkRight,null,null,null,redNonPlayerIdleRight}};
    public static final Animation[][] bluPlayer = {{bluPlayerWalkLeft,null,null,null,bluPlayerIdleLeft},{bluPlayerWalkRight,null,null,null,bluPlayerIdleRight}};
    public static final Animation[][] bluNonPlayer = {{bluNonPlayerWalkLeft,null,null,null,bluNonPlayerIdleLeft},{bluNonPlayerWalkRight,null,null,null,bluNonPlayerIdleRight}};

}
