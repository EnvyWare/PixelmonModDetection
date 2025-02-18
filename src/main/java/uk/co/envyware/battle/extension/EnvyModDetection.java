package uk.co.envyware.battle.extension;

import com.pixelmonmod.pixelmon.api.config.api.yaml.YamlConfigFactory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.envyware.battle.extension.command.ModDetectionCommand;
import uk.co.envyware.battle.extension.config.ModDetectionConfig;
import uk.co.envyware.battle.extension.listener.ModDetectionListener;

import java.io.IOException;

@Mod(EnvyModDetection.MOD_ID)
public class EnvyModDetection {

    public static final String MOD_ID = "pixelmonmoddetection";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    private static EnvyModDetection instance;

    private ModDetectionConfig config;

    public EnvyModDetection() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(new ModDetectionListener());
        MinecraftForge.EVENT_BUS.register(this);
        reload();
    }

    public static void reload() {
        try {
            instance.config = YamlConfigFactory.getInstance(ModDetectionConfig.class);
        } catch (IOException e) {
            LOGGER.error("Failed to load config", e);
        }
    }

    public static ModDetectionConfig getConfig() {
        return instance.config;
    }

    @SubscribeEvent
    public void onCommandRegister(RegisterCommandsEvent event) {
        new ModDetectionCommand(event.getDispatcher());
    }
}
