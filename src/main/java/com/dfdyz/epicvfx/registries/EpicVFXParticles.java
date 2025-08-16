package com.dfdyz.epicvfx.registries;

import com.dfdyz.epicvfx.EpicVFX;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EpicVFXParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_REGISTRIES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, EpicVFX.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> VFX_PROXY =
            PARTICLE_REGISTRIES.register("vfx_proxy", () -> {
                return new SimpleParticleType(true);
            });

}
