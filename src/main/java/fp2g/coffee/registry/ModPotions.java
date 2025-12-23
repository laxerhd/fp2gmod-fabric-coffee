package fp2g.coffee.registry;

import fp2g.coffee.Coffee;
import fp2g.coffee.effect.CoffeeEffect;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {

    public static final String MOD_ID = Coffee.MOD_ID;

    // Status Effect registrieren
    public static final RegistryEntry<StatusEffect> COFFEE_EFFECT = Registry.registerReference(
            Registries.STATUS_EFFECT,
            Identifier.of(MOD_ID, "coffee_effect"),
            new CoffeeEffect()
    );

    // Potion registrieren
    public static final RegistryEntry<Potion> COFFEE_POTION = Registry.registerReference(
            Registries.POTION,
            Identifier.of(MOD_ID, "coffee_potion"),
            new Potion("coffee_potion", new StatusEffectInstance(COFFEE_EFFECT, 3600))
    );


    // Vollständig registrieren
    public static void registerPotions() {
        System.out.println("Registriere Coffee Mod Potions...");
        /* VERALTET - gibt jetzt neues CoffeCup Item statt Potion
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    Ingredient.ofItem(ModItems.COFFEE_BEANS),
                    COFFEE_POTION
            );
        });
         */
    }
}