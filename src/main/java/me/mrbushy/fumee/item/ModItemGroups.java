package me.mrbushy.fumee.item;

import me.mrbushy.fumee.FumeeDeBushy;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup TOBACCO_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(FumeeDeBushy.MOD_ID, "tobacco"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.tobacco"))
                    .icon(() -> new ItemStack(ModItems.TOBACCO)).entries((displayContext, entries) -> {
                        entries.add(ModItems.CIGARETTE);
                        entries.add(ModItems.TOBACCO);
                    }).build());


    public static void registerItemGroups() {
        FumeeDeBushy.LOGGER.info("Registering item groups for " + FumeeDeBushy.MOD_ID);
    }
}
