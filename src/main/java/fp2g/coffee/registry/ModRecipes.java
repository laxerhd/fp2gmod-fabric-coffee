package fp2g.coffee.registry;

import fp2g.coffee.Coffee;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

public class ModRecipes {
    public static final String MOD_ID = Coffee.MOD_ID;

    public static void registerRecipes() {
        Coffee.LOGGER.info("Registriere Coffee Mod Recipes...");
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerItemRecipe(
                    Items.POTION,           // Input (Jede Trankflasche)
                    Ingredient.ofItem(ModItems.COFFEE_BEANS),  // Zutat
                    ModItems.COFFEE_CUP     // Output
            );
        });
    }
}
