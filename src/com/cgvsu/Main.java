package com.cgvsu;

import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;
import com.cgvsu.utils.modelUtils.Delete;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of("public/models/Torus.obj");
        String fileContent = Files.readString(fileName);

        Model model = ObjReader.read(fileContent);
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        Delete.deleteVertexes(model, array);


        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        ObjWriter.write(model, "output/Torus.obj");

    }
}
