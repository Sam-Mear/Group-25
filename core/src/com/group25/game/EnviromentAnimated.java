package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public class EnviromentAnimated extends GameEntity{

    private EnitiyAnimation animation;
    private TextureRegion currentTexture;
    private int frames;
    private int animationSpeed;

    public EnviromentAnimated(float positionX, float positionY, int width, int height, Sprite img, int frames, int animationSpeed, Rectangle hitbox) {
        super(positionX, positionY, width, height, img, hitbox);
        this.frames = frames;
        this.animationSpeed = animationSpeed;
        animation = new EnitiyAnimation(new TextureRegion(img),frames,animationSpeed, 0, frames);        // nees to add start and end frame
        currentTexture = animation.getCurrentFrame();
    }

    public EnviromentAnimated(float positionX, float positionY, int width, int height, Sprite img, int frames, int animationSpeed) {
        super(positionX, positionY, width, height, img);
        this.frames = frames;
        this.animationSpeed = animationSpeed;
        animation = new EnitiyAnimation(new TextureRegion(img),frames,animationSpeed, 0, frames);
        currentTexture = animation.getCurrentFrame();
    }

    public void update() {
        animation.update(1);
        currentTexture = animation.getCurrentFrame();
    }

    public TextureRegion getTexture(){
        return currentTexture;
    }
}
