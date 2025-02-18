package uk.co.envyware.battle.extension.listener;

import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import uk.co.envyware.battle.extension.EnvyModDetection;

public class ModDetectionListener {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerPartyStorage party = StorageProxy.getParty((ServerPlayerEntity) event.getPlayer());

        if (party == null) {
            return;
        }

        for (String blockedClass : EnvyModDetection.getConfig().getBlockedFiles()) {
            ResourceLocation resourceLocation = ResourceLocation.tryParse(blockedClass);

            if (resourceLocation == null) {
                EnvyModDetection.LOGGER.error("Invalid resource location: " + blockedClass);
                continue;
            }

            party.queryResourceLocationExistence(ResourceLocation.tryParse(blockedClass), result -> {
                if (result) {
                    EnvyModDetection.LOGGER.info("Player " + event.getPlayer().getName().getString() + " has a banned class: " + blockedClass);
                    for (String detectionCommand : EnvyModDetection.getConfig().getDetectionCommands()) {
                        ServerLifecycleHooks.getCurrentServer().getCommands().performCommand(
                                ServerLifecycleHooks.getCurrentServer().createCommandSourceStack(),
                                detectionCommand.replace("%player%", event.getPlayer().getName().getString())
                        );
                    }
                }
            });
        }
    }

}
