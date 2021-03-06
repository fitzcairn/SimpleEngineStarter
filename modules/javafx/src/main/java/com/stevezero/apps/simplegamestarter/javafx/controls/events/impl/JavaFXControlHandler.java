package com.stevezero.apps.simplegamestarter.javafx.controls.events.impl;

import com.stevezero.apps.simplegamestarter.javafx.controls.events.JavaFXEvent;
import com.stevezero.game.Game;
import com.stevezero.game.controls.events.ControlHandler;
import com.stevezero.game.controls.events.EventType;
import com.stevezero.game.controls.onscreen.Interactable;

/**
 * Translates keyboard actions for when this game runs as an com.stevezero.apps.simplegamestarter.javafx.
 */
public final class JavaFXControlHandler extends ControlHandler {

  public void onEventStart(JavaFXEvent event, Game game) {
    if (event.hasKeyEvent()) {
      // Transate from an Applet KeyEvent to a control event.
      switch (event.getKeyEvent().getCode()) {
        case ESCAPE:
          handleEvent(EventType.MENU_OPEN);
          handleEvent(EventType.MENU_CLOSE);
          break;
        case UP:
          handleEvent(EventType.MENU_UP);
          break;
        case DOWN:
          handleEvent(EventType.GAME_DUCK_START);
          handleEvent(EventType.MENU_DOWN);
          break;
        case LEFT:
          handleEvent(EventType.GAME_MOVE_LEFT_START);
          break;  
        case RIGHT:
          handleEvent(EventType.GAME_MOVE_RIGHT_START);
          break;
        case SPACE:
          handleEvent(EventType.GAME_JUMP_START);
          break;
        case ENTER:
          handleEvent(EventType.GAME_SHOOT_START);
          handleEvent(EventType.MENU_SELECT);
          break;
        case M:
          handleEvent(EventType.DEBUG_MAP_GEN);
          break;
      }
    }
    if (event.hasMouseEvent()) {
      // Run through the screen controls and detect which one was hit.
      for (Interactable control : game.getInteractables()) {
        if (control.wasActivated((int)event.getMouseEvent().getSceneX(), (int)event.getMouseEvent().getSceneY())) {
          handleEvent(control.getStartEvent());
        }
      }
    }
  }

  public void onEventStop(JavaFXEvent event, Game game) {
    if (event.hasKeyEvent()) {
      // Transate from an Applet KeyEvent to a control event.
      switch (event.getKeyEvent().getCode()) {
        case DOWN:
          handleEvent(EventType.GAME_DUCK_STOP);          
          break;
        case LEFT:
          handleEvent(EventType.GAME_MOVE_LEFT_STOP);          
          break;
        case RIGHT:
          handleEvent(EventType.GAME_MOVE_RIGHT_STOP);          
          break;
        case SPACE:
          handleEvent(EventType.GAME_JUMP_STOP);          
          break;
        case ENTER:
          handleEvent(EventType.GAME_SHOOT_STOP);          
          break;
      }
    }
    if (event.hasMouseEvent()) {
      // Run through the screen controls and detect which one was hit.
      for (Interactable control : game.getInteractables()) {
        if (control.wasActivated((int)event.getMouseEvent().getSceneX(), (int)event.getMouseEvent().getSceneY())) {
          handleEvent(control.getStopEvent());
        }
      }
    }
  }
}