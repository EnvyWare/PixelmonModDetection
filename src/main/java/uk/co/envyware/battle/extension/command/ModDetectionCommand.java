package uk.co.envyware.battle.extension.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import uk.co.envyware.battle.extension.EnvyModDetection;

public class ModDetectionCommand {

    public ModDetectionCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("moddetection")
                        .requires(commandSource -> commandSource.hasPermission(4))
                .executes(commandContext -> {
                    EnvyModDetection.reload();
                    commandContext.getSource().sendSuccess(new StringTextComponent("Reloaded mod detection"), false);
                    return 1;
                }));
    }

}
