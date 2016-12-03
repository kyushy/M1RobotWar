package plugins;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PluginsLoader {

	private static PluginsLoader instance;
	
	private PluginsLoader(){}
	
	public static PluginsLoader getInstance(){
		if(instance != null){
			return instance;
		}
		else {
			return new PluginsLoader();
		}
	}

	
	
	public Class<?> loadPlugin(String name) throws ClassNotFoundException, MalformedURLException {
		File pluginDir = new File("../Plugins" + File.separator + "target" + File.separator + "classes");
		//System.out.println("load file at " + pluginDir.getAbsolutePath());

		URL[] classLoaderUrls = new URL[]{ pluginDir.toURI().toURL() };
        URLClassLoader loader = new URLClassLoader(classLoaderUrls);
        return loader.loadClass(name);
	}
}
