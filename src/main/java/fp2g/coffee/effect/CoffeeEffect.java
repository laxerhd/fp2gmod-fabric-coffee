package fp2g.coffee.effect;

import fp2g.coffee.registry.ModPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class CoffeeEffect extends StatusEffect {

    public CoffeeEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x4e342e);
        this.addAttributeModifier(EntityAttributes.MOVEMENT_SPEED, Identifier.of(ModPotions.MOD_ID, "coffee_speed"), 2.0F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(EntityAttributes.JUMP_STRENGTH, Identifier.of(ModPotions.MOD_ID, "coffee_jump"), 0.3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        StatusEffectInstance hiddenNightVision = new StatusEffectInstance(
                StatusEffects.NIGHT_VISION,
                300,                        // Dauer: infinity damits bsl schöner aussieht
                0,                          // Stärke
                false,                      // ambient
                false,                      // visible
                false                       // showIcon
        );
        StatusEffectInstance hiddenHaste = new StatusEffectInstance(
                StatusEffects.HASTE,
                300,                        // Dauer: infinity damits bsl schöner aussieht
                1,                          // Stärke
                false,                      // ambient
                false,                      // visible
                false                       // showIcon
        );

        entity.addStatusEffect(hiddenNightVision);
        entity.addStatusEffect(hiddenHaste);
        return true;
    }


}
