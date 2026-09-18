/*
 * Copyright (c) 2025-2026 XiaoColorful (https://github.com/XColorful)
 * SPDX-License-Identifier: GPL-3.0-only
 *
 * Source: https://github.com/XColorful/Custom-Gun-Continued
 */

package dev.xcolorful.cgccompat.legacyitem.core.item.ammo;

import dev.xcolorful.cgccompat.legacyitem.core.api.minecraft.item.LegacyItemType;
import dev.xcolorful.customgun.core.init.registry.ModItems;
import dev.xcolorful.customgun.core.item.ammo.AmmoItem;

public class LegacyAmmoItem extends AmmoItem {

    protected LegacyAmmoItem(Properties properties) {
        super(properties);
    }
    public LegacyAmmoItem() {
        this(ModItems.CUSTOM_ITEM_PROPERTY.apply(LegacyItemType.AMMO.getRegistryLocation()));
    }
}
