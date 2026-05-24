# NoEnchantPlugin

A powerful PaperMC plugin that completely disables all enchantments in your Minecraft server.

Supports:

- PaperMC 1.21.x
- Java 21
- Minecraft 1.21 - 1.21.11

---

# Features

✅ Disable Enchanting Table  
✅ Disable Anvil Enchants  
✅ Disable Enchanted Books  
✅ Disable Villager Enchant Trades  
✅ Remove Fishing Enchants  
✅ Remove Existing Enchantments  
✅ Permission Bypass Support  
✅ Lightweight & Fast  
✅ PaperMC Optimized  

---

# Minecraft Version

| Version | Supported |
|---|---|
| 1.21 | ✅ |
| 1.21.1 | ✅ |
| 1.21.2 | ✅ |
| 1.21.3 | ✅ |
| 1.21.4 | ✅ |
| 1.21.5 | ✅ |
| 1.21.6 | ✅ |
| 1.21.7 | ✅ |
| 1.21.8 | ✅ |
| 1.21.9 | ✅ |
| 1.21.10 | ✅ |
| 1.21.11 | ✅ |

---

# Installation

1. Download the plugin `.jar`
2. Put the plugin inside:

```text
/plugins/
```

3. Restart the server

---

# Permissions

| Permission | Description |
|---|---|
| `noenchant.bypass` | Allows player to bypass enchant restrictions |

Default:
```text
OP only
```

---

# Commands

This plugin currently has no commands.

---

# Folder Structure

```text
NoEnchantPlugin/
├── pom.xml
├── README.md
├── .github/
│   └── workflows/
│       └── main.yml
│
└── src/
    └── main/
        ├── java/
        │   └── me/
        │       └── loyalty/
        │           └── noenchant/
        │               ├── NoEnchantPlugin.java
        │               └── EnchantListener.java
        │
        └── resources/
            └── plugin.yml
```

---

# Build Plugin

Use Maven:

```bash
mvn clean package
```

Generated file:

```text
target/NoEnchantPlugin-1.0.jar
```

---

# GitHub Actions

This project supports automatic builds using GitHub Actions.

Workflow file:

```text
.github/workflows/main.yml
```

Every push to the `main` branch will automatically build the plugin.

---

# Requirements

- Java 21
- Maven 3.9+
- PaperMC 1.21.x

---

# plugin.yml

```yaml
name: NoEnchantPlugin
version: '1.0'
main: me.loyalty.noenchant.NoEnchantPlugin
api-version: '1.21'
author: LoyaltyMC
description: Disable all enchantments in the server
```

---

# Developer

Created by LoyaltyMC

---

# License

This project is free to use and modify.

---

# Support

If you find bugs or issues:

- Open a GitHub issue
- Report build errors
- Suggest new features

---

# Future Updates

Planned features:

- Config.yml
- Disable specific enchants
- Disable mending only
- Disable netherite
- GUI settings
- Admin commands
- Reload command
- Multi-language support

---

# Status

✅ Stable  
✅ Production Ready  
✅ PaperMC Optimized  
✅ Minecraft 1.21.x Ready
