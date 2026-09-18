package dev.xcolorful.cgccompat.legacyitem;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class CgccLegacyItem {
    public static final String MOD_ID = "cgcclegacyitem";
    public static final Logger LOGGER = LogUtils.getLogger();

    protected static boolean initialized;

    public static void init() {
        if (initialized) return;

        initialized = true;
    }
}
