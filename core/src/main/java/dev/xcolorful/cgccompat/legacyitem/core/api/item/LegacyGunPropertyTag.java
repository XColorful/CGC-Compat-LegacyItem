package dev.xcolorful.cgccompat.legacyitem.core.api.item;

public class LegacyGunPropertyTag {

    // --------IGunDataAccess--------
    public static final String GUN_LOCATION_OLD1 = "GunId";
    public static final String GUN_DISPLAY_LOCATION_OLD1 = "GunDisplayId";

    // --------IGunStateAccess--------
    public static final String FIRE_MODE_TYPE_OLD1 = "GunFireMode";
    public static final String HEAT_OLD1 = "HeatAmount";
    public static final String OVERHEAT_LOCK_OLD1 = "OverHeated";
    public static final String ATTACHMENT_LOCK_OLD1 = "AttachmentLock";
    public static final String LASER_COLOR_OLD1 = "LaserColor";
    public static final String TOOLTIP_MASK_OLD1 = "HideFlags";

    // --------IGunAmmoDataAccess--------
    public static final String DUMMY_AMMO_OLD1 = "DummyAmmo";
    public static final String DUMMY_AMMO_LIMIT_OLD1 = "MaxDummyAmmo";
    public static final String MAG_AMMO_OLD1 = "GunCurrentAmmoCount";
    public static final String BARREL_AMMO_OLD1 = "HasBulletInBarrel";

    // --------IGunAttachmentDataAccess--------
    public static final String ATTACHMENT_PREFIX_OLD1 = "Attachment";

    // --------IGunExpAccess--------
    public static final String GUN_EXP_OLD1 = "GunLevelExp";

    private LegacyGunPropertyTag() {}
}
