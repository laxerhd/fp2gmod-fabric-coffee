package fp2g.coffee.registry;

import fp2g.coffee.Coffee;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final String MOD_ID = Coffee.MOD_ID;

    public static final RegistryEntry<SoundEvent> COFFEE_SLURP = registerSound("coffee_slurp");

    public static RegistryEntry<SoundEvent> registerSound(String name) {
        Identifier id = Identifier.of(MOD_ID, name);
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        System.out.println("Registriere Coffee Mod Sounds...");
    }
}
