package com.wsbg.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PropertieUtil {

	static {
		Properties props = new Properties();
		ClassLoader classLoader = PropertieUtil.class.getClassLoader();
		InputStreamReader in = null;
		try {
			in = new InputStreamReader(
					classLoader
							.getResourceAsStream("/prop/config.properties"),
					"UTF-8");
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();

			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String fileName;

	public PropertieUtil(String fileName) {
		this.fileName = fileName;
	}

	public String readProperty(String name) {
		Resource res = new ClassPathResource(this.fileName);
		Properties p = new Properties();
		try {
			p.load(res.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p.getProperty(name);
	}

	public static void main(String[] args) {
		PropertieUtil ps = new PropertieUtil(
				"/prop/config.properties");
		System.out.println(ps.readProperty("img_file_server_url"));
	}
}