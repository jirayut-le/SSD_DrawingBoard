package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import objects.*;

public class DrawingBoard extends JPanel {

	private MouseAdapter mouseAdapter; 
	private List<GObject> gObjects;
	private GObject target;

	private int gridSize = 10;

	public DrawingBoard() {
		gObjects = new ArrayList<GObject>();
		mouseAdapter = new MAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setPreferredSize(new Dimension(800, 600));
	}

	public void addGObject(GObject gObject) {
		gObjects.add(gObject);
		repaint();
	}

	public void groupAll() {
		CompositeGObject group = new CompositeGObject();
		for(GObject g : gObjects ){
			if(g instanceof CompositeGObject ){			
				group = (CompositeGObject)g;
			} else {
				g.deselected();
				group.add(g);
			}
		}
		group.recalculateRegion();	 
		gObjects.clear();
		gObjects.add(group);
		repaint();
	}

	public void deleteSelected() {
		for( GObject g : gObjects ){
			if(g.isSelected()){
				gObjects.remove(g);
				break;
			}
		}
		repaint();
	}

	public void clear() {
		gObjects.clear();
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintGrids(g);
		paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintGrids(Graphics g) {
		g.setColor(Color.lightGray);
		int gridCountX = getWidth() / gridSize;
		int gridCountY = getHeight() / gridSize;
		for (int i = 0; i < gridCountX; i++) {
			g.drawLine(gridSize * i, 0, gridSize * i, getHeight());
		}
		for (int i = 0; i < gridCountY; i++) {
			g.drawLine(0, gridSize * i, getWidth(), gridSize * i);
		}
	}

	private void paintObjects(Graphics g) {
		for (GObject go : gObjects) {
			go.paint(g);
		}
	}

	class MAdapter extends MouseAdapter {

		// TODO: You need some variables here

		private void deselectAll() {
			// TODO: Implement this method.
			for( GObject g : gObjects ){
				g.deselected();
			}
			target = null;
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			deselectAll();
			for(int i = gObjects.size()-1 ; i >= 0 ; i-- ){
				if(gObjects.get(i).pointerHit(e.getX(), e.getY())){
					gObjects.get(i).selected();
					target = gObjects.get(i)  ;
					break;
				}		
			}
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			for( GObject g : gObjects ){
				if(g.isSelected()){
					g.move(e.getX(),e.getY());
					break;
				}
			}
			repaint();
		}
	}

}
