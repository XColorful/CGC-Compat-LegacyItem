package dev.xcolorful.cgccompat.legacyitem.forgeclient;

import dev.xcolorful.cgccompat.legacyitem.client.CgccLegacyItemClient;

public class CgccLegacyItemForgeClient {

    protected static boolean initialized;

    public static void init() {
        if (initialized) return;

        CgccLegacyItemClient.init();
        initialized = true;
    }
}
