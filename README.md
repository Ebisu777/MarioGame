# comile
mvn clean compile assembly:single

# packaging
java -jar ~/Downloads/packr-all-4.0.0.jar --platform mac --jdk /Library/Java/JavaVirtualMachines/jdk1.8.0_281.jdk/Contents/Home --useZgcIfSupportedOs --executable Drop --classpath target/mario-1.0-SNAPSHOT-jar-with-dependencies.jar --mainclass fr.minesalbi.gsi.game.DesktopLauncher  --vmargs Xmx1G --resources src/main/resources --output DropMac

#todo
https://github.com/libgdx/packr
{
    "platform": "mac",
    "jdk": "/Users/badlogic/Downloads/OpenJDK8U-jdk_x64_mac_hotspot_8u252b09.tar.gz",
    "executable": "myapp",
    "classpath": [
        "myjar.jar"
    ],
    "removelibs": [
        "myjar.jar"
    ],
    "mainclass": "com.my.app.MainClass",
    "vmargs": [
       "Xmx1G"
    ],
    "resources": [
        "src/main/resources",
        "path/to/other/assets"
    ],
    "minimizejre": "soft",
    "output": "out-mac"
}
You can then invoke the tool like this:

java -jar packr-all.jar my-packr-config.json
