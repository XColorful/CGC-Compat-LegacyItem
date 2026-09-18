/*
 * Copyright (c) 2025-2026 XiaoColorful (https://github.com/XColorful)
 * SPDX-License-Identifier: GPL-3.0-only
 *
 * Source: https://github.com/XColorful/Custom-Gun-Continued
 */

package dev.xcolorful.cgccompat.legacyitem.core.item.ammo;

import dev.xcolorful.cgccompat.legacyitem.core.api.item.ammo.LegacyAmmoNBTAccessor;
import dev.xcolorful.cgccompat.legacyitem.core.api.minecraft.item.LegacyItemType;
import dev.xcolorful.customgun.core.api.item.ammo.AmmoNBTAccessor;
import dev.xcolorful.customgun.core.init.registry.ModItems;
import dev.xcolorful.customgun.core.item.ammo.AmmoItem;

/**
 * {@link LegacyAmmoNBTAccessor}比{@link AmmoNBTAccessor}提供了更具体的接口（再次default重载），所以会优先使用
 */
public class LegacyAmmoItem extends AmmoItem implements LegacyAmmoNBTAccessor {

    protected LegacyAmmoItem(Properties properties) {
        super(properties);
    }
    public LegacyAmmoItem() {
        this(ModItems.CUSTOM_ITEM_PROPERTY.apply(LegacyItemType.AMMO.getRegistryLocation()));
    }
}
