package nl.han.ica.childrenoffire.interfaces;

import nl.han.ica.childrenoffire.objects.Player;
import nl.han.ica.childrenoffire.ChildrenOfFire;

/**
 * Can be picked up interface
 * <p>
 * Interface for every object that can be picked up.
 */
public interface IPickupable {
    /**
     * Implement what will happen when an object is picked up.
     */
    public abstract void pickUp(Player player, ChildrenOfFire world);
}