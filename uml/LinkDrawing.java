package uml;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.util.Vector;


public class LinkDrawing {
	
	// Size variables
	private static final int ISUNDER_DELTA = 6;		// pixel delta around line position checking
	private static final int DASH_DELTA = 10;		// pixel size between dash
	private static final int DRAWSTRING_DELTA = 6;	// pixel space between line and Strings
	private static final int ARROW_SIZE = 10;		// pixel size for arrow drawing
	
	// Link type use by LinkDrawing class representation
	//public static final int DIRECTIONNAL = 0;
	//public static final int HERITAGE = 1;
	public static final int REALIZATION = 2;
	public static final int GENERALIZATION = 3;
	public static final int DEPENDANCY = 4;
	//public static final int ASSOCIATION = 5;
	public static final int N_ARY_ASSOCIATION = 6;
	public static final int BINARY_ASSOCIATION = 7;
	public static final int AGGREGATION = 8;
	public static final int COMPOSITION = 9;
	
	// Position variables for arrow drawing
	private static final int POSITION_ERROR = -1;
	private static final int TOP = 0;
	private static final int RIGHT = 1;
	private static final int BOTTOM = 2;
	private static final int LEFT = 3;
	
	private ClassDrawing motherClass;
	private ClassDrawing daughterClass;
	
	private Dimension motherClassPosition;
	private Dimension daughterClassPosition;
	private Vector<Dimension> points;
	
