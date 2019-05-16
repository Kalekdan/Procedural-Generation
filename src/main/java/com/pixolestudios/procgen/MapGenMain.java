package main.java.com.pixolestudios.procgen;

public class MapGenMain {

    public static void main(String[] args) {
        MapGrid map = new MapGrid(70,50);

        map.InitialGenerateDryMap(1, 255);
        ImageGen img = new ImageGen(map, "imgout/dryTerrainMap.png", 10);
        img.GenerateImg();

        map.FloodMap(90);
        ImageGen img5 = new ImageGen(map, "imgout/floodedTerrainMap.png", 10);
        img5.GenerateImg();

        map.RemoveTerrainNoise(3, 6);
        ImageGen img3 = new ImageGen(map, "imgout/smoothedFormationMap.png", 10);
        img3.GenerateImg();

        map.BasicSmoothHeightMap();
        ImageGen img6 = new ImageGen(map, "imgout/smoothedHeightMap.png", 10);
        img6.GenerateImg();

        map.AddBeaches(1, 3);
        ImageGen img4 = new ImageGen(map, "imgout/addedBeachesMap.png", 10);
        img4.GenerateImg();
    }

}
