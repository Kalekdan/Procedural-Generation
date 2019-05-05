public class MapGenMain {

    public static void main(String[] args) {
        MapGrid map = new MapGrid(15,20);

        map.InitialGenerateDryMap(1, 255);

      //  System.out.println(map.toString());
        ImageGen img = new ImageGen(map, "output.png", 1);
        img.GenerateImg();

        MapGrid smoothedMap = map;
        //new MapGrid(map.BasicSmoothHeightMap(), 15, 20);
        smoothedMap.BasicSmoothHeightMap();
        System.out.println(smoothedMap.toString());

        smoothedMap.FloodMap(120);
        ImageGen img2 = new ImageGen(smoothedMap, "output_smooth.png", 1);
        img2.GenerateImg();
    }

}
