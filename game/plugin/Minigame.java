package de.xcuzimsmart.pluginhelper.code.git.branch.game.plugin;

import de.xcuzimsmart.pluginhelper.code.git.branch.main.plugin.MCSpigotPlugin;

public class Minigame extends MCSpigotPlugin {

    public Minigame() {
        if (!config.isSet("prefix")) config.writeString("prefix", "");

        this.setPrefix(config.isSet("prefix") ? config.readString("prefix") : null);
    }
}
