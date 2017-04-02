package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		// TODO: Implement this method.
		for(GObject g : gObjects){
			g.x += dX - g.x;
			g.y += dY - g.y;
		}
		
		x = dX; 
		y = dY;
	}
	
	public void recalculateRegion() {
		// TODO: Implement this method.
		for(GObject g : gObjects){
			
		}
		
	}

	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		for (GObject gob : gObjects){
			gob.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		g.drawString("Composite", x, y+height+20);
	}
	
}
