package com.dfdyz.epicvfx.command;

import com.dfdyz.epicvfx.EpicVFX;
import com.dfdyz.epicvfx.client.photon.fx.EFPatchExecutor;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;


@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EpicVFX.MODID, value = {Dist.CLIENT})
public class ClientCommand {
    @SubscribeEvent
    public static void register(RegisterClientCommandsEvent event) {
        var dispatcher = event.getDispatcher();

        var cmdCtx = Commands.literal(EpicVFX.MODID);
        var cmdCtx_Debug = Commands.literal("debug").executes(c -> {
            Debug();
            return 1;
        });

        cmdCtx.then(cmdCtx_Debug);

        dispatcher.register(cmdCtx);

        EpicVFX.LOGGER.warn("Command Reg.");
    }

    public static void Debug(){
        try {

            EFPatchExecutor.CACHE.clear();
            var player = Minecraft.getInstance().player;
            var patch = EpicFightCapabilities.getEntityPatch(player, LocalPlayerPatch.class);

            var fx = FXHelper.getFX(ResourceLocation.fromNamespaceAndPath("photon", "test"));

            var biped = Armatures.BIPED;
            var executor = new EFPatchExecutor(fx, player.level() ,patch, EFPatchExecutor.GetJointHandler(biped.get().toolR));
            executor.start();

        }catch (Exception e){
            e.printStackTrace(System.err);
        }
    }

}
