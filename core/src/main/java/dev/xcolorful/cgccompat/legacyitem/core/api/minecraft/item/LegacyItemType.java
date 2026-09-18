package dev.xcolorful.cgccompat.legacyitem.core.api.minecraft.item;

import dev.xcolorful.cgccompat.legacyitem.CgccLegacyItem;
import dev.xcolorful.customgun.CustomGun;
import dev.xcolorful.customgun.core.api.resource.ResourceTag;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum LegacyItemType implements ResourceTag.RegistryTag {
    // Item
    GUN(LegacyItemTypeTag.GUN_OLD1),
    ATTACHMENT(LegacyItemTypeTag.ATTACHMENT_OLD1),
    AMMO(LegacyItemTypeTag.AMMO_OLD1),
    AMMO_BOX(LegacyItemTypeTag.AMMO_BOX_OLD1),;

    public final String typeName;
    public final String registryName;
    public final Identifier registryLocation;
    LegacyItemType(String name) {
        this.typeName = name;
        this.registryName = String.format("%s:%s", CustomGun.MOD_ID_OLD1, this.typeName);
        this.registryLocation = CgccLegacyItem.getMcRegistry().createResourceLocation(this.registryName);
    }
    @Override public String getTagName() {
        return this.typeName;
    }
    @Override public String getRegistryName() {
        return this.registryName;
    }
    @Override public Identifier getRegistryLocation() {
        return this.registryLocation;
    }

    private static final Map<String, LegacyItemType> ITEM_TYPES = new HashMap<>();

    static {
        for (LegacyItemType type : values()) {
            ITEM_TYPES.put(type.typeName, type);
            ITEM_TYPES.put(type.registryName, type);
        }
    }

    public static @Nullable LegacyItemType fromString(String name) {
        return name != null ? ITEM_TYPES.get(name) : null;
    }

    @Override
    public String toString() {
        return this.typeName;
    }
}
