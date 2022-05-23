package com.example.tilingalgorithm;

import java.util.Comparator;

public class PointComparatorInferior implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        return Integer.compare((o1.getHauteur()),o2.getHauteur());
    }
}
