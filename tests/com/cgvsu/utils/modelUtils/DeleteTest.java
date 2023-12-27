package com.cgvsu.utils.modelUtils;

import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTest {
    @Test
    public void testDeleteVertexes() throws IOException {
        System.out.println(System.getProperty("user.dir"));
        Path fileName = Path.of("public/models/Test.obj");
        String fileContent = Files.readString(fileName);
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        Delete.deleteVertexes(model, new Integer[] {1, 2, 3, 4, 5});

        assertEquals(283, model.vertices.size());

        ObjWriter.write(model, "output/DeleteVertexesTest.obj");
    }

    @Test
    public void testDeletePolygon() {
        Model model = new Model();
        Vector3f v1 = new Vector3f(0, 0, 0);
        Vector3f v2 = new Vector3f(1, 1, 1);
        Vector3f v3 = new Vector3f(2, 2, 2);
        Vector3f v4 = new Vector3f(3, 3, 3);
        Vector3f v5 = new Vector3f(4, 4, 4);
        Vector3f v6 = new Vector3f(5, 5, 5);
        model.vertices = new ArrayList<>(Arrays.asList(v1, v2, v3, v4, v5, v6));
        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(new ArrayList<>(Arrays.asList(4, 5, 6)));
        model.polygons = new ArrayList<>(List.of(polygon1, polygon2));

        // Удаляем вершину и проверяем, что индексы вершин, шедших после удаляемой уменьшились на 1
        Delete.deleteVertexes(model, new Integer[] {4});

        assertEquals(1, model.polygons.size());

        Polygon polygon = model.polygons.get(0);
        assertEquals(polygon.getVertexIndices(), new ArrayList<>(List.of(1, 2, 3)));
    }

    @Test
    public void testOffset() {
        Model model = new Model();
        Vector3f v1 = new Vector3f(0, 0, 0);
        Vector3f v2 = new Vector3f(1, 1, 1);
        Vector3f v3 = new Vector3f(2, 2, 2);
        Vector3f v4 = new Vector3f(3, 3, 3);
        Vector3f v5 = new Vector3f(4, 4, 4);
        Vector3f v6 = new Vector3f(5, 5, 5);
        model.vertices = new ArrayList<>(Arrays.asList(v1, v2, v3, v4, v5, v6));
        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(new ArrayList<>(Arrays.asList(4, 5, 6)));
        model.polygons = new ArrayList<>(List.of(polygon1, polygon2));

        Delete.deleteVertexes(model, new Integer[] {1});

        // Проверка на то, что у оставшегося полигона уменьшились индексы
        Polygon polygon = model.polygons.get(0);
        assertEquals(polygon.getVertexIndices(), new ArrayList<>(List.of(3, 4, 5)));
    }
}