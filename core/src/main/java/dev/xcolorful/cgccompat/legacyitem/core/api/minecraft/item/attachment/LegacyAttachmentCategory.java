package dev.xcolorful.cgccompat.legacyitem.core.api.minecraft.item.attachment;

import dev.xcolorful.cgccompat.legacyitem.core.api.item.LegacyGunPropertyTag;
import dev.xcolorful.customgun.core.api.item.attachment.AttachmentCategory;
import dev.xcolorful.customgun.core.api.resource.ResourceTag;

public enum LegacyAttachmentCategory implements ResourceTag {
    MUZZLE(LegacyAttachmentCategoryTag.MUZZLE_OLD1),
    LASER(LegacyAttachmentCategoryTag.LASER_OLD1),
    GRIP(LegacyAttachmentCategoryTag.GRIP_OLD1),
    MAGAZINE(LegacyAttachmentCategoryTag.MAGAZINE_OLD1),
    SCOPE(LegacyAttachmentCategoryTag.SCOPE_OLD1),
    STOCK(LegacyAttachmentCategoryTag.STOCK_OLD1)
    ;

    public final String tagName;
    LegacyAttachmentCategory(String name) {
        this.tagName = LegacyGunPropertyTag.ATTACHMENT_PREFIX_OLD1 + name;
    }

    @Override public String getTagName() {
        return this.tagName;
    }

    public static String getTagName(AttachmentCategory category) {
        return switch (category) {
            case MUZZLE -> MUZZLE.tagName;
            case LASER -> LASER.tagName;
            case GRIP -> GRIP.tagName;
            case MAGAZINE -> MAGAZINE.tagName;
            case SCOPE -> SCOPE.tagName;
            case STOCK -> STOCK.tagName;
            default -> AttachmentCategory.NONE.getTagName();
        };
    }
}
