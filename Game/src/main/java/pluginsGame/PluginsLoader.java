package pluginsGame;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Singleton
 * Classe pour charger les plugins avec leur nom
 * @author Admin
 *
 */
public class PluginsLoader {

	private static PluginsLoader instance;
	
	private PluginsLoader(){}
	
	/**
	 * Singleton
	 * @return instance of this object
	 */
	public static PluginsLoader getInstance(){
		if(instance != null){
			return instance;
		}
		else {
			return new PluginsLoader();
		}
	}

	
	/**
	 * Charge la classe dynamiquement avec le nom du plugin
	 * @param name nom du fichier .class à charger
	 * @return class du plugin charger
	 * @throws ClassNotFoundException
	 * @throws MalformedURLException
	 */
	public Class<?> loadPlugin(String name) throws ClassNotFoundException, MalformedURLException {
		File pluginDir = new File("../Plugins" + File.separator + "target" + File.separator + "classes");
		//System.out.println("load file at " + pluginDir2.getAbsolutePath());
		File pluginDir2 = new File("./Plugins/");
		URL[] classLoaderUrls = new URL[]{ pluginDir2.toURI().toURL() };
        URLClassLoader loader = new URLClassLoader(classLoaderUrls);
        return loader.loadClass(name);
	}
}
