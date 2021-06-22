public class Main {
    public static void main(String[] args) {
        String IPAdd = "163.121.12.30";
        Cutter IP = new Cutter(IPAdd);
        for (int i = 0; i < 4; i++) {
            int[] res = IP.docutting(IPAdd);
            System.out.println(res[i]);
        }

    }
}

