package me.lancastersstudios.skriptloader;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SkriptModuleLoader extends JavaPlugin {

    private File modulesFolder;
    private File skriptTargetFolder;

    @Override
    public void onEnable() {

        // Ensure Skript is installed
        if (Bukkit.getPluginManager().getPlugin("Skript") == null) {
            getLogger().severe("Skript not found! Disabling plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // Plugin folder: plugins/SkriptModuleLoader/modules
        modulesFolder = new File(getDataFolder(), "modules");

        // Skript folder: plugins/Skript/scripts/modules
        skriptTargetFolder = new File("plugins/Skript/scripts/modules");

        if (!modulesFolder.exists()) {
            modulesFolder.mkdirs();
        }

        if (!skriptTargetFolder.exists()) {
            skriptTargetFolder.mkdirs();
        }

        loadModules();
    }

    private void loadModules() {
        File[] files = modulesFolder.listFiles((dir, name) -> name.endsWith(".sk"));

        if (files == null || files.length == 0) {
            getLogger().info("No Skript modules found.");
            return;
        }

        for (File file : files) {
            File target = new File(skriptTargetFolder, file.getName());

            try {
                Files.copy(
                        file.toPath(),
                        target.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );
                getLogger().info("Loaded Skript module: " + file.getName());
            } catch (IOException e) {
                getLogger().severe("Failed to load module: " + file.getName());
                e.printStackTrace();
            }
        }

        // Reload Skript scripts
        Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "skript reload scripts"
        );
    }
}
