package xyz.devcmb.commander.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import xyz.devcmb.commander.client.screens.*;

public class CommanderClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            commandDispatcher.register(
                CommandManager.literal("tellraw")
                    .executes(context -> {
                        MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().setScreen(new TellRawCommandScreen()));
                        return 1;
                    })
            );

            commandDispatcher.register(
                CommandManager.literal("bossbar")
                    .executes(context -> {
                        MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().setScreen(new BossbarCommandScreen()));
                        return 1;
                    })
            );
        }));
    }
}
