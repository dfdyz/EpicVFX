package com.dfdyz.epicvfx;

import com.dfdyz.epicvfx.client.particle.EmitterProxy;
import com.dfdyz.epicvfx.registries.EpicVFXParticles;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;


@Mod(value = EpicVFX.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = EpicVFX.MODID, value = Dist.CLIENT)
public class EpicVFXClient {
    public EpicVFXClient(ModContainer container) {
        //container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(RegisterParticleProvidersEvent event) {
        event.registerSpecial(EpicVFXParticles.VFX_PROXY.get(), new EmitterProxy.Provider());
    }
}
