package shadows.apotheosis.village.wanderer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import shadows.placebo.config.Configuration;
import shadows.placebo.json.ItemAdapter;
import shadows.placebo.json.NBTAdapter;

/**
 * The wandering merchant sucks.  Trades are totally underwhelming and are borderline garbage 99% of the time.
 * @author Shadows
 *
 */
public class WandererReplacements {

	public static boolean clearNormTrades = false;
	public static boolean clearRareTrades = false;
	public static boolean affixTrades = true;

	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(BasicItemListing.class, BasicItemListingAdapter.INSTANCE).registerTypeAdapter(ItemStack.class, ItemAdapter.INSTANCE).registerTypeAdapter(CompoundTag.class, NBTAdapter.INSTANCE).create();

	@SubscribeEvent
	public static void replaceWandererArrays(WandererTradesEvent e) {
		if (clearNormTrades) e.getGenericTrades().clear();
		if (clearRareTrades) e.getRareTrades().clear();
		e.getGenericTrades().addAll(WandererTradeManager.INSTANCE.getNormalTrades());
		e.getRareTrades().addAll(WandererTradeManager.INSTANCE.getRareTrades());
	}

	public static void load(Configuration cfg) {
		clearNormTrades = cfg.getBoolean("Clear Generic Trades", "wanderer", false, "If the generic trade list will be cleared before datapack loaded trades are added.");
		clearRareTrades = cfg.getBoolean("Clear Rare Trades", "wanderer", false, "If the rare trade list will be cleared before datapack loaded trades are added.");
	}
}