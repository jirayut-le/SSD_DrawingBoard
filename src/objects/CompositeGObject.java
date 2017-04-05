package objects;

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
		recalculateRegion();	
	}

	public void remove(GObject gObject) {
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		for (GObject g : gObjects){
			g.x += dX -x;
			g.y += dY -y;
		}
		x = dX; 
		y = dY;
	}
	
	public void recalculateRegion() {	
		int maxWidth = 0;
		int maxHeight = 0;
		GObject current = gObjects.get(0);
		int minWidth = current.x;
		int minHeight = current.y;
	
		for(GObject g : gObjects){
			if ( g.x + g.width > maxWidth) maxWidth = g.x + g.width;
			if ( g.x < minWidth) minWidth = g.x;
			if ( g.y + g.height > maxHeight) maxHeight = g.y + g.height;
			if ( g.y < minHeight) minHeight = g.y;
		}	
		super.x = minWidth;
		super.y = minHeight;
		super.height = maxHeight - minHeight;
		super.width = maxWidth - minWidth;
	}

	@Override
	public void paintObject(Graphics g) {
		for (GObject gob : gObjects){
			gob.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		g.drawString("Composite", x, y+height+20);
	}
	
}