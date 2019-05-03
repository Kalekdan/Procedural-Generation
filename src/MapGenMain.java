public class MapGenMain {

    public static void main(String[] args) {
        MapGrid map = new MapGrid(15,20);

        map.InitialGenerateMap(0.9f,0);

        System.out.println(map.toString());
    }

}
