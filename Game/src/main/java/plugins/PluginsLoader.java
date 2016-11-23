package plugins;

import java.io.File;
import java.net.URLClassLoader;

public class PluginsLoader {

	private URLClassLoader loader;
	private static PluginsLoader instance;
	
	private PluginsLoader(){
		init();
	}
	
	public static PluginsLoader getInstance(){
		if(instance != null){
			return instance;
		}
		else {
			return new PluginsLoader();
		}
	}
	
	private void init(){
		File pluginDir = new File(".." + File.separator + "Plugins" + File.separator + "target" + File.separator + "classes");
	}
	
}
