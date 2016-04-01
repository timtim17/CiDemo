package org.usfirst.frc.team1294.robot.utilities;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class VersionInformation {

	private Manifest manifest;
	
	public VersionInformation() {
		this.manifest = loadManifest();
	}
	
	public String getHash() {
		return getAttribute("Git-Hash");
	}
	
	public String getTimestamp() {
		return getAttribute("Git-Timestamp");
	}
	
	public String getTag() {
		return getAttribute("Git-Tag");
	}
	
	public String getAuthor() {
		return getAttribute("Git-Author");
	}
	
	public String getBuildType() {
		return getAttribute("BuildType");
	}
	
	private Manifest loadManifest() {
		URLClassLoader cl = (URLClassLoader) VersionInformation.class
				.getClassLoader();
		try {
			URL url = cl.findResource("META-INF/MANIFEST.MF");
			return new Manifest(url.openStream());
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("loadManifest exception");
			return null;
		}
	}

	private String getAttribute(String attribute) {
		if (manifest == null) {
			return null;
		}
		Attributes attrs = manifest.getMainAttributes();
		String attr = attrs.getValue(attribute);
		return attr == null ? "<not found>" : attr;
	}
}
