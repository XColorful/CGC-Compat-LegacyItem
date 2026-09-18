/*
 * Copyright (c) 2025-2026 XiaoColorful (https://github.com/XColorful)
 * SPDX-License-Identifier: GPL-3.0-only
 *
 * Source: https://github.com/XColorful/Custom-Gun-Continued
 */

package dev.xcolorful.cgccompat.legacyitem.core.item.gun;

import dev.xcolorful.cgccompat.legacyitem.core.api.item.LegacyGunPropertyTag;
import dev.xcolorful.cgccompat.legacyitem.core.api.item.attachment.LegacyAttachmentNBTAccessor;
import dev.xcolorful.cgccompat.legacyitem.core.api.minecraft.item.LegacyItemType;
import dev.xcolorful.cgccompat.legacyitem.core.api.minecraft.item.attachment.LegacyAttachmentCategory;
import dev.xcolorful.customgun.CustomGun;
import dev.xcolorful.customgun.client.api.resource.ClientResourceApi;
import dev.xcolorful.customgun.client.resource.instance.assets.GunDisplayInstance;
import dev.xcolorful.customgun.client.resource.instance.data.ClientAttachmentIndexInstance;
import dev.xcolorful.customgun.core.api.item.AttachmentProperty;
import dev.xcolorful.customgun.core.api.item.IAttachment;
import dev.xcolorful.customgun.core.api.item.IGun;
import dev.xcolorful.customgun.core.api.item.attachment.AttachmentCategory;
import dev.xcolorful.customgun.core.api.item.attachment.IAttachmentGetter;
import dev.xcolorful.customgun.core.api.item.builder.AttachmentBuilder;
import dev.xcolorful.customgun.core.api.item.gun.FireModeType;
import dev.xcolorful.customgun.core.api.item.gun.IGunGetter;
import dev.xcolorful.customgun.core.api.resource.ResourceApi;
import dev.xcolorful.customgun.core.api.resource.ResourceTag;
import dev.xcolorful.customgun.core.developer.PlannedRefactor;
import dev.xcolorful.customgun.core.init.registry.ModItems;
import dev.xcolorful.customgun.core.item.gun.GunItem;
import dev.xcolorful.customgun.core.resource.instance.data.GunIndexInstance;
import dev.xcolorful.customgun.core.util.NBTUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LegacyGunItem extends GunItem {

    protected LegacyGunItem(Properties properties) {
        super(properties);
    }
    public LegacyGunItem() {
        this(ModItems.CUSTOM_ITEM_PROPERTY.apply(LegacyItemType.GUN.getRegistryLocation()));
    }

    // --------IGunDataAccess--------

    @Override
    public @NotNull Identifier getGunLocation(ItemStack gunItem) {
        var gunLocation = NBTUtils.getResourceLocation(gunItem, LegacyGunPropertyTag.GUN_LOCATION_OLD1);
        return gunLocation != null ? gunLocation : ResourceTag.NULL_LOCATION;
    }
    @Override
    public void setGunLocation(ItemStack gunItem, Identifier gunLocation) {
        NBTUtils.setResourceLocation(gunItem, LegacyGunPropertyTag.GUN_LOCATION_OLD1, gunLocation);
    }
    @Override
    public @NotNull Identifier getGunDisplayLocation(ItemStack gunItem) {
        var gunDisplayLocation = NBTUtils.getResourceLocation(gunItem, LegacyGunPropertyTag.GUN_DISPLAY_LOCATION_OLD1);
        if (gunDisplayLocation != null) return gunDisplayLocation;

        var gunLocation = this.getGunLocation(gunItem);
        @Nullable GunIndexInstance gunIndexInstance = ResourceApi.getGunIndexInstance(gunLocation);
        if (gunIndexInstance == null) return ResourceTag.NULL_LOCATION;

        return gunIndexInstance.getPojo().getDisplayIndexLocation();
    }
    @Override
    public void setGunDisplayLocation(ItemStack gunItem, Identifier gunDisplayLocation) {
        NBTUtils.setResourceLocation(gunItem, LegacyGunPropertyTag.GUN_DISPLAY_LOCATION_OLD1, gunDisplayLocation);
    }

    // --------IGunStateAccess--------

    @Override
    public @NotNull FireModeType getFireModeType(ItemStack gunItem) {
        if (gunItem.isEmpty()) return FireModeType.DEFAULT;
        FireModeType fireModeType = FireModeType.fromString(NBTUtils.getString(gunItem, LegacyGunPropertyTag.FIRE_MODE_TYPE_OLD1));
        return fireModeType != null ? fireModeType : FireModeType.DEFAULT;
    }
    @Override
    public void setFireModeType(ItemStack gunItem, FireModeType fireModeType) {
        if (gunItem.isEmpty()) return;
        NBTUtils.setString(gunItem, LegacyGunPropertyTag.FIRE_MODE_TYPE_OLD1, fireModeType.getTagName());
    }

    @Override
    public float getScopeZoomScale(ItemStack gunItem) {
        if (!CustomGun.getMcSide().isClientSide()) {
            if (PlannedRefactor.MOVE_ASSETS_TO_DATA) {
                throw new IllegalStateException("LegacyGunItem#getScopeZoomScale is client-side interface (currently)");
            }
        }

        @NotNull var scopeLocation = this.getAttachmentLocation(gunItem, AttachmentCategory.SCOPE);
        boolean builtIn = false;
        if (scopeLocation.equals(ResourceTag.NULL_LOCATION)) {
            scopeLocation = this.getBuiltinAttachmentLocation(gunItem, AttachmentCategory.SCOPE);
            builtIn = true;
        }

        float zoomScale = 1.0f;
        if (!scopeLocation.equals(ResourceTag.NULL_LOCATION)) {
            @Nullable CompoundTag attachmentCustomDataTag = this.getAttachmentCustomDataTag(gunItem, AttachmentCategory.SCOPE);
            int scopeViewIndex = builtIn ? 0 : LegacyAttachmentNBTAccessor.INSTANCE.getScopeViewIndex(attachmentCustomDataTag);
            if (PlannedRefactor.MOVE_SCOPE_VIEW_INDEX_TO_CORE) {
                return 0;
            }
            @Nullable ClientAttachmentIndexInstance attachmentIndexInstance = ClientResourceApi.getClientAttachmentIndexInstance(scopeLocation);
            if (attachmentIndexInstance != null) {
                float[] scopeZoomScale = attachmentIndexInstance.getAttachmentDisplay().getScopeZoomScale();
                if (scopeZoomScale != null) {
                    zoomScale = scopeZoomScale[scopeViewIndex % scopeZoomScale.length];
                }
            }
        } else {
            GunDisplayInstance gunDisplayInstance = ClientResourceApi.getGunDisplayInstance(gunItem);
            if (gunDisplayInstance != null) zoomScale = gunDisplayInstance.getPojo().getIronZoomScale();
        }
        return zoomScale;
    }

    @Override
    public boolean hasHeat(ItemStack gunItem) {
        return NBTUtils.hasKey(gunItem, LegacyGunPropertyTag.HEAT_OLD1);
    }
    @Override
    public float getHeatCount(ItemStack gunItem) {
        return NBTUtils.getFloat(gunItem, LegacyGunPropertyTag.HEAT_OLD1);
    }
    @Override
    public void setHeatCount(ItemStack gunItem, float amount) {
        NBTUtils.setFloat(gunItem, LegacyGunPropertyTag.HEAT_OLD1, amount);
    }
    @Override
    public boolean hasOverheatLock(ItemStack gunItem) {
        return NBTUtils.getBoolean(gunItem, LegacyGunPropertyTag.OVERHEAT_LOCK_OLD1);
    }
    @Override
    public void setOverheatLock(ItemStack gunItem, boolean locked) {
        NBTUtils.setBoolean(gunItem, LegacyGunPropertyTag.OVERHEAT_LOCK_OLD1, locked);
    }

    @Override
    public boolean hasAttachmentLock(ItemStack gunItem) {
        return NBTUtils.getBoolean(gunItem, LegacyGunPropertyTag.ATTACHMENT_LOCK_OLD1);
    }
    @Override
    public void setAttachmentLock(ItemStack gunItem, boolean value) {
        NBTUtils.setBoolean(gunItem, LegacyGunPropertyTag.ATTACHMENT_LOCK_OLD1, value);
    }

    @Override
    public boolean hasLaserColor(ItemStack gunItem) {
        return NBTUtils.hasKey(gunItem, LegacyGunPropertyTag.LASER_COLOR_OLD1);
    }
    @Override
    public int getLaserColorInt(ItemStack gunItem) {
        return NBTUtils.getInt(gunItem, LegacyGunPropertyTag.LASER_COLOR_OLD1);
    }
    @Override
    public void setLaserColorInt(ItemStack gunItem, int colorInt) {
        NBTUtils.setInt(gunItem, LegacyGunPropertyTag.LASER_COLOR_OLD1, colorInt);
    }

    @Override
    public boolean hasTooltipMask(ItemStack gunItem) {
        return NBTUtils.hasKey(gunItem, LegacyGunPropertyTag.TOOLTIP_MASK_OLD1);
    }
    @Override
    public int getTooltipMask(ItemStack gunItem) {
        return NBTUtils.getInt(gunItem, LegacyGunPropertyTag.TOOLTIP_MASK_OLD1);
    }
    @Override
    public void setTooltipMask(ItemStack gunItem, int tooltipMask) {
        NBTUtils.setInt(gunItem, LegacyGunPropertyTag.TOOLTIP_MASK_OLD1, tooltipMask);
    }

    // --------IGunAmmoDataAccess--------

    @Override
    public boolean useDummyAmmo(ItemStack gunItem) {
        return NBTUtils.hasKey(gunItem, LegacyGunPropertyTag.DUMMY_AMMO_OLD1);
    }
    @Override
    public int getDummyAmmoCount(ItemStack gunItem) {
        return Math.max(0, NBTUtils.getInt(gunItem, LegacyGunPropertyTag.DUMMY_AMMO_OLD1));
    }
    @Override
    public void setDummyAmmoCount(ItemStack gunItem, int amount) {
        NBTUtils.setInt(gunItem, LegacyGunPropertyTag.DUMMY_AMMO_OLD1, Math.min(amount, this.getDummyAmmoLimit(gunItem)));
    }
    @Override
    public boolean hasDummyAmmoLimit(ItemStack gunItem) {
        return NBTUtils.hasKey(gunItem, LegacyGunPropertyTag.DUMMY_AMMO_LIMIT_OLD1);
    }
    @Override
    public int getDummyAmmoLimit(ItemStack gunItem) {
        return Math.max(0, Math.min(NBTUtils.getInt(gunItem, LegacyGunPropertyTag.DUMMY_AMMO_LIMIT_OLD1), Integer.MAX_VALUE));
    }
    @Override
    public void setDummyAmmoLimit(ItemStack gunItem, int max) {
        NBTUtils.setInt(gunItem, LegacyGunPropertyTag.DUMMY_AMMO_LIMIT_OLD1, max);
    }

    @Override
    public int getMagAmmoCount(ItemStack gunItem) {
        return Math.max(0, NBTUtils.getInt(gunItem, LegacyGunPropertyTag.MAG_AMMO_OLD1));
    }
    @Override
    public void setMagAmmoCount(ItemStack gunItem, int count) {
        NBTUtils.setInt(gunItem, LegacyGunPropertyTag.MAG_AMMO_OLD1, count);
    }
    @Override
    public int consumeMagAmmoOnce(ItemStack gunItem) {
        int current = this.getMagAmmoCount(gunItem);
        if (current <= 0) return 0;
        NBTUtils.setInt(gunItem, LegacyGunPropertyTag.MAG_AMMO_OLD1, current - 1);
        return 1;
    }

    @Override
    public int getBarrelAmmoCount(ItemStack gunItem) {
        return NBTUtils.getBoolean(gunItem, LegacyGunPropertyTag.BARREL_AMMO_OLD1) ? 1 : 0;
    }
    @Override
    public void setBarrelAmmoCount(ItemStack gunItem, int amount) {
        NBTUtils.setInt(gunItem, LegacyGunPropertyTag.BARREL_AMMO_OLD1, amount);
    }

    // --------IGunAttachmentDataAccess--------

    @Override
    public @NotNull ItemStack getAttachment(ItemStack gunItem, AttachmentCategory attachmentCategory) {
        @Nullable CompoundTag attachmentCustomDataTag = this.getAttachmentCustomDataTag(gunItem, attachmentCategory);

        if (attachmentCustomDataTag == null) {
            return ItemStack.EMPTY;
        }

        return AttachmentBuilder.create(dev.xcolorful.cgccompat.legacyitem.core.init.registry.ModItems.ATTACHMENT.get())
                // 先写已有的NBT
                .setCustomDataTag(attachmentCustomDataTag)
                // 配件类型在gun nbt的key
                .setProperty(AttachmentProperty.ATTACHMENT_CATEGORY,
                        AttachmentCategory.class,
                        attachmentCategory)
                .build();
    }
    @Override
    public @NotNull ItemStack getBuiltinAttachment(ItemStack gunItem, AttachmentCategory attachmentCategory) {
        @Nullable IGun iGun = IGunGetter.fromItemStack(gunItem);
        if (iGun == null) return ItemStack.EMPTY;

        @Nullable GunIndexInstance gunIndexInstance = ResourceApi.getGunIndexInstance(iGun.getGunLocation(gunItem));
        if (gunIndexInstance == null) {
            return ItemStack.EMPTY;
        }

        var builtinAttachments = gunIndexInstance.getGunData().getBuiltinAttachments();
        if (builtinAttachments.containsKey(attachmentCategory)) {
            return AttachmentBuilder.create(dev.xcolorful.cgccompat.legacyitem.core.init.registry.ModItems.ATTACHMENT.get())
                    // 配件ResourceLocation
                    .setProperty(AttachmentProperty.ATTACHMENT_LOCATION,
                            Identifier.class,
                            builtinAttachments.get(attachmentCategory))
                    // 配件类型
                    .setProperty(AttachmentProperty.ATTACHMENT_CATEGORY,
                            AttachmentCategory.class,
                            attachmentCategory)
                    .build();
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public @Nullable CompoundTag getAttachmentCustomDataTag(ItemStack gunItem, AttachmentCategory attachmentCategory) {
        @Nullable var customData = NBTUtils.getCustomData(gunItem);
        if (customData == null) return null;

        if (!isAttachmentEnabled(gunItem, attachmentCategory)) {
            return null;
        }

        @NotNull CompoundTag customDataTag = NBTUtils.getCustomDataTag(customData); // 涉及tag复制 (1.21.1+)
        return NBTUtils.getCompoundTag(customDataTag,
                LegacyAttachmentCategory.getTagName(attachmentCategory));
//                attachmentCategory.getTagName());
    }
    @Override
    public void setAttachmentCustomDataTag(ItemStack gunItem, AttachmentCategory attachmentCategory, CompoundTag attachmentCustomDataTag) {
        @Nullable var customData = NBTUtils.getCustomData(gunItem);
        if (customData == null && attachmentCustomDataTag == null) return;

        @NotNull CompoundTag customDataTag = customData != null ? NBTUtils.getCustomDataTag(customData) : new CompoundTag();

        // 将attachment Tag写入tag
        NBTUtils.setCompoundTag(customDataTag, LegacyAttachmentCategory.getTagName(attachmentCategory), attachmentCustomDataTag);

        // 将tag存入item
        NBTUtils.setCustomDataTag(gunItem, customDataTag);
    }

    @Override
    public @NotNull Identifier getAttachmentLocation(ItemStack gunItem, AttachmentCategory attachmentCategory) {
        return LegacyAttachmentNBTAccessor.INSTANCE.getAttachmentLocation(this.getAttachmentCustomDataTag(gunItem, attachmentCategory));
    }

    @Override
    public boolean installAttachment(ItemStack gunItem, ItemStack attachmentItem) {
        @Nullable IAttachment iAttachment = IAttachmentGetter.fromItemStack(attachmentItem);
        if (iAttachment == null) return false;

        if (!this.canInstallAttachment(gunItem, attachmentItem)) {
            return false;
        }

        // 没有CustomData就没数据，为无效配件
        @Nullable var customData = NBTUtils.getCustomData(attachmentItem);
        if (customData == null) {
            return false;
        }
        @NotNull CompoundTag attachmentCustomDataTag = NBTUtils.getCustomDataTag(customData);

        AttachmentCategory category = iAttachment.getAttachmentCategory(attachmentItem);
        NBTUtils.setCompoundTag(gunItem,
                LegacyAttachmentCategory.getTagName(category),
//                category.getCategoryName(),
                attachmentCustomDataTag);
        return true;
    }
    @Override
    public void removeAttachment(ItemStack gunItem, AttachmentCategory attachmentCategory) {
        NBTUtils.removeKey(gunItem,
                LegacyAttachmentCategory.getTagName(attachmentCategory));
//                attachmentCategory.getCategoryName());
    }

    // --------IGunExpAccess--------

    @Override
    public int getGunExp(ItemStack gunItem) {
        return Math.max(0, NBTUtils.getInt(gunItem, LegacyGunPropertyTag.GUN_EXP_OLD1));
    }
    @Override
    public void setGunExp(ItemStack gunItem, int exp) {
        NBTUtils.setInt(gunItem, LegacyGunPropertyTag.GUN_EXP_OLD1, exp);
    }
}
