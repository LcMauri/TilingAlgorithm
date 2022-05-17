package com.example.tilingalgorithm;

import java.util.Comparator;

public class PointComparator implements Comparator<Point>{
    @Override
    public int compare(Point o1, Point o2) {
        return Integer.compare((o2.getHauteur()),o1.getHauteur());
    }
}
