
package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Page;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



@SpringBootApplication
@RestController

public class DemoApplication {
		static Table pdao ;
		static DAO dao = new DAO();
	public static void main(String[] args) {
		try {
			pdao = new DAO().ReadCsv();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			Desktop.getDesktop().browse(new URI("http://localhost:1010/"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		SpringApplication.run(DemoApplication.class, args);
	}



	@ResponseBody
	@GetMapping("/summary")
	public String summary() throws IOException, URISyntaxException {
		return pdao.summary().write().toString("html");
	}

	@GetMapping("/showData")
	public String showData() throws IOException, URISyntaxException {
		return pdao.sampleN(10).write().toString("html");
	}
	@GetMapping("/dataStructure")
	public String structure() throws IOException, URISyntaxException {
		return pdao.structure().write().toString("html");
	}



	@GetMapping("/CountCompanyJobs")
	public String CountCompanyJobs(@RequestParam(value = "rows", defaultValue = "10") String rows) throws IOException, URISyntaxException {
		int r =Integer.parseInt(rows);
		return dao.countComanyjobs(r).sortDescendingOn("count").write().toString("html");
	}
	@GetMapping("/CompanyCountGraph")
	public String CompanyCountGraph(@RequestParam(value = "nrows", defaultValue = "10") String rows) throws IOException, URISyntaxException {

		int p =Integer.parseInt(rows);
		return Page.pageBuilder(dao.showCompanyCountPie(p), "plot_div_id").build().toString();
	}

	@GetMapping("/MostPopularJobTitles")
	public String MostPopularJobTitles(@RequestParam(value = "ntitles", defaultValue = "10") String rows) throws IOException, URISyntaxException {
		int r =Integer.parseInt(rows);
		return dao.getMostPopularJobs(r).sortDescendingOn("count").write().toString("html");
	}
	@GetMapping("/JobTitleCountBarGraph")
	public String JobTitleCountBarGraph(@RequestParam(value = "titles", defaultValue = "10") String rows) throws IOException, URISyntaxException {
		int r =Integer.parseInt(rows);
		return Page.pageBuilder(dao.showTitlesBar(r), "plot_div_id").build().toString();
	}

	@GetMapping("/MostPopularJobAreas")
	public String MostPopularJobAreas(@RequestParam(value = "locations", defaultValue = "10") String rows) throws IOException, URISyntaxException {
		int r =Integer.parseInt(rows);
		return dao.getMostPopularAreas(r).sortDescendingOn("count").write().toString("html");
	}
	@GetMapping("/LocationCountGraph")
	public String LocationCountGraph(@RequestParam(value = "nlocations", defaultValue = "10") String rows) throws IOException, URISyntaxException {
		int r =Integer.parseInt(rows);
		return Page.pageBuilder(dao.showAreasBar(r), "plot_div_id").build().toString();
	}

	@GetMapping("/MostRepeatedSkills")
	public String MostRepeatedSkills(@RequestParam(value = "skills", defaultValue = "10") String rows) throws IOException, URISyntaxException {
		int r =Integer.parseInt(rows);
		return dao.getSkillsCount(r).sortDescendingOn("count").write().toString("html");
	}

	@GetMapping("/SkillsCountGraph")
	public String SkillsCountGraph(@RequestParam(value = "nskills", defaultValue = "10") String rows) throws IOException, URISyntaxException {
		int r =Integer.parseInt(rows);
		return Page.pageBuilder(dao.getSkillsCountGraph(r), "plot_div_id").build().toString();
	}

	@GetMapping("/YearsExperienceEncoding")
	public String YearsExperienceEncoding(@RequestParam(value = "nyears", defaultValue = "10") String rows) throws IOException, URISyntaxException {
		int r =Integer.parseInt(rows);
		return dao.factorizeYearsExprince(r).write().toString("html");
	}

	@GetMapping("/K-MeansClustering")
	public String MeansClustering(@RequestParam(value = "nclusters", defaultValue = "5") String numClusters) throws IOException, URISyntaxException {
		int c =Integer.parseInt(numClusters);
		return Page.pageBuilder(dao.showKmeansTable(c),"plot_div_id").build().toString();

	}

}
