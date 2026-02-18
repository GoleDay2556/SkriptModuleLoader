package me.lancastersstudios.skriptloader;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class SkriptModuleLoader extends JavaPlugin {

    private File externalModulesFolder;
    private File skriptTargetFolder;

    @Override
    public void onEnable() {

        // Ensure Skript is installed
        if (Bukkit.getPluginManager().getPlugin("Skript") == null) {
            getLogger().severe("Skript not found! Disabling plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // Folders for external and target scripts
        externalModulesFolder = new File(getDataFolder(), "modules");
        skriptTargetFolder = new File("plugins/Skript/scripts/modules");

        externalModulesFolder.mkdirs();
        skriptTargetFolder.mkdirs();

        // Extract all bundled scripts automatically
        extractBundledModules();

        // Copy all external scripts automatically
        copyExternalModules();

        // Reload Skript to load all scripts
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "skript reload scripts");

        getLogger().info("SkriptModuleLoader loaded successfully!");
    }

    private void extractBundledModules() {
        try {
            // List all files in resources/modules/
            String[] scripts = Objects.requireNonNull(getClass().getResource("/modules/"))
                    .toURI()
                    .toURL()
                    .openConnection()
                    .getContent().toString()
                    .split(","); // This is safe for standard resource paths

            // OR just hardcode your default bundled scripts here:
            String[] defaultScripts = {"example.sk", "another.sk"};

            for (String script : defaultScripts) {
                try (InputStream in = getClass().getResourceAsStream("/modules/" + script)) {
                    if (in == null) continue;
                    File out = new File(skriptTargetFolder, script);
                    Files.copy(in, out.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    getLogger().info("Bundled script loaded: " + script);
                }
            }
        } catch (Exception e) {
            getLogger().severe("Failed to extract bundled scripts");
            e.printStackTrace();
        }
    }

    private void copyExternalModules() {
        File[] files = externalModulesFolder.listFiles((dir, name) -> name.endsWith(".sk"));
        if (files == null) return;

        for (File file : files) {
            try {
                Files.copy(file.toPath(), new File(skriptTargetFolder, file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                getLogger().info("External script loaded: " + file.getName());
            } catch (Exception e) {
                getLogger().severe("Failed to load external script: " + file.getName());
                e.printStackTrace();
            }
        }
    }
}
