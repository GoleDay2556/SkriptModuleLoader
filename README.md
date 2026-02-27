
*A Java-style wrapper for Skript projects*

SkriptModuleLoader lets you package **Skript (`.sk`) files inside a Java plugin JAR**, making your Skript project look, build, and distribute like a **real Java/Maven project**.

This solves common Skript problems:
- ❌ Scripts getting deleted or forgotten
- ❌ Messy `scripts/` folders
- ❌ No proper versioning or releases
- ❌ Hard to distribute modular Skript systems

With this project, **your `.sk` files live inside the plugin JAR**, and are automatically extracted and loaded when the server starts.

---

## ✨ What This Project Is

Think of SkriptModuleLoader as:

**A wrapper that turns Skript into a Java-style project**

You write Skript  
➡️ Package it like Java  
➡️ Release it like a plugin  
➡️ Load it automatically  

No manual copying. No confusion.

---

## 🧠 How It Works (Concept)

1. You place `.sk` files into the Java project  
2. Maven bundles them into the plugin JAR  
3. The plugin extracts them at runtime into Skript’s folder  
4. Skript reloads automatically  

## 💻 How to use it

### Variant 1. 
**You compile the project**
1. Download the .zip file from the downloads page and unzip it.
2. Download Java 21 and Maven (if you haven't)
3. Open `\src\main\resources\modules` and place your .sk files there (no subfolders).
4. Open `\src\main\resources\config.yml` and set the `bundled-scripts:` with your script names (with .sk) you placed earlier.
5. Go to the root of the project and start program named `build.bat`.
6. Program will automatically compile the plugin.
7. Place the .jar file in `\plugins\` of your server. Plugin will automatically install and load the scripts.

### Variant 2.
**You load the .sk file through the plugin**
1. Download the .jar file to `\plugins\` of your server.
2. Start the server once and stop.
3. Place your .sk files to the newly generated folder `\plugins\SkriptModuleLoader\modules\` 
4. Start the server, and watch how plugin loads to scripts folder in Skript.

> You can't distribute the plugin in case of Variant 2.

Additionally you can edit the config.yml before or/and after the compilation.
