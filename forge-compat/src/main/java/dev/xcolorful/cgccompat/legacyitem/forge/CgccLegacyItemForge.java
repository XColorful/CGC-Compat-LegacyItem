package dev.xcolorful.cgccompat.legacyitem.forge;

import dev.xcolorful.cgccompat.legacyitem.CgccLegacyItem;
import dev.xcolorful.cgccompat.legacyitem.forgeclient.CgccLegacyItemForgeClient;
import dev.xcolorful.customgun.CustomGun;
import dev.xcolorful.customgun.core.api.common.McSide;
import net.minecraftforge.fml.common.Mod;

@Mod(CgccLegacyItem.MOD_ID)
public class CgccLegacyItemForge {

    public CgccLegacyItemForge() {
        McSide mcSide = CustomGun.getMcSide();

        CgccLegacyItem.init();

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
