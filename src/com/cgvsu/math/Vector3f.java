package com.cgvsu.math;

import static com.cgvsu.math.Constants.EPS;
public class Vector3f {
    float x, y, z;
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public boolean equals(Vector3f other) {
        return Math.abs(x - other.x) < EPS && Math.abs(y - other.y) < EPS && Math.abs(z - other.z) < EPS;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
