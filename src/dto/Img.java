package dto;

import org.springframework.web.multipart.MultipartFile;

public class Img {
	MultipartFile file;

	public Img() {
		super();
	}

	public Img(MultipartFile file) {
		super();
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
