package uk.co.envyware.battle.extension;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EnvyModDetection.MOD_ID)
@Mod.EventBusSubscriber(modid = EnvyModDetection.MOD_ID)
public class EnvyModDetection {

    public static final String MOD_ID = "pixelmonmoddetection";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

}
