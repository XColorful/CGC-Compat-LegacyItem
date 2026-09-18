/*
 * Copyright (c) 2025-2026 XiaoColorful (https://github.com/XColorful)
 * SPDX-License-Identifier: GPL-3.0-only
 *
 * Source: https://github.com/XColorful/Custom-Gun-Continued
 */

package dev.xcolorful.cgccompat.legacyitem.core.api.item.attachment;

import dev.xcolorful.cgccompat.legacyitem.core.api.item.LegacyAttachmentPropertyTag;
import dev.xcolorful.customgun.core.api.item.AttachmentProperty;
import dev.xcolorful.customgun.core.api.item.attachment.AttachmentCategory;
import dev.xcolorful.customgun.core.api.item.attachment.AttachmentNBTAccessor;
import dev.xcolorful.customgun.core.api.resource.ResourceApi;
import dev.xcolorful.customgun.core.api.resource.ResourceTag;
import dev.xcolorful.customgun.core.resource.data.index.AttachmentIndex;
import dev.xcolorful.customgun.core.resource.instance.data.AttachmentIndexInstance;
import dev.xcolorful.customgun.core.util.NBTUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LegacyAttachmentNBTAccessor extends AttachmentNBTAccessor {

    /**
     * TODO 部分功能实现仍然硬编码地用了{@link AttachmentNBTAccessor#INSTANCE}
     */
    LegacyAttachmentNBTAccessor INSTANCE = new LegacyAttachmentNBTAccessor() {};

    // --------IAttachmentNBTAccess--------

//    1.20.1
//    从枪械上取的attachmentCustomDataTag
//    {
//        "id": "tacz:attachment",
//        "count": 1,
//        "tag": {
//            "AttachmentId": ""
//        }
//    }
//    配件物品的attachmentCustomDataTag
//    {
//        "AttachmentId": ""
//    }
//    读的时候优先直读（根目录），其次额外检测tag
//    写的时候不增加层级（防止反复装卸配件无限扩大NBT）
//    LegacyGunItem卸下来的配件是Legacy的，还需要防止装上新的配件再卸下后丢信息，所以还要先检查新格式；但是不按新格式写，避免旧基础设施识别不到
    @Override
    default @NotNull ResourceLocation getAttachmentLocation(CompoundTag attachmentCustomDataTag) {
        var attachmentLocation = NBTUtils.getResourceLocation(attachmentCustomDataTag, AttachmentProperty.ATTACHMENT_LOCATION.getTagName());
        if (attachmentLocation != null) return attachmentLocation;

        attachmentLocation = NBTUtils.getResourceLocation(attachmentCustomDataTag, LegacyAttachmentPropertyTag.ATTACHMENT_LOCATION_OLD1);
        if (attachmentLocation != null) return attachmentLocation;

        @Nullable CompoundTag itemTag = NBTUtils.getCompoundTag(attachmentCustomDataTag, LegacyAttachmentPropertyTag.TAG);
        attachmentLocation = NBTUtils.getResourceLocation(itemTag, LegacyAttachmentPropertyTag.ATTACHMENT_LOCATION_OLD1);
        return attachmentLocation != null ? attachmentLocation : ResourceTag.NULL_LOCATION;
    }
    @Override
    default void setAttachmentLocation(CompoundTag attachmentCustomDataTag, ResourceLocation attachmentLocation) {
        NBTUtils.setResourceLocation(attachmentCustomDataTag, LegacyAttachmentPropertyTag.ATTACHMENT_LOCATION_OLD1, attachmentLocation);
    }

    @Override
    default @NotNull AttachmentCategory getAttachmentCategory(CompoundTag attachmentCustomDataTag) {
        var attachmentLocation = this.getAttachmentLocation(attachmentCustomDataTag);
        @Nullable AttachmentIndexInstance attachmentIndexInstance = ResourceApi.getAttachmentIndexInstance(attachmentLocation);
        if (attachmentIndexInstance == null) return AttachmentCategory.NONE;

        AttachmentIndex attachmentIndex = attachmentIndexInstance.getPojo();
        return attachmentIndex.getAttachmentCategory();
    }
    @Override
    default void setAttachmentCategory(CompoundTag attachmentCustomDataTag, AttachmentCategory attachmentCategory) {
//        NBTUtils.setString(attachmentCustomDataTag, AttachmentProperty.ATTACHMENT_CATEGORY.getTagName(),
//                attachmentCategory.getCategoryName()); // 存在配件NBT里用不带前缀的简写
    }

    @Override
    default int getScopeViewIndex(CompoundTag attachmentCustomDataTag) {
        if (NBTUtils.hasKey(attachmentCustomDataTag, AttachmentProperty.SCOPE_VIEW_INDEX.getTagName())) {
            return NBTUtils.getInt(attachmentCustomDataTag, AttachmentProperty.SCOPE_VIEW_INDEX.getTagName());
        }

        if (NBTUtils.hasKey(attachmentCustomDataTag, LegacyAttachmentPropertyTag.SCOPE_VIEW_INDEX_OLD1)) {
            return NBTUtils.getInt(attachmentCustomDataTag, LegacyAttachmentPropertyTag.SCOPE_VIEW_INDEX_OLD1);
        }

        @Nullable CompoundTag itemTag = NBTUtils.getCompoundTag(attachmentCustomDataTag, LegacyAttachmentPropertyTag.TAG);
        return NBTUtils.getInt(itemTag, LegacyAttachmentPropertyTag.SCOPE_VIEW_INDEX_OLD1);
    }
    @Override
    default void setScopeViewIndex(CompoundTag attachmentCustomDataTag, int scopeViewIndex) {
        NBTUtils.setInt(attachmentCustomDataTag, LegacyAttachmentPropertyTag.SCOPE_VIEW_INDEX_OLD1, scopeViewIndex);
    }

    @Override
    default boolean hasLaserColor(CompoundTag attachmentCustomDataTag) {
        if (NBTUtils.hasKey(attachmentCustomDataTag, AttachmentProperty.LASER_COLOR.getTagName())) {
            return true;
        }

        if (NBTUtils.hasKey(attachmentCustomDataTag, LegacyAttachmentPropertyTag.LASER_COLOR_OLD1)) {
            return true;
        }

        @Nullable CompoundTag itemTag = NBTUtils.getCompoundTag(attachmentCustomDataTag, LegacyAttachmentPropertyTag.TAG);
        return NBTUtils.hasKey(itemTag, LegacyAttachmentPropertyTag.LASER_COLOR_OLD1);
    }
    @Override
    default int getLaserColor(CompoundTag attachmentCustomDataTag) {
        if (NBTUtils.hasKey(attachmentCustomDataTag, AttachmentProperty.LASER_COLOR.getTagName())) {
            return NBTUtils.getInt(attachmentCustomDataTag, AttachmentProperty.LASER_COLOR.getTagName());
        }

        if (NBTUtils.hasKey(attachmentCustomDataTag, LegacyAttachmentPropertyTag.LASER_COLOR_OLD1)) {
            return NBTUtils.getInt(attachmentCustomDataTag, LegacyAttachmentPropertyTag.LASER_COLOR_OLD1);
        }

        @Nullable CompoundTag itemTag = NBTUtils.getCompoundTag(attachmentCustomDataTag, LegacyAttachmentPropertyTag.TAG);
        return NBTUtils.getInt(itemTag, LegacyAttachmentPropertyTag.LASER_COLOR_OLD1);
    }
    @Override
    default void setLaserColor(CompoundTag attachmentCustomDataTag, int laserColor) {
        NBTUtils.setInt(attachmentCustomDataTag, LegacyAttachmentPropertyTag.LASER_COLOR_OLD1, laserColor);
    }
}
