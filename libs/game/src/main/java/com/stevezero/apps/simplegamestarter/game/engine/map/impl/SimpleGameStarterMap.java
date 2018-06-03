package com.stevezero.apps.simplegamestarter.game.engine.map.impl;

import com.stevezero.game.engine.Engine;
import com.stevezero.game.engine.actor.Actor;
import com.stevezero.game.engine.graphics.backgrounds.TiledScrollingBackground;
import com.stevezero.game.engine.graphics.rendering.Renderable;
import com.stevezero.game.engine.map.Map;

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

  }
}
