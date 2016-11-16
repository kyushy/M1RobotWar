package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import robot.Robot;

/**
 * 
 * @author Fred
 *	Classe qui represente la zone graphique ou les robots s'affrontent. 
 */

public class Arene extends JPanel{

	private int longueur;
	private int largeur;
	
	public Arene(int longueur , int largeur){
		this.longueur = longueur;
		this.largeur = largeur;
		
		GridLayout layout = new GridLayout(largeur, longueur);
		this.setLayout(layout);
		this.setBackground(Color.WHITE);
		
		//DEBUG : affichage des cases 
		this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		for (int i =0; i<(largeur*longueur); i++){
		    final JLabel label = new JLabel("");
		    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    this.add(label);
		}
	}
	
	public Arene(int longueur , int largeur, ArrayList<Robot> listeRobot){
		this.longueur = longueur;
		this.largeur = largeur;
		
		GridLayout layout = new GridLayout(largeur, longueur);
		this.setLayout(layout);
		//this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		
		for (int i =0; i<(largeur*longueur); i++){
		    JLabel label = new JLabel("");
		    if (i==10){
		    	label.setBackground(Color.RED);
		    }else{
		    	label.setBackground(Color.WHITE);
		    }
		    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    this.add(label);
		}
	}

	public int getLongueur() {
		return longueur;
	}

	public int getLargeur() {
		return largeur;
	}
	
	
}
