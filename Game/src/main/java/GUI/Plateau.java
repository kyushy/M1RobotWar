package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * 
 * @author Fred
 *	Classe principale qui va contenir tous les elements graphiques du jeu
 *	TODO : définir un layout pour disposer les divers elements
 */
public class Plateau extends JFrame {

	private Arene arene;
	
	public Plateau(Arene arene){
		this.setSize(300, 300);
		this.setLayout(new BorderLayout());
		
		//ajout de l'arene au centre de la fenetre
		this.arene = arene;
		this.add(arene, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	public Arene getArene() {
		return arene;
	}

}
