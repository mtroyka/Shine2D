package shinetest;

import org.starfire.shine.util.Log;

import java.io.File;

/**
 * Created by haplo on 12/13/2015.
 */
public class LibLoader {
    private static final String LIBRARY_SYSTEM_PROPERTY = "java.library.path";
    private static final String LWJGL_LIBRARY_PROPERTY = "org.lwjgl.librarypath";
    private static final String JINPUT_LIBRARY_PROPERTY = "net.java.games.input.librarypath";

    public static final void load(){

        // points to the library directory with your jar libs and native lib folders, assumed to be in the same directory as your main jar.
        String path = new File(ShineTest.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getAbsolutePath();
        String libpath; // used for pointing to the folder where the appropriate native libraries are.

        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("linux")) {
            libpath = path + File.separator + "linux";
        }else if (OS.contains("win")) {
            libpath = path + File.separator + "windows";
        }else{ // If future developers want to support more operating systems then add them here. (I'll add MacOS support soon)
            throw new IllegalStateException("Encountered an unknown platform while loading native libraries");
        }

        //Add the path to the appropriate native libs to the system library path.
        System.setProperty(LIBRARY_SYSTEM_PROPERTY,libpath + File.pathSeparator+ System.getProperty(LIBRARY_SYSTEM_PROPERTY));

        //Tell lwjgl where to look for native libraries
        System.setProperty(LWJGL_LIBRARY_PROPERTY, libpath);
        System.setProperty(JINPUT_LIBRARY_PROPERTY, libpath);
    }
}
