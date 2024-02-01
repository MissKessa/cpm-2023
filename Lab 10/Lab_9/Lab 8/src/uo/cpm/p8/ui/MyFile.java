package uo.cpm.p8.ui;

import java.io.File;

public class MyFile {
	private File file = null;

	public MyFile(File f) {
		file = f;
	}

	public File getFile() {
		return file;
	}

	@Override
	public String toString() {
		return file.getName().replace(".mp3", "");
	}
}
