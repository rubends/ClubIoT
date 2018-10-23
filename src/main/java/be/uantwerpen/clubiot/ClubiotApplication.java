package be.uantwerpen.clubiot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class ClubiotApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubiotApplication.class, args);
    }


    //starter-jdbc wasn't included in older versions, now okay --> app.prop
    /*@Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://143.129.39.127:3306/MUSIC_DB?useSSL=false");
        dataSource.setUsername("web_dj");
        dataSource.setPassword("Web-DJ-1");
        return dataSource;
    }*/
}
