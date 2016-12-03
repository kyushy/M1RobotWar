package moteur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import robot.Robot;

public class SystemeSauvegarde {

	private MoteurDeJeu moteur;

	public SystemeSauvegarde(MoteurDeJeu moteur) {
		this.moteur = moteur;
	}

	public HashMap<String, Object> sauvegarder(File cheminDeSauvegarde) {

		HashMap<String, Object> savePartie = new HashMap<>();

		ArrayList<HashMap<String, Object>> dataRobots = new ArrayList<>();
		System.out.println(this.moteur.getListeRobot().size() + " robots");
		for (Robot r : this.moteur.getListeRobot()) {
			System.out.println(r);

			HashMap<String, Object> dataRobot = new HashMap<String, Object>();
			loadDataFields(r, dataRobot);
			dataRobots.add(dataRobot);
		}

		savePartie.put("Robot", dataRobots);

		try {
			FileOutputStream fos = new FileOutputStream(cheminDeSauvegarde);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(savePartie);
			oos.close();
			fos.close();
			System.out.println("Données sauvegardées sur" + cheminDeSauvegarde.getAbsolutePath());
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}

		return savePartie;

	}

	public void loadDataFields(Object o, HashMap<String, Object> hmapRetour) {

		if (o == null) { return; }
		Class superClass = o.getClass();

		// Parcours des super classes
		while (superClass != null) {

			Field[] fields = superClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {

				fields[i].setAccessible(true);
				
				// Primitif
				if (fields[i].getType().isPrimitive()) {
					try {
						hmapRetour.put(fields[i].getName(), fields[i].get(o));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				// Objet
				else {
					try {
						if (fields[i].get(o) == null) { 
							hmapRetour.put(fields[i].getName(), fields[i].get(o));
						}
						else {
							if (fields[i].get(o).getClass().getName().contains("plugins.")) {
								hmapRetour.put(fields[i].getName(), fields[i].get(o).getClass().getName());
							}
							else {
								hmapRetour.put(fields[i].getName(), fields[i].get(o));
							}
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}

			}

			superClass = superClass.getSuperclass();

		}
	}

	public HashMap<String, Object> chargerSauvegarde(File cheminVersauvegarde) {

		HashMap<String, Object> dataPartie = null;
		try {
			FileInputStream fis = new FileInputStream(cheminVersauvegarde /*"robotwarplay.ser"*/);
			ObjectInputStream ois = new ObjectInputStream(fis);
			dataPartie = (HashMap) ois.readObject();
			ois.close();
			fis.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			return dataPartie;
		}
		catch (ClassNotFoundException c)
		{
			System.out.println("Class not found");
			c.printStackTrace();
			return dataPartie;
		}
		/*System.out.println("Deserialisation de la hashmap..");
		// Display content using Iterator
		Set set = dataPartie.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			System.out.print("key: "+ mentry.getKey() + " & Value: ");
			System.out.println(mentry.getValue());
		}*/
		return dataPartie;
	}

}
