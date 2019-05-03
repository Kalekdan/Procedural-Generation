public class MapGenMain {

    public static void main(String[] args) {
        MapGrid map = new MapGrid(15,20);

        map.InitialGenerateMap(0.4f,2, 10);

        System.out.println(map.toString());

        ImageGen img = new ImageGen(map, "output.png", 1);
        img.GenerateImg();
    }

}
