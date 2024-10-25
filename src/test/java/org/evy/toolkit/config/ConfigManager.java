package org.evy.toolkit.config;

import org.aeonbits.owner.ConfigCache;

public final class ConfigManager {

    private ConfigManager(){}


    public static ToolkitConfig getConfig(){
        return ConfigCache.getOrCreate(ToolkitConfig.class);
    }
}
