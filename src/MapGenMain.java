public class MapGenMain {

    public static void main(String[] args) {
        MapGrid map = new MapGrid(40,30);

        map.InitialGenerateDryMap(1, 255);
        map.FloodMap(100);

        //  System.out.println(map.toString());
        ImageGen img = new ImageGen(map, "output.png", 1);
        img.GenerateImg();

        MapGrid smoothedMap = map;
        //new MapGrid(map.BasicSmoothHeightMap(), 15, 20);
        smoothedMap.BasicSmoothHeightMap();
        //System.out.println(smoothedMap.toString());

        ImageGen img2 = new ImageGen(smoothedMap, "output_smooth.png", 1);
        img2.GenerateImg();


        smoothedMap.RemoveTerrainNoise(3, 6);
        ImageGen img3 = new ImageGen(smoothedMap, "output_smooth_terrain.png", 1);
        img3.GenerateImg();

        smoothedMap.AddBeaches(1, 3);
        ImageGen img4 = new ImageGen(smoothedMap, "output_smooth_terrain2.png", 1);
        img4.GenerateImg();
    }

}
