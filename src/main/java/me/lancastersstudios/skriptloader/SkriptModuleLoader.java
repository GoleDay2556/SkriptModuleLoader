package me.lancastersstudios.skriptloader;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class SkriptModuleLoader extends JavaPlugin {

    private File skriptTargetFolder;

    @Override
    public void onEnable() {

        log("=================================");
        log("Starting SkriptModuleLoader v" + getDescription().getVersion());
        log("=================================");

        // Dependency check
        if (Bukkit.getPluginManager().getPlugin("Skript") == null) {
            error("Skript NOT found! Disabling plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        skriptTargetFolder = new File("plugins/Skript/scripts/modules");

        log("Target Skript modules folder:");
        log("→ " + skriptTargetFolder.getAbsolutePath());

        if (!skriptTargetFolder.exists()) {
            boolean created = skriptTargetFolder.mkdirs();
            log("Created Skript modules folder: " + created);
        }

        extractBundledModules();

        Bukkit.getScheduler().runTaskLater(this, () -> {
            log("Reloading Skript scripts...");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "skript reload scripts");
            log("Skript reload command executed.");
        }, 90L);

        log("SkriptModuleLoader ENABLED successfully.");
    }

    private void extractBundledModules() {

        log("Scanning JAR for embedded .sk files...");
        log("Expected path inside JAR: /modules/*.sk");

        try {
            URL jarUrl = getClass().getProtectionDomain().getCodeSource().getLocation();
            JarURLConnection connection = (JarURLConnection) new URL("jar:" + jarUrl + "!/").openConnection();

            try (JarFile jar = connection.getJarFile()) {
                Enumeration<JarEntry> entries = jar.entries();
                boolean foundAny = false;

                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();

                    if (name.startsWith("modules/") && name.endsWith(".sk")) {
                        foundAny = true;
                        String fileName = name.substring("modules/".length());
                        File target = new File(skriptTargetFolder, fileName);

                        log("Extracting: " + name);
                        log("→ " + target.getAbsolutePath());

                        try (InputStream in = jar.getInputStream(entry)) {
                            Files.copy(in, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }

                        log("✔ Extracted " + fileName);
                    }
                }

                if (!foundAny) {
                    warn("NO .sk FILES FOUND inside JAR!");
                    warn("Make sure they are in: src/main/resources/modules/");
                }
            }

        } catch (Exception e) {
            error("FAILED to extract embedded modules!");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        log("SkriptModuleLoader disabled.");
    }

    /* ---------- Logging helpers ---------- */

    private void log(String msg) {
        getLogger().info(msg);
    }

    private void warn(String msg) {
        getLogger().warning(msg);
    }

    private void error(String msg) {
        getLogger().severe(msg);
    }
}
