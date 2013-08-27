package pl.quider.pixell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Register {
	private static Register instance = null;
	private Properties p;

	public Register() {
		p = new Properties();
		try {
			p.load(new FileReader(new File("propertires.props")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Register getInstance(){
		if(instance == null){
			instance = new Register();
		}
		return instance;
	}
	
	public void save(){
		try {
			p.store(new FileWriter(new File("properties.props")), "W³aœciwoœci");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
