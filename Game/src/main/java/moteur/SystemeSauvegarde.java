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
			loadDataFields(r, 5, dataRobot);
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

	public void loadDataFields(Object o, int profondeur, HashMap<String, Object> hmapRetour) {

		//System.out.println("Profondeur : " + profondeur + " avec " + o);
		if (o == null) { return; }
		Class superClass = o.getClass();

		// Parcours des super classes
		while (superClass != null) {

			Field[] fields = superClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {

				fields[i].setAccessible(true);

				// Tableau
				if (fields[i].getType().isArray()) {
				//System.out.println("tableau");
					//System.out.print(fields[i].getName() + "=");
					//System.out.print(" {");
					try {
						for (int j = 0; j < Array.getLength(fields[i].get(o)); j++) {
							//System.out.print(Array.get(fields[i].get(r), j) + ";");
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.print("} ");

				}
				// Primitif
				else if (fields[i].getType().isPrimitive()) {
					//System.out.println("primitif");
					try {
						hmapRetour.put(fields[i].getName(), fields[i].get(o));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// Objet
				else {
					//System.out.println("objet");
					/*if (profondeur>=0) {
						try {
							this.loadDataFields(fields[i].get(o), profondeur--, hmapRetour);
						} catch (IllegalArgumentException
								| IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}*/
					try {
						/*System.out.println(fields[i].getName());
						System.out.println();
						System.out.println(fields[i].get(o));*/
						hmapRetour.put(fields[i].getName(), fields[i].get(o));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
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
