package dev.xcolorful.cgccompat.legacyitem.core.api.item.ammobox;

import dev.xcolorful.cgccompat.legacyitem.core.api.item.LegacyAmmoBoxPropertyTag;
import dev.xcolorful.cgccompat.legacyitem.core.api.item.ammo.LegacyAmmoNBTAccessor;
import dev.xcolorful.customgun.core.api.item.ammobox.AmmoBoxDataAccessor;
import dev.xcolorful.customgun.core.util.NBTUtils;
import net.minecraft.world.item.ItemStack;

public interface LegacyAmmoBoxDataAccessor extends AmmoBoxDataAccessor, LegacyAmmoNBTAccessor {

    // --------IAmmoDataAccess--------

    @Override
    default int getAmmoCount(ItemStack ammoItem) {
        return Math.max(0, NBTUtils.getInt(ammoItem, LegacyAmmoBoxPropertyTag.AMMO_COUNT_OLD1));
    }
    @Override
    default void setAmmoCount(ItemStack ammoItem, int ammoCount) {
        NBTUtils.setInt(ammoItem, LegacyAmmoBoxPropertyTag.AMMO_COUNT_OLD1, ammoCount);
    }

    // --------IAmmoBoxDataAccess--------

    @Override
    default int getBoxLevel(ItemStack ammoItem) {
        return Math.max(0, NBTUtils.getInt(ammoItem, LegacyAmmoBoxPropertyTag.BOX_LEVEL_OLD1));
    }
    @Override
    default void setBoxLevel(ItemStack ammoItem, int boxLevel) {
        NBTUtils.setInt(ammoItem, LegacyAmmoBoxPropertyTag.BOX_LEVEL_OLD1, boxLevel);
    }
}
