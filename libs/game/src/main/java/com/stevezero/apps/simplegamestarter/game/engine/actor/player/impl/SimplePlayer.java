package com.stevezero.apps.simplegamestarter.game.engine.actor.player.impl;

import com.stevezero.apps.simplegamestarter.game.engine.graphics.motion.animation.impl.SimplePlayerAnimation;
import com.stevezero.game.assets.loader.Loader;
import com.stevezero.game.controls.events.ControlEvent;
import com.stevezero.game.controls.events.ControlHandler;
import com.stevezero.game.controls.events.EventType;
import com.stevezero.game.engine.Engine;
import com.stevezero.game.engine.actor.Role;
import com.stevezero.game.engine.actor.Actor;
import com.stevezero.game.engine.actor.consumable.Consumable;
import com.stevezero.game.engine.actor.enemy.Enemy;
import com.stevezero.game.engine.actor.player.Player;
import com.stevezero.game.engine.actor.projectile.Projectile;
import com.stevezero.game.engine.graphics.motion.MotionGraph;
import com.stevezero.game.engine.physics.Direction;
import com.stevezero.game.geometry.Vector2;
import com.stevezero.game.util.Util;

/**
 * Example Player implementation.
 */
public final class SimplePlayer extends Player {
  // Velocity constants.
  private static final int MOVE_VX = 1;
  
  // Unit mass
  private final static float MASS = 50f;
  
  // Actions the player can execute.  Note that a player can be jumping and shooting at once.
  private boolean isWalking = false;

  // Movement smoothers
  private boolean leftKeyDown = false;
  private boolean rightKeyDown = false;

  private Engine engine;

  public SimplePlayer() {
    super(MASS, 0);
  }

  //
  // Controls ------------------------>
  //
  
  @Override
  public void setControls(ControlHandler controls) {
    controls.registerEvent(EventType.GAME_MOVE_LEFT_START, new ControlEvent() {
      @Override
      public void onEvent() {
        moveLeft();
      }
    });
    controls.registerEvent(EventType.GAME_MOVE_LEFT_STOP, new ControlEvent() {
      @Override
      public void onEvent() {
        stopMoveLeft();
      }
    });  
    controls.registerEvent(EventType.GAME_MOVE_RIGHT_START, new ControlEvent() {
      @Override
      public void onEvent() {
        moveRight();
      }
    });
    controls.registerEvent(EventType.GAME_MOVE_RIGHT_STOP, new ControlEvent() {
      @Override
      public void onEvent() {
        stopMoveRight();
      }
    });
  }
  
  
  //
  // Handlers for Player Events ------>
  //
  
  /**
   * Override to initialize SimplePlayerAnimation.  Allows for lazy initialization.
   * This callback is guaranteed to occur before the first update tick is processed, or
   * Player.update will throw an exception.
   */
  @Override
  protected void onInitPlayer(Engine engine) {
    this.engine = engine;
    int state = 0; // Only one sprite state.
    
    // Load the sprite states.
    Loader loader = engine.getLoader();
    sprite.addAnimation(new SimplePlayerAnimation(loader, state));

    // Starting sprite state.
    setFacingDirection(Direction.LEFT);
    sprite.setAnimationTo(state);
  }

  /**
   * Override to reset SimplePlayerAnimation when Player.resetPlayer is called.
   */
  @Override
  protected void onResetPlayer(Engine engine) {
  }

  /**
   * Override to update velocity on Player movements.
   */
  @Override
  protected void onUpdateBeforeVelocityApplied(Engine engine) {
    // X Velocity -->
    if (isWalking && facingDirection == Direction.LEFT) {
      velocity.addX(-MOVE_VX);
    }
    if (isWalking && facingDirection == Direction.RIGHT) {
      velocity.addX(MOVE_VX);
    }

    // Add a velo cap to avoid instability.
    velocity.setX(Util.absCap(velocity.getX(), MOVE_VX));
  }
  

  @Override
  protected void onPlayerUpdate(Engine engine) {
  }

  @Override
  protected void onConsume(Consumable consumable) {
  }
   
  @Override
  public void onCollision(Direction direction, Actor other) {
  }
  
  @Override
  protected void onDeath() {
  }

  @Override
  protected void onHealthChange(int health) {
  }
  
  
  //
  // Movement Helpers ------------------------>
  //

  private void moveRight() {
    rightKeyDown = true;
    facingDirection = Direction.RIGHT;
    isWalking = true;
  }

  private void moveLeft() {
    leftKeyDown = true;
    facingDirection = Direction.LEFT;
    isWalking = true;
  }

  private void stopMoveLeft() {
    leftKeyDown = false;

    if (rightKeyDown) {
      moveRight();
      return;
    }
    
    isWalking = false;
  }

  private void stopMoveRight() {
    rightKeyDown = false;

    if (leftKeyDown) {
      moveLeft();
      return;
    }
    
    isWalking = false;
  }
}
