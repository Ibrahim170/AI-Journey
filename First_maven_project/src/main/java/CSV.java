import joinery.DataFrame;

import java.io.IOException;
import java.util.List;

public class CSV {

    public static void main(String[] args){
        try {
            DataFrame<Object> df = DataFrame.readCsv("vgsales.csv");
           DataFrame<Object> df1 = df.retain("Year" , "NA_Sales","EU_Sales").groupBy(row -> row.get(0)).mean();
            System.out.println(df1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
