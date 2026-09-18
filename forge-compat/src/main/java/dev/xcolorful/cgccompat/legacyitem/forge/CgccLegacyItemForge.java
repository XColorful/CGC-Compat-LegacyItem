package dev.xcolorful.cgccompat.legacyitem.forge;

import dev.xcolorful.cgccompat.legacyitem.CgccLegacyItem;
import dev.xcolorful.cgccompat.legacyitem.core.init.registry.ModItems;
import dev.xcolorful.cgccompat.legacyitem.forgeclient.CgccLegacyItemForgeClient;
import dev.xcolorful.customgun.core.api.common.McSide;
import dev.xcolorful.customgun.core.api.init.registry.IRegistrarFactory;
import dev.xcolorful.customgun.core.api.minecraft.IMcRegistry;
import dev.xcolorful.customgun.forge.init.registry.ForgeRegistrarFactory;
import dev.xcolorful.customgun.forge.minecraft.ForgeRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;

@Mod(CgccLegacyItem.MOD_ID)
public class CgccLegacyItemForge {

    public static IRegistrarFactory registrarFactory;
    public static IMcRegistry mcRegistry;

    public CgccLegacyItemForge() {
        CgccLegacyItemForge.registrarFactory = new ForgeRegistrarFactory();
        CgccLegacyItemForge.mcRegistry = new ForgeRegistry();
        Dist dist = FMLLoader.getDist();
        McSide mcSide = dist.isClient() ? McSide.CLIENT : McSide.DEDICATED_SERVER;

        CgccLegacyItem.init(CgccLegacyItemForge.registrarFactory, CgccLegacyItemForge.mcRegistry);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.registerAll(modEventBus);

        if (mcSide == McSide.CLIENT) {
            _CgccLegacyItemForgeClient.init();
        }
    }

    private static class _CgccLegacyItemForgeClient {
        public static void init() {
            CgccLegacyItemForgeClient.init();
        }
    }
}
