public class MapGenMain {

    public static void main(String[] args) {
        MapGrid map = new MapGrid(400,300);

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


        smoothedMap.RemoveTerrainNoise(1, 6);
        ImageGen img3 = new ImageGen(smoothedMap, "output_smooth_terrain.png", 1);
        img3.GenerateImg();

        smoothedMap.RemoveTerrainNoise(10, 6);
        ImageGen img4 = new ImageGen(smoothedMap, "output_smooth_terrain2.png", 1);
        img4.GenerateImg();
    }

}
