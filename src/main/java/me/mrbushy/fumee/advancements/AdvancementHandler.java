package me.mrbushy.fumee.advancements;

import me.mrbushy.fumee.FumeeDeBushy;
import me.mrbushy.fumee.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class AdvancementHandler {
    public static final Identifier SMOKE_100_TIMES = new Identifier(FumeeDeBushy.MOD_ID, "smoke_100_times");

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(AdvancementHandler::checkPlayerCigaretteUsage);
    }

    private static void checkPlayerCigaretteUsage(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            // Get the player's bow usage stat
            Stat<?> cigaretteUsageStat = Stats.USED.getOrCreateStat(ModItems.CIGARETTE);
            int cigaretteUsageCount = player.getStatHandler().getStat(cigaretteUsageStat);

            // Check if the player has used the bow 100 times
            if (cigaretteUsageCount >= 100) {
                // Get the advancement from the server
                Advancement advancement = server.getAdvancementLoader().get(SMOKE_100_TIMES);
                if (advancement != null) {
                    // Get the player's advancement progress
                    AdvancementProgress progress = player.getAdvancementTracker().getProgress(advancement);
                    if (!progress.isDone()) {
                        // Grant the advancement
                        for (String criterion : progress.getUnobtainedCriteria()) {
                            player.getAdvancementTracker().grantCriterion(advancement, criterion);
                        }
                    }
                }
            }
        }
    }
}
