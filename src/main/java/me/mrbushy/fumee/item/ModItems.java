package me.mrbushy.fumee.item;

import me.mrbushy.fumee.FumeeDeBushy;
import me.mrbushy.fumee.item.custom.CigaretteItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item TOBACCO = registerItem("tobacco", new Item(new FabricItemSettings()));

    public static final Item CIGARETTE = registerItem("cigarette",
            new CigaretteItem(new FabricItemSettings().maxDamage(8)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(TOBACCO);
    }
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(CIGARETTE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FumeeDeBushy.MOD_ID, name), item);
    }

    public static void registerModItems (){
        FumeeDeBushy.LOGGER.info("Registering items for " + FumeeDeBushy.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
    }
}
