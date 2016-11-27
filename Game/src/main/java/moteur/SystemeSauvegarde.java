package moteur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import robot.Robot;

public class SystemeSauvegarde {

	private MoteurDeJeu moteur;

	public SystemeSauvegarde(MoteurDeJeu moteur) {
		this.moteur = moteur;
	}

	public HashMap<String, Object> sauvegarder() {

		HashMap<String, Object> savePartie = new HashMap<>();

		ArrayList<HashMap<String, Object>> dataRobots = new ArrayList<>();
		System.out.println(this.moteur.getListeRobot().size() + " robots");
		for (Robot r : this.moteur.getListeRobot()) {
			System.out.println(r);
			
			HashMap<String, Object> dataRobot = new HashMap<String, Object>();
			loadDataFields(r, 1, dataRobot);
			dataRobots.add(dataRobot);
		}

		savePartie.put("Robot", dataRobots);

		try {
			FileOutputStream fos = new FileOutputStream("robotwarplay.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(savePartie);
			oos.close();
			fos.close();
			System.out.println("Données sauvegardées sur robotwarplay.ser");
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return savePartie;
		
	}

	//TODO : cas tableau
	public void loadDataFields(Object o, int profondeur, HashMap<String, Object> hmapRetour) {

		System.out.println("Profondeur : " + profondeur + " avec " + o);
		if (o == null) { return; }
		Class superClass = o.getClass();

		// Parcours des super classes
		while (superClass != null) {

			Field[] fields = superClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {

				fields[i].setAccessible(true);

				// Tableau
				/*if (fields[i].getType().isArray()) {
					System.out.print(fields[i].getName() + "=");
					System.out.print(" {");
					for (int j = 0; j < Array.getLength(fields[i].get(r)); j++) {
						System.out.print(Array.get(fields[i].get(r), j) + ";");
					}
					System.out.print("} ");

				}
				// Primitif
				else*/ if (fields[i].getType().isPrimitive()) {
					try {
						hmapRetour.put(fields[i].getName(), fields[i].get(o));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// Objet
				else {
					if (profondeur>=0) {
						try {
							this.loadDataFields(fields[i].get(o), profondeur--, hmapRetour);
						} catch (IllegalArgumentException
								| IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

			superClass = superClass.getSuperclass();

		}
	}

	public void chargerSauvegarde() {

	}

}
