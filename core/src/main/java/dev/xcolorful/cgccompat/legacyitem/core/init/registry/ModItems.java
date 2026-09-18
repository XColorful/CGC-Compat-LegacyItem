package dev.xcolorful.cgccompat.legacyitem.core.init.registry;

import dev.xcolorful.cgccompat.legacyitem.CgccLegacyItem;
import dev.xcolorful.cgccompat.legacyitem.core.api.minecraft.item.LegacyItemType;
import dev.xcolorful.cgccompat.legacyitem.core.item.gun.LegacyGunItem;
import dev.xcolorful.customgun.CustomGun;
import dev.xcolorful.customgun.core.api.init.registry.IRegistrar;
import dev.xcolorful.customgun.core.api.init.registry.IRegistryObject;
import dev.xcolorful.customgun.core.item.gun.GunItem;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final IRegistrar<Item> ITEMS = CgccLegacyItem.getRegistrarFactory().createItems(CustomGun.MOD_ID_OLD1);


    public static final IRegistryObject<GunItem> GUN = ITEMS.register(LegacyItemType.GUN.getTagName(), LegacyGunItem::new);
    public static final IRegistryObject<GunItem> ATTACHMENT = ITEMS.register(LegacyItemType.ATTACHMENT.getTagName(), LegacyGunItem::new);
    public static final IRegistryObject<GunItem> AMMO = ITEMS.register(LegacyItemType.AMMO.getTagName(), LegacyGunItem::new);
    public static final IRegistryObject<GunItem> AMMO_BOX = ITEMS.register(LegacyItemType.AMMO_BOX.getTagName(), LegacyGunItem::new);
}
