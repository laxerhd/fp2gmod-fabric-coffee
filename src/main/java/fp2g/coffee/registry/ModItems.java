package fp2g.coffee.registry;

import fp2g.coffee.Coffee;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

import java.util.List;
import java.util.function.Consumer;

public class ModItems {

    public static final String MOD_ID = Coffee.MOD_ID;

    public static final Item COFFEE_BEANS = registerItem("coffee_beans");
    public static final Item COFFEE_CUP = registerCoffeeCup("coffee_cup");

    private static Item registerItem(String name) {
        Identifier id = Identifier.of(MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = new Item.Settings().registryKey(key);
        Item item = new Item(settings);
        return Registry.register(Registries.ITEM, key, item);
    }

    private static Item registerCoffeeCup(String name) {
        Identifier id = Identifier.of(MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        FoodComponent food = new FoodComponent.Builder()
                .nutrition(4)
                .saturationModifier(0.1f)
                .alwaysEdible()
                .build();

        ConsumableComponent consumable = ConsumableComponent.builder()
                .consumeSeconds(2.0f)
                .useAction(UseAction.DRINK)
                .sound(ModSounds.COFFEE_SLURP)
                .consumeEffect(new ApplyEffectsConsumeEffect(
                        new StatusEffectInstance(ModPotions.COFFEE_EFFECT, 3600)
                ))
                .build();

        Item.Settings settings = new Item.Settings()
                .registryKey(key)
                .maxCount(16)
                .component(DataComponentTypes.FOOD, food)
                .component(DataComponentTypes.CONSUMABLE, consumable)
                .component(DataComponentTypes.TOOLTIP_DISPLAY,
                        TooltipDisplayComponent.DEFAULT.with(DataComponentTypes.POTION_CONTENTS, true)
                );

        Item item = new PotionItem(settings) {

            @Override
            public Text getName(ItemStack stack) {
                return Text.translatable(this.getTranslationKey());
            }

            @Override
            public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
                Text effectName = Text.translatable(ModPotions.COFFEE_EFFECT.value().getTranslationKey());
                Text fullText = Text.empty()
                        .append(effectName)
                        .append(" (3:00)")
                        .formatted(Formatting.BLUE);
                textConsumer.accept(fullText);
            }

            @Override
            public ItemStack getDefaultStack() {
                ItemStack stack = super.getDefaultStack();
                stack.remove(DataComponentTypes.POTION_CONTENTS);
                return stack;
            }
        };

        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModItems() {
        Coffee.LOGGER.info("Registriere Coffee Mod Items...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(COFFEE_BEANS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(COFFEE_CUP);
        });
    }
}