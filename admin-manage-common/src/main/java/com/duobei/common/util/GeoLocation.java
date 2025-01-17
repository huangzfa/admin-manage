package com.duobei.common.util;

/**
 * <p>
 * Represents a point on the surface of a sphere. (The Earth is almost
 * spherical.)
 * </p>
 * 
 * <p>
 * To create an instance, call one of the static methods fromDegrees() or
 * fromRadians().
 * </p>
 * 
 * <p>
 * This code was originally published at <a
 * href="http://JanMatuschek.de/LatitudeLongitudeBoundingCoordinates#Java">
 * http://JanMatuschek.de/LatitudeLongitudeBoundingCoordinates#Java</a>.
 * </p>
 * 
 * @author Jan Philip Matuschek
 * @version 22 September 2010
 */
public class GeoLocation {

	private double radLat; // latitude in radians
	private double radLon; // longitude in radians

	private double degLat; // latitude in degrees
	private double degLon; // longitude in degrees

	private static final double MIN_LAT = Math.toRadians(-90d); // -PI/2
	private static final double MAX_LAT = Math.toRadians(90d); // PI/2
	private static final double MIN_LON = Math.toRadians(-180d); // -PI
	private static final double MAX_LON = Math.toRadians(180d); // PI

	public static final double earthRadius = 6371010;// ����뾶

	private GeoLocation() {
	}

	/**
	 * @param latitude
	 *            the latitude, in degrees.
	 * @param longitude
	 *            the longitude, in degrees.
	 */
	public static GeoLocation fromDegrees(double latitude, double longitude) {
		GeoLocation result = new GeoLocation();
		result.radLat = Math.toRadians(latitude);
		result.radLon = Math.toRadians(longitude);
		result.degLat = latitude;
		result.degLon = longitude;
		result.checkBounds();
		return result;
	}

	/**
	 * @param latitude
	 *            the latitude, in radians.
	 * @param longitude
	 *            the longitude, in radians.
	 */
	public static GeoLocation fromRadians(double latitude, double longitude) {
		GeoLocation result = new GeoLocation();
		result.radLat = latitude;
		result.radLon = longitude;
		result.degLat = Math.toDegrees(latitude);
		result.degLon = Math.toDegrees(longitude);
		result.checkBounds();
		return result;
	}

	private void checkBounds() {
		if (radLat < MIN_LAT || radLat > MAX_LAT || radLon < MIN_LON || radLon > MAX_LON)
			throw new IllegalArgumentException();
	}

	/**
	 * @return the latitude, in degrees.
	 */
	public double getLatitudeInDegrees() {
		return degLat;
	}

	/**
	 * @return the longitude, in degrees.
	 */
	public double getLongitudeInDegrees() {
		return degLon;
	}

	/**
	 * @return the latitude, in radians.
	 */
	public double getLatitudeInRadians() {
		return radLat;
	}

	/**
	 * @return the longitude, in radians.
	 */
	public double getLongitudeInRadians() {
		return radLon;
	}

	@Override
	public String toString() {
		return degLat + " " + degLon;
	}

	/**
	 * Computes the great circle distance between this GeoLocation instance and
	 * the location argument.
	 * 
	 * @param radius
	 *            the radius of the sphere, e.g. the average radius for a
	 *            spherical approximation of the figure of the Earth is
	 *            approximately 6371.01 kilometers.
	 * @return the distance, measured in the same unit as the radius argument.
	 */
	public double distanceTo(GeoLocation location, double radius) {
		return Math.acos(Math.sin(radLat) * Math.sin(location.radLat) + Math.cos(radLat) * Math.cos(location.radLat)
				* Math.cos(radLon - location.radLon))
				* radius;
	}

	/**
	 * <p>
	 * Computes the bounding coordinates of all points on the surface of a
	 * sphere that have a great circle distance to the point represented by this
	 * GeoLocation instance that is less or equal to the distance argument.
	 * </p>
	 * <p>
	 * For more information about the formulae used in this method visit <a
	 * href="http://JanMatuschek.de/LatitudeLongitudeBoundingCoordinates">
	 * http://JanMatuschek.de/LatitudeLongitudeBoundingCoordinates</a>.
	 * </p>
	 * 
	 * @param distance
	 *            the distance from the point represented by this GeoLocation
	 *            instance. Must me measured in the same unit as the radius
	 *            argument.
	 * @param radius
	 *            the radius of the sphere, e.g. the average radius for a
	 *            spherical approximation of the figure of the Earth is
	 *            approximately 6371.01 kilometers.
	 * @return an array of two GeoLocation objects such that:
	 *         <ul>
	 *         <li>The latitude of any point within the specified distance is
	 *         greater or equal to the latitude of the first array element and
	 *         smaller or equal to the latitude of the second array element.</li>
	 *         <li>If the longitude of the first array element is smaller or
	 *         equal to the longitude of the second element, then the longitude
	 *         of any point within the specified distance is greater or equal to
	 *         the longitude of the first array element and smaller or equal to
	 *         the longitude of the second array element.</li>
	 *         <li>If the longitude of the first array element is greater than
	 *         the longitude of the second element (this is the case if the
	 *         180th meridian is within the distance), then the longitude of any
	 *         point within the specified distance is greater or equal to the
	 *         longitude of the first array element <strong>or</strong> smaller
	 *         or equal to the longitude of the second array element.</li>
	 *         </ul>
	 */
	public GeoLocation[] boundingCoordinates(double distance, double radius) {

		if (radius < 0d || distance < 0d)
			throw new IllegalArgumentException();

		// angular distance in radians on a great circle
		double radDist = distance / radius;

		double minLat = radLat - radDist;
		double maxLat = radLat + radDist;

		double minLon, maxLon;
		if (minLat > MIN_LAT && maxLat < MAX_LAT) {
			double deltaLon = Math.asin(Math.sin(radDist) / Math.cos(radLat));
			minLon = radLon - deltaLon;
			if (minLon < MIN_LON)
				minLon += 2d * Math.PI;
			maxLon = radLon + deltaLon;
			if (maxLon > MAX_LON)
				maxLon -= 2d * Math.PI;
		} else {
			// a pole is within the distance
			minLat = Math.max(minLat, MIN_LAT);
			maxLat = Math.min(maxLat, MAX_LAT);
			minLon = MIN_LON;
			maxLon = MAX_LON;
		}

		return new GeoLocation[] { fromRadians(minLat, minLon), fromRadians(maxLat, maxLon) };
	}

	public static void main(String args[]) {
		double earthRadius = 6371010;// 6378137
		GeoLocation myLocation = GeoLocation.fromDegrees(31.853597, 117.308229);
		double distance = 357;
		GeoLocation[] result = myLocation.boundingCoordinates(distance, earthRadius);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i].toString());
		}
		
		System.out.println(MIN_LAT);
		System.out.println(MAX_LAT);
		System.out.println(MIN_LON);
		System.out.println(MAX_LON);
	}
}
