package com.stevezero.apps.simplegamestarter.game.engine.map.impl;

import com.stevezero.game.engine.Engine;
import com.stevezero.game.engine.actor.Actor;
import com.stevezero.game.engine.graphics.backgrounds.TiledScrollingBackground;
import com.stevezero.game.engine.graphics.rendering.Renderable;
import com.stevezero.game.engine.map.Map;
import com.stevezero.game.geometry.Point2;

import java.util.List;


/**
 * A very simple map implementation.
 */
public final class SimpleGameStarterMap extends Map {
  
  public SimpleGameStarterMap(Engine engine, List<Renderable> renderQueue,
                              List<Actor> simulationQueue) {
    super(engine, renderQueue, simulationQueue);

    // Add background.
    renderQueue.add(new TiledScrollingBackground(engine.getLoader(), "bg_tile.png", camera));

    this.width = engine.getSystemManager().getViewWidth();
    this.height = engine.getSystemManager().getViewHeight();

    // Set the player start.
    this.playerStart = new Point2(width/2, height/2);
  }
}
