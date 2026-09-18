/*
 * Copyright (c) 2025-2026 XiaoColorful (https://github.com/XColorful)
 * SPDX-License-Identifier: GPL-3.0-only
 *
 * Source: https://github.com/XColorful/Custom-Gun-Continued
 */

package dev.xcolorful.cgccompat.legacyitem.core.api.item.attachment;

import dev.xcolorful.cgccompat.legacyitem.core.api.item.LegacyAttachmentPropertyTag;
import dev.xcolorful.customgun.core.api.item.attachment.AttachmentNBTAccessor;
import dev.xcolorful.customgun.core.api.resource.ResourceTag;
import dev.xcolorful.customgun.core.util.NBTUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public interface LegacyAttachmentNBTAccessor extends AttachmentNBTAccessor {

    LegacyAttachmentNBTAccessor INSTANCE = new LegacyAttachmentNBTAccessor() {};

    // --------IAttachmentNBTAccess--------

    @Override
    default @NotNull ResourceLocation getAttachmentLocation(CompoundTag attachmentCustomDataTag) {
        var attachmentLocation = NBTUtils.getResourceLocation(attachmentCustomDataTag, LegacyAttachmentPropertyTag.ATTACHMENT_LOCATION_OLD1);
        return attachmentLocation != null ? attachmentLocation : ResourceTag.NULL_LOCATION;
    }

    @Override
    default int getScopeViewIndex(CompoundTag attachmentCustomDataTag) {
        return NBTUtils.getInt(attachmentCustomDataTag, LegacyAttachmentPropertyTag.SCOPE_VIEW_INDEX_OLD1);
    }
}
