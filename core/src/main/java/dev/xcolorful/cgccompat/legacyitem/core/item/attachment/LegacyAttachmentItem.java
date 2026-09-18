/*
 * Copyright (c) 2025-2026 XiaoColorful (https://github.com/XColorful)
 * SPDX-License-Identifier: GPL-3.0-only
 *
 * Source: https://github.com/XColorful/Custom-Gun-Continued
 */

package dev.xcolorful.cgccompat.legacyitem.core.item.attachment;

import dev.xcolorful.cgccompat.legacyitem.core.api.item.attachment.LegacyAttachmentNBTAccessor;
import dev.xcolorful.cgccompat.legacyitem.core.api.minecraft.item.LegacyItemType;
import dev.xcolorful.customgun.core.init.registry.ModItems;
import dev.xcolorful.customgun.core.item.attachment.AttachmentItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LegacyAttachmentItem extends AttachmentItem implements LegacyAttachmentNBTAccessor {

    protected LegacyAttachmentItem(Properties properties) {
        super(properties);
    }
    public LegacyAttachmentItem() {
        this(ModItems.CUSTOM_ITEM_PROPERTY.apply(LegacyItemType.ATTACHMENT.getRegistryLocation()));
    }

    // --------IAttachmentNBTAccess--------

    @Override
    public @NotNull ResourceLocation getAttachmentLocation(CompoundTag attachmentCustomDataTag) {
        return LegacyAttachmentNBTAccessor.super.getAttachmentLocation(attachmentCustomDataTag);
    }
    @Override
    public void setAttachmentLocation(CompoundTag attachmentCustomDataTag, ResourceLocation attachmentLocation) {
        LegacyAttachmentNBTAccessor.super.setAttachmentLocation(attachmentCustomDataTag, attachmentLocation);
    }

    @Override
    public int getScopeViewIndex(CompoundTag attachmentCustomDataTag) {
        return LegacyAttachmentNBTAccessor.super.getScopeViewIndex(attachmentCustomDataTag);
    }
    @Override
    public void setScopeViewIndex(CompoundTag attachmentCustomDataTag, int scopeViewIndex) {
        LegacyAttachmentNBTAccessor.super.setScopeViewIndex(attachmentCustomDataTag, scopeViewIndex);
    }

    @Override
    public boolean hasLaserColor(CompoundTag attachmentCustomDataTag) {
        return LegacyAttachmentNBTAccessor.super.hasLaserColor(attachmentCustomDataTag);
    }
    @Override
    public int getLaserColor(CompoundTag attachmentCustomDataTag) {
        return LegacyAttachmentNBTAccessor.super.getLaserColor(attachmentCustomDataTag);
    }
    @Override
    public void setLaserColor(CompoundTag attachmentCustomDataTag, int laserColor) {
        LegacyAttachmentNBTAccessor.super.setLaserColor(attachmentCustomDataTag, laserColor);
    }
}
