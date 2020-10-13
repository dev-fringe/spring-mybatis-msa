package dev.fringe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

import dev.fringe.config.DataSourceConfig;
import dev.fringe.mapper.TMapper;

@Import(DataSourceConfig.class)
public class App {
	
	@Autowired TMapper tMapper;
	
	public static void main(String[] args) {
        new AnnotationConfigApplicationContext(App.class).getBean(App.class).run(args);
    }

    private void run(String[] args) {
    	System.out.println(tMapper.select());
	}
}