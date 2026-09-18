package dev.xcolorful.cgccompat.legacyitem.neoforgeclient;

import dev.xcolorful.cgccompat.legacyitem.client.CgccLegacyItemClient;

public class CgccLegacyItemNeoforgeClient {

    protected static boolean initialized;

    public static void init() {
        if (initialized) return;

        CgccLegacyItemClient.init();
        initialized = true;
    }
}
