package org.shout2me.postgis.util;

import java.util.List;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class PostGISUtil {

	/**
	 * Retorna um objeto do tipo {@link Polygon} de acordo com os pontos passados
	 * @param pointsIn
	 * 		lista de {@link PointWrap} representando o polígono cuja área é incluída
	 * @param pointsOut
	 * 		lista de {@link PointWrap} representando o polígono cuja área é excluída
	 * @return
	 * 		{@link Polygon} representando o polígono com os pontos passados
	 * @throws ParseException
	 * 			caso os pontos passados não representem um polígono
	 */
	public static Polygon getPolygon(List<Point> pointsIn,
			List<Point> pointsOut) throws ParseException {
		WKTReader fromText = new WKTReader();
		StringBuffer sb = new StringBuffer();
		sb.append("POLYGON(");
		sb.append("(");
		for (int i = 0; i < pointsIn.size(); i++) {
			Point point = pointsIn.get(i);
			sb.append(point.getX());
			sb.append(' ');
			sb.append(point.getY());
			if (i != pointsIn.size() - 1) {
				sb.append(',');
			}
		}
		if (pointsOut != null && pointsOut.size() > 0) {
			sb.append("),");
			sb.append("(");
			for (int i = 0; i < pointsOut.size(); i++) {
				Point point = pointsOut.get(i);
				sb.append(point.getX());
				sb.append(' ');
				sb.append(point.getY());
				if (i != pointsOut.size() - 1) {
					sb.append(',');
				}
			}
			sb.append(")");
		} else {
			sb.append(")");
		}
		sb.append(")");
		Polygon p = (Polygon) PostGISUtil.setSRID(fromText.read(sb.toString()));
		return p;
	}


	/**
	 * Retorna um {@link Point} dada a representação por longitude e latitude
	 * @param longitude
	 * @param latitude
	 * @return
	 * 		{@link Point}
	 * @throws ParseException
	 * 			caso os pontos passados não representem um polígono
	 */
	public static Point getPoint(Double longitude, Double latitude)
			throws ParseException {
		WKTReader fromText = new WKTReader();
		StringBuffer sb = new StringBuffer();
		sb.append("POINT(");
		sb.append(longitude);
		sb.append(' ');
		sb.append(latitude);
		sb.append(')');
		Point p = (Point) PostGISUtil.setSRID(fromText.read(sb.toString()));
		return p;
	}

	private static Geometry setSRID(Geometry g) {
		g.setSRID(4326);
		return g;
	}

	public static Double getDistanceWithin(Geometry g1, Geometry g2) {
		return g1.distance(g2);
	}
	
	public static Double getDistanceInAngle(Integer inMeters) {
		return (inMeters*180)/(Math.PI* 6378137);
	}

}
