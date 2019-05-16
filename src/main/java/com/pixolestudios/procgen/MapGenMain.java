package main.java.com.pixolestudios.procgen;

public class MapGenMain {

    public static void main(String[] args) {
        MapGrid map = new MapGrid(150,100);

        map.InitialGenerateDryMap(1, 255);
        ImageGen img = new ImageGen(map, "imgout/dryTerrainMap.png", 10);
        img.GenerateImg();

        map.FloodMap(90);
        ImageGen img5 = new ImageGen(map, "imgout/floodedTerrainMap.png", 10);
        img5.GenerateImg();

        map.RemoveTerrainNoise(3, 31, 3);
        ImageGen img3 = new ImageGen(map, "imgout/smoothedFormationMap.png", 10);
        img3.GenerateImg();

        map.BasicSmoothHeightMap(3);
        ImageGen img6 = new ImageGen(map, "imgout/smoothedHeightMap.png", 10);
        img6.GenerateImg();

        map.AddBeaches(1, 20, 4);
        ImageGen img4 = new ImageGen(map, "imgout/addedBeachesMap.png", 10);
        img4.GenerateImg();
    }

}
