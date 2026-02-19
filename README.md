**SkriptModuleLoader** allows you to load **any number of `.sk` scripts** modularly into your server. This guide covers **everything** from setup to running your scripts. Also, it can be used as a "Java" plugin that loads Skript Plugins

---

## 1️⃣ Requirements

- **Java 21** installed (or portable)  
- **Apache Maven 3.9+** installed  
- **Paper 1.21+** server  
- **Skript 2.14.1+** plugin  

---

## 2️⃣ Project Setup & Compilation
1. Open project folder, and locate ```\<root-folder>\SkriptModuleLoader\src\main\recources\modules```

2. Add your Skript ```(.sk)``` file(s) to ```\src\main\recources\modules```
  
1. Go to the root directory of the project

4. Run the build.bat file or run it via PowerShell ```./build.bat```

5. After a successful build, the JAR will be here:

```<project_root>/target/SkriptModuleLoader-1.1.0.jar```


---

## 3️⃣ Installing the Plugin

1. Copy the compiled JAR into your server’s `plugins` folder:

```<server_root>/plugins/```


2. And just start the server, Plugin will automatically install the Skripts and enable them!


---

##  Starting the Server

Start your Paper server normally.  
SkriptModuleLoader will automatically copy scripts to the correct location and reload them after 2 seconds.

---

## 4️⃣ Usage Notes

- Supports **any number of `.sk` scripts**.  
- No bundled scripts are included — you control which scripts are loaded.  
- Recommended: **restart the server** after upgrading or adding many scripts.  
- If you see errors in the console, check that all scripts are valid `.sk` files compatible with Skript 2.14.1+.

---

## 5️⃣ Optional: One-Click Build Script

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

## 6️⃣ Folder Overview

Project structure:

```SkriptModuleLoader/```

```├─ pom.xml```

```├─ src/```

```│ └─ main/```

```│ └─ java/```

```│ └─ me/lancastersstudios/skriptloader/SkriptModuleLoader.java```

```├─ target/```

```│ └─ SkriptModuleLoader-1.1.0.jar```

```└─ modules/ <-- Place all .sk files here```


Server structure after installation:

```<server_root>/plugins/```

```├─ SkriptModuleLoader-1.1.0.jar```

```├─ Skript/```

```│ └─ scripts/```

```│ └─ modules/```

```│ ├─ engine.sk```

```│ ├─ essentialsL.sk```

```│ └─ myCustomModule.sk```

