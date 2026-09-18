package dev.xcolorful.cgccompat.legacyitem.neoforge;

import dev.xcolorful.cgccompat.legacyitem.CgccLegacyItem;
import dev.xcolorful.cgccompat.legacyitem.core.init.registry.ModItems;
import dev.xcolorful.cgccompat.legacyitem.neoforgeclient.CgccLegacyItemNeoforgeClient;
import dev.xcolorful.customgun.core.api.common.McSide;
import dev.xcolorful.customgun.core.api.init.registry.IRegistrarFactory;
import dev.xcolorful.customgun.core.api.minecraft.IMcRegistry;
import dev.xcolorful.customgun.neoforge.init.registry.NeoRegistrarFactory;
import dev.xcolorful.customgun.neoforge.minecraft.NeoRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;

@Mod(CgccLegacyItem.MOD_ID)
public class CgccLegacyItemNeoforge {

    public static IRegistrarFactory registrarFactory;
    public static IMcRegistry mcRegistry;

    public CgccLegacyItemNeoforge(IEventBus modEventBus) {
        CgccLegacyItemNeoforge.registrarFactory = new NeoRegistrarFactory();
        CgccLegacyItemNeoforge.mcRegistry = new NeoRegistry();
        Dist dist = FMLLoader.getCurrent().getDist();
        McSide mcSide = dist.isClient() ? McSide.CLIENT : McSide.DEDICATED_SERVER;

        CgccLegacyItem.init(CgccLegacyItemNeoforge.registrarFactory, CgccLegacyItemNeoforge.mcRegistry);

        ModItems.ITEMS.registerAll(modEventBus);

        if (mcSide == McSide.CLIENT) {
            _CgccLegacyItemNeoforgeClient.init();
        }
    }

    private static class _CgccLegacyItemNeoforgeClient {
        public static void init() {
            CgccLegacyItemNeoforgeClient.init();
        }
    }
}
