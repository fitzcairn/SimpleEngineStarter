package com.stevezero.apps.simplegamestarter.game.engine.actor.obstacle.impl;

import com.stevezero.game.assets.loader.Loader;
import com.stevezero.game.engine.actor.obstacle.Obstacle;
import com.stevezero.game.engine.physics.Direction;
import com.stevezero.apps.simplegamestarter.game.engine.graphics.motion.animation.impl.WallRest;

public final class WallBottomRightCorner extends Obstacle {

  public WallBottomRightCorner(Loader loader, int x, int y, int fill) {
    super(loader, x, y);
    
    sprite.setAnimationTo(sprite.addAnimation(new WallRest(loader, Direction.RIGHT, Direction.DOWN, fill)));
  }
}