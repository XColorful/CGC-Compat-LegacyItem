package dev.xcolorful.cgccompat.legacyitem.client;

public class CgccLegacyItemClient {

    protected static boolean initialized;

    public static void init() {
        if (initialized) return;

        initialized = true;
    }
}
