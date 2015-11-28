package com.quentillionaire.dddminimap;

import com.google.common.base.Stopwatch;
import com.quentillionaire.dddminimap.Networking.CommonProxy;
import com.quentillionaire.dddminimap.Utillity.StringMap;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import com.quentillionaire.dddminimap.Loaders.ConfigLoader;
import com.quentillionaire.dddminimap.Utillity.Logger;

import java.util.concurrent.TimeUnit;

@Mod(modid = StringMap.ID, name = StringMap.Name, version = StringMap.VersionBuildName, acceptedMinecraftVersions = "["+StringMap.MinecraftVersion+"]")
public class DDDMiniMapMod {
    
    @Mod.Instance(StringMap.ID)
    public static DDDMiniMapMod instance;

    @SidedProxy(modId = StringMap.ID, clientSide = StringMap.clientProxyPath, serverSide = StringMap.serverProxyPath)
    public static CommonProxy proxy;

    private double launchTime;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Pre-Initialization started");
                ConfigLoader.init(event.getSuggestedConfigurationFile());
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Pre-Initialization process successfully done");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Initialization started");
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Initialization process successfully done");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Post-Initialization started");
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Post-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
            Logger.info("Post-Initialization process successfully done");
        Logger.info("Total launch time for " + StringMap.Name + " : " + launchTime + " ms");
    }
}
