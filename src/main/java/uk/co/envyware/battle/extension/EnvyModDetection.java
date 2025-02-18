package uk.co.envyware.battle.extension;

import com.pixelmonmod.pixelmon.api.config.api.yaml.YamlConfigFactory;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.envyware.battle.extension.config.ModDetectionConfig;

import java.io.IOException;

@Mod(EnvyModDetection.MOD_ID)
@Mod.EventBusSubscriber(modid = EnvyModDetection.MOD_ID)
public class EnvyModDetection {

    public static final String MOD_ID = "pixelmonmoddetection";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    private static EnvyModDetection instance;

    private ModDetectionConfig config;

    public EnvyModDetection() {
        instance = this;
        this.reload();
    }

    public void reload() {
        try {
            this.config = YamlConfigFactory.getInstance(ModDetectionConfig.class);
        } catch (IOException e) {
            LOGGER.error("Failed to load config", e);
        }
    }

    public static ModDetectionConfig getConfig() {
        return instance.config;
    }
}
