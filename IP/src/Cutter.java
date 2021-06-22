public class Cutter {
    String IPAdd ;


    public String getIPAdd() {
        return IPAdd;
    }

    public void setIPAdd(String IPAdd) {
        this.IPAdd = IPAdd;
    }

    public Cutter(String IPAdd) {
        this.IPAdd = IPAdd;


    }




    public int[] docutting(String IPAdd) {
        String[] parts = IPAdd.split("\\.");



        if (parts.length != 4) {
            return new int[]{Integer.parseInt("Error!")};

        } else {


            int [] result = new int [parts.length];
            for (int i = 0 ; i < result.length ; i++) {
                result[i] =Integer.parseInt(parts[i]);

            }
               return result;

        }




    }
}
