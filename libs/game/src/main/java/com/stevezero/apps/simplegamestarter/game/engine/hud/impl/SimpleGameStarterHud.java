package com.stevezero.apps.simplegamestarter.game.engine.hud.impl;

import java.util.ArrayList;
import java.util.List;

import com.stevezero.game.Game;
import com.stevezero.game.controls.onscreen.Button;
import com.stevezero.game.engine.Engine;
import com.stevezero.game.engine.hud.Hud;
import com.stevezero.game.screens.Screen;

/**
 * Manages the HUD shown during gameplay.
 */
public class SimpleGameStarterHud extends Hud {
  private final List<Button> buttons = new ArrayList<Button>();

  public SimpleGameStarterHud(Engine engine) {
    super(engine);
  }

  @Override
  public void render(Screen screen) {
  }

  @Override
  public List<Button> createOnScreenControls(Game game) {
    return buttons;
  }
}
