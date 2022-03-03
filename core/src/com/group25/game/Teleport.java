package com.group25.game;

import com.badlogic.gdx.math.Rectangle;

public class Teleport extends Rectangle{

  private String levelName;

  public Teleport(int x, int y, int width, int height,String levelName) {
    super(x, y, width, height);
    this.levelName = levelName;
  }

  public String getLevelName() {
    return levelName;
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }
  
}
