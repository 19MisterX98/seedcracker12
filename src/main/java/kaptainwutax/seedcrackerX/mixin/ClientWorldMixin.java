package kaptainwutax.seedcrackerX.mixin;

import kaptainwutax.seedcrackerX.SeedCracker;
import kaptainwutax.seedcrackerX.profile.config.ConfigScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BuiltinBiomes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin {

    @Inject(method = "disconnect", at = @At("HEAD"))
    private void disconnect(CallbackInfo ci) {
        SeedCracker.get().setActive(ConfigScreen.getConfig().isActive());
        SeedCracker.get().reset();
    }

    @Inject(method = "getGeneratorStoredBiome", at = @At("HEAD"), cancellable = true)
    private void getGeneratorStoredBiome(int x, int y, int z, CallbackInfoReturnable<Biome> ci) {
        ci.setReturnValue(BuiltinBiomes.THE_VOID);
    }

}
