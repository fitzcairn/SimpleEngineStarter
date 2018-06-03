package com.stevezero.apps.simplegamestarter.game.manifest.impl;

import java.util.List;

import com.stevezero.apps.simplegamestarter.game.engine.actor.player.impl.SimplePlayer;
import com.stevezero.apps.simplegamestarter.game.engine.ai.impl.SimpleGameStarterAi;
import com.stevezero.apps.simplegamestarter.game.engine.hud.impl.SimpleGameStarterHud;
import com.stevezero.apps.simplegamestarter.game.engine.map.impl.SimpleGameStarterMap;
import com.stevezero.game.Game;
import com.stevezero.game.assets.font.manager.Fonts;
import com.stevezero.game.assets.loader.Loader;
import com.stevezero.game.engine.Engine;
import com.stevezero.game.engine.actor.Actor;
import com.stevezero.game.engine.actor.player.Player;
import com.stevezero.game.engine.ai.Ai;
import com.stevezero.game.engine.graphics.rendering.Renderable;
import com.stevezero.game.engine.hud.Hud;
import com.stevezero.game.engine.map.Map;
import com.stevezero.game.manifest.Manifest;
import com.stevezero.game.screens.Screen;


/**
 * Game Manifest for SimpleGameStarter.
 */
public class SimpleGameStarter extends Manifest {
  @Override
  public Screen getScreen(Game game, int screenId) {
    return game.getEngine();
  }

  @Override
  public Screen setScreenStart(Game game) {
    Screen s = game.getEngine();
    s.activate();
    s.registerControls();
    return s;
  }

  @Override
  public Screen setScreenLevelComplete(Game game) {
    return game.getEngine();
  }

  @Override
  public Screen setScreenLoss(Game game) {
    return game.getEngine();
  }

  @Override
  public Screen setScreenPause(Game game) {
    return game.getEngine();
  }

  @Override
  public Screen setScreen(Game game, int screenId) {
    return game.getEngine();
  }

  @Override
  protected Player createAndInitPlayer(Engine engine) {
    Player samplePlayer = new SimplePlayer();
    samplePlayer.initPlayer(engine);
    return samplePlayer;
  }

  @Override
  protected Map createAndInitMap(Engine engine, List<Actor> simulatableQueue,
      List<Renderable> renderableQueue) {
    return new SimpleGameStarterMap(engine, renderableQueue, simulatableQueue);
  }

  @Override
  protected Fonts createAndInitFonts(Loader loader) {
    return null;
  }

  @Override
  protected Hud createAndInitHud(Engine engine) {
    return new SimpleGameStarterHud(engine);
  }

  @Override
  protected Ai createAndInitAi(Engine engine) {
    Ai gameAi = new SimpleGameStarterAi();
    return gameAi;
  }
}
