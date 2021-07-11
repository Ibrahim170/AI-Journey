
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class YoutubeTitleWordCount {


    private static final String COMMA_DELIMITER = ",";
    public static void main(String[] args) throws IOException {
        Logger.getLogger ("org").setLevel (Level.ERROR);
        // CREATE SPARK CONTEXT
        SparkConf conf = new SparkConf ().setAppName ("wordCounts").setMaster ("local[3]");
        JavaSparkContext sparkContext = new JavaSparkContext (conf);
        // LOAD DATASETS
        JavaRDD<String> videos = sparkContext.textFile ("USvideos.csv");



//        // TRANSFORMATIONS
//        JavaRDD<String> titles = videos
//                .map (YoutubeTitleWordCount::extractTitle)
//                .filter (StringUtils::isNotBlank);
//        // JavaRDD<String>
//        JavaRDD<String> words = titles.flatMap (title -> Arrays.asList (title
//                .toLowerCase ()
//                .trim ()
//                .replaceAll ("\\p{Punct}", " ")
//                .split (" ")).iterator ());
//        System.out.println("1st" + words.toString ());
//        // COUNTING
//        Map<String, Long> wordCounts = words.countByValue ();
//        List<Map.Entry> sorted = wordCounts.entrySet ().stream ()
//                .sorted (Map.Entry.comparingByValue ()).collect (Collectors.toList ());
//        // DISPLAY
//        for (Map.Entry<String, Long> entry : sorted) {
//            System.out.println (entry.getKey () + " : " + entry.getValue ());
//        }

        JavaRDD<String> tags = videos.map(YoutubeTitleWordCount:: extractTag ).filter(StringUtils::isNotBlank);

        JavaRDD<String> t = tags.flatMap(tag -> Arrays.asList(tag.toLowerCase().trim()
                .split("\\|")).iterator());


        Map<String ,Long> tagcounts = t.countByValue();
        List<Map.Entry> sotredTags = tagcounts.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

        for (Map.Entry<String,Long> entry : sotredTags) {
            System.out.println(entry.getKey() + ":"+ entry.getValue());
        }


    }
    public static String extractTitle(String videoLine) {
        try {
            return videoLine.split (COMMA_DELIMITER)[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }



}
    public static String extractTag(String videoLine){
        try {
            return videoLine.split (COMMA_DELIMITER)[6];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }


    }
}
