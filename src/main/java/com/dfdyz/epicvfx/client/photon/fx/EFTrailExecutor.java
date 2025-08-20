package com.dfdyz.epicvfx.client.photon.fx;

import ca.weblite.objc.Client;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.gameobject.IFXObject;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.client.animation.property.TrailInfo;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.ArrayList;

public class EFTrailExecutor extends EFPatchExecutor{

    public final AssetAccessor<? extends StaticAnimation> animation;
    public final TrailInfo trailInfo;

    public EFTrailExecutor(FX fx, LivingEntityPatch<?> entityPatch, Joint joint, AssetAccessor<? extends StaticAnimation> animation, TrailInfo info) {
        super(fx, entityPatch.getLevel(), entityPatch, GetJointHandler(joint));
        this.animation = animation;
        this.trailInfo = info;
        this.setAllowMulti(true);
    }

    public EFTrailExecutor(FX fx, ClientLevel level, LivingEntityPatch<?> entityPatch, Joint joint, AssetAccessor<? extends StaticAnimation> animation, TrailInfo info) {
        super(fx, level, entityPatch, GetJointHandler(joint));
        this.animation = animation;
        this.trailInfo = info;
        this.setAllowMulti(true);
    }

    @Override
    public void updateFXObjectTick(IFXObject fxObject) {
        super.updateFXObjectTick(fxObject);

    }

    public boolean canContinue() {
        AnimationPlayer animPlayer = entityPatch.getAnimator().getPlayerFor(this.animation);
        return entityPatch.getOriginal().isAlive()
                && this.animation == animPlayer.getRealAnimation()
                && animPlayer.getElapsedTime() <= this.trailInfo.endTime();
    }

    public boolean canContinue(float pt) {
        AnimationPlayer animPlayer = entityPatch.getAnimator().getPlayerFor(this.animation);

        float cet = animPlayer.getElapsedTime();
        float pet = animPlayer.getPrevElapsedTime();
        float ret = (cet - pet) * pt + pet;

        return entityPatch.getOriginal().isAlive()
                && this.animation == animPlayer.getRealAnimation()
                && ret <= this.trailInfo.endTime() + 0.02f;
    }
}
