package uml;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Vector;

import model.UMLNature;


public class LinkDrawing {
	
	// Size variables
	private static final int ISUNDER_DELTA = 6;		// pixel delta around line position checking
	private static final int DASH_DELTA = 10;		// pixel size between dash
	private static final int DRAWSTRING_DELTA = 6;	// pixel space between line and Strings
	private static final int ARROW_SIZE = 10;		// pixel size for arrow drawing
	
	// Position variables for arrow drawing
	private static final int POSITION_ERROR = -1;
	private static final int TOP = 0;
	private static final int RIGHT = 1;
	private static final int BOTTOM = 2;
	private static final int LEFT = 3;
	
	private ArrayList<ClassDrawing> classes;
	private ArrayList<Dimension> classPositions;
	private Vector<Dimension> points;
	
	private Object id;
	private Object type;
	private String text;
	private ArrayList<String> multiplicity;
	
	private boolean moved; // not use to its max potential
	private boolean isColored;

	/**
	 * Main constructor, create a LinkDrawing representation of a link
	 * 
	 * @param motherClass
	 *            main class, where the arrow points
	 * @param daughterClass
	 *            second class
	 * @param type
	 *            LinkDrawing link type
	 */
	public LinkDrawing(Object id, Object type, ArrayList<ClassDrawing> classes) {
		this.id = id;
		this.type = type;
		
		this.classes = classes;
		classPositions = new ArrayList<Dimension>();
		for (int i = 0 ; i < classes.size() ; i++) {
			classPositions.add(new Dimension(classes.get(i).getX(), classes.get(i).getY()));
		}
		points = new Vector<Dimension>();
		
		definePoints();
		text = "";
		multiplicity = new ArrayList<String>();
		
		moved = false;
		isColored = false;
	}
	
	/**
	 * Return if a point is into a specified class
	 * 
	 * @return true if point is into class
	 */
	private boolean isIntoClass(Dimension point, ClassDrawing aClass) {
		return (aClass.getX() <= point.width && point.width <= (aClass.getX() + aClass.getWidth())
				&& aClass.getY() <= point.height && point.height <= (aClass.getY() + aClass.getHeight()));
	}
	
