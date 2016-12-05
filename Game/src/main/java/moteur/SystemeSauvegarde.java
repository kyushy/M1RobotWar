package moteur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import robot.Robot;

public class SystemeSauvegarde {

	private MoteurDeJeu moteur;

	/**
	 * Constructeur du systeme de sauvegarde
	 * 
	 * @param moteur MoteurDeJeu
	 */
	public SystemeSauvegarde(MoteurDeJeu moteur) {
		this.moteur = moteur;
	}

	/**
	 * Sauvegarder la partie actuelle en se basant sur les informations recuperees par le moteur
	 * Generation d'une HashMap qui sera serialisee et stocker dans le fichier indique
	 * 
	 * @param cheminDeSauvegarde File Fichier ou stocker la HashMap serialisee
	 * @return HashMap<String, Object> savePartie HashMap resultant de l'operation
	 */
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

	/**
	 * Stocke les attributs obtenus par reflexivite et leur valeur dans la HashMap indiquee en parametre
	 * 
	 * @param o Object Actuellement objet robot de la liste du moteur
	 * @param hmapRetour HashMap<String, Object> Est completee par la methode
	 */
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
	
	/**
	 * Deserialisation de la HashMap dans le fichier de sauvegarde indique et renvoie de cette derniere
	 * 
	 * @param cheminDeSauvegarde File Fichier ou aller recuperer la HashMap serialisee
	 * @return HashMap<String, Object> dataPartie HashMap issue de la sauvegarde
	 */
	public HashMap<String, Object> chargerSauvegarde(File cheminVersauvegarde) {

		HashMap<String, Object> dataPartie = null;
		try {
			FileInputStream fis = new FileInputStream(cheminVersauvegarde);
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

		return dataPartie;
	}

}
