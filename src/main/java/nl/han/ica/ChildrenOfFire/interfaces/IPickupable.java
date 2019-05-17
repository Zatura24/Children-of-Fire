package nl.han.ica.ChildrenOfFire.interfaces;

import nl.han.ica.ChildrenOfFire.ChildrenOfFire;
import nl.han.ica.ChildrenOfFire.objects.Player;

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