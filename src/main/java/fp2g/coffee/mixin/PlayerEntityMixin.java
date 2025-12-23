package fp2g.coffee.mixin;


import com.mojang.datafixers.util.Either;
import fp2g.coffee.registry.ModPotions;
import net.fabricmc.fabric.mixin.event.lifecycle.LivingEntityMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    // TrySleep wird aufgerufen bei rechtsklick auf bett
    @Inject(method = "trySleep", at = @At("HEAD"), cancellable = true)
    private void preventSleepEntry(BlockPos pos, CallbackInfoReturnable<Either<PlayerEntity.SleepFailureReason, Unit>> cir) {

        if (this.hasStatusEffect(ModPotions.COFFEE_EFFECT)) {
            PlayerEntity player = (PlayerEntity) (Object) this;
            player.sendMessage(Text.translatable("message.coffee.insomnia"), true);
            // Stop from sleeping
            cir.setReturnValue(Either.left(PlayerEntity.SleepFailureReason.OTHER));
        }
    }

}