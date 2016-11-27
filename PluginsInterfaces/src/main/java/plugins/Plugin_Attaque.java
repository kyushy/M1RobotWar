package plugins;

import java.awt.Point;
import java.util.HashMap;

public interface Plugin_Attaque {
	
	public HashMap<String, Object> attaque(Point positionAttaquant, 
			int longueurArene, int largeurArene);

}
