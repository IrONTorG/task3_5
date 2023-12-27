package com.cgvsu.math;

import static com.cgvsu.math.Constants.EPS;

public class Vector2f {
    float x, y;
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Vector2f other) {
        return Math.abs(x - other.x) < EPS && Math.abs(y - other.y) < EPS;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
