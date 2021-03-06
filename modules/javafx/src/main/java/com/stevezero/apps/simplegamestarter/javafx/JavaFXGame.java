package com.stevezero.apps.simplegamestarter.javafx;


import com.stevezero.apps.simplegamestarter.game.manifest.impl.SimpleGameStarter;
import com.stevezero.apps.simplegamestarter.javafx.assets.loader.impl.JavaFXLoader;
import com.stevezero.apps.simplegamestarter.javafx.assets.sound.impl.JavaFXSoundManager;
import com.stevezero.apps.simplegamestarter.javafx.controls.events.JavaFXEvent;
import com.stevezero.apps.simplegamestarter.javafx.controls.events.impl.JavaFXControlHandler;
import com.stevezero.apps.simplegamestarter.javafx.rendering.impl.JavaFXRenderer;
import com.stevezero.apps.simplegamestarter.javafx.system.impl.JavaFXSystemManager;
import com.stevezero.game.Game;
import com.stevezero.game.external.Achievements;
import com.stevezero.game.external.Leaderboards;
import com.stevezero.game.external.services.ServiceManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * Run the game as an JavaFX Application.
 */
public class JavaFXGame extends Application {
  private final String title = "SimpleGameStarter";

  // App primitives
  private Canvas screen;

  // The game instance.
  private Game game;

  // Controls
  private JavaFXControlHandler controls;

  @Override
  public void init() {
    // Create the system manager.
    JavaFXSystemManager systemManager = new JavaFXSystemManager();

    // Create the graphics buffer.
    screen = new Canvas(systemManager.getViewWidth(), systemManager.getViewHeight());

    // Create the controls.
    controls = new JavaFXControlHandler();

    // Create the game instance.
    game = new Game(
        systemManager,
        new JavaFXRenderer(screen),
        new JavaFXLoader(),
        controls,
        new JavaFXSoundManager(),
        new ServiceManager(new Achievements(), new Leaderboards()),
        new SimpleGameStarter());
  }

  @Override
  public void start(Stage stage) {
    // Set up the screen.
    Group root = new Group();
    root.getChildren().add(screen);
    Scene scene = new Scene(root, screen.getWidth(), screen.getHeight());
    stage.setTitle(title);
    stage.setScene(scene);

    // Set up Controls
    registerControls(scene);

    // The game loop, runs in the UI thread.
    AnimationTimer animator = new AnimationTimer() {

      @Override
      public void handle(long arg0) {
        // Start a game loop iteration.
        game.start();

        // Tick the engine forward.
        game.update();

        // Draw a frame using the renderer, which will render it to a backbuffer.
        game.render();

        // Do any cleanup.
        game.finalize();
      }
    };

    animator.start();
    stage.show();
  }

  private void registerControls(Scene scene) {
    // Key down
    scene.setOnKeyPressed(
        new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {
            controls.onEventStart(new JavaFXEvent() {
              @Override
              public boolean hasKeyEvent() {
                return true;
              }

              @Override
              public boolean hasMouseEvent() {
                return false;
              }

              @Override
              public KeyEvent getKeyEvent() {
                return e;
              }

              @Override
              public MouseEvent getMouseEvent() {
                return null;
              }

            }, game);
          }
        });

    // Key up
    scene.setOnKeyReleased(
        new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {
            controls.onEventStop(new JavaFXEvent() {
              @Override
              public boolean hasKeyEvent() {
                return true;
              }

              @Override
              public boolean hasMouseEvent() {
                return false;
              }

              @Override
              public KeyEvent getKeyEvent() {
                return e;
              }

              @Override
              public MouseEvent getMouseEvent() {
                return null;
              }
            }, game);
          }
        });

    // Mouse down
    scene.setOnMousePressed(
        new EventHandler<MouseEvent>() {
          public void handle(MouseEvent e) {
            controls.onEventStart(new JavaFXEvent() {
              @Override
              public boolean hasKeyEvent() {
                return false;
              }

              @Override
              public boolean hasMouseEvent() {
                return true;
              }

              @Override
              public KeyEvent getKeyEvent() {
                return null;
              }

              @Override
              public MouseEvent getMouseEvent() {
                return e;
              }
            }, game);
          }
        });

    // Mouse up
    scene.setOnMouseReleased(
        new EventHandler<MouseEvent>() {
          public void handle(MouseEvent e) {
            controls.onEventStop(new JavaFXEvent() {
              @Override
              public boolean hasKeyEvent() {
                return false;
              }

              @Override
              public boolean hasMouseEvent() {
                return true;
              }

              @Override
              public KeyEvent getKeyEvent() {
                return null;
              }

              @Override
              public MouseEvent getMouseEvent() {
                return e;
              }
            }, game);
          }
        });

  }
}
