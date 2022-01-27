package com.group25.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import org.graalvm.compiler.core.common.cfg.Loop;

public class EnitiyAnimation{

    /**
     * Animation object
     */
    private Animation animation;
    /**
     * Frames which will be cycled through
     */
    private Array<TextureRegion> frames;
    /**
     * Current frame appearing on screen
     */
    private int currentFrame;
    /**
     * How long a frame appears on the screen
     */
    private int frameTime;
    private int currentFrameTime;
    private int numberOfFrames;

    public EnitiyAnimation(TextureRegion animationSegment,int numberOfFrames,int frameTime){
        int frameWidth = animationSegment.getRegionWidth()/numberOfFrames;
        int framdeHeight = animationSegment.getTexture().getHeight();

        this.numberOfFrames = numberOfFrames;
        this.frameTime = frameTime;

        frames = new Array<TextureRegion>();

        for(int i=0;i<numberOfFrames;i++){
            frames.add(new TextureRegion(animationSegment,i*frameWidth,0,frameWidth,framdeHeight));
        }
        currentFrame = 0;

    }

    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime>frameTime){
            currentFrameTime = 0;
            currentFrame++;
        }

        if(currentFrame >= numberOfFrames){
            currentFrame = 0;
        }
    }

    public TextureRegion getCurrentFrame(){
        return frames.get(currentFrame);
    }
}
