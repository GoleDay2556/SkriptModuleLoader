package me.lancastersstudios.skriptloader;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SkriptModuleLoader extends JavaPlugin {

    private File externalModulesFolder;
    private File skriptTargetFolder;

    @Override
    public void onEnable() {

        if (Bukkit.getPluginManager().getPlugin("Skript") == null) {
            getLogger().severe("Skript not found! Disabling plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        externalModulesFolder = new File(getDataFolder(), "modules");
        skriptTargetFolder = new File("plugins/Skript/scripts/modules");

        externalModulesFolder.mkdirs();
        skriptTargetFolder.mkdirs();

        copyExternalModules();

        // Delay reload until Skript is fully ready
        Bukkit.getScheduler().runTaskLater(this, () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "skript reload scripts");
            getLogger().info("Skript scripts reloaded.");
        }, 40L); // 2 seconds

        getLogger().info("SkriptModuleLoader enabled successfully!");
    }

    private void copyExternalModules() {
        File[] files = externalModulesFolder.listFiles((dir, name) -> name.endsWith(".sk"));
        if (files == null) return;

        for (File file : files) {
            try {
                Files.copy(
                    file.toPath(),
                    new File(skriptTargetFolder, file.getName()).toPath(),
                    StandardCopyOption.REPLACE_EXISTING
                );
                getLogger().info("External script loaded: " + file.getName());
            } catch (Exception e) {
                getLogger().severe("Failed to load external script: " + file.getName());
                e.printStackTrace();
            }
        }
    }
}
