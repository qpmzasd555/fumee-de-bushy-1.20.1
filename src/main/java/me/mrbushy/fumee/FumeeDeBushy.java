package me.mrbushy.fumee;

import me.mrbushy.fumee.item.ModItemGroups;
import me.mrbushy.fumee.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FumeeDeBushy implements ModInitializer {
	public static final String MOD_ID = "fumee-de-bushy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Smoking is harmful, care of yourself!");

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}