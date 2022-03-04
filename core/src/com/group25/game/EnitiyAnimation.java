package com.group25.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

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


    private int startFrame, endFrame;

    //Loads the entities animation and includes all the necessary infomation to perform the animation
    public EnitiyAnimation(TextureRegion animationSegment,int numberOfFrames,int frameTime, int startFrame, int endFrame){
        int frameWidth = animationSegment.getRegionWidth()/numberOfFrames;
        int frameHeight = animationSegment.getTexture().getHeight();

        this.numberOfFrames = numberOfFrames;
        this.frameTime = frameTime;

        this.startFrame = startFrame;
        this.endFrame = endFrame;

        frames = new Array<TextureRegion>();

        for(int i=0;i<numberOfFrames;i++){
            frames.add(new TextureRegion(animationSegment,i*frameWidth,0,frameWidth,frameHeight));
        }
        currentFrame = startFrame;
    }
    //Returns the current frame
    public int getCurrentFrameNumber(){
        return currentFrame;
    }

    //Determine the number of fraems in the animation
    public void setCurrentFrameNumber(int x){
        this.currentFrame = x;
    }
    
    //Last frame
    public void end(){
        this.endFrame = this.startFrame;
    }


    /**
     * Updates the animations frame (swaps out each image every number of frames provided)
     * @param dt
     */
    public void update(double dt){
        currentFrameTime += dt;
        if(currentFrameTime>frameTime){
            currentFrameTime = 0;
            currentFrame++;
        }

        if(currentFrame >= numberOfFrames || currentFrame > endFrame){
            if(startFrame-endFrame > 2)
                startFrame = startFrame+1;
            else{
                currentFrame = startFrame;
            }
        }
    }

    public TextureRegion getCurrentFrame(){
     //   return frames.get(currentFrame);
     return frames.get(currentFrame);
    }
}
