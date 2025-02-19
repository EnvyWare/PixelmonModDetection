package uk.co.envyware.battle.extension.config;

import com.pixelmonmod.pixelmon.api.config.api.data.ConfigPath;
import com.pixelmonmod.pixelmon.api.config.api.yaml.AbstractYamlConfig;
import info.pixelmon.repack.org.spongepowered.objectmapping.ConfigSerializable;
import info.pixelmon.repack.org.spongepowered.objectmapping.meta.Comment;

import java.util.Arrays;
import java.util.List;

@ConfigSerializable
@ConfigPath("config/ModDetection/config.yml")
public class ModDetectionConfig extends AbstractYamlConfig {

    @Comment("A list of files that the client will scan for, and return true if found. You can find the class name by decompiling the mod's JAR file and looking at the resource files they have!")
    private List<String> blockedFiles = Arrays.asList(
            "pokehud:textures/gui/compass.png"
    );

    @Comment("The commands executed by console when a player is detected using illegal mods. %player% will be replaced with the player's name.")
    private List<String> detectionCommands = Arrays.asList(
            "broadcast %player% has been detected using illegal mods!",
            "kick %player% Please uninstall your hacks you dirty hacker!"
    );

    public ModDetectionConfig() {
        super();
    }

    public List<String> getBlockedFiles() {
        return this.blockedFiles;
    }

    public List<String> getDetectionCommands() {
        return this.detectionCommands;
    }
}
