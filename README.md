SkriptModuleLoader is a **Java plugin wrapper for Skript** that allows you to package `.sk` scripts into a Java project, build it with Maven, and deploy them automatically to your server.  
This allows modular script management, versioning, and professional release workflow.

---

## ✨ Requirements

| Software | Minimum Version |
|----------|----------------|
| Minecraft | 1.21+ |
| Paper | 1.21+ |
| Skript | 2.14.1+ |
| Java | 21 |
| Maven | 3.9+ |

---

## 📁 Project Structure

Your project folder should look like this:

SkriptModuleLoader/

├─ pom.xml

├─ src/

│ └─ main/

│ ├─ java/

│ │ └─ me/lancastersstudios/skriptloader/

│ │ └─ SkriptModuleLoader.java

│ └─ resources/

│ ├─ plugin.yml

│ └─ modules/

│ ├─ engine.sk

│ ├─ essentialsL.sk

│ └─ myCustomModule.sk


> **Important:** All `.sk` scripts must go inside `src/main/resources/modules/` to be bundled in the plugin JAR.

---

## ✍️ Writing Your Skript Modules

Write your `.sk` files as normal Skript scripts. Examples:

**test.sk**
skript
```on load:
    broadcast "&aEngine module loaded!"
myCustomModule.sk

every 10 minutes:
    broadcast "&bCustom module is running"
You can split your system into multiple scripts — the plugin will handle loading all of them.
```
## 🔨 Building the Plugin
# Option A — Using Maven (Command Line)
Open a terminal in the project root.

Set Java and Maven paths (if needed):
```
set JAVA_HOME=C:\path\to\java21
set PATH=%JAVA_HOME%\bin;%PATH%

set MAVEN_HOME=C:\path\to\apache-maven-3.9.12\bin
Compile the plugin:

"%MAVEN_HOME%\mvn.cmd" clean package
```
After building, the JAR is created here:

```target/SkriptModuleLoader-1.1.0.jar```
# Option B — One-Click Build Script (Windows)
Create build.bat in your project root:

```@echo off
echo Building SkriptModuleLoader...

set JAVA_HOME=C:\path\to\java21
set PATH=%JAVA_HOME%\bin;%PATH%

set MAVEN_HOME=C:\path\to\apache-maven-3.9.12\bin
"%MAVEN_HOME%\mvn.cmd" clean package


pause
```
Double-click to compile automatically.

## 🚀 Installing on a Server
Copy the compiled JAR:

```target/SkriptModuleLoader-1.1.0.jar```
Paste it into your server's plugins/ folder:

```<server_root>/plugins/```
Start the server normally.

🗂 How It Works
On server start, the plugin checks if Skript is installed.

It creates the target folder if missing:

```plugins/Skript/scripts/modules/```
It copies all .sk files from the plugin JAR into the modules folder.

It reloads Skript automatically after 5 seconds (90 ticks).

Example resulting folder:

plugins/Skript/scripts/modules/

├─ engine.sk

├─ essentialsL.sk

└─ myCustomModule.sk
⚠️ Do not edit these files manually — always edit the source .sk files in the Java project.

## 🔄 Updating Scripts
Edit scripts in:

```src/main/resources/modules/```
Rebuild the JAR with Maven.

Replace the old plugin in the server plugins/ folder.

Restart the server.

The updated scripts are automatically loaded.

## 🧪 Debugging
The plugin provides detailed logging:

Checks if .sk files exist in the JAR

Logs extraction progress

Logs Skript reload status

Common issues:

No .sk files found → Your scripts are not inside src/main/resources/modules/.

Skript not found → Ensure Skript plugin is installed and compatible.

## ✅ Recommended Usage
Modular Skript systems

Teams working on Skript

Clean production servers

Public releases (GitHub, Modrinth)

# ⚠️ Notes
SkriptModuleLoader does not replace Skript. You still write .sk scripts normally.

Only works with Skript 2.14.1+ and Paper 1.21+.

Always edit scripts in the Java project, not the extracted server folder.

## 🏁 Summary
SkriptModuleLoader turns your .sk files into a Java-style, distributable plugin.
You can:

Package multiple .sk scripts

Version control them

Distribute them cleanly

Automatically load them into Skript on server start
