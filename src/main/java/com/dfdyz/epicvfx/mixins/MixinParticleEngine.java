package com.dfdyz.epicvfx.mixins;

import com.dfdyz.epicvfx.client.photon.fx.EFPatchExecutor;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleEngine.class)
public abstract class MixinParticleEngine {

    /**
     * clear effect cache while level changes.
     */
    @Inject(method = "setLevel",
            at = @At(value = "RETURN"))
    private void photon$injectSetLevel(ClientLevel level, CallbackInfo ci) {
        EFPatchExecutor.CACHE.clear();
    }
}
