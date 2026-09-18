package dev.xcolorful.cgccompat.legacyitem.core.api.item.ammo;

import dev.xcolorful.cgccompat.legacyitem.core.api.item.LegacyAmmoPropertyTag;
import dev.xcolorful.customgun.core.api.item.ammo.AmmoNBTAccessor;
import dev.xcolorful.customgun.core.api.resource.ResourceTag;
import dev.xcolorful.customgun.core.util.NBTUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public interface LegacyAmmoNBTAccessor extends AmmoNBTAccessor {

    LegacyAmmoNBTAccessor INSTANCE = new LegacyAmmoNBTAccessor() {};

    // --------IAmmoNBTAccess--------

    @Override
    default @NotNull ResourceLocation getAmmoLocation(CompoundTag ammoItemCustomDataTag) {
        var ammoLocation = NBTUtils.getResourceLocation(ammoItemCustomDataTag, LegacyAmmoPropertyTag.AMMO_LOCATION_OLD1);
        return ammoLocation != null ? ammoLocation : ResourceTag.NULL_LOCATION;
    }
    @Override
    default void setAmmoLocation(CompoundTag ammoItemCustomDataTag, ResourceLocation ammoLocation) {
        NBTUtils.setResourceLocation(ammoItemCustomDataTag, LegacyAmmoPropertyTag.AMMO_LOCATION_OLD1, ammoLocation);
    }

    @Override
    default boolean hasInfiniteFeed(CompoundTag ammoItemCustomDataTag) {
        return NBTUtils.getBoolean(ammoItemCustomDataTag, LegacyAmmoPropertyTag.INFINITE_FEED_OLD1) || this.isAlmightyAmmo(ammoItemCustomDataTag);
    }
    @Override
    default void setInfiniteFeed(CompoundTag ammoItemCustomDataTag, boolean infiniteFeed) {
        NBTUtils.setBoolean(ammoItemCustomDataTag, LegacyAmmoPropertyTag.INFINITE_FEED_OLD1, infiniteFeed);
        if (!infiniteFeed) {
            if (this.isAlmightyAmmo(ammoItemCustomDataTag)) this.setAlmightyAmmo(ammoItemCustomDataTag, false);
        }
    }

    @Override
    default boolean isAlmightyAmmo(CompoundTag ammoItemCustomDataTag) {
        return NBTUtils.getBoolean(ammoItemCustomDataTag, LegacyAmmoPropertyTag.ALMIGHTY_AMMO_OLD1);
    }
    @Override
    default void setAlmightyAmmo(CompoundTag ammoItemCustomDataTag, boolean almighty) {
        NBTUtils.setBoolean(ammoItemCustomDataTag, LegacyAmmoPropertyTag.ALMIGHTY_AMMO_OLD1, almighty);
    }
}
