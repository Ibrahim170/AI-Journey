package com.example.demo;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import smile.clustering.XMeans;
import smile.data.DataFrame;
import smile.data.measure.NominalScale;
import smile.data.vector.IntVector;
import smile.io.Read;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.*;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Margin;

public class DAO {

    Table data;

    // ------------ 1
    public Table ReadCsv() throws IOException, URISyntaxException {

        DataFrame Wuzzuf = Read.csv("Wuzzuf_Jobs.csv", CSVFormat.DEFAULT.withDelimiter(',')
                .withHeader("Title", "Company", "Location", "Type", "Level", "YearsExp", "Country", "Skills")
                .withSkipHeaderRecord(true));
        Wuzzuf.omitNullRows();

        String[] names = Wuzzuf.names();
        Table WuzzufTable = Table.create();

        for (int i = 0; i < names.length; i++) {
            StringColumn collumn = StringColumn.create(names[i], Wuzzuf.column(i).toStringArray());
            WuzzufTable.addColumns(collumn);
        }
        WuzzufTable = WuzzufTable.dropDuplicateRows();

        return WuzzufTable;
    }

    public Table showdata() throws IOException, URISyntaxException {
        data = new DAO().ReadCsv();
        return data.summary();
    }
    public Table showdataStructure() throws IOException, URISyntaxException {
        data = new DAO().ReadCsv();
        return data.structure();
    }


    //--------------------------------------------------------------
    public Table countComanyjobs(int nrows) {

        Table c = null;
        try {
            c = new DAO().ReadCsv().countBy("Company").sortAscendingOn("Count").last(nrows);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        return c;
    }

    public Figure showCompanyCountPie(int nrows) {
        Figure fig = PiePlot.create("Most Demanding Companies for Jobs", countComanyjobs(nrows), "Category", "Count");
        Plot.show(fig);

        return fig;
    }



    //---------------------------------------


    public Table getMostPopularJobs(int nrows) throws IOException, URISyntaxException {
        data = new DAO().ReadCsv();
        Table j = data.countBy("Title").sortAscendingOn("Count").last(nrows);

        return j;
    }

    public Figure showTitlesBar(int nrows) throws IOException, URISyntaxException {
        Figure t = HorizontalBarPlot.create("Most Popular Job Titles", getMostPopularJobs(nrows), "Category", "Count");
        Plot.show(t);
        return t;
    }

    // _--------- 8 :

    public Table getMostPopularAreas(int rows) throws IOException, URISyntaxException {
        data = new DAO().ReadCsv();
        Table j = data.countBy("Location").sortAscendingOn("Count").last(rows);

        return j;
    }

    public Figure showAreasBar(int rows) throws IOException, URISyntaxException {
        Figure a = HorizontalBarPlot.create("Most Popular Job Locations", getMostPopularAreas(rows), "Category", "Count");
        Plot.show(a);
        return a;
    }


    // ----------- 10 :
    public Table getSkillsCount(int rows) throws IOException, URISyntaxException {
        data = new DAO().ReadCsv();
        StringColumn s = StringColumn.create("skills");
        for (int i = 0; i < data.rowCount(); i++) {
            s = s.addAll(Arrays.asList(data.get(i, 7).toString().split(",")));
        }
        return Table.create(s).countBy("skills").sortAscendingOn("Count").last(rows);
    }

    public Figure getSkillsCountGraph(int rows) throws IOException, URISyntaxException {

        Figure fig = HorizontalBarPlot.create("Most Required Skills", getSkillsCount(rows), "Category", "Count");
        Plot.show(fig);
        return fig;
    }

    // -------- 11 :
    public Table factorizeYearsExprince(int rows) throws IOException, URISyntaxException {
        data = new DAO().ReadCsv();
        StringColumn YearsExp = StringColumn.create("Experience");
        for (int i = 0; i < data.rowCount(); i++) {
            YearsExp = YearsExp.addAll(Arrays.asList(data.get(i, 5).toString().split(" ")[0]));
        }
        DataFrame df = Table.create(YearsExp).smile().toDataFrame();
        df = df.merge(IntVector.of("ExpFac", Encoder(df, "Experience")));
        String[] names = df.names();
        Table table = Table.create();

        for (int i = 0; i < names.length; i++) {
            StringColumn collumn = StringColumn.create(names[i], df.column(i).toStringArray());
            table.addColumns(collumn);
        }

        return table.first(rows);
    }

    // ------ 12 :
    public static int[] Encoder(DataFrame df, String columnName) {

        String[] values = df.stringVector(columnName).distinct().toArray(new String[]{});
        int[] pclassValues = df.stringVector(columnName).factorize(new NominalScale(values)).toIntArray();
        return pclassValues;
    }

    public Figure showKmeansTable(int nclusters) throws IOException, URISyntaxException {
        data = new DAO().ReadCsv();
        DataFrame df = data.select("Company", "Title").smile().toDataFrame();
        DataFrame dfTrain = DataFrame.of(IntVector.of("Company", Encoder(df, "Company")));


        dfTrain = dfTrain.merge(IntVector.of("Title", Encoder(df, "Title")));

        XMeans clusters = XMeans.fit(dfTrain.toArray(), nclusters);
        dfTrain = dfTrain.merge(IntVector.of("cat", clusters.y));

        String[] names = dfTrain.names();
        Table tablek = Table.create();

        for (int i = 0; i < names.length; i++) {
            IntColumn collumn = IntColumn.create(names[i], dfTrain.column(i).toIntArray());
            tablek.addColumns(collumn);
        }
        Figure k = ScatterPlot.create("K-Means Clustering", tablek, "Title", "Company", "cat");
        Plot.show(k);


        return k;
    }

}