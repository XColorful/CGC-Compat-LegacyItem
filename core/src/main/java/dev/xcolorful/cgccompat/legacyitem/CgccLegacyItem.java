package dev.xcolorful.cgccompat.legacyitem;

import com.mojang.logging.LogUtils;
import dev.xcolorful.customgun.core.api.init.registry.IRegistrarFactory;
import dev.xcolorful.customgun.core.api.minecraft.IMcRegistry;
import org.slf4j.Logger;

public class CgccLegacyItem {
    public static final String MOD_ID = "cgcclegacyitem";
    public static final Logger LOGGER = LogUtils.getLogger();

    protected static boolean initialized;
    private static IRegistrarFactory registrarFactory;
    private static IMcRegistry mcRegistry;

    public static void init(IRegistrarFactory factory, IMcRegistry mcRegistry) {
        if (initialized) return;
        CgccLegacyItem.registrarFactory = factory;
        CgccLegacyItem.mcRegistry = mcRegistry;

        initialized = true;
    }

    public static IRegistrarFactory getRegistrarFactory() {
        if (registrarFactory == null) {
            throw new IllegalStateException("Registrar factory has not been initialized. Call init() first.");
        }
        return registrarFactory;
    }
    public static IMcRegistry getMcRegistry() {
        if (mcRegistry == null) {
            throw new IllegalStateException("Mc registry has not been initialized. Call init() first.");
        }
        return mcRegistry;
    }
}
