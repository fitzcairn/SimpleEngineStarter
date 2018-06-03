package com.stevezero.apps.simplegamestarter.game.engine.ai.impl;

import com.stevezero.apps.simplegamestarter.game.engine.ai.processor.impl.SimpleGameStarterAiProcessor;
import com.stevezero.game.engine.ai.Ai;

/**
 * AI for enemies in GMS Crusader.  To be provided by the Manifest.
 */
public final class SimpleGameStarterAi extends Ai {
  
  public SimpleGameStarterAi() {
    super(new SimpleGameStarterAiProcessor());
  } 
}
