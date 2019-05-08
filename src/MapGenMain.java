public class MapGenMain {

    public static void main(String[] args) {
        MapGrid map = new MapGrid(40,30);

        map.InitialGenerateDryMap(1, 255);
        ImageGen img = new ImageGen(map, "dryTerrainMap.png", 10);
        img.GenerateImg();

        map.FloodMap(90);
        ImageGen img5 = new ImageGen(map, "floodedTerrainMap.png", 10);
        img5.GenerateImg();
        //  System.out.println(map.toString());

        MapGrid smoothedMap = map;
        //new MapGrid(map.BasicSmoothHeightMap(), 15, 20);
        smoothedMap.BasicSmoothHeightMap();
        ImageGen img6 = new ImageGen(smoothedMap, "smoothedHeightMap.png", 10);
        img6.GenerateImg();
        //System.out.println(smoothedMap.toString());



        smoothedMap.RemoveTerrainNoise(3, 6);
        ImageGen img3 = new ImageGen(smoothedMap, "smoothedFormationMap.png", 10);
        img3.GenerateImg();

        smoothedMap.AddBeaches(1, 3);
        ImageGen img4 = new ImageGen(smoothedMap, "addedBeachesMap.png", 10);
        img4.GenerateImg();
    }

}