	private int type;
	private String text;
	private String motherMultiplicity;
	private String daughterMultiplicity;
	private boolean moved; // not use to its max potential

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
	public LinkDrawing(ClassDrawing motherClass, ClassDrawing daughterClass, int type) {
		this.motherClass = motherClass;
		motherClassPosition = new Dimension(motherClass.getX(), motherClass.getY());
		this.daughterClass = daughterClass;
		
		daughterClassPosition = new Dimension(daughterClass.getX(), daughterClass.getY());
		points = new Vector<Dimension>();
		definePoints();
		
		this.type = type;
		text = "";
		motherMultiplicity = "";
		daughterMultiplicity = "";
		
		moved = false;
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
		if (motherClass.getX() + motherClass.getWidth() <= daughterClass.getX()) {
			// daugtherClass is at TOP-LEFT of motherClass
			if (motherClass.getY() > daughterClass.getY() + daughterClass.getHeight()/2) {
				points.add(new Dimension(motherClass.getX() + motherClass.getWidth(), motherClass.getY() + motherClass.getHeight()/4));
				points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth()/4, motherClass.getY() + motherClass.getHeight()/4));
				points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth()/4, daughterClass.getY() + daughterClass.getHeight()));
			} else {
				// daugtherClass is at BOTTOM-LEFT of motherClass
				if (motherClass.getY() + motherClass.getHeight() < daughterClass.getY() + daughterClass.getHeight()/2) {
					points.add(new Dimension(motherClass.getX() + motherClass.getWidth(), motherClass.getY() + motherClass.getHeight()*3/4));
					points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth()/4, motherClass.getY() + motherClass.getHeight()*3/4));
					points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth()/4, daughterClass.getY()));
				}
				// daugtherClass is at MIDDLE-LEFT of motherClass
				else {
					points.add(new Dimension(motherClass.getX() + motherClass.getWidth(), daughterClass.getY() + daughterClass.getHeight()/2));
					points.add(new Dimension(daughterClass.getX(), daughterClass.getY() + daughterClass.getHeight()/2));
				}
			}
		}
		else {
			// daugtherClass is at RIGHT of motherClass
			if (motherClass.getX() >= daughterClass.getX() + daughterClass.getWidth()) {
			// daugtherClass is at TOP-RIGHT of motherClass
				if (motherClass.getY() > daughterClass.getY() + daughterClass.getHeight()/2) {
					points.add(new Dimension(motherClass.getX(), motherClass.getY() + motherClass.getHeight()/4));
					points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth()*3/4, motherClass.getY() + motherClass.getHeight()/4));
					points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth()*3/4, daughterClass.getY() + daughterClass.getHeight()));
				} else {
					// daugtherClass is at BOTTOM-RIGHT of motherClass
					if (motherClass.getY() + motherClass.getHeight() < daughterClass.getY() + daughterClass.getHeight()/2) {
						points.add(new Dimension(motherClass.getX(), motherClass.getY() + motherClass.getHeight()*3/4));
						points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth()*3/4, motherClass.getY() + motherClass.getHeight()*3/4));
						points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth()*3/4, daughterClass.getY()));
					}
					// daugtherClass is at MIDDLE-RIGHT of motherClass
					else {
						points.add(new Dimension(motherClass.getX(), daughterClass.getY() + daughterClass.getHeight()/2));
						points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth(), daughterClass.getY() + daughterClass.getHeight()/2));
					}
				}
			}
			// is daugtherClass at MIDDLE TOP or BOTTOM of motherClass ?
			else {
				// daughterClass is at TOP-MIDDLE of motherClass
				if (motherClass.getY() >= daughterClass.getY() + daughterClass.getHeight()) {
					// daughterClass is at TOP-MIDDLE-RIGHT of motherClass
					if (motherClass.getX() + motherClass.getWidth()/2 <= daughterClass.getX()) {
						points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, motherClass.getY()));
						points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, daughterClass.getY() + daughterClass.getHeight()*3/4));
						points.add(new Dimension(daughterClass.getX(), daughterClass.getY() + daughterClass.getHeight()*3/4));
					} else {
						// daughterClass is at TOP-MIDDLE-LEFT of motherClass
						if (motherClass.getX() + motherClass.getWidth()/2 >= daughterClass.getX() + daughterClass.getWidth()) {
							points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, motherClass.getY()));
							points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, daughterClass.getY() + daughterClass.getHeight()*3/4));
							points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth(), daughterClass.getY() + daughterClass.getHeight()*3/4));
						}
						// daughterClass is at TOP-MIDDLE-MID of motherClass
						else {
							points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, motherClass.getY()));
							points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, daughterClass.getY() + daughterClass.getHeight()));
						}
					}
				} else {
				// daughterClass is at BOTTOM-MIDDLE of motherClass
					if (motherClass.getY() + motherClass.getHeight() <= daughterClass.getY()) {
						// daughterClass is at BOTTOM-MIDDLE-RIGHT of motherClass
						if (motherClass.getX() + motherClass.getWidth()/2 <= daughterClass.getX()) {
							points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, motherClass.getY() + motherClass.getHeight()));
							points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, daughterClass.getY() + daughterClass.getHeight()/4));
							points.add(new Dimension(daughterClass.getX(), daughterClass.getY() + daughterClass.getHeight()/4));
						} else {
							// daughterClass is at BOTTOM-MIDDLE-LEFT of motherClass
							if (motherClass.getX() + motherClass.getWidth()/2 >= daughterClass.getX() + daughterClass.getWidth()) {
								points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, motherClass.getY() + motherClass.getHeight()));
								points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, daughterClass.getY() + daughterClass.getHeight()/4));
								points.add(new Dimension(daughterClass.getX() + daughterClass.getWidth(), daughterClass.getY() + daughterClass.getHeight()/4));
							}
							// daughterClass is at BOTTOM-MIDDLE-MID of motherClass
							else {
								points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, motherClass.getY() + motherClass.getHeight()));
								points.add(new Dimension(motherClass.getX() + motherClass.getWidth()/2, daughterClass.getY()));
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
		if (motherClass.getX() != motherClassPosition.getWidth() || motherClass.getY() != motherClassPosition.getHeight()) {
			Dimension delta = new Dimension(motherClass.getX() - (int)motherClassPosition.getWidth(), motherClass.getY() - (int)motherClassPosition.getHeight());
			
			// vertical line
			if (points.firstElement().getWidth() == points.get(1).getWidth()) {
				points.get(1).setSize(points.get(1).getWidth() + delta.getWidth(), points.get(1).getHeight());
			} else { // horizontal line
				points.get(1).setSize(points.get(1).getWidth(), points.get(1).getHeight() + delta.getHeight());
			}
			points.firstElement().setSize(points.firstElement().getWidth() + delta.getWidth(), points.firstElement().getHeight() + delta.getHeight());
			
			motherClassPosition.setSize(motherClass.getX(), motherClass.getY());
			moved = true;
		}
		
		// check Daughter Class position
		if (daughterClass.getX() != daughterClassPosition.getWidth() || daughterClass.getY() != daughterClassPosition.getHeight()) {
			Dimension delta = new Dimension(daughterClass.getX() - (int)daughterClassPosition.getWidth(), daughterClass.getY() - (int)daughterClassPosition.getHeight());
			
			// vertical line
			if (points.get(points.size() - 2).getWidth() == points.lastElement().getWidth()) {
				points.get(points.size() - 2).setSize(points.get(points.size() - 2).getWidth() + delta.getWidth(), points.get(points.size() - 2).getHeight());
			} else { // horizontal line
				points.get(points.size() - 2).setSize(points.get(points.size() - 2).getWidth(), points.get(points.size() - 2).getHeight() + delta.getHeight());
			}
			points.lastElement().setSize(points.lastElement().getWidth() + delta.getWidth(), points.lastElement().getHeight() + delta.getHeight());
			
			daughterClassPosition.setSize(daughterClass.getX(), daughterClass.getY());
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
				else if (isIntoClass(points.get(i), motherClass)){
					toRemove = true;
				}
				else if (isIntoClass(points.get(i), daughterClass) && i != points.size() -1) {
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
		
		if ( !((points.lastElement().width == daughterClass.getX() || points.lastElement().width == daughterClass.getX() + daughterClass.getWidth())
				&& points.lastElement().height >= daughterClass.getY() && points.lastElement().height <= daughterClass.getY() + daughterClass.getHeight())
				||
				!((points.lastElement().height == daughterClass.getY() || points.lastElement().height == daughterClass.getY() + daughterClass.getHeight())
				&& points.lastElement().width >= daughterClass.getX() && points.lastElement().width <= daughterClass.getX() + daughterClass.getWidth())
				) {
			// add points
			int x = points.lastElement().width;
			int y = points.lastElement().height;
			if (points.lastElement().width < daughterClass.getX()) {
				x = daughterClass.getX();
			} else if (points.lastElement().width > daughterClass.getX() + daughterClass.getWidth()) {
				x = daughterClass.getX() + daughterClass.getWidth();
			}
			
			if (points.lastElement().height < daughterClass.getY()) {
				y = daughterClass.getY();
			} else if (points.lastElement().height > daughterClass.getY() + daughterClass.getHeight()) {
				y = daughterClass.getY() + daughterClass.getHeight();
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
			if (points.get(points.size() - 2).width < daughterClass.getX()) {
				points.lastElement().width = daughterClass.getX();
			} else if (points.get(points.size() - 2).width > daughterClass.getX() + daughterClass.getWidth()) {
				points.lastElement().width = daughterClass.getX() + daughterClass.getWidth();
			}
			
			if (points.get(points.size() - 2).height < daughterClass.getY()) {
				points.lastElement().height = daughterClass.getY();
			} else if (points.get(points.size() - 2).height > daughterClass.getY() + daughterClass.getHeight()) {
				points.lastElement().height = daughterClass.getY() + daughterClass.getHeight();
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
		
		switch (type) {
		/*
		case DIRECTIONNAL:
			// NOT IMPLEMENTABLE
			break;
		case HERITAGE:
			// NOT IMPLEMENTABLE
			break;
		*/
		case REALIZATION:
			// complete empty arrow
			//break; // go to GENERALIZATION
		case GENERALIZATION:
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
			
			break;
		case DEPENDANCY:
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
			
			break;
		/*
		case ASSOCIATION:
			// NOT IMPLEMENTABLE
			break;
		*/
		case N_ARY_ASSOCIATION:
			// NOT IMPLEMENTED
			break;
		case BINARY_ASSOCIATION:
			// no arrow
			break;
		case AGGREGATION:
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
			break;
		case COMPOSITION:
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
			break;
		default:
			//Null
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
			
			motherClassPosition.setSize(motherClass.getX(), motherClass.getY());
			daughterClassPosition.setSize(daughterClass.getX(), daughterClass.getY());
//		} else {
//			checkClassPosition();	// OK
//			checkAndRemovePoints();	// OK
//			if (moved) {
//				//checkAndAddPoints();	// TODO checkAndAddPoints
//				//moved = false;
//			}
//		}
	
		for (int i = 1; i < points.size() ; i++) {
			if (type != REALIZATION && type != DEPENDANCY) {
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
		
		// drawString motherWeighting and text
		if (points.size() >= 1) {
			if (points.firstElement().getWidth() == motherClass.getX()) {
				g.drawString(motherMultiplicity, points.firstElement().width - g.getFontMetrics().stringWidth(motherMultiplicity) - DRAWSTRING_DELTA, points.firstElement().height - DRAWSTRING_DELTA);
				g.drawString(text, points.firstElement().width - g.getFontMetrics().stringWidth(motherMultiplicity) - g.getFontMetrics().stringWidth(text) - DRAWSTRING_DELTA*3, points.firstElement().height - DRAWSTRING_DELTA);
			} else if (points.firstElement().getWidth() == motherClass.getX() + motherClass.getWidth()) {
				g.drawString(motherMultiplicity, points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height - DRAWSTRING_DELTA);
				g.drawString(text, points.firstElement().width + g.getFontMetrics().stringWidth(motherMultiplicity) + DRAWSTRING_DELTA*3, points.firstElement().height - DRAWSTRING_DELTA);
			} else if (points.firstElement().getHeight() == motherClass.getY()) {
				g.drawString(motherMultiplicity, points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height - DRAWSTRING_DELTA);
				g.drawString(text, points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height - g.getFont().getSize() - DRAWSTRING_DELTA*3);
			} else {
				g.drawString(motherMultiplicity, points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height + g.getFont().getSize());
				g.drawString(text, points.firstElement().width + DRAWSTRING_DELTA, points.firstElement().height + 2*g.getFont().getSize() + DRAWSTRING_DELTA*2);
			}
			
			// drawString daughterWeighting
			if (points.lastElement().getWidth() == daughterClass.getX()) {
				g.drawString(daughterMultiplicity, points.lastElement().width - g.getFontMetrics().stringWidth(daughterMultiplicity) - DRAWSTRING_DELTA, points.lastElement().height - DRAWSTRING_DELTA);
			} else if (points.lastElement().getWidth() == daughterClass.getX() + daughterClass.getWidth()) {
				g.drawString(daughterMultiplicity, points.lastElement().width + DRAWSTRING_DELTA, points.lastElement().height - DRAWSTRING_DELTA);
			} else if (points.lastElement().getHeight() == daughterClass.getY()) {
				g.drawString(daughterMultiplicity, points.lastElement().width + DRAWSTRING_DELTA, points.lastElement().height - DRAWSTRING_DELTA);
			} else {
				g.drawString(daughterMultiplicity, points.lastElement().width + DRAWSTRING_DELTA, points.lastElement().height + g.getFont().getSize());
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
	public void invertClass() {
		ClassDrawing aux = this.motherClass;
		this.motherClass = this.daughterClass;
		this.daughterClass = aux;
		
		String temp = this.motherMultiplicity;
		this.motherMultiplicity = this.daughterMultiplicity;
		this.daughterMultiplicity = temp;
	}
	
	/**
	 * Get type of link drawing
	 * 
	 * @return type as defined in LinkDrawing
	 */
	public int getType() {
		return type;
	}

	/**
	 * Set type of link drawing
	 * 
	 * @param type
	 *            as defined in LinkDrawing
	 */
	public void setType(int type) {
		this.type = type;
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
	 * Get main class name of link drawing
	 * 
	 * @return main class name
	 */
	public String getMotherClass() {
		return motherClass.getName();
	}
	
	/**
	 * Get multiplicity text of main class of link drawing
	 * 
	 * @return multiplicity
	 */
	public String getMotherMultiplicity() {
		return motherMultiplicity;
	}

	/**
	 * Set multiplicity text of main class of link drawing
	 * 
	 * @param multiplicity
	 *            multiplicity to draw
	 */
	public void setMotherMultiplicity(String motherMultiplicity) {
		this.motherMultiplicity = motherMultiplicity;
	}
	
	/**
	 * Get second class name of link drawing
	 * 
	 * @return second class name
	 */
	public String getDaughterClass() {
		return daughterClass.getName();
	}

	/**
	 * Get multiplicity text of second class of link drawing
	 * 
	 * @return multiplicity
	 */
	public String getDaughterMultiplicity() {
		return daughterMultiplicity;
	}

	/**
	 * Set multiplicity text of second class of link drawing
	 * 
	 * @param multiplicity
	 *            multiplicity to draw
	 */
	public void setDaughterMultiplicity(String daughterMultiplicity) {
		this.daughterMultiplicity = daughterMultiplicity;
	}
}
