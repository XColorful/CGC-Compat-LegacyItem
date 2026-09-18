[English](#English)

# 架构总览

> 本文档作为项目架构的导航索引

## 项目结构

基于`dev.xcolorful.cgccompat.legacyitem.core`顶层包的模块划分

### API
> _./core/api_

### 初始化
> _./core/init_

- Registry：模组注册表
	- ModItems：模组物品

### 物品
> _./core/item_

- Ammo：子弹
	- LegacyAmmoItem：旧子弹物品
- AmmoBox：子弹盒
	- LegacyAmmoBoxItem：旧子弹盒物品
- Attachment：配件
	- LegacyAttachmentItem：旧配件物品
- Gun：枪械
	- LegacyGunItem：旧枪械物品

# English

> This document serves as a navigation index for the project architecture

## Project Structure

Module division based on the `dev.xcolorful.cgccompat.legacyitem.core` top-level package

### API
> _./core/api_

### Initialization
> _./core/init_

- Registry: Mod registry
	- ModItems: Mod items

### Item
> _./core/item_

- Ammo:
	- LegacyAmmoItem: Legacy ammo item
- AmmoBox:
	- LegacyAmmoBoxItem: Legacy ammo box item
- Attachment:
	- LegacyAttachmentItem: Legacy attachment item
	- modifier: Attachment modifier
- Gun:
	- LegacyGunItem: Legacy gun item
