package nl.han.ica.childrenoffire.objects;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Text object
 * <p>
 * Simple class for a text object
 * 
 * @see GameObject
 */
public class TextObject extends GameObject {
    private String text;

    /**
     * Basic constructor for a text class
     * 
     * @param String text - string the object will be initialized with
     */
    public TextObject(String text) {
        this.text = text;
    }

    /**
     * Set the current text
     * 
     * @param String text - what the current text will be set to
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * This function will be called every frame
     */
    @Override
    public void update() {

    }

    /**
     * This function draws the gameobject
     */
    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT, g.TOP);
        g.textSize(32);
        g.text(text, getX(), getY());
    }
}