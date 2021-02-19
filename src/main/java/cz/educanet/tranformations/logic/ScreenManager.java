package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;

import java.util.*;

public class ScreenManager {

    private Set<Coordinate> selectedPoints = new HashSet<>();

    public void select(Coordinate coordinate) {
        selectedPoints.add(coordinate);
    }

    public void unselect(Coordinate coordinate) {
        selectedPoints.remove(coordinate);
    }

    public boolean isSelected(Coordinate coordinate) {
        return selectedPoints.contains(coordinate);
    }

    public Set<Coordinate> getSelectedPoints() {
        return selectedPoints;
    }

    public int edgeFunction(Coordinate a, Coordinate b, Coordinate c) {
        return (b.getY() - c.getY()) * (a.getX() - c.getX()) - (c.getX() - b.getX()) * (c.getY() - a.getY());
    }

    public boolean isFilledIn(Coordinate coordinate) {
        Coordinate[] coordsArr = selectedPoints.toArray(new Coordinate[]{});

        Coordinate vA = coordsArr[0];
        Coordinate vB = coordsArr[1];
        Coordinate vC = coordsArr[2];

        System.out.println(Arrays.toString(coordsArr));

        int a = ((coordinate.getX() - vC.getX()) * (vB.getY() - vC.getY())) + ((coordinate.getY()) - vC.getY()) * (vC.getX() - vB.getX());
        int b = ((coordinate.getX() - vC.getX()) * (vC.getY() - vA.getY())) + ((coordinate.getY()) - vC.getY()) * (vA.getX() - vC.getX());
        int c = edgeFunction(vA, vB, vC) - a - b;

        int min = Math.min(edgeFunction(vA, vB, vC), 0);
        int max = Math.max(edgeFunction(vA, vB, vC), 0);

        if (a < min || a > max)
            return true;

        else if (b < min || b > max)
            return true;

        else
            return c >= min && c <= max;
    }
}

