package api;

/**
 * @author Yehiel Siri
 * @since  06/12/2021
 * 
 * A geographic location representation class-object
 */
public class Geo_Location implements GeoLocation {
	private double x, y, z;

	/**
	 * Constructor
	 *
	 * @param (double) x
	 * @param (double) y
	 * @param (double) z
	 */
	public Geo_Location(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Copy constructor
	 *
	 * @param other
	 */
	public Geo_Location(Geo_Location other) {
//		this.x = other.x();
//		this.y = other.y();
//		this.z = other.z();
		
//		The right programming is as below but there is a problem with final or something
		new Geo_Location(other.x(), other.y(), other.z());
	}

	@Override
	public double x() {
		return this.x;
	}

	@Override
	public double y() {
		return this.y;
	}

	@Override
	public double z() {
		return this.z;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Calculate distance between two points is the space.
	 * 
	 * @param other - the second point in the space
	 * @return distance - the distance between 'this' and 'other'
	 */
	@Override
	public double distance(GeoLocation other) {
		double distance;

		double distX = Math.pow((other.x() - this.x()), 2);
		double distY = Math.pow((other.y() - this.y()), 2);
		double distZ = Math.pow((other.z() - this.z()), 2);

		distance = Math.sqrt(distX + distY + distZ);

		return distance;
	}

	/**
	 * @return point - Geo_Location as a point in space
	 */
	@Override
	public String toString() {
		return "(" + x() + ", " + y() + ", " + z() + ")";
	}
	
	/**
	 * Equivalence checking function between this and other:
	 * Two points in space, two locations, are equal iff x1=x2 & y1=y2 & z1=z2
	 * 
	 * @return boolean
	 */
	public boolean isEqual(Geo_Location other) {
		return (this.x == other.x()) && (this.y == other.y()) && (this.z == other.z());
	}
}
