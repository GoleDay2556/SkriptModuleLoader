**SkriptModuleLoader** allows you to load **any number of `.sk` scripts** modularly into your server. This guide covers **everything** from setup to running your scripts.

---

## 1️⃣ Requirements

- **Java 21** installed (or portable)  
- **Apache Maven 3.9+** installed  
- **Paper 1.21+** server  
- **Skript 2.14.1+** plugin  

---

## 2️⃣ Project Setup & Compilation

1. Open the project folder (`SkriptModuleLoader`) in a terminal or PowerShell.

2. Set your Java 21 path (optional if Java is in PATH):

```set JAVA_HOME=C:\path\to\java21```
```set PATH=%JAVA_HOME%\bin;%PATH%```


3. Set your Maven path (optional if Maven is in PATH):

```set MAVEN_HOME=C:\path\to\apache-maven-3.9.12\bin```


4. Compile the plugin using Maven:

```"%MAVEN_HOME%\mvn.cmd" clean package```


5. After a successful build, the JAR will be here:

```<project_root>/target/SkriptModuleLoader-1.1.0.jar```


---

## 3️⃣ Installing the Plugin

1. Copy the compiled JAR into your server’s `plugins` folder:

```<server_root>/plugins/```


2. Create the modules folder (if it doesn’t exist):

```<server_root>/plugins/Skript/scripts/modules/```


3. Copy **all your `.sk` scripts** into that folder:

```<server_root>/plugins/Skript/scripts/modules/```
```├─ engine.sk```
```├─ essentialsL.sk```
```├─ myCustomModule.sk```


---

## 4️⃣ Starting the Server

Start your Paper server normally.  
SkriptModuleLoader will automatically copy scripts to the correct location and reload them after 2 seconds.

---

## 5️⃣ Usage Notes

- Supports **any number of `.sk` scripts**.  
- No bundled scripts are included — you control which scripts are loaded.  
- Recommended: **restart the server** after upgrading or adding many scripts.  
- If you see errors in the console, check that all scripts are valid `.sk` files compatible with Skript 2.14.1+.

---

## 6️⃣ Optional: One-Click Build Script

You can create a `.bat` file in the project folder for easier compilation:

```@echo off```
```echo Building SkriptModuleLoader...```
```set JAVA_HOME=C:\path\to\java21```
```set PATH=%JAVA_HOME%\bin;%PATH%```
```set MAVEN_HOME=C:\path\to\apache-maven-3.9.12\bin```
```"%MAVEN_HOME%\mvn.cmd" clean package```
```pause```


Double-clicking this file will compile the plugin automatically.

---

## 7️⃣ Folder Overview

Project structure:

```SkriptModuleLoader/```
```├─ pom.xml```
```├─ src/```
```│ └─ main/```
```│ └─ java/```
```│ └─ me/lancastersstudios/skriptloader/SkriptModuleLoader.java```
```├─ target/```
```│ └─ SkriptModuleLoader-1.1.0.jar```
```└─ modules/ <-- Optional for bundled scripts if you use them```


Server structure after installation:

```<server_root>/plugins/```
```├─ SkriptModuleLoader-1.1.0.jar```
```├─ Skript/```
```│ └─ scripts/```
```│ └─ modules/```
```│ ├─ engine.sk```
```│ ├─ essentialsL.sk```
```│ └─ myCustomModule.sk```