	/**
	 * Set an automated pool of points to draw
	 */
	private void definePoints() {
		// daugtherClass is at LEFT of motherClass
		if (classes.get(0).getX() + classes.get(0).getWidth() <= classes.get(classes.size()-1).getX()) {
			// daugtherClass is at TOP-LEFT of motherClass
			if (classes.get(0).getY() > classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/2) {
				points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth(), classes.get(0).getY() + classes.get(0).getHeight()/4));
				points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()/4, classes.get(0).getY() + classes.get(0).getHeight()/4));
				points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()/4, classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()));
			} else {
				// daugtherClass is at BOTTOM-LEFT of motherClass
				if (classes.get(0).getY() + classes.get(0).getHeight() < classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/2) {
					points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth(), classes.get(0).getY() + classes.get(0).getHeight()*3/4));
					points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()/4, classes.get(0).getY() + classes.get(0).getHeight()*3/4));
					points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()/4, classes.get(classes.size()-1).getY()));
				}
				// daugtherClass is at MIDDLE-LEFT of motherClass
				else {
					points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth(), classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/2));
					points.add(new Dimension(classes.get(classes.size()-1).getX(), classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/2));
				}
			}
		}
		else {
			// daugtherClass is at RIGHT of motherClass
			if (classes.get(0).getX() >= classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()) {
			// daugtherClass is at TOP-RIGHT of motherClass
				if (classes.get(0).getY() > classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/2) {
					points.add(new Dimension(classes.get(0).getX(), classes.get(0).getY() + classes.get(0).getHeight()/4));
					points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()*3/4, classes.get(0).getY() + classes.get(0).getHeight()/4));
					points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()*3/4, classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()));
				} else {
					// daugtherClass is at BOTTOM-RIGHT of motherClass
					if (classes.get(0).getY() + classes.get(0).getHeight() < classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/2) {
						points.add(new Dimension(classes.get(0).getX(), classes.get(0).getY() + classes.get(0).getHeight()*3/4));
						points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()*3/4, classes.get(0).getY() + classes.get(0).getHeight()*3/4));
						points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()*3/4, classes.get(classes.size()-1).getY()));
					}
					// daugtherClass is at MIDDLE-RIGHT of motherClass
					else {
						points.add(new Dimension(classes.get(0).getX(), classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/2));
						points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth(), classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/2));
					}
				}
			}
			// is daugtherClass at MIDDLE TOP or BOTTOM of motherClass ?
			else {
				// daughterClass is at TOP-MIDDLE of motherClass
				if (classes.get(0).getY() >= classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()) {
					// daughterClass is at TOP-MIDDLE-RIGHT of motherClass
					if (classes.get(0).getX() + classes.get(0).getWidth()/2 <= classes.get(classes.size()-1).getX()) {
						points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(0).getY()));
						points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()*3/4));
						points.add(new Dimension(classes.get(classes.size()-1).getX(), classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()*3/4));
					} else {
						// daughterClass is at TOP-MIDDLE-LEFT of motherClass
						if (classes.get(0).getX() + classes.get(0).getWidth()/2 >= classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()) {
							points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(0).getY()));
							points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()*3/4));
							points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth(), classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()*3/4));
						}
						// daughterClass is at TOP-MIDDLE-MID of motherClass
						else {
							points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(0).getY()));
							points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()));
						}
					}
				} else {
				// daughterClass is at BOTTOM-MIDDLE of motherClass
					if (classes.get(0).getY() + classes.get(0).getHeight() <= classes.get(classes.size()-1).getY()) {
						// daughterClass is at BOTTOM-MIDDLE-RIGHT of motherClass
						if (classes.get(0).getX() + classes.get(0).getWidth()/2 <= classes.get(classes.size()-1).getX()) {
							points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(0).getY() + classes.get(0).getHeight()));
							points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/4));
							points.add(new Dimension(classes.get(classes.size()-1).getX(), classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/4));
						} else {
							// daughterClass is at BOTTOM-MIDDLE-LEFT of motherClass
							if (classes.get(0).getX() + classes.get(0).getWidth()/2 >= classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()) {
								points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(0).getY() + classes.get(0).getHeight()));
								points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/4));
								points.add(new Dimension(classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth(), classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()/4));
							}
							// daughterClass is at BOTTOM-MIDDLE-MID of motherClass
							else {
								points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(0).getY() + classes.get(0).getHeight()));
								points.add(new Dimension(classes.get(0).getX() + classes.get(0).getWidth()/2, classes.get(classes.size()-1).getY()));
							}
						}
					} else {
						// daughterClass is INTO motherClass drawing !
					}
				}
			}
		}
	}
	
	/**
	 * Check if class linked have moved and move points
	 * 
	 * @return true if class moved
	 */
	private boolean checkClassPosition() {
		boolean moved = false;
		// check Mother Class position
		if (classes.get(0).getX() != classPositions.get(0).getWidth() || classes.get(0).getY() != classPositions.get(0).getHeight()) {
			Dimension delta = new Dimension(classes.get(0).getX() - (int)classPositions.get(0).getWidth(), classes.get(0).getY() - (int)classPositions.get(0).getHeight());
			
			// vertical line
			if (points.firstElement().getWidth() == points.get(1).getWidth()) {
				points.get(1).setSize(points.get(1).getWidth() + delta.getWidth(), points.get(1).getHeight());
			} else { // horizontal line
				points.get(1).setSize(points.get(1).getWidth(), points.get(1).getHeight() + delta.getHeight());
			}
			points.firstElement().setSize(points.firstElement().getWidth() + delta.getWidth(), points.firstElement().getHeight() + delta.getHeight());
			
			classPositions.set(0, new Dimension(classes.get(0).getX(), classes.get(0).getY()));
			moved = true;
		}
		
		// check Daughter Class position
		if (classes.get(classes.size()-1).getX() != classPositions.get(classPositions.size()-1).getWidth() || classes.get(classes.size()-1).getY() != classPositions.get(classPositions.size()-1).getHeight()) {
			Dimension delta = new Dimension(classes.get(classes.size()-1).getX() - (int)classPositions.get(classPositions.size()-1).getWidth(), classes.get(classes.size()-1).getY() - (int)classPositions.get(classPositions.size()-1).getHeight());
			
			// vertical line
			if (points.get(points.size() - 2).getWidth() == points.lastElement().getWidth()) {
				points.get(points.size() - 2).setSize(points.get(points.size() - 2).getWidth() + delta.getWidth(), points.get(points.size() - 2).getHeight());
			} else { // horizontal line
				points.get(points.size() - 2).setSize(points.get(points.size() - 2).getWidth(), points.get(points.size() - 2).getHeight() + delta.getHeight());
			}
			points.lastElement().setSize(points.lastElement().getWidth() + delta.getWidth(), points.lastElement().getHeight() + delta.getHeight());
			
			classPositions.set(classPositions.size()-1, new Dimension(classes.get(classes.size()-1).getX(), classes.get(classes.size()-1).getY()));
			moved = true;
		}
		
		return moved;
	}
	
	
	/**
	 * Check if points collapsed between them or with class and remove it
	 */
	private void checkAndRemovePoints() {
		boolean toRemove = false;
		
		if (points.size() > 2) {
			for (int i = 1 ; i < points.size() - 1 ; i++) {
				if (points.get(i).width == points.get(i-1).width && points.get(i).height == points.get(i-1).height) {
					toRemove = true;
				}
				else if (isIntoClass(points.get(i), classes.get(0))){
					toRemove = true;
				}
				else if (isIntoClass(points.get(i), classes.get(classes.size()-1)) && i != points.size() -1) {
					toRemove = true;
				}
				if (toRemove) {
					points.remove(i);
					i--;
					toRemove = false;
				}
			}
		}
	}
	
	/**
	 * Check if points are well placed, and add eventual new points to finish link drawing
	 */
	private void checkAndAddPoints() { // TODO checkAndAddPoints
		
		// check first points is on motherClass
		/*		if ( !((points.firstElement().width == motherClass.getX() || points.firstElement().width == motherClass.getX() + motherClass.getWidth())
				&& points.firstElement().height >= motherClass.getY() && points.firstElement().height <= motherClass.getY() + motherClass.getHeight())
				||
				!((points.firstElement().height == motherClass.getY() || points.firstElement().height == motherClass.getY() + motherClass.getHeight())
				&& points.firstElement().width >= motherClass.getX() && points.firstElement().width <= motherClass.getX() + motherClass.getWidth())
				) {

			int x = points.firstElement().width;
			int y = points.firstElement().height;
			if (points.firstElement().width < motherClass.getX()) {
				x = motherClass.getX();
			} else if (points.firstElement().width > motherClass.getX() + motherClass.getWidth()) {
				x = motherClass.getX() + motherClass.getWidth();
			}
			
			if (points.firstElement().height < motherClass.getY()) {
				y = motherClass.getY();
			} else if (points.firstElement().height > motherClass.getY() + motherClass.getHeight()) {
				y = motherClass.getY() + motherClass.getHeight();
			}
			points.add(0, new Dimension(x,y));
		} else {
			if (points.get(1).width < motherClass.getX()) {
				points.firstElement().width = motherClass.getX();
			} else if (points.get(1).width > motherClass.getX() + motherClass.getWidth()) {
				points.firstElement().width = motherClass.getX() + motherClass.getWidth();
			}
			
			if (points.get(1).height < motherClass.getY()) {
				points.firstElement().height = motherClass.getY();
			} else if (points.get(1).height > motherClass.getY() + motherClass.getHeight()) {
				points.firstElement().height = motherClass.getY() + motherClass.getHeight();
			}
		}*/
		
		/*
		if (points.firstElement().width < motherClass.getX()) {
			points.firstElement().width = motherClass.getX();
		}
		if (points.firstElement().width > motherClass.getX() + motherClass.getWidth()) {
			points.firstElement().width = motherClass.getX() + motherClass.getWidth();
		}
		if (points.firstElement().height < motherClass.getY()) {
			points.firstElement().height = motherClass.getY();
		}
		if (points.firstElement().height > motherClass.getY() + motherClass.getHeight()) {
			points.firstElement().height = motherClass.getY() + motherClass.getHeight();
		}*/

		// check all other points
		for (int i = 1 ; i < points.size() ; i++) {
			if (points.get(i).width < points.get(i-1).width) {
				points.get(i).height = points.get(i-1).height;
			} else
			if (points.get(i).width > points.get(i-1).width) {
				points.get(i).height = points.get(i-1).height;
			} else
			if (points.get(i).height < points.get(i-1).height) {
				points.get(i).width = points.get(i-1).width;
			} else
			if (points.get(i).height > points.get(i-1).height) {
				points.get(i).width = points.get(i-1).width;
			}
		}
		
		// if last points is not on daughterClass add point
		
		/*if ( !((points.lastElement().width == daughterClass.getX() || points.lastElement().width == daughterClass.getX() + daughterClass.getWidth())
				&& points.lastElement().height >= daughterClass.getY() && points.lastElement().height <= daughterClass.getY() + daughterClass.getHeight())
				||
				!((points.lastElement().height == daughterClass.getY() || points.lastElement().height == daughterClass.getY() + daughterClass.getHeight())
				&& points.lastElement().width >= daughterClass.getX() && points.lastElement().width <= daughterClass.getX() + daughterClass.getWidth())
				) {
			if (points.get(points.size() - 2).getWidth() == points.lastElement().getWidth()) { // last line is vertical
				int y = points.lastElement().height;
				points.add(new Dimension(daughterClass.getX(), y));
			} else {
				int x = points.lastElement().width;
				points.add(new Dimension(x, daughterClass.getY()));
			}
		}
		*/
		/*
		if (points.lastElement().width < daughterClass.getX()) {
			points.lastElement().width = daughterClass.getX();
		}
		if (points.lastElement().width > daughterClass.getX() + daughterClass.getWidth()) {
			points.lastElement().width = daughterClass.getX() + daughterClass.getWidth();
		}
		if (points.lastElement().height < daughterClass.getY()) {
			points.lastElement().height = daughterClass.getY();
		}
		if (points.lastElement().height > daughterClass.getY() + daughterClass.getHeight()) {
			points.lastElement().height = daughterClass.getY() + daughterClass.getHeight();
		}*/
		
		if ( !((points.lastElement().width == classes.get(classes.size()-1).getX() || points.lastElement().width == classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth())
				&& points.lastElement().height >= classes.get(classes.size()-1).getY() && points.lastElement().height <= classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight())
				||
				!((points.lastElement().height == classes.get(classes.size()-1).getY() || points.lastElement().height == classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight())
				&& points.lastElement().width >= classes.get(classes.size()-1).getX() && points.lastElement().width <= classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth())
				) {
			// add points
			int x = points.lastElement().width;
			int y = points.lastElement().height;
			if (points.lastElement().width < classes.get(classes.size()-1).getX()) {
				x = classes.get(classes.size()-1).getX();
			} else if (points.lastElement().width > classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()) {
				x = classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth();
			}
			
			if (points.lastElement().height < classes.get(classes.size()-1).getY()) {
				y = classes.get(classes.size()-1).getY();
			} else if (points.lastElement().height > classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()) {
				y = classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight();
			}
			points.add(new Dimension(x,y));
			/*
			if (points.get(points.size() - 2).getWidth() == points.lastElement().getWidth()) { // last line is vertical
				int y = points.lastElement().height;
				points.add(new Dimension(daughterClass.getX(), y));
			} else {
				int x = points.lastElement().width;
				points.add(new Dimension(x, daughterClass.getY()));
			}*/
		} else {
			if (points.get(points.size() - 2).width < classes.get(classes.size()-1).getX()) {
				points.lastElement().width = classes.get(classes.size()-1).getX();
			} else if (points.get(points.size() - 2).width > classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()) {
				points.lastElement().width = classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth();
			}
			
			if (points.get(points.size() - 2).height < classes.get(classes.size()-1).getY()) {
				points.lastElement().height = classes.get(classes.size()-1).getY();
			} else if (points.get(points.size() - 2).height > classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight()) {
				points.lastElement().height = classes.get(classes.size()-1).getY() + classes.get(classes.size()-1).getHeight();
			}
		}
	}
	
	/**
	 * Return arrow position (as defined in LinkDrawing constants) from  main class
	 * 
	 * @return arrow position
	 */
	private int getArrowPosition() {
		int position = POSITION_ERROR;
		
		if (points.size() >= 2) {
			if (points.firstElement().width == points.get(1).width) {
				if (points.firstElement().height < points.get(1).height) {
					position = BOTTOM;
				} else {
					position = TOP;
				}
			} else {
				if ((points.firstElement().width < points.get(1).width)) {
					position = RIGHT;
				} else {
					position = LEFT;
				}
			}
		}
		
		return position;
	}
	
	/**
	 * Draw arrow
	 * 
	 * @param g
	 *            Graphics2D where to draw
	 */
	private void drawArrow(Graphics2D g) {
		int x = 0;
		int y = 0;
		int position = getArrowPosition();
		Polygon pol = new Polygon();
		
		if (position == TOP) {
			x = points.firstElement().width - ARROW_SIZE/2;
			y = points.firstElement().height - ARROW_SIZE;
		}
		else if (position == RIGHT) {
			x = points.firstElement().width;
			y = points.firstElement().height - ARROW_SIZE/2;
		}
		else if (position == BOTTOM) {
			x = points.firstElement().width - ARROW_SIZE/2;
			y = points.firstElement().height;
		}
		else if (position == LEFT) {
			x = points.firstElement().width - ARROW_SIZE;
			y = points.firstElement().height - ARROW_SIZE/2;
		}
		
		if (type.equals(UMLNature.REALIZATION) || type.equals(UMLNature.AGGREGATION)) {
			// complete empty arrow
			if (position == TOP) {
				g.clearRect(points.firstElement().width, points.firstElement().height - ARROW_SIZE, 1, ARROW_SIZE);
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x, y);
				pol.addPoint(x + ARROW_SIZE, y);
			}
			else if (position == RIGHT) {
				g.clearRect(points.firstElement().width, points.firstElement().height, ARROW_SIZE, 1);
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x + ARROW_SIZE, y);
				pol.addPoint(x + ARROW_SIZE, y + ARROW_SIZE);
			}
			else if (position == BOTTOM) {
				g.clearRect(points.firstElement().width, points.firstElement().height, 1, ARROW_SIZE);
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x + ARROW_SIZE, y + ARROW_SIZE);
				pol.addPoint(x, y + ARROW_SIZE);
			}
			else if (position == LEFT) {
				g.clearRect(points.firstElement().width - ARROW_SIZE, points.firstElement().height, ARROW_SIZE, 1);
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x, y + ARROW_SIZE);
				pol.addPoint(x, y);
			}
			
			g.drawPolygon(pol);
			
		} else if (type.equals(UMLNature.DEPENDANCY)) {
			// simple arrow
			if (position == TOP) {
				g.drawLine(points.firstElement().width, points.firstElement().height, x, y);
				g.drawLine(x + ARROW_SIZE, y, points.firstElement().width, points.firstElement().height);
			}
			else if (position == RIGHT) {
				g.drawLine(points.firstElement().width, points.firstElement().height, x + ARROW_SIZE, y);
				g.drawLine(x + ARROW_SIZE, y + ARROW_SIZE, points.firstElement().width, points.firstElement().height);
			}
			else if (position == BOTTOM) {
				g.drawLine(points.firstElement().width, points.firstElement().height, x + ARROW_SIZE, y + ARROW_SIZE);
				g.drawLine(x, y + ARROW_SIZE, points.firstElement().width, points.firstElement().height);
			}
			else if (position == LEFT) {
				g.drawLine(points.firstElement().width, points.firstElement().height, x, y + ARROW_SIZE);
				g.drawLine(x, y, points.firstElement().width, points.firstElement().height);
			}
			
/*		} else if (type.equals(UMLNature.N_ASSOCIATION)) {
			// NOT IMPLEMENTED
		} else if (type.equals(UMLNature.ASSOCIATION)) {
			// no arrow
*/		} else if (type.equals(UMLNature.AGGREGATION)) {
			// empty diamond
			if (position == TOP) {
				g.clearRect(points.firstElement().width, points.firstElement().height - ARROW_SIZE*2, 1, ARROW_SIZE*2);
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x, y);
				pol.addPoint(x + ARROW_SIZE/2, y - ARROW_SIZE);
				pol.addPoint(x + ARROW_SIZE, y);
			}
			else if (position == RIGHT) {
				g.clearRect(points.firstElement().width, points.firstElement().height, ARROW_SIZE*2, 1);
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x + ARROW_SIZE, y);
				pol.addPoint(x + ARROW_SIZE*2, y + ARROW_SIZE/2);
				pol.addPoint(x + ARROW_SIZE, y + ARROW_SIZE);
			}
			else if (position == BOTTOM) {
				g.clearRect(points.firstElement().width, points.firstElement().height, 1, ARROW_SIZE*2);
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x + ARROW_SIZE, y + ARROW_SIZE);
				pol.addPoint(x + ARROW_SIZE/2, y + ARROW_SIZE*2);
				pol.addPoint(x, y + ARROW_SIZE);
			}
			else if (position == LEFT) {
				g.clearRect(points.firstElement().width - ARROW_SIZE*2, points.firstElement().height, ARROW_SIZE*2, 1);
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x, y + ARROW_SIZE);
				pol.addPoint(x - ARROW_SIZE, y + ARROW_SIZE/2);
				pol.addPoint(x, y);
			}
			
			g.drawPolygon(pol);
			
		} else if (type.equals(UMLNature.COMPOSITION)) {
			// full diamond
			if (position == TOP) {
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x, y);
				pol.addPoint(x + ARROW_SIZE/2, y - ARROW_SIZE);
				pol.addPoint(x + ARROW_SIZE, y);
			}
			else if (position == RIGHT) {
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x + ARROW_SIZE, y);
				pol.addPoint(x + ARROW_SIZE*2, y + ARROW_SIZE/2);
				pol.addPoint(x + ARROW_SIZE, y + ARROW_SIZE);
			}
			else if (position == BOTTOM) {
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x + ARROW_SIZE, y + ARROW_SIZE);
				pol.addPoint(x + ARROW_SIZE/2, y + ARROW_SIZE*2);
				pol.addPoint(x, y + ARROW_SIZE);
			}
			else if (position == LEFT) {
				pol.addPoint(points.firstElement().width, points.firstElement().height);
				pol.addPoint(x, y + ARROW_SIZE);
				pol.addPoint(x - ARROW_SIZE, y + ARROW_SIZE/2);
				pol.addPoint(x, y);
			}
			
			g.fillPolygon(pol);
		}
	}
	
	/**
	 * Draw link in specified Graphics2D
	 * 
	 * @param g
	 *            Graphics2D where to draw
	 */
	public void draw(Graphics2D g) {

//		if (!moved) {
			// points vector reset
			points.removeAllElements();
			
			definePoints();
			
			for (int i = 0 ; i < classes.size() ; i++) {
				classPositions.set(i, new Dimension(classes.get(i).getX(), classes.get(i).getY()));				
			}
//		} else {
//			checkClassPosition();	// OK
//			checkAndRemovePoints();	// OK
//			if (moved) {
//				//checkAndAddPoints();	// TODO checkAndAddPoints
//				//moved = false;
//			}
//		}
	
		for (int i = 1; i < points.size() ; i++) {
			if (!type.equals(UMLNature.REALIZATION) && !type.equals(UMLNature.DEPENDANCY)) {
				g.drawLine((int)points.get(i-1).getWidth(), (int)points.get(i-1).getHeight(), (int)points.get(i).getWidth(), (int)points.get(i).getHeight());
			} else {
				// draw dashed line
				Stroke temp = g.getStroke();
				Stroke drawingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{DASH_DELTA}, 0);
				
				g.setStroke(drawingStroke);
				g.drawLine((int)points.get(i-1).getWidth(), (int)points.get(i-1).getHeight(), (int)points.get(i).getWidth(), (int)points.get(i).getHeight());
				
				g.setStroke(temp);
			}
		}
		
		drawArrow(g);
		
		// drawString text
		if (points.size() >= 1) {
			
			if (points.firstElement().getWidth() == classes.get(0).getX()) {
				g.drawString(text, points.firstElement().width - g.getFontMetrics().stringWidth(multiplicity.get(0)) - g.getFontMetrics().stringWidth(text) - DRAWSTRING_DELTA*3, points.firstElement().height - DRAWSTRING_DELTA);
			} else if (points.firstElement().getWidth() == classes.get(0).getX() + classes.get(0).getWidth()) {
				g.drawString(text, points.firstElement().width + g.getFontMetrics().stringWidth(multiplicity.get(0)) + DRAWSTRING_DELTA*3, points.firstElement().height - DRAWSTRING_DELTA);
			} else if (points.firstElement().getHeight() == classes.get(0).getY()) {
				g.drawString(text, points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height - g.getFont().getSize() - DRAWSTRING_DELTA*3);
			} else {
				g.drawString(text, points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height + 2*g.getFont().getSize() + DRAWSTRING_DELTA*2);
			}
			
			// drawString motherWeighting
			if (points.firstElement().getWidth() == classes.get(0).getX() && multiplicity.size() >= 1) {
				g.drawString(multiplicity.get(0), points.firstElement().width - g.getFontMetrics().stringWidth(multiplicity.get(0)) - DRAWSTRING_DELTA, points.firstElement().height - DRAWSTRING_DELTA);
			} else if (points.firstElement().getWidth() == classes.get(0).getX() + classes.get(0).getWidth()) {
				g.drawString(multiplicity.get(0), points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height - DRAWSTRING_DELTA);
			} else if (points.firstElement().getHeight() == classes.get(0).getY()) {
				g.drawString(multiplicity.get(0), points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height - DRAWSTRING_DELTA);
			} else {
				g.drawString(multiplicity.get(0), points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height + g.getFont().getSize());
			}
			
			// drawString daughterWeighting
			if (points.lastElement().getWidth() == classes.get(classes.size()-1).getX() && multiplicity.size() >= 2) {
				g.drawString(multiplicity.get(multiplicity.size()-1), points.lastElement().width - g.getFontMetrics().stringWidth(multiplicity.get(multiplicity.size()-1)) - DRAWSTRING_DELTA, points.lastElement().height - DRAWSTRING_DELTA);
			} else if (points.lastElement().getWidth() == classes.get(classes.size()-1).getX() + classes.get(classes.size()-1).getWidth()) {
				g.drawString(multiplicity.get(multiplicity.size()-1), points.lastElement().width + DRAWSTRING_DELTA, points.lastElement().height - DRAWSTRING_DELTA);
			} else if (points.lastElement().getHeight() == classes.get(classes.size()-1).getY()) {
				g.drawString(multiplicity.get(multiplicity.size()-1), points.lastElement().width + DRAWSTRING_DELTA, points.lastElement().height - DRAWSTRING_DELTA);
			} else {
				g.drawString(multiplicity.get(multiplicity.size()-1), points.lastElement().width + DRAWSTRING_DELTA, points.lastElement().height + g.getFont().getSize());
			}
		}
	}
	
	/**
	 * Move link line under position of X and Y
	 * 
	 * @param pos
	 *            movement position (mouse)
	 * @param x
	 *            move on X-axis
	 * @param y
	 *            move on Y-axis
	 */
	public void move(Dimension pos, int x, int y) {
		boolean find = false;
		int i = 1;
		
		moved = true;
		
		// find the line to move
		while (i < points.size() && !find) {
			if (points.get(i-1).getWidth() == points.get(i).getWidth()) { // vertical line
				if (Math.min(points.get(i-1).getWidth(), points.get(i).getWidth()) - ISUNDER_DELTA <= pos.width
						&& pos.width < Math.max(points.get(i-1).getWidth(), points.get(i).getWidth()) + ISUNDER_DELTA
						&& Math.min(points.get(i-1).getHeight(), points.get(i).getHeight()) <= pos.height
						&& pos.height < Math.max(points.get(i-1).getHeight(), points.get(i).getHeight())) {
					find = true;
				}
			} else { // horizontal line
				if (Math.min(points.get(i-1).getWidth(), points.get(i).getWidth()) <= pos.width
						&& pos.width < Math.max(points.get(i-1).getWidth(), points.get(i).getWidth())
						&& Math.min(points.get(i-1).getHeight(), points.get(i).getHeight()) - ISUNDER_DELTA <= pos.height
						&& pos.height < Math.max(points.get(i-1).getHeight(), points.get(i).getHeight()) + ISUNDER_DELTA) {
					find = true;
				}
			}
			i++;
		}
		
		if (find) {
			i--;
			if (points.get(i-1).getWidth() == points.get(i).getWidth()) { // vertical line
				points.get(i-1).width += x;
				points.get(i).width += x;
			} else { // horizontal line
				points.get(i-1).height += y;
				points.get(i).height += y;
			}
		}
	}
	
	/**
	 * Move link line under position of Dimension width (X) and height (Y)
	 * 
	 * @param pos
	 *            movement position (mouse)
	 * @param delta
	 *            movement delta
	 */
	public void move(Dimension pos, Dimension delta) {
		this.move (pos, delta.width, delta.height);
	}
	
	/**
	 * Reset moved memorization and re-allow automated point setting
	 */
	public void resetMoved() {
		moved = false;
	}
	
	/**
	 * Return if link is under a position
	 * 
	 * @param true if under position
	 */
	public boolean isUnder(Dimension pos) {
		boolean under = false;
		int i = 1;
		
		while (i < points.size() && !under) {
			if (points.get(i-1).getWidth() == points.get(i).getWidth()) { // vertical line
				if (Math.min(points.get(i-1).getWidth(), points.get(i).getWidth()) - ISUNDER_DELTA <= pos.width
						&& pos.width < Math.max(points.get(i-1).getWidth(), points.get(i).getWidth()) + ISUNDER_DELTA
						&& Math.min(points.get(i-1).getHeight(), points.get(i).getHeight()) <= pos.height
						&& pos.height < Math.max(points.get(i-1).getHeight(), points.get(i).getHeight())) {
					under = true;
				}
			} else { // horizontal line
				if (Math.min(points.get(i-1).getWidth(), points.get(i).getWidth()) <= pos.width
						&& pos.width < Math.max(points.get(i-1).getWidth(), points.get(i).getWidth())
						&& Math.min(points.get(i-1).getHeight(), points.get(i).getHeight()) - ISUNDER_DELTA <= pos.height
						&& pos.height < Math.max(points.get(i-1).getHeight(), points.get(i).getHeight()) + ISUNDER_DELTA) {
					under = true;
				}
			}
			i++;
		}
		
		return under;
	}

	/**
	 * Invert main class and so arrow drawing position
	 */
	public void reverseClass() {
		if (!type.equals(UMLNature.ASSOCIATION) || ! type.equals(UMLNature.N_ASSOCIATION)) {
			ClassDrawing aux = this.classes.get(0);
			this.classes.set(0, classes.get(1));
			this.classes.set(1, aux);
			
			String temp = this.multiplicity.get(0);
			this.multiplicity.set(0, multiplicity.get(1));
			this.multiplicity.set(1, temp);
		}
	}
	
	/**
	 * Get id of link drawing
	 * 
	 * @return id object
	 */
	public Object getInstanceID() {
		return id;
	}
	
	/**
	 * Get type of link drawing
	 * 
	 * @return UMLNature object
	 */
	public Object getType() {
		return type;
	}

	/**
	 * Get text of link drawing
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Set text of link drawing
	 * 
	 * @param text
	 *            text to draw
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Get main class id of link drawing
	 * 
	 * @return main class id
	 */
	public Object getMotherClassID() {
		return classes.get(0).getInstanceID();
	}
	
	/**
	 * Get multiplicity text of main class of link drawing
	 * 
	 * @return multiplicity
	 */
	public String getMotherMultiplicity() {
		return multiplicity.get(0);
	}
	
	/**
	 * Get second class id of link drawing
	 * 
	 * @return second class id
	 */
	public Object getDaughterClassID() {
		return classes.get(classes.size()-1).getInstanceID();
	}

	/**
	 * Get multiplicity text of second class of link drawing
	 * 
	 * @return multiplicity
	 */
	public String getDaughterMultiplicity() {
		return multiplicity.get(1);
	}

	/**
	 * Set multiplicity text of second class of link drawing
	 * 
	 * @param multiplicity
	 *            multiplicity to draw
	 */
	public void setMultiplicity(ArrayList<String> multiplicity) {
		this.multiplicity = multiplicity;
	}
	
	/**
	 * Get if link drawing is colored
	 * 
	 * @return colored
	 */
	public boolean isColored() {
		return isColored;
	}

	/**
	 * Set if link drawing is colored
	 * 
	 * @param colored
	 *            if true, lines and text are colored
	 */
	public void setColored(boolean colored) {
		this.isColored = colored;
	}
}
