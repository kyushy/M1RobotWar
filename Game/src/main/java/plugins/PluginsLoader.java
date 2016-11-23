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

	private URLClassLoader loader;
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
	
	public void load(){
		File pluginDir = new File("Plugins" + File.separator + "target" + File.separator + "classes" + File.separator + "plugins");
		System.out.println("load file at " + pluginDir.getAbsolutePath());

		URL[] urls = new URL[(int)pluginDir.length()];
		for(int i = 0; i< pluginDir.listFiles().length; i++){
			try {
				System.out.println("file loaded :" + pluginDir.listFiles()[i].getName());
				urls[i] = pluginDir.listFiles()[i].toURI().toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		this.loader = new URLClassLoader(urls);
	}

	public Class<?> loadPlugin(String className){
		try {
			return this.loader.loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void close(){
		try {
			this.loader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
